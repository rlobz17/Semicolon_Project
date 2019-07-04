package Temp;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class AnswerManager {

	private AnswerManagerDao dao;
	
	public AnswerManager() {
		dao = new AnswerManagerDao();
	}
	
	
	/**
	 * @return 
	 * ArrayList<Answer> - correctly ordered answers for question with this id
	 * null - for sql Error 
	 */
	public ArrayList<Answer> getAllAnswer(int question_id, Connection con){
		ArrayList<Answer> result = dao.getAllAnswer(question_id, con);
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
	public int addAnswer(Answer newAnswer, Connection con) {
		return dao.addAnswer(newAnswer, con);
	}
}
