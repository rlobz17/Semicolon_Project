package History;

import java.util.Date;

public class Story {

	
	private int accountID;
	private int quizID;
	private Date takenDate;
	private int score;
	
	
	public Story(int accountID, int quizID, Date takenDate, int score) {
		this.accountID = accountID;
		this.quizID = (quizID);
		this.takenDate = takenDate;
		this.score = score;
	}
	
	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @return the quizID
	 */
	public int getQuizID() {
		return quizID;
	}

	/**
	 * @return the takenDate
	 */
	public Date getTakenDate() {
		return takenDate;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Account("+accountID+") completed quiz("+quizID+") and got "+score+" ----- "+takenDate.toString();
	}
	
}
