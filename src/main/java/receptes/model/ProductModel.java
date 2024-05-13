package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.type.ProductType;

@Component
public class ProductModel {
	protected Connection conn;

	public ProductModel() {
		System.out.println("ProductModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	public List<ProductType> getProductsByRecipeId(int recepteID) {
		System.out.println("getProductsByRecipeId(recepteID: " + recepteID + ')');
	    String database = DatabaseConnection.getDatabase();
	    String sql = "SELECT p.produktsID, p.nosaukums "
	    		+ "FROM " + database + ".ProduktsRecepte pr "
	    		+ "JOIN " + database + ".Produkts p ON pr.produktsID = p.produktsID "
				+ "WHERE pr.recepteID = ?";
	    List<ProductType> products = new LinkedList<>();

	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, recepteID);
	        ResultSet result = preparedStatement.executeQuery();
	        if (result.next()) {
	        	products.add(new ProductType(
	                result.getInt("produktsID"),
	                result.getString("nosaukums")
    			));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println(products.toString());

	    return products;
	}
}
