package Quiz;

import java.util.ArrayList;

public class QuizGrade {
	private double totalScore;
	private  ArrayList<String> eachQuestionScore;
	private int maxScore;
	
	public QuizGrade(double totalUserScore,int maxScore, ArrayList<String> eachQuestionScore) {
		this.maxScore = maxScore;
		this.totalScore = totalUserScore;
		this.eachQuestionScore = eachQuestionScore;
	}
	
	/**
	 * returns userScore as string
	 * */
	public String getUserScore() {
		return totalScore+"";
	}

	/**
	 * @return the totalScore
	 */
	public String getTotalScore() {
		return totalScore+"/"+maxScore;
	}

	/**
	 * @return the eachQuestionScore
	 */
	public ArrayList<String> getEachQuestionScore() {
		return eachQuestionScore;
	}

	/**
	 * returns percentage of this quiz correct answers as string
	 * */
	public String getPrecentage() {
		return (int)((totalScore/maxScore)*100)+"%";
	}
	
}
