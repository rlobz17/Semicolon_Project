package Temp;

import java.sql.Statement;

public class QuestionManager {
	
	private QuestionManagerDao dao;
	
	public QuestionManager() {
		dao = new QuestionManagerDao();
	}
	
	/**
	 * @return returns null if SQLError or QuestionID not found, Question_Name if it was found
	 */
	public String getQuestionType(int questionID, Statement stm) {
		return dao.getQuestionType(questionID, stm);
	}

}
