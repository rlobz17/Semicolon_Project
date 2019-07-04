package Temp;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import History.QuizHistoryManager;

public class QuizManager {
	
	private QuizManagerDao dao;
	private QuestionManager questionManager;
	private QuizHistoryManager historyManager;
	
	public QuizManager(QuestionManager questionManager, QuizHistoryManager historyManager) {
		dao = new QuizManagerDao();
		this.questionManager = questionManager;
		this.historyManager = historyManager;
	}

	
	/**
	 * @return 
	 * Quiz - found Quiz with this quizId
	 * null - for sql error
	 */
	public Quiz getQuiz(int quizId, Connection con) {
		return dao.getQuiz(quizId, questionManager, historyManager, con);
	}
	
	/**
	 * @return
	 * 0 - quiz  was not added
	 * quizID - quiz was added with this id 
	 * -1 - if sql Error
	 */
	public int addQuiz(Quiz quiz, Connection con) {
		ArrayList<Question> questions = quiz.getQuestions();
		ArrayList<Integer> questionIDs = new ArrayList<>();
		for(int i=0; i<questions.size(); i++) {
			int result = questionManager.addQuestion(questions.get(i), con);
			if(result == 0 || result == -1) return result;
			questionIDs.add(result);
			questions.get(i).setQuestionID(result);
		}
		return dao.addQuiz(quiz,questionIDs, con);
	}
	
	
	/**
	 * @return 
	 * true  - if quiz was updated successfully
	 * false - for sql error
	 */
	private boolean addQuizTakenCount(int quiz_id, Connection con) {
		return dao.addQuizTakenCount(quiz_id, con);
	}
	
	
	

}
