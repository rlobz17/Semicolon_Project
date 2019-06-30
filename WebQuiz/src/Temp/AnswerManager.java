package Temp;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class AnswerManager {

	private AnswerManagerDao dao;
	
	public AnswerManager() {
		dao = new AnswerManagerDao();
	}
	
	
	/**
	 * @return returns correctly ordered answers for question.
	 */
	
	public ArrayList<Answer> getAllAnswer(int question_id, Statement stm){
		ArrayList<Answer> result = dao.getAllAnswer(question_id, stm);
		if(result==null) {
			return null;
		}
		Collections.sort(result);
		return result;
	}
	
	/**
	 * @return
	 *  0 - if done without any problem,
	 *  1 - answer was not added
	 * -1 - if sql Error
	 */
	public int addAnswer(Answer newAnswer, Statement stm) {
		return dao.addAnswer(newAnswer, stm);
	}
}
