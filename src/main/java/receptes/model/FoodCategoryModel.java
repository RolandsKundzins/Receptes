package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.type.FoodCategoryType;

@Component
public class FoodCategoryModel {
	protected Connection conn;

	public FoodCategoryModel() {
		System.out.println("FoodCategoryModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	public List<FoodCategoryType> getAllFoodCategories() {
		String sql = "SELECT * FROM " + DatabaseConnection.getDatabase() + ".EdienaKategorija";
        List<FoodCategoryType> foodCategories = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
            	foodCategories.add(new FoodCategoryType(results.getInt("edienaKategorijasID"), results.getString("nosaukums")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return foodCategories;
    }
	
	public FoodCategoryType getFoodCategoryById(int foodCategoryId) {
		String database = DatabaseConnection.getDatabase();
        String sql = "SELECT * FROM " + database + ".EdienaKategorija WHERE edienaKategorijasID = ?";
        FoodCategoryType foodCategory = null;
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, foodCategoryId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                foodCategory = new FoodCategoryType(
            		result.getInt("edienaKategorijasID"), 
            		result.getString("nosaukums")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println(foodCategory.toString());

        return foodCategory;
    }
	
	//nav uztaisits getFoodCategoriesByRecipieId
	
	public void addFoodCategory(FoodCategoryType foodCategory) {
	    String sql = "INSERT INTO " + DatabaseConnection.getDatabase() + ".EdienaKategorija (nosaukums) VALUES (?)";
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, foodCategory.getNosaukums());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void updateFoodCategory(FoodCategoryType foodCategory) {
	    String sql = "UPDATE " + DatabaseConnection.getDatabase() + ".EdienaKategorija SET nosaukums = ? WHERE edienaKategorijasID = ?";
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, foodCategory.getNosaukums());
	        preparedStatement.setInt(2, foodCategory.getFoodCategoryId());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void deleteFoodCategory(int foodCategory) {
	    String sql = "DELETE FROM " + DatabaseConnection.getDatabase() + ".EdienaKategorija WHERE edienaKategorijasID = ?";
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, foodCategory);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
