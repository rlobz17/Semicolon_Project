package History;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;

public class QuizHistoryManagerDao {

	
	/**
	 * 
	 * @param quizID id of quiz that exists
	 * @param con
	 * @return
	 * ArrayList<Strory> - history of quiz with this quizID
	 * null - for sql error
	 */
	public ArrayList<Story> getQuizHistory(int quizID, Connection con) {
		ArrayList<Story> result = new ArrayList<>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			
			String getQuizHistory =  "select * from webquizdatabase.accountquiztakelinks l";
			getQuizHistory += " join webquizdatabase.takehistory h on l.takeHistory_id = h.takeHistory_id";
			getQuizHistory += " where l.quiz_id = " + quizID;
			getQuizHistory += " order by h.takeHistory_date desc";
			
			
			ResultSet rs = stm.executeQuery(getQuizHistory);
			while(rs.next()) {
				int storyID = rs.getInt("takeHistory_id");
				int accountID = rs.getInt("account_id");
				Date takenDate;
				try {
					takenDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("takeHistory_date"));
				} catch (ParseException e) {
					e.printStackTrace();
					stm.close();
					return null;
				}
				double score = rs.getDouble("takeHistory_score");
				result.add(new Story(storyID, accountID, quizID, takenDate, score));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;
	}
	
	/**
	 * 
	 * @param quizID id of quiz that exists
	 * @param con
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
	 * @param quizID id of quiz that exists
	 * @param con
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

