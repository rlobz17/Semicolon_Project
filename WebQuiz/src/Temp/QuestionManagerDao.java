package Temp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DataBaseINFO;

public class QuestionManagerDao {

	
	/**
	 * @return
	 * String QuestionTypeName - for the question with this id 
	 * null - for sql Error
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
	
	
	/**
	 * @return
	 * Question - question with id of @param questionID
	 * null - if sql Error
	 */
	public Question getQuestion(int questionID, AnswerManager answerManager, Statement stm) {
		Question result = null;
		int questionTypeID;
		String questionDetail, questionTask;
		ArrayList<Answer> correctAnswers;
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM questions where question_id =  "+ questionID);			
			if(rs.next()) {				
				questionTypeID = rs.getInt("questionType_id"); 
				questionDetail = rs.getString("question_detail");
				questionTask = rs.getString("question_task");			
			}else {
				return null;
			}
			
			if(rs.next()) {
				return null;
			}
			correctAnswers = answerManager.getAllAnswer(questionID, stm);
			if(correctAnswers == null) {
				return null;
			}
			
			if(questionTask == null) {
				questionTask = getQuestionDefaultTask(questionTypeID, stm);
				if(questionTask==null) {
					return null;
				}
			}
			result = new Question(questionID, questionTypeID, questionDetail, questionTask, correctAnswers);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;	
	}
	
	
	/**
	 * @return
	 * String defaultTask - default task for this questionTypeID
	 * null - if sql Error
	 */
	public String getQuestionDefaultTask(int questionTypeID, Statement stm) {
		String result = null;
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT questionType_defaultTask FROM questiontypes t "
					+ "where t.questionType_id = "+questionTypeID);
			
			if(rs.next()) {
				result = rs.getString(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return result;	
	}
	
	
	/**
	 * @return
	 *  0 - question was not added
	 *  questionID - if question was added successfully
	 * -1 - if sql Error
	 */
	public int addQuestion(Question newQuestion, Statement stm) {
		int result = 1;
		
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addQuestionString = "INSERT INTO questions (questionType_id, question_detail, question_task) VALUES";
			addQuestionString += "(" +newQuestion.getQuestionType();
			if(newQuestion.getQuestionDetail() == null) {addQuestionString += ",null";}
			else {addQuestionString += ",'" + newQuestion.getQuestionDetail()+"'";}
			if(newQuestion.getQuestionTask()== null) {addQuestionString += ",null)";}
			else {addQuestionString += ",'" + newQuestion.getQuestionTask()+"')";}
			
						
			
			stm.executeUpdate(addQuestionString);
			
			ResultSet rs =  stm.executeQuery("select last_insert_id()");
			if(rs.next()) {
				result = rs.getInt(1);
			}else {
				result = -1;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		
		return result;
	}

}
