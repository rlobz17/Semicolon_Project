package Quiz;

import java.util.ArrayList;

public class Question {
	
	//ivars
	private int questionID;
	private ArrayList<Integer> quizIDs; // stores ids of quizes in which this question is used
	private ArrayList<String> answers;
	private String questionType;
	private String answer;
	
	public Question(ArrayList<String> answers, String questionType, int questionID,ArrayList<Integer> quizIDs) {
		this.questionID = questionID;
		this.questionType = questionType;
		this.answers = (answers);
		this.quizIDs = (quizIDs);
	}
	
	
	/**
	 * @param string containing answer
	 * checks if given param and answer are same
	 * */
	public boolean isCorrectAnswer(String answer) {
		return this.answer.equals(answer);
	}
	

	/**
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	}

	
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}


	/**
	 * @return the quizIDs
	 */
	public ArrayList<Integer> getQuizIDs() {
		return quizIDs;
	}




	/**
	 * @return the answers
	 */
	public ArrayList<String> getAnswers() {
		return answers;
	}



	
}
