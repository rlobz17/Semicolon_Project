package Message;

import java.util.Date;

import Temp.Account;

public class ChallengeMessage {
	
	private int from;//account_id
	private int to;//account_id
	private Date date;
	private int quizLink;//link_id
	private double maxScore;
	private int messageID;
	
	public ChallengeMessage(int from, int to, Date date, int quizLink, int messageID, double maxScore) {
		this.from = from;
		this.to = to; 
		this.date = date;
		this.quizLink = quizLink;
		this.maxScore = maxScore;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuizLink() {
		return quizLink;
	}
	
	public void setQuizLink(int quizLink) {
		this.quizLink = quizLink;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getMessageType() {
		return "ChallengeMessage";
	}

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	
	@Override
	public String toString() {
		return "User: " + from + 
				" with best score: " + maxScore + " has challenged you!"; 	
	}
}
