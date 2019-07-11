package Message;

import java.util.Date;

import Temp.Account;

public class TextMessage {
	
	private int from;//account_id
	private int to;//account_id
	private String message;
	private Date date;
	private int messageID;
	
	public TextMessage(int from, int to, String message, Date date, int messageID) {
		this.from = from;
		this.to = to;
		this.message = message;
		this.date = date;
		this.messageID = messageID;
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

	
	public String getMessageType() {
		return "TextMessage";
	}
	
	@Override
	public String toString() {
		return "You have message from: " + from +
				"\n" + "Message: " + message;
	}

}
