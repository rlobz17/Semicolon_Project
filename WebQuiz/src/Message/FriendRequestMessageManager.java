package Message;

import java.sql.Connection;

public class FriendRequestMessageManager {

	
	private FriendRequestMessageManagerDao dao;
	
	public	FriendRequestMessageManager() {
		dao = new FriendRequestMessageManagerDao();
	}
	
	/**
	 * @return
	 * FriendRequestMessage - message with id - messageID
	 * null - if sql Error
	 */
	public FriendRequestMessage getFriendRequestMessage(int messageID, Connection con) {
		return dao.getFriendRequestMessage(messageID, con);
	}
	
	/**
	 * @return
	 *  0 - message was not added
	 *  messageID - if message was added successfully
	 * -1 - if sql Error
	 */
	public int addFriendRequestMessage(FriendRequestMessage newFriendRequestMessage, Connection con) {
		int res = dao.addFriendRequestMessage(newFriendRequestMessage, con);
		return res;	
	}
	
}
