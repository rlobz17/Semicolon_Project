package Temp;

import java.util.Date;

public class NoteMessage {
	
	private Account from;
	private Account to;
	private String message;
	private Date date;
	
	public NoteMessage(Account from, Account to, String message, Date date) {
		this.from = from;
		this.to = to;
		this.message = message;
		this.date = date;
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
}
