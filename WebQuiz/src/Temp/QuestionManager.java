package Temp;

import java.sql.Statement;
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
	public String getQuestionType(int questionID, Statement stm) {
		return dao.getQuestionType(questionID, stm);
	}
	
	
	/**
	 * @return
	 * Question - question with id of @param questionID
	 * null - if sql Error
	 */
	public Question getQuestion(int questionID, Statement stm) {
		return dao.getQuestion(questionID, answerManager, stm);
	}
	
	
	/**
	 * @return
	 *  0 - question was not added
	 *  questionID - if question was added successfully
	 * -1 - if sql Error
	 */
	public int addQuestion(Question newQuestion, Statement stm) {
		int result =  dao.addQuestion(newQuestion, stm);
		ArrayList<Answer> answers = newQuestion.getCorrectAnswers();
		if(answers==null) {
			return 0;
		}
		for(int i=0; i<answers.size(); i++) {
			Answer answer = answers.get(i);
			answer.setQuestionID(result);
			int answerManagerResult = answerManager.addAnswer(answer, stm);
			if(answerManagerResult == -1) {
				return -1;
			}
		}
		return result;
	}

}
