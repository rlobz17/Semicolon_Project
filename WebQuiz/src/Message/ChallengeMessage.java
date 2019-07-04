package Message;

import java.util.Date;

import Temp.Account;

public class ChallengeMessage {
	
	private Account from;
	private Account to;
	private Date date;
	private final String quizLink;
	private int messageID;
	private String messageType;
	private double maxScore;
	
	public ChallengeMessage(Account from, Account to, Date date, String quizLink, int messageID, String messageType, double maxScore) {
		this.from = from;
		this.to = to; 
		this.date = date;
		this.quizLink = quizLink;
		this.maxScore = maxScore;
		this.messageID = messageID;
		this.messageType = messageType;
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

	public String getQuizLink() {
		return quizLink;
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

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	
	@Override
	public String toString() {
		return "User: " + from.getFirstName() + " " + from.getLastName() + 
				" with best score: " + maxScore + " has challenged you!"; 	
	}
}
