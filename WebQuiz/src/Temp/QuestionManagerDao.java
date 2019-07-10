package Temp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Database.DataBaseINFO;

public class QuestionManagerDao {

	
	/**
	 * @return
	 * String QuestionTypeName - for the questionType with this id 
	 * null - for sql Error
	 */
	public String getQuestionType(int questionTypeID, Connection con) {
		String result = null;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT t.questionType_name FROM questiontypes t "
					+ "where t.questionType_id = " + questionTypeID);
			
			if(rs.next()) {
				result = rs.getString(1);
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
	 * Question - question with id of @param questionID
	 * null - if sql Error
	 */
	public Question getQuestion(int questionID, AnswerManager answerManager,  Connection con) {
		Question result = null;
		int questionTypeID;
		String questionDetail, questionTask, questionImgUrl;
		ArrayList<Answer> correctAnswers, possibleAnswers;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT * FROM questions where question_id =  "+ questionID);			
			if(rs.next()) {				
				questionTypeID = rs.getInt("questionType_id"); 
				questionDetail = rs.getString("question_detail");
				questionTask = rs.getString("question_task");	
				questionImgUrl = rs.getString("question_imgUrl");
			}else {
				stm.close();
				return null;
			}
			
			if(rs.next()) {
				stm.close();
				return null;
			}
			correctAnswers = answerManager.getAllCorrectAnswer(questionID, con);
			if(correctAnswers == null) {
				stm.close();
				return null;
			}
			possibleAnswers = answerManager.getAllPossibleAnswer(questionID, con);
			if(possibleAnswers == null) {
				stm.close();
				return null;
			}
			if(possibleAnswers.size()==0) {
				possibleAnswers = null;
			}
			
			
			if(questionTask == null) {
				questionTask = getQuestionDefaultTask(questionTypeID, con);
				if(questionTask==null) {
					stm.close();
					return null;
				}
			}
			result = new Question(questionID, questionTypeID, questionDetail, questionTask, questionImgUrl, correctAnswers, possibleAnswers);
			stm.close();
			
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
	public String getQuestionDefaultTask(int questionTypeID,  Connection con) {
		String result = null;
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT questionType_defaultTask FROM questiontypes t "
					+ "where t.questionType_id = "+questionTypeID);
			
			if(rs.next()) {
				result = rs.getString(1);
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
	 *  0 - question was not added
	 *  questionID - if question was added successfully
	 * -1 - if sql Error
	 */
	public int addQuestion(Question newQuestion, Connection con) {
		int result = 1;
		
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addQuestionString = "INSERT INTO questions (questionType_id, question_detail, question_task, question_imgUrl) VALUES";
			addQuestionString += "(" +newQuestion.getQuestionType();
			if(newQuestion.getQuestionDetail() == null) {addQuestionString += ",null";}
			else {addQuestionString += ",'" + newQuestion.getQuestionDetail()+"'";}
			if(newQuestion.getQuestionTask()== null) {addQuestionString += ",null";}
			else {addQuestionString += ",'" + newQuestion.getQuestionTask()+"'";}
			if(newQuestion.getQuestionImgUrl()== null) {addQuestionString += ",null)";}
			else {addQuestionString += ",'" + newQuestion.getQuestionImgUrl()+"')";}
			
						
			
			stm.executeUpdate(addQuestionString);
			
			ResultSet rs =  stm.executeQuery("select last_insert_id()");
			if(rs.next()) {
				result = rs.getInt(1);
			}else {
				result = -1;
			}
			
			stm.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
		
		
		return result;
	}

}
