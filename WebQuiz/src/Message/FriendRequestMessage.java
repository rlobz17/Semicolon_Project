package Message;

import java.util.Date;

import Temp.Account;

public class FriendRequestMessage {
	
	private Account from;
	private Account to;
	private Date date;
	private boolean accept;
	private int messageID;
	private String messageType;
	
	public FriendRequestMessage(Account from, Account to, Date date, int messageID, String messageType) {
		this.from = from;
		this.to = to;
		this.date = date;
		accept = false;
		this.messageID = messageID;
		this.messageType = messageType;
	}

	public void acceptFriendship() {
		accept = true;
	}
	
	public boolean isFriendshipAccepted() {
		return accept;
	}
	
	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
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
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	@Override
	public String toString() {
		return "User: " + from.getFirstName() + " " + from.getLastName() + 
				" sent you a friend request!";
	}
}
