package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.exception.CustomException;
import receptes.type.ProductRecipeType;

@Component
public class ProductRecipeModel {
	protected Connection conn;


	public ProductRecipeModel() {
		System.out.println("ProductRecipeModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	public void InsertProductRecipe(ProductRecipeType productRecipeType) {
		System.out.println("InsertProductRecipe");

		String database = DatabaseConnection.getDatabase();
		String sql = "INSERT INTO " + database + ".`ProduktsRecepte` (`recepteID`, `produktsID`) VALUES (?, ?);";

		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, productRecipeType.getRecepteID());
			preparedStatement.setInt(2, productRecipeType.getProduktsID());
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new CustomException("Rows affected equal to zero for product recipe insert!");
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datu bāzes kļūda pievienojot produkts recepte datus", e);
		}
	}
	
	public void DeleteByRecipeId(int recipeId) {
		System.out.println("DeleteByRecipeId");

		String database = DatabaseConnection.getDatabase();
		String sql = "DELETE FROM " + database + ".`ProduktsRecepte` WHERE `recepteID` = ?;";

		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, recipeId);
			
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new CustomException("Rows affected equal to zero for product recipe delete!");
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datu bāzes kļūda dzesot produkts recepte datus", e);
		}
	}
}
