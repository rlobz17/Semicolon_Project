package Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Database.DataBaseINFO;

public class ChallengeMessageManagerDao {

	/**
	 * @return
	 * ChallengeMessage - message with id - messageID
	 * null - if sql Error
	 */
	public ChallengeMessage getChallengeMessage(int messageID, Connection con) {
		
		ChallengeMessage res = null;
		
		int from, to, quizLink;
		double maxScore;
		Date sentDate;
		
		
		try {
			
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM messages where message_id =  " + messageID);
			
			if(rs.next()) {				
				from = rs.getInt("from_account_id");
				to = rs.getInt("to_account_id");
				sentDate = rs.getDate("sent_date");
				quizLink = rs.getInt("quiz_id");
				maxScore = rs.getDouble("max_score");
			}else {
				stm.close();
				return null;
			}
			
			if(rs.next()) {
				stm.close();
				return null;
			}
			
			res = new ChallengeMessage(from, to, sentDate, quizLink, maxScore, messageID);
			stm.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		
		return res;
	}

	
	
	/**
	 * @return
	 *  0 - message was not added
	 *  messageID - if message was added successfully
	 * -1 - if sql Error
	 */
	public int addChallengeMessage(ChallengeMessage newChallengeMessage, Connection con) {
		int res = 1;
		
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addChallengeMessageString = "INSERT INTO messages (messageType_name, from_account_id, to_account_id,"
					+ " sent_date, text_message, quiz_id, max_score) VALUES";
			
			addChallengeMessageString += "(" + "'" + newChallengeMessage.getMessageType() + "'";
			addChallengeMessageString += "," + newChallengeMessage.getFrom();
			addChallengeMessageString += "," + newChallengeMessage.getTo();
			addChallengeMessageString += "," + newChallengeMessage.getDate();
			addChallengeMessageString += ",null";//Text message doesn't exist
			addChallengeMessageString += "," + newChallengeMessage.getQuizLink();
			addChallengeMessageString += "," + newChallengeMessage.getMaxScore() + ")";
			
			stm.executeUpdate(addChallengeMessageString);
			
			ResultSet rs =  stm.executeQuery("select last_insert_id()");
			if(rs.next()) {
				res = rs.getInt(1);
			}else {
				res = -1;
			}
			
			stm.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		
		return res;
	}

}













