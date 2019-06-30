package Temp;

import java.sql.Statement;
import java.util.ArrayList;

import javafx.util.Pair;

public class QuizLiteManager {
	
	QuizLiteManagerDao dao;
	
	public QuizLiteManager() {
		dao = new QuizLiteManagerDao();
	}
	
	/**
	 * @return 
	 * int allQuizNumber - counts all quiz number in the search.
	 * -1 - for sql Error 
	 */
	public int getAllQuizNumber(Statement stm) {
		return dao.getAllQuizNumber(stm);
	}
	
	
	/**
	 * @return 
	 * Pair<ArrayList<QuizLite>, Integer> - ArrayList<QuizLite> is searched, ordered and limited QuizLite list, Integer is full number of QuizLites found in this search.
	 * null - for sql Error 
	 */
	public  Pair<ArrayList<QuizLite>, Integer> searchQuizLites(String search, Integer user_id, int beginIndex, int count, Statement stm) {
		if(search ==null ) {
			search = "";
		}
		return dao.searchQuizLites(search, user_id, beginIndex, count, stm);
	}

}
