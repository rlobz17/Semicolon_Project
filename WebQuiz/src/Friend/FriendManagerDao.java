package Friend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;
import Message.FriendRequestMessage;

public class FriendManagerDao {

	
	/**
	 * @return
	 * getFriends - friend's ids array list
	 * null - if sql Error
	 */
	public Friend getFriends(int accountID, Connection con) {
		Friend res = null;
		ArrayList<Integer> friends = new ArrayList<Integer>();
		
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT friend_of_account_id FROM friendLinks where account_id =  " + accountID);
			
			while(rs.next()) {
				friends.add(rs.getInt("friend_of_account_id"));
			}
			
			res = new Friend(accountID, friends);
			
			stm.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return res;
	}

	
	/**
	 * @param con 
	 * @return
	 * true - if friends
	 * false - if not friends
	 * null - if sql Error
	 */
	public boolean areFriends(int frstAccID, int secAccID, Connection con) {
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT friend_of_account_id FROM friendLinks where account_id =  " + frstAccID);
			
			while(rs.next()) {
				if(rs.getInt("friend_of_account_id") == secAccID) {
					return true;
				}
			}
		
			stm.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}	
		
		return false;
	}

	
	/**
	 * @return
	 *  0 - friends were not added(it may happen when already added friend)
	 *  1 - if friends were added successfully
	 * -1 - if sql Error
	 */
	public int addFriends(int frstAccID, int secAccID, Connection con) {
		int res = 0;
		
		try {
		
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
		
			ResultSet rs = stm.executeQuery("SELECT friend_of_account_id FROM friendLinks where account_id =  " + frstAccID);
			
			int checker = 0;
			while(rs.next()) {
				if(rs.getInt("friend_of_account_id") == secAccID) {
					checker = 1;
				}
			}
			
			if(checker == 0) {
				String addNewFriendString = "INSERT INTO friendLinks (account_id, friend_of_account_id) VALUES";
				addNewFriendString += "(" + frstAccID;
				addNewFriendString += "," + secAccID + ")";
					
				stm.executeUpdate(addNewFriendString);//added friend for the first one
					
				addNewFriendString = "INSERT INTO friendLinks (account_id, friend_of_account_id) VALUES";
				addNewFriendString += "(" + secAccID;
				addNewFriendString += "," + frstAccID + ")";
					
				stm.executeUpdate(addNewFriendString);//added friend for the second one
				
				res = 1;
			}
			
			stm.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		
		return res ;
	}

}













