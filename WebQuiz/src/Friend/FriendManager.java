package Friend;

import java.sql.Connection;

import java.util.ArrayList;

import Message.FriendRequestMessage;



public class FriendManager {

	private FriendManagerDao dao;
	
	public FriendManager() {
		dao = new FriendManagerDao();
	}

	/**
	 * @return
	 * getFriends - friend's ids array list
	 * null - if sql Error
	 */
	public Friend getFriends(int accountID, Connection con) {
		return dao.getFriends(accountID, con);
	}
	
	/**
	 * @return
	 * true - if friends
	 * false - if not friends/if sql Error
	 */
	public boolean areFriends(int frstAccID, int secAccID, Connection con) {
		return dao.areFriends(frstAccID, secAccID, con);
	}
	
	/**
	 * @return
	 *  0 - friends were not added(it may happen when already added friend)
	 *  1 - if friends were added successfully
	 * -1 - if sql Error
	 */
	public int addFriends(int frstAccID, int secAccID, Connection con) {
		int res = dao.addFriends(frstAccID, secAccID, con);
		return res;
	}


}

