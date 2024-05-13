package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.type.RecipeType;


@Component
public class RecipeModel {
	protected Connection conn;


	public RecipeModel() {
		System.out.println("RecipeModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	public List<RecipeType> getAllRecipes() {
		String sql = "SELECT * FROM " + DatabaseConnection.getDatabase() + ".Recepte";
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
            			0, //sobrid nesuta lietotaju prieks receptes saraksta
            			null
    			));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return recipes;
    }
	
	
	public RecipeType getRecipeById(int recepteId) {
		String database = DatabaseConnection.getDatabase();
        String sql = "SELECT r.*, l.lietotajsID, l.lietotajvards "
        		+ "FROM " + database + ".Recepte r "
        		+ "JOIN " + database + ".Lietotajs l ON l.lietotajsID = r.lietotajsID "
        		+ "WHERE r.recepteID = ?";
        RecipeType recipe = null;
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, recepteId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                recipe = new RecipeType(
            		result.getInt("recepteID"), 
            		result.getString("nosaukums"), 
            		result.getInt("pagatavosanasLaiks"),
            		result.getTimestamp("pievienosanasDatums"),
            		result.getString("receptesApraksts"),
            		result.getInt("lietotajsID"),
            		result.getString("lietotajvards")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println(recipe.toString());

        return recipe;
    }
}
