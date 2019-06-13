package Temp;

import java.sql.Statement;
import java.util.ArrayList;

public class QuizLiteManager {
	
	QuizLiteManagerDao dao;
	
	public QuizLiteManager() {
		dao = new QuizLiteManagerDao();
	}
	
	public int getAllQuizNumber(Statement stm) {
		return dao.getAllQuizNumber(stm);
	}
	
	public  ArrayList<QuizLite> getQuizLites(String search, Integer user_id, int beginIndex, int count, Statement stm) {
		if(search ==null ) {
			search = "";
		}
		return dao.getQuizLites(search, user_id, beginIndex, count, stm);
	}

}
