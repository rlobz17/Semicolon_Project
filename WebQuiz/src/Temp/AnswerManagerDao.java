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
	
	
	/**
	 * @return
	 *  0 - if done without any problem,
	 *  1 - answer was not added
	 * -1 - if sql Error
	 */
	public int addAnswer(Answer newAnswer, Statement stm) {
		int result = 1;
				
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addAnswerString = "INSERT INTO answers (question_id, answer_index, answer_detail) VALUES";
			addAnswerString+= "("+newAnswer.getQuestionID();
			addAnswerString+= ","+newAnswer.getAnswerIndex();
			addAnswerString+= ",'"+newAnswer.getAnswerDetail()+"')";
			
			System.out.println(addAnswerString);
			stm.executeUpdate(addAnswerString);
			result = 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
}
