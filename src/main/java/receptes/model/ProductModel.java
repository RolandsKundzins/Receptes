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
	
	public List<ProductType> getAllProducts() {
		String sql = "SELECT * FROM " + DatabaseConnection.getDatabase() + ".Produkts";
        List<ProductType> products = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
            	products.add(new ProductType(results.getInt("produktsID"), results.getString("nosaukums")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
	
	public ProductType getProductById(int produktsID) {
		String database = DatabaseConnection.getDatabase();
        String sql = "SELECT * FROM " + database + ".Produkts WHERE produktsID = ?";
        ProductType product = null;
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, produktsID);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                product = new ProductType(
            		result.getInt("produktsID"), 
            		result.getString("nosaukums")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println(product.toString());

        return product;
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
	
	public void addProduct(ProductType product) {
	    String sql = "INSERT INTO " + DatabaseConnection.getDatabase() + ".Produkts (nosaukums) VALUES (?)";
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, product.getNosaukums());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void updateProduct(ProductType product) {
	    String sql = "UPDATE " + DatabaseConnection.getDatabase() + ".Produkts SET nosaukums = ? WHERE produktsID = ?";
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, product.getNosaukums());
	        preparedStatement.setInt(2, product.getProduktsID());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void deleteProduct(int productId) {
	    String sql = "DELETE FROM " + DatabaseConnection.getDatabase() + ".Produkts WHERE produktsID = ?";
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, productId);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
