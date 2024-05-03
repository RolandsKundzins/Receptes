package com.example.receptes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RecipeList {
	protected Connection conn;

	static final String hostname = "receptes1.ch62g4ug00sh.eu-north-1.rds.amazonaws.com";
	static final String user = "admin";
	static final String password = "oop12345";
	static final String database = "ReceptesDB";

	public RecipeList() {
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
	
	
	
	
	//Si funkcija izdruka visas receptes konsolee 
	public void printRecipes() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + database + ".Recepte");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Assuming you have columns like "recipe_id", "recipe_name" in your table
                int recepteId = resultSet.getInt("recepteId");
                String nosaukums = resultSet.getString("nosaukums");
                System.out.println("Recipe ID: " + recepteId + ", Recipe Name: " + nosaukums);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public void closeConnection() {
		// Close connection to the database server
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
