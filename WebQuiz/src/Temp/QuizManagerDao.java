package Temp;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;

public class QuizManagerDao {

	
	
	public Quiz getQuiz(int quizId, QuestionManager questionManager, Statement stm) {
		Quiz result = null;
		try{  
			
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectQuestionIDs = "Select question_id from quizquestionlinks ";
			selectQuestionIDs += "where quiz_id = " + quizId;
			
			ResultSet rs = stm.executeQuery(selectQuestionIDs);
			
			ArrayList<Integer> questionIDs = new ArrayList<>();
			while(rs.next()) {
				questionIDs.add(rs.getInt(1));
			}
			
			if(questionIDs.size() == 0) {
				return null;
			}
			
			ArrayList<Question> questions = new ArrayList<>();
			
			for(int i=0; i<questionIDs.size(); i++) {
				Question question = questionManager.getQuestion(questionIDs.get(i), stm);
				if(question == null) {
					return null;
				}
				questions.add(question);
			}
			
			String selectQuiz = "Select * from quizes ";
			selectQuiz += "where quiz_id = " + quizId;
			
			rs = stm.executeQuery(selectQuiz);
			
			if(rs.next()) {
				String quiz_name = rs.getString("quiz_name");
				Date CreationDate , UpdatedDate;
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
					return null;
				}
				int creatorID = rs.getInt("quiz_publisherId");
				String imgURL = rs.getString("quiz_imgUrl");
				int quizTaken = rs.getInt("quiz_doneCount");
				result = new Quiz(quizId, quiz_name, questions, creatorID, UpdatedDate, CreationDate, quizTaken, imgURL);
			}else {
				return null;
			}
			
			if(rs.next()) {
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
}
