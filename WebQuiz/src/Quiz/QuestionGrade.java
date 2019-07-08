package Quiz;

import java.util.ArrayList;


public class QuestionGrade {
	private double score;
	private ArrayList<String> correctAnswers;
	
	public QuestionGrade(double score, ArrayList<String> correctAnswers) {
		this.score = score;
		this.correctAnswers = correctAnswers;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score+"/1.00";
	}

	/**
	 * @return the correctAnswers
	 */
	public ArrayList<String> getCorrectAnswers() {
		return correctAnswers;
	}
	
}
