package receptes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import receptes.config.DatabaseConnection;

@Component
public class RecipeLikeModel {
	protected Connection conn;


	public RecipeLikeModel() {
		System.out.println("RecipeModel constructor");
		conn = DatabaseConnection.getConnection();
	}
	
	
	//Lietotājs atzīmē, ka viņam patīk recepte.
	public boolean addRecipeLike(String lietotajvards, int recepteID) {
		String sql = "INSERT INTO " + DatabaseConnection.getDatabase() + ".LietotajsReceptePatik(lietotajvards, recepteID) VALUES (?, ?)";
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, lietotajvards);
			preparedStatement.setInt(2, recepteID);
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	


	public boolean deleteRecipeLike(String lietotajvardsPatik, int recepteID) {
		String sql = String.join("\n",
			"DELETE FROM " + DatabaseConnection.getDatabase() + ".LietotajsReceptePatik",
			"WHERE lietotajvards = ? AND recepteID = ?"
		);
		int rowsAffected = 0;
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, lietotajvardsPatik);
			preparedStatement.setInt(2, recepteID);
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	

	//Atgriež patiess, ja lietotajs recepti jau atzīmēja ar patīk.
	public boolean isRecipeAlreadyLiked(String lietotajvards, int recepteID) {
	    String sql = "SELECT COUNT(*) FROM " + DatabaseConnection.getDatabase() + ".LietotajsReceptePatik WHERE lietotajvards = ? AND recepteID = ?";
	    
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, lietotajvards);
	        preparedStatement.setInt(2, recepteID);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            int likeCount = resultSet.getInt(1);
	            return likeCount > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}

	

	//Skaits, cik daudz lietotāju ir atzīmējuši, ka viņiem patīk recepte.
	public int recipeLikeCount(int recepteID) {
		String sql = String.join(" ", 
			"SELECT COUNT(*) as patik_skaits",
			"FROM", DatabaseConnection.getDatabase(), ".LietotajsReceptePatik",
			"WHERE recepteID = ?",
			"GROUP BY recepteID"
		);
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, recepteID);
			ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
            	return results.getInt("patik_skaits");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return 0;
	}
}
