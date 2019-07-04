package Temp;

import java.sql.Connection;
import java.util.ArrayList;

public class QuestionManager {
	
	private QuestionManagerDao dao;
	private AnswerManager answerManager;
	
	public QuestionManager() {
		dao = new QuestionManagerDao();
		answerManager = new AnswerManager();
	}
	
	/**
	 * @return
	 * String QuestionTypeName - for the question with this id 
	 * null - for sql Error
	 */
	public String getQuestionType(int questionID, Connection con) {
		return dao.getQuestionType(questionID, con);
	}
	
	
	/**
	 * @return
	 * Question - question with id of @param questionID
	 * null - if sql Error
	 */
	public Question getQuestion(int questionID, Connection con) {
		return dao.getQuestion(questionID, answerManager, con);
	}
	
	
	/**
	 * @return
	 *  0 - question was not added
	 *  questionID - if question was added successfully
	 * -1 - if sql Error
	 */
	public int addQuestion(Question newQuestion, Connection con) {
		int result =  dao.addQuestion(newQuestion, con);
		ArrayList<Answer> answers = newQuestion.getCorrectAnswers();
		if(answers==null) {
			return 0;
		}
		for(int i=0; i<answers.size(); i++) {
			Answer answer = answers.get(i);
			answer.setQuestionID(result);
			int answerManagerResult = answerManager.addAnswer(answer, con);
			if(answerManagerResult == -1) {
				return -1;
			}
		}
		return result;
	}

}
