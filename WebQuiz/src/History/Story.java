package History;

import java.util.Date;

public class Story {

	private int storyID;
	private int accountID;
	private int quizID;
	private Date takenDate;
	private double score;
	
	
	public Story(int storyID, int accountID, int quizID, Date takenDate, double score) {
		this.storyID = storyID;
		this.accountID = accountID;
		this.quizID = (quizID);
		this.takenDate = takenDate;
		this.score = score;
	}

	
	/**
	 * @return the storyID
	 */
	public int getStoryID() {
		return storyID;
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
	public double getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "::("+storyID+"):: Account("+accountID+") completed quiz("+quizID+") and got "+score+" ----- "+takenDate.toString();
	}
	
}
