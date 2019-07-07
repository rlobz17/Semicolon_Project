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
	 * ArrayList<Answer> - correctly ordered correct answers for question with this id
	 * null - for sql Error 
	 */
	public ArrayList<Answer> getAllCorrectAnswer(int question_id, Connection con){
		ArrayList<Answer> result = dao.getAllCorrectAnswer(question_id, con);
		if(result==null) {
			return null;
		}
		Collections.sort(result);
		return result;
	}
	
	/**
	 * @return 
	 * ArrayList<Answer> - correctly ordered possible answers for question with this id
	 * null - for sql Error 
	 */
	public ArrayList<Answer> getAllPossibleAnswer(int question_id, Connection con){
		ArrayList<Answer> result = dao.getAllPossibleAnswer(question_id, con);
		if(result==null) {
			return null;
		}
		Collections.sort(result);
		return result;
	}
	
	/**
	 * @return
	 *  0 - if done without any problem,
	 *  1 - correct answer was not added
	 * -1 - if sql Error
	 */
	public int addCorrectAnswer(Answer newAnswer, Connection con) {
		return dao.addCorrectAnswer(newAnswer, con);
	}
	
	/**
	 * @return
	 *  0 - if done without any problem,
	 *  1 - possible answer was not added
	 * -1 - if sql Error
	 */
	public int addPossibleAnswer(Answer newAnswer, Connection con) {
		return dao.addPossibleAnswer(newAnswer, con);
	}
}
