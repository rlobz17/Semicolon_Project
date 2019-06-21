package Temp;

import java.sql.Statement;

public class QuizManager {
	
	private QuizManagerDao dao;
	
	public QuizManager() {
		dao = new QuizManagerDao();	
	}

	
	/**
	 * @return returns null if SQLError or Quiz was not found, Quiz if it was found
	 */
	public Quiz getQuiz(int quizId, QuestionManager questionManager, Statement stm) {
		return dao.getQuiz(quizId, questionManager, stm);
	}
	
	/**
	 * @return returns -1 if SQLError, 0 if quiz is not added, 1 if quiz was added successfully
	 */
	public int addQuiz(Quiz quiz, Statement stm) {
		//TODO implement.
		return 0;
	}
	
	
	/**
	 * @return returns -1 if SQLError, 0 if quiz with this id was not found, 1 if quiz was updated successfully
	 */
	public int addQuizTakenCount(String quiz_id, Statement stm) {
		//TODO implement.
		return 0;
	}
	

}
