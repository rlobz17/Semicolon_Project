package Temp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;
import javafx.util.Pair;

public class QuizLiteManagerDao {

	
	public int getAllQuizNumber(Statement stm) {
		int result = 0;
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT count(1) FROM quizes");
			while(rs.next()) {
				result = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
	
	public Pair<ArrayList<QuizLite>, Integer> searchQuizLites(String search, Integer user_id, int beginIndex, int count, Statement stm) {
		ArrayList<QuizLite> result = new ArrayList<>();
		int allFoundCount = 0;
		try {
			
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
			query += " q.quiz_doneCount,";
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
					return null;
				}
				
				int quizDone = rs.getInt("quiz_doneCount");
				
				
				QuizLite newQuizLite = new QuizLite(quiz_id, title, publisher, imgurl, createDate, quizDone);
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
				return null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return new Pair<ArrayList<QuizLite>, Integer>(result, allFoundCount);
	}
}
