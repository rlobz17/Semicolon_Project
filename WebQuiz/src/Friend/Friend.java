package Friend;

import java.util.ArrayList;

public class Friend {
	
	private int accountID;
	private ArrayList<Integer> friendsID;
	
	public Friend(int accountID, ArrayList<Integer> friendsID) {
		this.accountID = accountID;
		this.friendsID = friendsID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public ArrayList<Integer> getFriendsID() {
		return friendsID;
	}

	public void setFriendsID(ArrayList<Integer> friendsID) {
		this.friendsID = friendsID;
	}

	@Override
	public String toString() {
		return "Account with id: " + accountID + " has " + friendsID.size() + " friends"; 	
	}
}

