package Temp;

import java.util.Date;

public class ChallengeMessage {
	
	private Account from;
	private Account to;
	private Date date;
	private final String quizLink;
	
	public ChallengeMessage(Account from, Account to, Date date, String quizLink) {
		this.from = from;
		this.to = to; 
		this.date = date;
		this.quizLink = quizLink;
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
}
