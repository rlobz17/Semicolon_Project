package Temp;

import java.util.Date;

public class FriendRequestMessage {
	
	private Account from;
	private Account to;
	private Date date;
	private boolean accept;
	
	public FriendRequestMessage(Account from, Account to, Date date) {
		this.from = from;
		this.to = to;
		this.date = date;
		accept = false;
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
}
