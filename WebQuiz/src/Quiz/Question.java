package Quiz;

import java.util.ArrayList;

public class Question {
	
	//ivars
	private ArrayList<String> questions;
	private int questionID;
	private ArrayList<Integer> quizIDs; // stores ids of quizes in which this question is used
	private String answer;
	
	public Question(ArrayList<String> questions, int questionID,ArrayList<Integer> quizIDs) {
		this.questionID = questionID;
		this.questions = questions;
		this.setQuizIDs(quizIDs);
	}
	
	/**
	 * @return the questions
	 */
	public ArrayList<String> getQuestions() {
		return questions;
	}
	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}
	/**
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	}
	/**
	 * @param questionID the questionID to set
	 */
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the quizIDs
	 */
	public ArrayList<Integer> getQuizIDs() {
		return quizIDs;
	}

	/**
	 * @param quizIDs the quizIDs to set
	 */
	public void setQuizIDs(ArrayList<Integer> quizIDs) {
		this.quizIDs = quizIDs;
	}
	
}
