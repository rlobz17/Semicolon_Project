package History;

import java.sql.Statement;
import java.util.ArrayList;

public class QuizHistoryManager {

	private QuizHistoryManagerDao dao;
	
	public QuizHistoryManager() {
		dao = new QuizHistoryManagerDao();
	}
	
	/**
	 * 
	 * @param quizID
	 * @param stm
	 * @return
	 * ArrayList<Strory> - history of quiz with this quizID
	 * null - for sql error
	 */
	public ArrayList<Story> getQuizHistory(int quizID, Statement stm) {
		
		return null;
	}
	
	/**
	 * 
	 * @param quizID
	 * @param stm
	 * @return
	 * double average - average score of quiz with this quizID
	 * -1 - for sql error 
	 */
	public double getQuizAverageScore(int quizID, Statement stm) {
		return dao.getQuizAverageScore(quizID, stm);
	}
}
