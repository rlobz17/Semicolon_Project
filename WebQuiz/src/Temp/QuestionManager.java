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
	 * String QuestionTypeName - for the questionType with this id 
	 * null - for sql Error
	 */
	public String getQuestionType(int questionID, Connection con) {
		return dao.getQuestionType(questionID, con);
	}
	
	
	/**
	 * @return
	 * int QuestionTypeID - for the questionType with this name 
	 * -1 - for sql Error
	 */
	public int getQuestionType(String questionTypeName, Connection con) {
		return dao.getQuestionType(questionTypeName, con);
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
		ArrayList<Answer> correctAnswers = newQuestion.getCorrectAnswers();
		if(correctAnswers==null) {
			return 0;
		}
		for(int i=0; i<correctAnswers.size(); i++) {
			Answer answer = correctAnswers.get(i);
			answer.setQuestionID(result);
			int answerManagerResult = answerManager.addCorrectAnswer(answer, con);
			if(answerManagerResult == -1) {
				return -1;
			}
		}
		
		ArrayList<Answer> possibleAnswers = newQuestion.getPossibleAnswers();
		if(possibleAnswers!=null) {
			for(int i=0; i<possibleAnswers.size(); i++) {
				Answer answer = possibleAnswers.get(i);
				answer.setQuestionID(result);
				int answerManagerResult = answerManager.addPossibleAnswer(answer, con);
				if(answerManagerResult == -1) {
					return -1;
				}
			}
		}
		
		return result;
	}

}
