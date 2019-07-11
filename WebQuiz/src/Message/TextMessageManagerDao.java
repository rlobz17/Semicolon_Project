package Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Database.DataBaseINFO;


public class TextMessageManagerDao {

	/**
	 * @return
	 * TextMessage - message with id - messageID
	 * null - if sql Error
	 */
	public TextMessage getTextMessage(int messageID, Connection con) {
		TextMessage res = null;
		
		int from, to;
		Date sentDate;
		String textMessage;
		
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM messages where message_id =  " + messageID);
			if(rs.next()) {				
				from = rs.getInt("from_account_id");
				to = rs.getInt("to_account_id");
				sentDate = rs.getDate("sent_date");
				textMessage = rs.getString("text_message");
			}else {
				stm.close();
				return null;
			}
			
			if(rs.next()) {
				stm.close();
				return null;
			}
			
			res = new TextMessage(from, to, textMessage, sentDate, messageID);
			stm.close();
			
		}catch (SQLException e) {
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
	public int addTextMessage(TextMessage newTextMessage, Connection con) {
		int res = 1;
		
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addTextMessageString = "INSERT INTO messages (messageType_name, from_account_id, to_account_id,"
					+ " sent_date, text_message, quiz_id, max_score) VALUES";
			
			addTextMessageString += "(" + "'" + newTextMessage.getMessageType() + "'";
			addTextMessageString += "," + newTextMessage.getFrom();
			addTextMessageString += "," + newTextMessage.getTo();
			addTextMessageString += "," + newTextMessage.getDate();
			if(newTextMessage.getMessage() == null) {
				addTextMessageString += ",null";
			}else {
				addTextMessageString += ",'" + newTextMessage.getMessage() + "'";
			}
			addTextMessageString += ",-1" + ",-1)";//quiz_id, max_score(here don't needed;

			
			stm.executeUpdate(addTextMessageString);
			
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
