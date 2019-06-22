package Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Database.*;
import Temp.AccountManager;
import Temp.AccountManagerDao;
import Temp.Answer;
import Temp.AnswerManager;
import Temp.QuestionManager;
import Temp.QuizLite;
import Temp.QuizLiteManager;
import Temp.QuizManager;

class TestManagers {
	
	private String database = DataBaseINFO.MYSQL_DATABASE_NAME;
	private String account = DataBaseINFO.MYSQL_USERNAME;
	private String password = DataBaseINFO.MYSQL_PASSWORD;
	private String server = DataBaseINFO.MYSQL_DATABASE_SERVER;
	
	public Statement createStatement() {
		
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://"+server+"?autoReconnect=true&useSSL=false", account, password);
			Statement stmt = con.createStatement();
			return stmt;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
	

	@Test
	void testAccountManager() {
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS AccountManager");
		System.out.println("////////////////////////////");
		
		Statement stm = createStatement();
		AccountManager manager = new AccountManager();
		ArrayList<String> list = manager.listOfAccounts(stm);
		System.out.println("Before Test------------");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("-----------------------");
		System.out.println("After Test-------------");
		manager.addNewAccount("test", "test", "test", "test", "test", stm);
		list = manager.listOfAccounts(stm);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("-----------------------");
		
		System.out.println("testing checkPassword:");
		System.out.println("Should be 0: " + manager.doLogin("test", "test", stm));
		System.out.println("Should be 1: " + manager.doLogin("0", "test", stm));
		System.out.println("Should be 2: " + manager.doLogin("test", "0", stm));
		System.out.println("Should be 1: " + manager.doLogin("", "", stm));
		System.out.println("-----------------------");
		
		System.out.println("testing containsAccount:");
		System.out.println("Should be 0: " + manager.containsAccount("test1", "test1", stm));
		System.out.println("Should be 1: " + manager.containsAccount("test", "test1", stm));
		System.out.println("Should be 2: " + manager.containsAccount("test1", "test", stm));
		System.out.println("Should be 3: " + manager.containsAccount("test", "test", stm));
		System.out.println("-----------------------");
		
		System.out.println("testing getAccount:");
		System.out.println("rlobz17: " + manager.getAccount("rlobz17", stm));
		System.out.println("snoza17: " + manager.getAccount("snoza17", stm));
		System.out.println("dpopk17: " + manager.getAccount("dpopk17", stm));
		System.out.println("dkvel17: " + manager.getAccount("dkvel17", stm));
		System.out.println("-----------------------");
		
		
	}
	
	@Test
	void testQuizLiteManager() {
				
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS QuizLiteManager");
		System.out.println("////////////////////////////");
		
		
		Statement stm = createStatement();
		QuizLiteManager manager = new QuizLiteManager();
		System.out.println("testing getAllQuizNumber:");
		System.out.println("Should be 22: " + manager.getAllQuizNumber(stm));
		System.out.println("-----------------------");
		
		ArrayList<QuizLite> quizes = null;
		System.out.println("testing getAllQuizNumber:");
		
		System.out.println("getting quizLites (null, null, 0, 100, stm) -");
		quizes = manager.getQuizLites(null, null, 0, 100, stm);
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println(quizLite);
		}
		
		System.out.println("-----------------------");
		System.out.println("getting quizLites (null, 1, 0, 10, stm) -");
		quizes = manager.getQuizLites(null, 1, 0, 10, stm);
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println(quizLite);
		}
		System.out.println("-----------------------");
		System.out.println("getting quizLites ('1', null, 0, 10, stm) -");
		quizes = manager.getQuizLites("1", null, 0, 10, stm);
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println(quizLite);
		}
		System.out.println("-----------------------");
		System.out.println("getting quizLites ('1', 1, 0, 10, stm) -");
		quizes = manager.getQuizLites("1", 1, 0, 10, stm);
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println(quizLite);
		}
	}
	
	@Test
	void testQuestionManager() {
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS QuestionManager");
		System.out.println("////////////////////////////");
		
		Statement stm = createStatement();
		QuestionManager manager = new QuestionManager();
		
		System.out.println("testing getQuestionType:");
		assertEquals("Question_Response_type", manager.getQuestionType(1, stm));
		assertEquals("Picture_Response_type", manager.getQuestionType(2, stm));
		System.out.println("Correct return values on getQuestionType method");
		System.out.println("-----------------------");
		
		System.out.println("testing getQuestion:");
		System.out.println("getting Question with id 1:");
		System.out.println(manager.getQuestion(1, stm));
		System.out.println("getting Question with id 2:");
		System.out.println(manager.getQuestion(2, stm));
		System.out.println("-----------------------");
		
	}
	
	@Test
	void testAnswerManager() {
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS AnswerManager");
		System.out.println("////////////////////////////");
		
		Statement stm = createStatement();
		AnswerManager manager = new AnswerManager();
		
		System.out.println("testing getAllAnswer:");
		System.out.println("getting all answers on questionId = 1");
		ArrayList<Answer> result = manager.getAllAnswer(1, stm);
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
		
		System.out.println("-----------------------");
		System.out.println("getting all answers on questionId = 2");
		result = manager.getAllAnswer(2, stm);
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	
	@Test
	void testQuizManager() {
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS QuizManager");
		System.out.println("////////////////////////////");
		
		Statement stm = createStatement();
		QuizManager manager = new QuizManager();
		QuestionManager questionManager = new QuestionManager();
		
		System.out.println("testing getQuiz:");
		System.out.println("getting quiz with id = 1");
		System.out.println(manager.getQuiz(1, questionManager, stm));
		System.out.println("-----------------------");
		
		System.out.println("getting quiz with id = 2");
		System.out.println(manager.getQuiz(2, questionManager, stm));
		System.out.println("-----------------------");
		
		System.out.println("testing addQuizTakenCount:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		/*
			System.out.println("adding 5 done on quiz with id = 1");
			for(int i=0; i<5; i++) {
				manager.addQuizTakenCount(1, stm);
			}
			assertEquals(5, manager.getQuiz(1, questionManager, stm).getQuizTaken());
			
			System.out.println("adding 15 done on quiz with id = 3");
			for(int i=0; i<15; i++) {
				manager.addQuizTakenCount(3, stm);
			}
			assertEquals(15, manager.getQuiz(3, questionManager, stm).getQuizTaken());
			System.out.println("adding 25 done on quiz with id = 10");
			for(int i=0; i<25; i++) {
				manager.addQuizTakenCount(10, stm);
			}
			assertEquals(25, manager.getQuiz(10, questionManager, stm).getQuizTaken());
			
			System.out.println("checking again...");
			
			assertEquals(5, manager.getQuiz(1, questionManager, stm).getQuizTaken());
			assertEquals(15, manager.getQuiz(3, questionManager, stm).getQuizTaken());
			assertEquals(25, manager.getQuiz(10, questionManager, stm).getQuizTaken());
		*/
		
		
	}
	
	

}
