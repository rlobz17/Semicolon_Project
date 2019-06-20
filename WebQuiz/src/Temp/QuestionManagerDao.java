package Temp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.DataBaseINFO;

public class QuestionManagerDao {

	
	/**
	 * @return returns null if SQLError or QuestionID not found, Question_Name if it was found
	 */
	public String getQuestionType(int questionID, Statement stm) {
		String result = null;
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT t.questionType_name FROM questiontypes t "
					+ "where t.questionType_id = (select q.questionType_id from questions q where q.question_id = "+questionID+");");
			
			if(rs.next()) {
				result = rs.getString(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;	
	}

}
