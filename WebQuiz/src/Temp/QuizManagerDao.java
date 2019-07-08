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

public class QuizManagerDao {

	
	/**
	 * @return 
	 * Quiz - found Quiz with this quizId
	 * null - for sql error
	 */
	public Quiz getQuiz(int quizId, QuestionManager questionManager, QuizHistoryManager historyManager, Connection con) {
		Quiz result = null;
		try{  
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectQuestionIDs = "Select question_id from quizquestionlinks ";
			selectQuestionIDs += "where quiz_id = " + quizId;
			
			ResultSet rs = stm.executeQuery(selectQuestionIDs);
			
			ArrayList<Integer> questionIDs = new ArrayList<>();
			while(rs.next()) {
				questionIDs.add(rs.getInt(1));
			}
			
			ArrayList<Question> questions = new ArrayList<>();
			
			for(int i=0; i<questionIDs.size(); i++) {
				Question question = questionManager.getQuestion(questionIDs.get(i), con);
				if(question == null) {
					stm.close();
					System.out.println("2");
					return null;
				}
				questions.add(question);
			}
			
			String selectQuiz = "Select * from quizes ";
			selectQuiz += "where quiz_id = " + quizId;
			
			rs = stm.executeQuery(selectQuiz);
			
			String quiz_name, imgURL;
			Date CreationDate , UpdatedDate;
			int creatorID, quizCategoryID;
			
			if(rs.next()) {
				quiz_name = rs.getString("quiz_name");
				try {
					CreationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("quiz_created"));
					String updateDateString = rs.getString("quiz_edited");
					if(updateDateString == null) {
						UpdatedDate = null;
					}else {
						UpdatedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateDateString);
					}
				} catch (ParseException e) {
					e.printStackTrace();
					stm.close();
					return null;
				}
				creatorID = rs.getInt("quiz_publisherId");
				imgURL = rs.getString("quiz_imgUrl");
				quizCategoryID = rs.getInt("quizCategory_id");
				
			}else {
				return null;
			}
			
			if(rs.next()) {
				return null;
			}
			
			double quizAverage = historyManager.getQuizAverageScore(quizId, con);
			if(quizAverage == -1) {
				stm.close(); 
				return null;
			}
			
			int quizTaken = historyManager.getQuizTakenCount(quizId, con);
			if(quizTaken == -1) {
				stm.close();
				return null;
			}
					
			
			result = new Quiz(quizId, quiz_name, questions, creatorID, UpdatedDate, CreationDate,quizCategoryID, quizTaken, quizAverage, imgURL);
			stm.close();
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	
	
//	/**
//	 * @return 
//	 * true  - if quiz was updated successfully
//	 * false - for sql error
//	 */
//	public boolean addQuizTakenCount(int quiz_id,  Connection con) {
//		boolean result = false;
//		try{  
//			
//			Statement stm = con.createStatement();
//			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
//			
//			String updateDoneCount = "UPDATE quizes SET quiz_doneCount = quiz_doneCount + 1 ";
//			updateDoneCount += "where quiz_id = " + quiz_id;
//			
//			result = stm.execute(updateDoneCount);
//			stm.close();
//			
//		}catch(SQLException e){
//			e.printStackTrace();
//			return false;
//		}
//		return result;
//	}
	
	/**
	 * @return
	 * 0 - quiz  was not added
	 * quizID - quiz was added with this id 
	 * -1 - if sql Error
	 */
	public int addQuiz(Quiz quiz,ArrayList<Integer> questionIDs, Connection con) {
		int result = 0;
		try{  
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String addQuiz = "INSERT INTO quizes (quiz_name, quiz_publisherId,quizCategory_id, quiz_imgUrl) VALUES";
			addQuiz += "('"+ quiz.getQuizName()+"'";
			addQuiz += "," + quiz.getCreatorID();
			if(quiz.getQuizCategoryID() == null) {addQuiz += ",null";}
			else {addQuiz += "," + quiz.getQuizCategoryID();}
			addQuiz += ",'" + quiz.getImgUrl() + "')";
			
		
			stm.executeUpdate(addQuiz);
			
			ResultSet rs =  stm.executeQuery("select last_insert_id()");
			if(rs.next()) {
				result = rs.getInt(1);
			}else {
				stm.close();
				return -1;
			}
			
			
			for(int i=0; i<questionIDs.size(); i++) {
				stm.executeUpdate("INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES ("+result+","+questionIDs.get(i)+")");
			}
			 
			stm.close();
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
}






















