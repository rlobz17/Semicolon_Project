package Message;

import java.util.Date;

import Temp.Account;

public class NoteMessage {
	
	private Account from;
	private Account to;
	private String message;
	private Date date;
	private int messageID;
	
	public NoteMessage(Account from, Account to, String message, Date date, int messageID) {
		this.from = from;
		this.to = to;
		this.message = message;
		this.date = date;
		this.setMessageID(messageID);
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
}
