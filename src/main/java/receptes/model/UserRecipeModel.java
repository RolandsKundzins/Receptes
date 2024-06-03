package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.security.core.context.SecurityContextHolder;

import receptes.config.DatabaseConnection;
import receptes.exception.CustomException;
import receptes.type.RecipeType;

@Component
public class UserRecipeModel {
	
	protected Connection conn;

	public UserRecipeModel() {
		conn = DatabaseConnection.getConnection();
	}
	
	public List<RecipeType> getUserRecipies() {
		String sql = String.join("\n", 
			    "SELECT r.*, l.lietotajvards, ek.nosaukums AS edienaKategorijasNosaukums, wc.skatSkaits",
			    "FROM " + DatabaseConnection.getDatabase() + ".Recepte r",
			    "JOIN " + DatabaseConnection.getDatabase() + ".Lietotajs l ON r.lietotajsID = l.lietotajsID",
	    		"JOIN " + DatabaseConnection.getDatabase() + ".EdienaKategorija ek ON ek.edienaKategorijasID = r.edienaKategorijaID",
	    		"LEFT JOIN " + DatabaseConnection.getDatabase() + ".ViewGetTotalViewCount wc ON wc.recepteID = r.recepteID"
			);
		
		String lietotajvards= SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(lietotajvards != null && !lietotajvards.isEmpty()) {
			sql+=  " WHERE l.lietotajvards='" + lietotajvards + "'";
		}
		
        List<RecipeType> recipies = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	recipies.add(new RecipeType(
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
                    ));
            }
        } catch (SQLException e) {
        	throw new CustomException("Notika datu bāzes kļūda iegūstot lietotāja veidotās receptes", e);
        }

        return recipies;
    }
	
	public List<RecipeType> getUserLikedRecipies() {
		String sql = String.join("\n",
				"SELECT r.*, ll.lietotajvards, ek.nosaukums AS edienaKategorijasNosaukums, wc.skatSkaits",
				"FROM " + DatabaseConnection.getDatabase() + ".LietotajsReceptePatik l",
				"JOIN " + DatabaseConnection.getDatabase() + ".Recepte r on l.recepteID = r.recepteID",
				"JOIN " + DatabaseConnection.getDatabase() + ".EdienaKategorija ek ON ek.edienaKategorijasID = r.edienaKategorijaID",
				"JOIN " + DatabaseConnection.getDatabase() + ".Lietotajs ll ON r.lietotajsID = ll.lietotajsID",
				"LEFT JOIN " + DatabaseConnection.getDatabase() + ".ViewGetTotalViewCount wc ON wc.recepteID = r.recepteID"
		);
		
		String lietotajvards= SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(lietotajvards != null && !lietotajvards.isEmpty()) {
			sql+=  " WHERE l.lietotajvards='" + lietotajvards + "'";
		}
		
        List<RecipeType> recipies = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
            	recipies.add(new RecipeType(
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
                    ));
            }
        } catch (SQLException e) {
        	throw new CustomException("Notika datu bāzes kļūda iegūstot receptes, kuras lietotājam patīk", e);
        }

        return recipies;
    }
	
}
