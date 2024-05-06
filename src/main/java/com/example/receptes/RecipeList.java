package com.example.receptes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class RecipeList {
	protected Connection conn;

	static final String hostname = "receptes1.ch62g4ug00sh.eu-north-1.rds.amazonaws.com";
	static final String user = "admin";
	static final String password = "oop12345";
	static final String database = "ReceptesDB";

	public RecipeList() {
		System.out.println("RecipeList constructor");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":3306/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", user, password);
			conn.setAutoCommit(false);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	
	public List<Recipe> getAllRecipes() {
		String sql = "SELECT * FROM " + database + ".Recepte";
        List<Recipe> recipes = new LinkedList<>();
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//additional parameters example: preparedStatement.setString(1, "%" + firstName + "%");
			ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
            	recipes.add(new Recipe(results.getInt("recepteId"), results.getString("nosaukums"), results.getInt("pagatavosanasLaiks"), results.getString("receptesApraksts")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return recipes;
    }
	
	
	// TODO sis pagaidam nekur netiek saukts, jo uz "Terminate/Disconnect All" viss tiek brutali nogalinats
	public void closeConnection() {
		// Close connection to the database server
		try {
			System.out.println("closeConnection");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
