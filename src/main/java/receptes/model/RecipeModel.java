package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.enums.RecipeOrderBy;
import receptes.type.RecipeType;
import receptes.type.RecipeViewType;


@Component
public class RecipeModel {
	protected Connection conn;


	public RecipeModel() {
		System.out.println("RecipeModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	public List<RecipeType> getAllRecipes() {
		String sql = String.join("\n", 
		    "SELECT r.*, l.lietotajvards",
		    "FROM " + DatabaseConnection.getDatabase() + ".Recepte r",
		    "JOIN " + DatabaseConnection.getDatabase() + ".Lietotajs l ON r.lietotajsID = l.lietotajsID"
		);
        List<RecipeType> recipes = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//additional parameters example: preparedStatement.setString(1, "%" + firstName + "%");
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
            	        results.getInt("edienaKategorijaID")
            	    ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return recipes;
    }
	
	
	public RecipeType getRecipeById(int recepteID) {
		System.out.println("getRecipeById");
		String database = DatabaseConnection.getDatabase();
        String sql = "SELECT r.*, l.lietotajsID, l.lietotajvards "
        		+ "FROM " + database + ".Recepte r "
        		+ "JOIN " + database + ".Lietotajs l ON l.lietotajsID = r.lietotajsID "
        		+ "WHERE r.recepteID = ?";
        RecipeType recipe = null;
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, recepteID);
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
            		result.getInt("edienaKategorijaID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return recipe;
    } 

	public List<RecipeType> getListOfRecipes(String search, RecipeOrderBy orderBy) {
		String sql = String.join("\n", 
		    "SELECT r.*, l.lietotajvards",
		    "FROM " + DatabaseConnection.getDatabase() + ".Recepte r",
		    "JOIN " + DatabaseConnection.getDatabase() + ".Lietotajs l ON r.lietotajsID = l.lietotajsID"
		);
		
		if (search != null && !search.isEmpty()) {
			sql += " WHERE r.nosaukums like ?";
		}
		
		if (orderBy != null) {
			
			switch(orderBy) {
		      case NAMEASC:
		    	  sql += " ORDER BY r.nosaukums ASC";
		    	  break;
		      case NAMEDESC:
		    	  sql += " ORDER BY r.nosaukums DESC";
		    	  break;
		      case COOKINGTIMEASC:
		    	  sql += " ORDER BY r.pagatavosanasLaiks ASC";
		    	  break;
		      case COOKINGTIMEDESC:
		    	  sql += " ORDER BY r.pagatavosanasLaiks DESC";
		    	  break;
		    }
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
            		results.getInt("edienaKategorijaID")
    			));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
	
	
	//Kad lietotajs apskata recepti, tad tiek pievienots ieraksts tabulā "LietotajsRecepteSkatits" ar apskates laiku
	public boolean insertRecipeUserViewed(RecipeViewType recipeViewType) {
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
				throw new Exception("Rows affected equal to zero for statistics insert!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
