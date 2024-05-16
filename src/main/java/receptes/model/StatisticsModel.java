package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
		String database = DatabaseConnection.getDatabase();
		String sql = "INSERT INTO " + database + ".`Statistika` (`lietotajvards`, `recepteID`) VALUES (?, ?);";
		//skatLaiks vertibai tiek izmantots DB default value (CURRENT_TIMESTAMP)
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, statistika.getLietotajvards());
			preparedStatement.setInt(2, statistika.getRecepteID());
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new Exception("Rows affected equal to zero for statistics insert!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}



	public List<StatisticsType> getStatisticsByLietotajsID(int lietotajvards) {
		return null;
	}
}
