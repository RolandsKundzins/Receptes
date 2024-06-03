package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import java.sql.Statement;

import receptes.config.DatabaseConnection;
import receptes.enums.RecipeOrderBy;
import receptes.exception.CustomException;
import receptes.type.RecipeType;
import receptes.type.RecipeViewType;


@Component
public class RecipeModel {
	protected Connection conn;


	public RecipeModel() {
		System.out.println("RecipeModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	public RecipeType getRecipeById(int recepteID) {
		System.out.println("getRecipeById");
		String database = DatabaseConnection.getDatabase();
        String sql = "SELECT r.*, l.lietotajsID, l.lietotajvards, ek.nosaukums AS edienaKategorijasNosaukums, wc.skatSkaits "
        		+ "FROM " + database + ".Recepte r "
        		+ "JOIN " + database + ".Lietotajs l ON l.lietotajsID = r.lietotajsID "
        		+ "JOIN " + database + ".EdienaKategorija ek ON ek.edienaKategorijasID = r.edienaKategorijaID "
        		+ "LEFT JOIN " + database + ".ViewGetTotalViewCount wc ON wc.recepteID = ? "
        		+ "WHERE r.recepteID = ?";
        RecipeType recipe = null;
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, recepteID);
            preparedStatement.setInt(2, recepteID);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                recipe = new RecipeType(
            		result.getInt("recepteID"), 
            		result.getString("nosaukums"), 
            		result.getInt("pagatavosanasLaiks"),
            		result.getTimestamp("pievienosanasDatums"),
            		result.getString("receptesApraksts"),
            		result.getInt("lietotajsID"),
            		result.getString("lietotajvards"),
            		result.getInt("edienaKategorijaID"),
            		result.getString("edienaKategorijasNosaukums"),
            		result.getInt("skatSkaits")
                );
            }
        } catch (SQLException e) {
            throw new CustomException("Notika kļūda iegūstot receptes datus pēc receptes id", e);
        }
        
        return recipe;
    } 

	public List<RecipeType> getListOfRecipes(String search, RecipeOrderBy orderBy) {
		String sql = String.join("\n", 
		    "SELECT r.*, l.lietotajvards, ek.nosaukums AS edienaKategorijasNosaukums, wc.skatSkaits",
		    "FROM " + DatabaseConnection.getDatabase() + ".Recepte r",
		    "JOIN " + DatabaseConnection.getDatabase() + ".Lietotajs l ON r.lietotajsID = l.lietotajsID",
    		"JOIN " + DatabaseConnection.getDatabase() + ".EdienaKategorija ek ON ek.edienaKategorijasID = r.edienaKategorijaID",
    		"LEFT JOIN " + DatabaseConnection.getDatabase() + ".ViewGetTotalViewCount wc ON wc.recepteID = r.recepteID"
		);
		
		if (search != null && !search.isEmpty()) {
			sql += " WHERE r.nosaukums like ?";
		}
		
		if (orderBy != null) {
			sql += " ORDER BY " + orderBy.getSqlLauks();
			
		}
		
		System.out.print(sql + "\n");
		
        List<RecipeType> recipes = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			if (search != null && !search.isEmpty()) {
				preparedStatement.setString(1, "%" + search + "%");
			}
			
			ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
            	recipes.add(new RecipeType(
        			results.getInt("recepteID"), 
            		results.getString("nosaukums"), 
            		results.getInt("pagatavosanasLaiks"),
            		results.getTimestamp("pievienosanasDatums"),
            		results.getString("receptesApraksts"),
        	        results.getInt("lietotajsID"),
        	        results.getString("lietotajvards"),
            		results.getInt("edienaKategorijaID"),
            		results.getString("edienaKategorijasNosaukums"),
            		results.getInt("skatSkaits")
    			));
            }
        } catch (SQLException e) {
            throw new CustomException("Notika kļūda iegūstot recepšu sarakstu ar meklēšanu un kārtošanu!", e);
        }
        return recipes;
    }
	
	
	//Kad lietotajs apskata recepti, tad tiek pievienots ieraksts tabulā "LietotajsRecepteSkatits" ar apskates laiku
	public void insertRecipeUserViewed(RecipeViewType recipeViewType) {
		System.out.println("insertRecipeView");

		String database = DatabaseConnection.getDatabase();
		String sql = "INSERT INTO " + database + ".`LietotajsRecepteSkatits` (`lietotajvardsSkatitajs`, `recepteID`) VALUES (?, ?);";
		//skatLaiks vertibai tiek izmantots DB default value (CURRENT_TIMESTAMP)
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, recipeViewType.getLietotajvardsSkatitajs());
			preparedStatement.setInt(2, recipeViewType.getRecepteID());
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new CustomException("Rows affected equal to zero for statistics insert!");
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datu bāzes kļūda pievienojot receptes skatījuma datus", e);
		}
	}
	
	public int InsertRecipe(RecipeType recipeType) {
		System.out.println("insertRecipe");
		
		String database = DatabaseConnection.getDatabase();
		String sql = "INSERT INTO " + database + ".`Recepte` (`nosaukums`, `pagatavosanasLaiks`, `receptesApraksts`, `lietotajsID`, `edienaKategorijaID`) VALUES (?, ?, ?, ?, ?);";
		System.out.println(sql);
		
		int rowsAffected = 0;
		int generatedId = -1;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, recipeType.getNosaukums());
			preparedStatement.setInt(2, recipeType.getPagatavosanasLaiks());
			preparedStatement.setString(3, recipeType.getReceptesApraksts());
			preparedStatement.setInt(4, recipeType.getLietotajsID());
			preparedStatement.setInt(5, recipeType.getKategorijaID());
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new CustomException("Rows affected equal to zero for recipe insert!");
			}
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	        	if (generatedKeys.next()) {
	        		generatedId = generatedKeys.getInt(1);
	        	} else {
	        		throw new CustomException("Failed to retrieve the generated ID for the new recipe");
	        	}
		} catch (SQLException e) {
			throw new CustomException("Notika datu bāzes kļūda pievienojot receptes datus", e);
		}
		
		return generatedId;
	}
	
	public void UpdateRecipe(RecipeType recipeType) {
		System.out.println("updateRecipe");
		
		String database = DatabaseConnection.getDatabase();
		String sql = "UPDATE " + database + ".`Recepte` SET `nosaukums` = ?, `pagatavosanasLaiks` = ?, `receptesApraksts` = ?, `lietotajsID` = ?, `edienaKategorijaID` = ? WHERE `recepteID` = ?;";
		System.out.println(sql);
		
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, recipeType.getNosaukums());
			preparedStatement.setInt(2, recipeType.getPagatavosanasLaiks());
			preparedStatement.setString(3, recipeType.getReceptesApraksts());
			preparedStatement.setInt(4, recipeType.getLietotajsID());
			preparedStatement.setInt(5, recipeType.getKategorijaID());
			preparedStatement.setInt(6, recipeType.getRecepteID());
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new CustomException("Rows affected equal to zero for recipe update!");
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datu bāzes kļūda redigejot recepti", e);
		}
	}
}
