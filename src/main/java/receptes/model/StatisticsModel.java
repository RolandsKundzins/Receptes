package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.type.StatisticsType;

@Component
public class StatisticsModel {
	protected Connection conn;


	public StatisticsModel() {
		System.out.println("StatisticsModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	
	public boolean insertStatistics(StatisticsType statistika) {
		// #5 Write an sql statement that inserts teacher in database.
		String database = DatabaseConnection.getDatabase();
		String sql = "INSERT INTO " + database + ".`Statistika` (`lietotajvards`, `recepteID`) VALUES (?, ?);";
		//skatLaiks vertibai tiek izmantots DB default value
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, statistika.getLietotajvards());
			preparedStatement.setInt(2, statistika.getRecepteID());
			rowsAffected = preparedStatement.executeUpdate();
			conn.commit();
			if(rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
}
