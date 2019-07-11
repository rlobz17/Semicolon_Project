package Temp;

import java.sql.Connection;
import java.util.ArrayList;

import History.QuizHistoryManager;
import javafx.util.Pair;

public class QuizLiteManager {
	
	private QuizLiteManagerDao dao;
	private QuizHistoryManager historyManager;
	
	public QuizLiteManager() {
		dao = new QuizLiteManagerDao();
		historyManager = new QuizHistoryManager();
	}
	
	/**
	 * @return 
	 * int allQuizNumber - counts all quiz number in the search.
	 * -1 - for sql Error 
	 */
	public int getAllQuizNumber(Connection con) {
		return dao.getAllQuizNumber(con);
	}
	
	
	/**
	 * @return 
	 * Pair<ArrayList<QuizLite>, Integer> - ArrayList<QuizLite> is searched, ordered and limited QuizLite list, Integer is full number of QuizLites found in this search.
	 * null - for sql Error 
	 */
	public  Pair<ArrayList<QuizLite>, Integer> searchQuizLites(String search, Integer user_id, Integer quizCategoryID, int beginIndex, int count, Connection con) {
		if(search ==null ) {
			search = "";
		}
		return dao.searchQuizLites(search, user_id, quizCategoryID, beginIndex, count, historyManager, con);
	}
	
	/**
	 * 
	 * @param quizesCount
	 * @param con
	 * @return
	 * ArrayList<QuizLite> - top 10 quizes ordered by quizTakenTimes.
	 * null - for sql error
	 */
	public ArrayList<QuizLite> getTopQuizes(int quizesCount, Connection con){
		ArrayList<Integer> topQuizIDs = historyManager.getTopQuizIDs(quizesCount, con);
		if(topQuizIDs== null) return null;
		return dao.getQuizLites(topQuizIDs,historyManager, con);
	}

}
