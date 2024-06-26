package receptes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import receptes.type.UserType;

import java.sql.Connection;

import receptes.config.DatabaseConnection;
import receptes.exception.CustomException;

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
				return new UserType(
					results.getInt("lietotajsID"),
					results.getString("epasts"), 
					results.getString("parole"), 
					results.getString("lietotajvards"), 
					results.getBoolean("irAktivs"),
					results.getString("loma")
				);
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datubāzes kļūda iegūstot lietotāju pēc lietotājvārda!", e);
		}
		
		return null;
	}
	
	
	//Check if user exists based on email and username
	public String checkUserExists(String lietotajvards, String epasts) {		
		String sql = "SELECT lietotajvards, epasts FROM " + DatabaseConnection.getDatabase() + ".Lietotajs WHERE lietotajvards = ? OR epasts = ?";
		String result = "";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, lietotajvards);
			preparedStatement.setString(2, epasts);
			ResultSet results = preparedStatement.executeQuery();
			
			if(results.next()) {
				if(results.getString("lietotajvards").equals(lietotajvards)) {
					result = "lietotajvārdu";
				}
				if(results.getString("epasts").equals(epasts)) {
					if(!result.isEmpty()) result += " un ";
					result += "epastu";
				}
				
				if(!"".equals(result)) {
					result = String.join(" ", "Lietotājs ar šādu", result, "jau eksistē!");
				}
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datubāzes kļūda pārbaudot vai lietotājs eksistē!", e);
		}
		
		return result;
	}
	
	
	public void insertUser(String epasts, String parole, String lietotajvards) {
		String sql = "INSERT INTO " + DatabaseConnection.getDatabase() + ".`Lietotajs` (`epasts`, `parole`, `lietotajvards`) VALUES (?,?,?);";
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, epasts);
			preparedStatement.setString(2, parole);
			preparedStatement.setString(3, lietotajvards);
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new CustomException("Notika kļūda ievietojot jaunu lietotāju - netika pievienota neviena rinda!");
			}
		} catch (SQLException e) {
			throw new CustomException("Notika datubāzes kļūda ievietojot jaunu lietotāju!", e);
		}
	}
}
