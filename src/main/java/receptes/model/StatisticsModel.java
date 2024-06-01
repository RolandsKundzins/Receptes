package receptes.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	
	
	
	public boolean insertRecipeView(StatisticsType statistika) {
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


	// ******************* STATISTIKAS IEGUVE ***************************
	//Atgriež skatījumu un patīk skaitu konkrētā datumu periodā priekš lietotāja, kurš apskata statistikas lapu.
	//Tiek atgriezta statistika par visām šī lietotāja (autora) receptēm
	public List<StatisticsByDateType> getStatisticsPerDate(String lietotajvardsSkatitajs, LocalDate startDate, LocalDate endDate) {
		System.out.println("getStatisticsPerDate");

		List<StatisticsByDateType> statisticsByDate = new LinkedList<>();
				
		Map<LocalDate, Integer> viewCountMap = getViewsPerDate(lietotajvardsSkatitajs, startDate, endDate);
		Map<LocalDate, Integer> likeCountMap = getLikesPerDate(lietotajvardsSkatitajs, startDate, endDate);

		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
		    int viewCount = viewCountMap.getOrDefault(date, 0);
		    int likeCount = likeCountMap.getOrDefault(date, 0);

		    statisticsByDate.add(new StatisticsByDateType(
				date, 
				viewCount,
				likeCount
		    ));
		}
        
        return statisticsByDate;
	}


	private Map<LocalDate, Integer> getCountsPerDate(String sqlQuery, String lietotajvardsSkatitajs, LocalDate startDate, LocalDate endDate) {
	    Map<LocalDate, Integer> countMap = new HashMap<>();

	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
	        preparedStatement.setString(1, lietotajvardsSkatitajs);
	        preparedStatement.setDate(2, Date.valueOf(startDate));
	        preparedStatement.setDate(3, Date.valueOf(endDate));
	        ResultSet results = preparedStatement.executeQuery();

	        while (results.next()) {
	            countMap.put(
	                results.getDate("datums").toLocalDate(),
	                results.getInt("skaits")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return countMap;
	}

	private Map<LocalDate, Integer> getViewsPerDate(String lietotajvardsSkatitajs, LocalDate startDate, LocalDate endDate) {
	    String sqlViews = String.join("\n",
	        "SELECT DATE(s.skatLaiks) as datums, COUNT(*) as skaits",
	        "FROM", DatabaseConnection.getDatabase(), ".Statistika s",
	        "JOIN", DatabaseConnection.getDatabase(), ".Recepte r ON r.recepteID = s.recepteID",
	        "JOIN", DatabaseConnection.getDatabase(), ".Lietotajs l ON r.lietotajsID = l.lietotajsID",
	        "WHERE l.lietotajvards = ?",
	        "AND DATE(s.skatLaiks) >= ?",
	        "AND DATE(s.skatLaiks) <= ?",
	        "GROUP BY DATE(s.skatLaiks);"
	    );

	    return getCountsPerDate(sqlViews, lietotajvardsSkatitajs, startDate, endDate);
	}

	private Map<LocalDate, Integer> getLikesPerDate(String lietotajvardsSkatitajs, LocalDate startDate, LocalDate endDate) {
	    String sqlLikes = String.join("\n",
	        "SELECT DATE(lrp.patikLaiks) as datums, COUNT(*) as skaits",
	        "FROM", DatabaseConnection.getDatabase(), ".LietotajsReceptePatik lrp",
	        "JOIN", DatabaseConnection.getDatabase(), ".Recepte r ON r.recepteID = lrp.recepteID",
	        "JOIN", DatabaseConnection.getDatabase(), ".Lietotajs l ON r.lietotajsID = l.lietotajsID",
	        "WHERE l.lietotajvards = ?",
	        "AND DATE(lrp.patikLaiks) >= ?",
	        "AND DATE(lrp.patikLaiks) <= ?",
	        "GROUP BY DATE(lrp.patikLaiks);"
	    );

	    return getCountsPerDate(sqlLikes, lietotajvardsSkatitajs, startDate, endDate);
	}
	// ******************* STATISTIKAS IEGUVE (līdz šejienei) ***************************
}
