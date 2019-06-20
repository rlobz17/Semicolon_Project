package Temp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DataBaseINFO;

public class AnswerManagerDao {

	
	public ArrayList<Answer> getAllAnswer(int questionId, Statement stm) {
		ArrayList<Answer> result = new ArrayList<>();
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM answers "
					+ "where "+questionId+" = question_id;");
			
			while(rs.next()) {
				int answerID = rs.getInt("answer_id");
				int questionID = rs.getInt("question_id");
				int answerIndex = rs.getInt("answer_index");
				String answerDetail = rs.getString("answer_detail");
				
				Answer newAnswer = new Answer(answerID, questionID, answerIndex, answerDetail);
				result.add(newAnswer);		
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;	
	}
}
