package Quiz;

import java.util.ArrayList;


public class QuestionGrade {
	private String score;
	private ArrayList<String> correctAnswers;
	
	public QuestionGrade(double score, ArrayList<String> correctAnswers) {
		this.score = score+"";
		if(this.score.equals("1.0")) {
			this.score = "1.00";
		} else if (this.score.length()>4) {
			this.score = this.score.substring(0,4);
		}
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
