package Temp;

import java.sql.Statement;
import java.util.ArrayList;

import javafx.util.Pair;

public class QuizLiteManager {
	
	QuizLiteManagerDao dao;
	
	public QuizLiteManager() {
		dao = new QuizLiteManagerDao();
	}
	
	public int getAllQuizNumber(Statement stm) {
		return dao.getAllQuizNumber(stm);
	}
	
	public  Pair<ArrayList<QuizLite>, Integer> searchQuizLites(String search, Integer user_id, int beginIndex, int count, Statement stm) {
		if(search ==null ) {
			search = "";
		}
		return dao.searchQuizLites(search, user_id, beginIndex, count, stm);
	}

}
