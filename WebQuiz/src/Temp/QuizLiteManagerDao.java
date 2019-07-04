package Temp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;
import History.QuizHistoryManager;
import javafx.util.Pair;

public class QuizLiteManagerDao {

	
	/**
	 * @return 
	 * int allQuizNumber - counts all quiz number in the search.
	 * -1 - for sql Error 
	 */
	public int getAllQuizNumber(Connection con) {
		int result = 0;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT count(1) FROM quizes");
			while(rs.next()) {
				result = rs.getInt(1);
			}	
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
	
	
	/**
	 * @return 
	 * Pair<ArrayList<QuizLite>, Integer> - ArrayList<QuizLite> is searched, ordered and limited QuizLite list, Integer is full number of QuizLites found in this search.
	 * null - for sql Error 
	 */
	public Pair<ArrayList<QuizLite>, Integer> searchQuizLites(String search, Integer user_id, int beginIndex, int count, QuizHistoryManager historyManager, Connection con) {
		ArrayList<QuizLite> result = new ArrayList<>();
		int allFoundCount = 0;
		try {
			Statement stm = con.createStatement();
			
			String fullTextSearch ="", likeClauseSearch = "%";
			if(search.length()!=0) {
				for(int i=0; i<search.length(); i++) {
					char current = search.charAt(i);
					likeClauseSearch += current + "%";
				}
				fullTextSearch = search + "*" ;
			}
			
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			String query = " SELECT q.quiz_id,";
			query += " q.quiz_name,";
			query += " (select a.account_username from accounts a where a.account_id = q.quiz_publisherId) publisher,";
			query += " q.quiz_imgUrl,";
			query += " q.quiz_created,";
			query += " MATCH (quiz_name) AGAINST('"+fullTextSearch+"' IN BOOLEAN MODE) as score";
			query += " FROM quizes q ";
			query += " where (q.quiz_name like '"+likeClauseSearch+"' or (soundex(q.quiz_name) like soundex('"+search+"')))";
			if(user_id != null) {query += " and q.quiz_publisherId = " + user_id;}
			if(search.length()!=0) {query += " order by score desc ";}
			else {query += " order by quiz_created desc ";}
			query += " limit "+beginIndex+", "+ count +";";

			
			ResultSet rs = stm.executeQuery(query);
			
			
			while(rs.next()) {
				int quiz_id = rs.getInt("quiz_id");
				String title = rs.getString("quiz_name");
				String publisher = rs.getString("publisher");
				String imgurl = rs.getString("quiz_imgUrl");
				
				Date createDate;
				try {
					createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("quiz_created"));
				} catch (ParseException e) {
					e.printStackTrace();
					stm.close();
					return null;
				}
				
				int quizDone = historyManager.getQuizTakenCount(quiz_id, con);
				double quizAverage = historyManager.getQuizAverageScore(quiz_id, con);
				
				
				QuizLite newQuizLite = new QuizLite(quiz_id, title, publisher, imgurl, createDate, quizDone, quizAverage);
				result.add(newQuizLite);	
			}
			
			
			query = " SELECT count(1)";
			query += " FROM quizes q ";
			query += " where (q.quiz_name like '"+likeClauseSearch+"' or (soundex(q.quiz_name) like soundex('"+search+"')))";
			if(user_id != null) {query += " and q.quiz_publisherId = " + user_id;}
			rs = stm.executeQuery(query);
			if(rs.next()) {
				allFoundCount = rs.getInt(1);
			}else {
				stm.close();
				return null;
			}
			
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return new Pair<ArrayList<QuizLite>, Integer>(result, allFoundCount);
	}
}
