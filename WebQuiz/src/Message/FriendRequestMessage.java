package Message;

import java.util.Date;

import Temp.Account;

public class FriendRequestMessage {
	
	private int from;//account_id
	private int to;//account_id 
	private Date date;
	private boolean accept;
	private int messageID;
	
	public FriendRequestMessage(int from, int to, Date date, int messageID) {
		this.from = from;
		this.to = to;
		this.date = date;
		accept = false;
		this.messageID = messageID;
	}
 
	
	public void acceptFriendship() {
		accept = true;
	}
	
	
	public boolean isFriendshipAccepted() {
		return accept;
	}
	
	
	public int getFrom() {
		return from;
	}

	
	public void setFrom(int from) {
		this.from = from;
	}

	
	public int getTo() {
		return to;
	}

	
	public void setTo(int to) {
		this.to = to;
	}

	
	public Date getDate() {
		return date;
	}

	
	public void setDate(Date date) {
		this.date = date;
	}

	
	public int getMessageID() {
		return messageID;
	}

	
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	
	public String getMessageType() {
		return "FriendRequest";
	}
	
	
	@Override
	public String toString() {
		return "User: " + from + 
				" sent you a friend request!";
	}
}
