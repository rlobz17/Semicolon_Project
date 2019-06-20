package Temp;


import java.util.ArrayList;

public class Question {
	
	//ivars
	private int questionID;
	private ArrayList<Answer> correctAnswers;
	private int questionTypeID;
	private String questionDetail;
	private String questionTask;
	
	public Question(int questionID, int questionTypeID, String questionDetail, String questionTask, ArrayList<Answer> correctAnswers) {
		this.questionID = questionID;
		this.questionTypeID = questionTypeID;
		this.questionDetail = questionDetail;
		this.questionTask = questionTask;
		this.correctAnswers = correctAnswers;
	}
	
	
	

	/**
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	}

	/**
	 * @return the answers
	 */
	public ArrayList<Answer> getCorrectAnswers() {
		return correctAnswers;
	}
	
	
	/**
	 * @return the questionType
	 */
	public int getQuestionType() {
		return questionTypeID;
	}


	/**
	 * @return the questionType
	 */
	public String getQuestionDetail() {
		return questionDetail;
	}

	/**
	 * @return the questionType
	 */
	public String getQuestionTask() {
		return questionTask;
	}
	
	
	@Override
	public String toString() {
		return "questionID " + questionID +" | questionTypeID "+questionTypeID + " | questionDetail " + questionDetail + 
				" | questionTask " + questionTask;
	}

	
}
