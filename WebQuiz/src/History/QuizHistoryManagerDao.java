package History;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DataBaseINFO;

public class QuizHistoryManagerDao {

	
	/**
	 * 
	 * @param quizID
	 * @param stm
	 * @return
	 * ArrayList<Strory> - history of quiz with this quizID
	 * null - for sql error
	 */
	public ArrayList<Story> getQuizHistory(int quizID, Connection con) {
		
		return null;
	}
	
	/**
	 * 
	 * @param quizID
	 * @param stm
	 * @return
	 * double average - average score of quiz with this quizID
	 * -1 - for sql error 
	 */
	public double getQuizAverageScore(int quizID, Connection con) {
		double result = 0;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String getAvarageString = "Select avg(h.takeHistory_score) from takeHistory h";
			getAvarageString += " where h.takeHistory_id in";
			getAvarageString += " (select takeHistory_id from accountQuizTakeLinks where quiz_id = " +quizID + ")";
			
			
			ResultSet rs = stm.executeQuery(getAvarageString);
			if(rs.next()) {
				result = rs.getDouble(1);
			}else {
				result = 0;
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
	
	/**
	 * 
	 * @param quizID
	 * @param stm
	 * @return
	 * int count - taken count of quiz with this quizID
	 * -1 - for sql error 
	 */
	public int getQuizTakenCount(int quizID, Connection con) {
		int result = 0;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String getAvarageString = "Select count(h.takeHistory_score) from takeHistory h";
			getAvarageString += " where h.takeHistory_id in";
			getAvarageString += " (select takeHistory_id from accountQuizTakeLinks where quiz_id = " +quizID + ")";
			
			
			ResultSet rs = stm.executeQuery(getAvarageString);
			if(rs.next()) {
				result = rs.getInt(1);
			}else {
				result = 0;
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
	
	
	
}

