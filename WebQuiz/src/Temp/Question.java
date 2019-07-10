package Temp;


import java.util.ArrayList;

public class Question {
	
	//ivars
	private int questionID;
	private ArrayList<Answer> correctAnswers;
	private int questionTypeID;
	private String questionDetail;
	private String questionTask;
	private String questionImgUrl;
	private ArrayList<Answer> possibleAnswers;
	private boolean questionAnswerOrder;
	
	public Question(int questionID, int questionTypeID, String questionDetail, String questionTask, String questionImgUrl, ArrayList<Answer> correctAnswers, ArrayList<Answer> possibleAnswers, boolean questionAnswerOrder) {
		this.questionID = questionID;
		this.questionTypeID = questionTypeID;
		this.questionDetail = questionDetail;
		this.questionTask = questionTask;
		this.questionImgUrl = questionImgUrl;
		this.correctAnswers = correctAnswers;
		this.possibleAnswers = possibleAnswers;
		this.questionAnswerOrder = questionAnswerOrder;
	}
	
	
	

	/**
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	}
	
	/**
	 * sets the questionID
	 */
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	/**
	 * @return the correct answers
	 */
	public ArrayList<Answer> getCorrectAnswers() {
		return correctAnswers;
	}
	
	/**
	 * @return the possible answers
	 */
	public ArrayList<Answer> getPossibleAnswers() {
		return possibleAnswers;
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
		
	/**
	 * @return the questionImgUrl
	 */
	public String getQuestionImgUrl() {
		return questionImgUrl;
	}
	
	/**
	 * @return the questionAnswerOrder
	 */
	public boolean getQuestionAnswerOrder() {
		return questionAnswerOrder;
	}



	@Override
	public String toString() {
		String corrects = "\n";
		for(int i=0; i<correctAnswers.size(); i++) {
			corrects += correctAnswers.get(i).toString() + "\n";
		}
		
		String possibles = "\n";
		if(possibleAnswers == null) {
			possibles += "NO Possible Answers";
		}else {
			for(int i=0; i<possibleAnswers.size(); i++) {
				possibles += possibleAnswers.get(i).toString() + "\n";
			}
		}	
		
		return "questionID " + questionID +" | questionTypeID "+questionTypeID + " | questionDetail " + questionDetail + 
				" | questionTask " + questionTask + " | questionAnswerOrder " + questionAnswerOrder + " | questionImgUrl " + questionImgUrl +"\n\nPossible Answers :" + possibles +"\nCorrect Answers :" + corrects;
	}

	
}
