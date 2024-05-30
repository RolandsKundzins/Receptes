package receptes.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;
import receptes.type.StatisticsByDateType;
import receptes.type.StatisticsType;

@Component
public class StatisticsModel {
	protected Connection conn;


	public StatisticsModel() {
		System.out.println("StatisticsModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	
	public boolean insertStatistics(StatisticsType statistika) {
		System.out.println("insertStatistics");

		String database = DatabaseConnection.getDatabase();
		String sql = "INSERT INTO " + database + ".`Statistika` (`lietotajvards`, `recepteID`) VALUES (?, ?);";
		//skatLaiks vertibai tiek izmantots DB default value (CURRENT_TIMESTAMP)
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, statistika.getSkatitajsLietotajvards());
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


	//TODO Return view count of all use recipes in last 7 days
	//Something like this: day, view_count
	public List<StatisticsByDateType> getViewCountsPerDay(String lietotajvardsSkatitajs, LocalDate startDate, LocalDate endDate) {
		System.out.println("getStatisticsByLietotajvards");

		String sql = String.join("\n",
			"SELECT DATE(s.skatLaiks) as skatijumaDatums, COUNT(*) as skatijumuSkaits",
			"FROM ", DatabaseConnection.getDatabase(), ".Statistika s",
			"WHERE s.lietotajvards = ?", //'user1'
			"	AND DATE(s.skatLaiks) >= ?", //'2024-05-15'  startDate
			"	AND DATE(s.skatLaiks) <= ?", //'2024-05-22'  endDate
			"GROUP BY DATE(s.skatLaiks);"
		);
		
		List<StatisticsByDateType> statisticsByDate = new LinkedList<>();
				
				
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, lietotajvardsSkatitajs);
			preparedStatement.setDate(2, Date.valueOf(startDate));
			preparedStatement.setDate(3, Date.valueOf(endDate));
			ResultSet results = preparedStatement.executeQuery();
			
            while (results.next()) {
            	statisticsByDate.add(new StatisticsByDateType(
        	        results.getDate("skatijumaDatums").toLocalDate(), 
        	        results.getInt("skatijumuSkaits")
        	    ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return statisticsByDate;
	}
}
