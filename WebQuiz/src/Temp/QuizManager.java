package Temp;

import java.sql.Statement;

public class QuizManager {
	
	private QuizManagerDao dao;
	private QuestionManager questionManager;
	
	public QuizManager(QuestionManager questionManager) {
		dao = new QuizManagerDao();
		this.questionManager = questionManager;
	}

	
	/**
	 * @return returns null if SQLError or Quiz was not found, Quiz if it was found
	 */
	public Quiz getQuiz(int quizId, Statement stm) {
		return dao.getQuiz(quizId, questionManager, stm);
	}
	
	/**
	 * @return
	 * 0 - if done without any problem,
	 * 1 - quiz was not added
	 * -1 - if sql Error
	 */
	public int addQuiz(Quiz quiz, Statement stm) {
		//TODO implement.
		return 0;
	}
	
	
	/**
	 * @return returns -1 if SQLError, 0 if quiz with this id was not found, 1 if quiz was updated successfully
	 */
	public boolean addQuizTakenCount(int quiz_id, Statement stm) {
		return dao.addQuizTakenCount(quiz_id, stm);
	}
	

}
