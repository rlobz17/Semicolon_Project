package Message;

import java.sql.Connection;

public class TextMessageManager {
	
	private TextMessageManagerDao dao;
	
	public TextMessageManager() {
		dao = new TextMessageManagerDao();
	}
	
	/**
	 * @return
	 * TextMessage - message with id - messageID
	 * null - if sql Error
	 */
	public TextMessage getTextMessage(int messageID, Connection con) {
		return dao.getTextMessage(messageID, con);
	}
	
	/**
	 * @return
	 *  0 - message was not added
	 *  messageID - if message was added successfully
	 * -1 - if sql Error
	 */
	public int addTextMessage(TextMessage newTextMessage, Connection con) {
		int res = dao.addTextMessage(newTextMessage, con);
		return res;	
	}
}
