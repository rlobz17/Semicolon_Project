package Temp;

import java.sql.Statement;

public class QuestionManager {
	
	private QuestionManagerDao dao;
	private AnswerManager answerManager;
	
	public QuestionManager() {
		dao = new QuestionManagerDao();
		answerManager = new AnswerManager();
	}
	
	/**
	 * @return returns null if SQLError or QuestionID not found, Question_Name if it was found
	 */
	public String getQuestionType(int questionID, Statement stm) {
		return dao.getQuestionType(questionID, stm);
	}
	
	public Question getQuestion(int questionID, Statement stm) {
		return dao.getQuestion(questionID, answerManager, stm);
	}
	
	

}
