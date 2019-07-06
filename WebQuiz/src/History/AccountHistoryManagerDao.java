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

public class AccountHistoryManagerDao {

	
	/**
	 * @param accountID 
	 * @param stm
	 * @return 
	 * ArrayList<Strory> - history of account with this accountID
	 * null - for sql error
	 */
	public ArrayList<QuizTakeStory> getAccountHistory(int accountID, Connection con) {
		ArrayList<QuizTakeStory> result = new ArrayList<>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			
			String getAccountHistory =  "select * from webquizdatabase.accountquiztakelinks l";
			getAccountHistory += " join webquizdatabase.takehistory h on l.takeHistory_id = h.takeHistory_id";
			getAccountHistory += " where l.account_id = " + accountID;
			getAccountHistory += " order by h.takeHistory_date desc";
			
			ResultSet rs = stm.executeQuery(getAccountHistory);
			while(rs.next()) {
				int storyID = rs.getInt("takeHistory_id");
				int quizID = rs.getInt("quiz_id");
				Date takenDate;
				try {
					takenDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("takeHistory_date"));
				} catch (ParseException e) {
					e.printStackTrace();
					stm.close();
					return null;
				}
				double score = rs.getDouble("takeHistory_score");
				result.add(new QuizTakeStory(storyID, accountID, quizID, takenDate, score));
			}
			
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return result;
	}
	
	/**
	 * @param accountID 
	 * @param stm
	 * @return 
	 * double average - average score of account with this accountID
	 * -1 - for sql error
	 */
	public double getAccountAverageScore(int accountID, Connection con) {
		double result = 0;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String getAvarageString = "Select avg(h.takeHistory_score) from takeHistory h";
			getAvarageString += " where h.takeHistory_id in";
			getAvarageString += " (select takeHistory_id from accountQuizTakeLinks where account_id = " +accountID + ")";
			
			
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
	 * @param accountID
	 * @param stm
	 * @return
	 * int quizTakenCount - quizes count which account took
	 * -1 - for sql error
	 */
	public int getAccountQuizTakenCount(int accountID, Connection con) {
		int result = -1;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String getQuizTakenCount = "Select count(1) from accountQuizTakeLinks";
			getQuizTakenCount += "  where account_id = " +accountID;

			
			
			ResultSet rs = stm.executeQuery(getQuizTakenCount);
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
	
	/**
	 * @param newStory
	 * @param con
	 * @return 0 - for successful insert.
	 * -1 - for sql error.
	 */
	public int addAccountQuizTakeStory(QuizTakeStory newStory, Connection con) {
		int result = -1;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addTakeHistory = "INSERT INTO takeHistory (takeHistory_score) VALUES";
			addTakeHistory += "(" + newStory.getScore() + ")";

			String getTakeHistoryID = "select last_insert_id()";
			
			stm.executeUpdate(addTakeHistory);
			ResultSet rs = stm.executeQuery(getTakeHistoryID);
			int takenStoryID = 0;
			if(rs.next()) {
				takenStoryID = rs.getInt(1);
			}else {
				stm.close();
				return -1;
			}
			
			
			String addAccountQuizTakeLinks = "INSERT INTO accountQuizTakeLinks (account_id, quiz_id, takeHistory_id) VALUES";
			addAccountQuizTakeLinks += "(" + newStory.getAccountID();
			addAccountQuizTakeLinks += "," + newStory.getQuizID();
			addAccountQuizTakeLinks += "," + takenStoryID  + ")";
			
			stm.executeUpdate(addAccountQuizTakeLinks);
			result = 0;
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
}
