package receptes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import receptes.type.UserType;

import java.sql.Connection;

import receptes.config.DatabaseConnection;

@Component
public class UserModel {
	protected Connection conn;


	public UserModel() {
		System.out.println("UserModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	public UserType findByUsername(String username) {		
		String sql = "SELECT * FROM " + DatabaseConnection.getDatabase() + ".Lietotajs WHERE lietotajvards = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet results = preparedStatement.executeQuery();
			
			if(results.next()) {
				return new UserType(results.getString("epasts"), results.getString("parole"), 
						results.getString("lietotajvards"), results.getBoolean("irAktivs"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new UserType();
	}
	
	
	public OperationResult insertUser(String epasts, String parole, String lietotajvards) {
		String sql = "INSERT INTO " + DatabaseConnection.getDatabase() + ".`Lietotajs` (`epasts`, `parole`, `lietotajvards`) VALUES (?,?,?);";
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, epasts);
			preparedStatement.setString(2, parole);
			preparedStatement.setString(3, lietotajvards);
			rowsAffected = preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
			e.printStackTrace();
			
			return new OperationResult(false, "Error occurred while inserting user: " + e.getMessage());
		}
		
		if(rowsAffected > 0) {
	        return new OperationResult(true, "User inserted successfully");
		}
	    return new OperationResult(false, "Failed to insert user. Please contact administrator!");
	}
}
