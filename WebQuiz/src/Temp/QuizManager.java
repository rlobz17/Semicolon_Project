package Temp;

import java.sql.Statement;
import java.util.ArrayList;

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
	 * 0 - quiz  was not added
	 * quizID - quiz was added with this id 
	 * -1 - if sql Error
	 */
	public int addQuiz(Quiz quiz, Statement stm) {
		ArrayList<Question> questions = quiz.getQuestions();
		ArrayList<Integer> questionIDs = new ArrayList<>();
		for(int i=0; i<questions.size(); i++) {
			int result = questionManager.addQuestion(questions.get(i), stm);
			if(result == 0 || result == -1) return result;
			questionIDs.add(result);
			questions.get(i).setQuestionID(result);
		}
		return dao.addQuiz(quiz,questionIDs, stm);
	}
	
	
	/**
	 * @return returns -1 if SQLError, 0 if quiz with this id was not found, 1 if quiz was updated successfully
	 */
	public boolean addQuizTakenCount(int quiz_id, Statement stm) {
		return dao.addQuizTakenCount(quiz_id, stm);
	}
	

}
