package receptes.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection conn;

	static final String hostname = "receptes1.ch62g4ug00sh.eu-north-1.rds.amazonaws.com";
	static final String user = "admin";
	static final String password = "oop12345";
	static final String database = "ReceptesDB";
	
	public DatabaseConnection() {
	}

	public static Connection getConnection() {
		System.out.println("getConnection()");
		if (conn == null) {
			System.out.println("Initializing conn");
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
		} else {
			System.out.println("Connection not null - already initialized");
		}
		return conn;
	}
	
	
	// TODO sis pagaidam nekur netiek saukts, jo uz "Terminate/Disconnect All" viss tiek brutali nogalinats
	public static void closeConnection() {
		// Close connection to the database server
		try {
			System.out.println("closeConnection");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//getters and setters
	public static String getDatabase() {
		return database;
	}
}
