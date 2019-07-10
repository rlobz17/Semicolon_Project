package Temp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DataBaseINFO;

public class AnswerManagerDao {

	
	
	/**
	 * @return 
	 * ArrayList<Answer> - correctly ordered correct answers for question with this id
	 * null - for sql Error 
	 */
	public ArrayList<Answer> getAllCorrectAnswer(int questionId,  Connection con) {
		ArrayList<Answer> result = new ArrayList<>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM answers "
					+ "where "+questionId+" = question_id and answer_possible = true;");
			
			while(rs.next()) {
				int answerID = rs.getInt("answer_id");
				int questionID = rs.getInt("question_id");
				int answerIndex = rs.getInt("answer_index");
				String answerDetail = rs.getString("answer_detail");
				boolean answerOrder = rs.getBoolean("answer_order");
				
				Answer newAnswer = new Answer(answerID, questionID, answerIndex, answerDetail, answerOrder);
				result.add(newAnswer);		
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;	
	}
	
	
	/**
	 * @return 
	 * ArrayList<Answer> - correctly ordered possible answers for question with this id
	 * null - for sql Error 
	 */
	public ArrayList<Answer> getAllPossibleAnswer(int questionId,  Connection con) {
		ArrayList<Answer> result = new ArrayList<>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM answers "
					+ "where "+questionId+" = question_id and answer_possible = false;");
			
			while(rs.next()) {
				int answerID = rs.getInt("answer_id");
				int questionID = rs.getInt("question_id");
				int answerIndex = rs.getInt("answer_index");
				String answerDetail = rs.getString("answer_detail");
				boolean answerOrder = rs.getBoolean("answer_order");
				
				
				Answer newAnswer = new Answer(answerID, questionID, answerIndex, answerDetail,answerOrder);
				result.add(newAnswer);		
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;	
	}
	
	
	
	/**
	 * @return
	 *  0 - if done without any problem,
	 *  1 - correct answer was not added
	 * -1 - if sql Error
	 */
	public int addCorrectAnswer(Answer newAnswer,  Connection con) {
		int result = 1;
				
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addAnswerString = "INSERT INTO answers (question_id, answer_index, answer_detail) VALUES";
			addAnswerString+= "("+newAnswer.getQuestionID();
			addAnswerString+= ","+newAnswer.getAnswerIndex();
			addAnswerString+= ",'"+newAnswer.getAnswerDetail()+"')";
			
			stm.executeUpdate(addAnswerString);
			result = 0;
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
	
	/**
	 * @return
	 *  0 - if done without any problem,
	 *  1 - possible answer was not added
	 * -1 - if sql Error
	 */
	public int addPossibleAnswer(Answer newAnswer,  Connection con) {
		int result = 1;
				
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addAnswerString = "INSERT INTO answers (question_id, answer_index, answer_detail, answer_possible) VALUES";
			addAnswerString+= "("+newAnswer.getQuestionID();
			addAnswerString+= ","+newAnswer.getAnswerIndex();
			addAnswerString+= ",'"+newAnswer.getAnswerDetail() + "'";
			addAnswerString+= ", false)";

			stm.executeUpdate(addAnswerString);
			result = 0;
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		return result;
	}
}
