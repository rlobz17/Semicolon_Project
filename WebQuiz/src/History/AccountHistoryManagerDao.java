package History;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DataBaseINFO;

public class AccountHistoryManagerDao {

	
	/**
	 * @param accountID 
	 * @param stm
	 * @return 
	 * ArrayList<Strory> - history of account with this accountID
	 * null - for sql error
	 */
	public ArrayList<Story> getAccountHistory(int accountID, Connection con) {
	
		return null;
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
}
