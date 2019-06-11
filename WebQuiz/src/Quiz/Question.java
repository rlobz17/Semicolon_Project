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
		this.setAnswers(answers);
		this.setQuizIDs(quizIDs);
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


	/**
	 * @return the answers
	 */
	public ArrayList<String> getAnswers() {
		return answers;
	}


	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	
}
