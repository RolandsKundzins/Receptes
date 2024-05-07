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
}
