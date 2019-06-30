package Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Database.*;
import Temp.Account;
import Temp.AccountManager;
import Temp.AccountManagerDao;
import Temp.Answer;
import Temp.AnswerManager;
import Temp.Question;
import Temp.QuestionManager;
import Temp.QuizLite;
import Temp.QuizLiteManager;
import Temp.QuizManager;
import javafx.util.Pair;

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
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS AccountManager");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		
		Statement stm = createStatement();
		AccountManager manager = new AccountManager();
		ArrayList<String> list = manager.listOfAccounts(stm);
		System.out.println("testing addNewAccount:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		/*
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
		*/
		System.out.println("////////////////////////////");
		System.out.println("testing checkPassword:");
		System.out.println("Should be 0: " + manager.doLogin("test", "test", stm));
		System.out.println("Should be 1: " + manager.doLogin("0", "test", stm));
		System.out.println("Should be 2: " + manager.doLogin("test", "0", stm));
		System.out.println("Should be 1: " + manager.doLogin("", "", stm));
		System.out.println("-----------------------");
		
		System.out.println("////////////////////////////");
		System.out.println("testing containsAccount:");
		System.out.println("testing is correct. i just put it in comments because, it needed addNewAccountTest to be runned");
		/*
			System.out.println("Should be 0: " + manager.containsAccount("test1", "test1", stm));
			System.out.println("Should be 1: " + manager.containsAccount("test", "test1", stm));
			System.out.println("Should be 2: " + manager.containsAccount("test1", "test", stm));
			System.out.println("Should be 3: " + manager.containsAccount("test", "test", stm));
		*/
		System.out.println("-----------------------");
		
		System.out.println("////////////////////////////");
		System.out.println("testing getAccount methods (2 with username, 2 with id):");
		System.out.println("rlobz17: " + manager.getAccount("rlobz17", stm));
		System.out.println("id = 2: " + manager.getAccount(2, stm));
		System.out.println("dpopk17: " + manager.getAccount("dpopk17", stm));
		System.out.println("id = 4: " + manager.getAccount(4, stm));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing getAccountUsername method:");
		System.out.println("id = 1: " + manager.getAccountUsername(1, stm));
		System.out.println("id = 2: " + manager.getAccountUsername(2, stm));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		Pair< ArrayList<Account>, Integer> result = null;
		System.out.println("testing searchAccounts:");
		
		System.out.println("-----------------------");
		System.out.println("getting account (null, 0, 2, stm) -");
		result = manager.searchAccounts(null, 0, 2, stm);
		ArrayList<Account> accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting account (rlobz, 0, 100, stm) -");
		result = manager.searchAccounts("rlobz", 0, 100, stm);
		accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting account (l, 0, 100, stm) -");
		result = manager.searchAccounts("l", 0, 100, stm);
		accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting account (rexi, 0, 100, stm) -");
		result = manager.searchAccounts("rexi", 0, 100, stm);
		accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing accountUpdates:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		
	/*	
		System.out.println("-----------------------");
		System.out.println("update account_first_name to rezi1 with id=1");
		assertEquals(0, manager.changeFirstName(1, "rezi1", stm));
		System.out.println("update account_first_name to rezi1 with id=5 (should return 1)");
		assertEquals(1, manager.changeFirstName(5, "rezi1", stm));
		
		System.out.println("-----------------------");
		System.out.println("update account_last_name to lobzhanidze1 with id=1");
		assertEquals(0, manager.changeLastName(1, "lobzhanidze1", stm));
		System.out.println("update account_last_name to lobzhanidze1 with id=5 (should return 1)");
		assertEquals(1, manager.changeLastName(5, "lobzhanidze1", stm));
		
		System.out.println("-----------------------");
		System.out.println("update account_username to rezgo with id=1");
		assertEquals(0, manager.changeUsername(1, "rezgo", stm));
		System.out.println("update account_username to rezi1 with id=5 (should return 1)");
		assertEquals(1, manager.changeUsername(5, "rezi1", stm));
		System.out.println("update account_username to snoza17 with id=1 (should return 2)");
		assertEquals(2, manager.changeUsername(1, "snoza17", stm));
		
		System.out.println("-----------------------");
		System.out.println("update account_mail to rezi.lobzhanidze@gmail.com with id=1");
		assertEquals(0, manager.changeMail(1, "rezi.lobzhanidze@gmail.com", stm));
		System.out.println("update account_mail to rezi.lobzhanidze@gmail.com with id=5 (should return 1)");
		assertEquals(1, manager.changeMail(5, "rezi.lobzhanidze@gmail.com", stm));
		System.out.println("update account_mail to snoza17@freeuni.edu.ge with id=1 (should return 2)");
		assertEquals(2, manager.changeMail(1, "snoza17@freeuni.edu.ge", stm));
		
		System.out.println("-----------------------");
		System.out.println("update account_imgUrl to someImageURL with id=1");
		assertEquals(0, manager.changeImg(1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeMLoLFFu0SpECMrDVcyDNMr5lxhAVTbl48UJ_pWqu62-FFQmr2Q", stm));
		System.out.println("update account_imgUrl to someImageURL with id=5 (should return 1)");
		assertEquals(1, manager.changeImg(5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeMLoLFFu0SpECMrDVcyDNMr5lxhAVTbl48UJ_pWqu62-FFQmr2Q", stm));
		
		
		System.out.println("-----------------------");
		System.out.println("update account_quizesTaken to id=1");
		assertEquals(0, manager.addQuizesTaken(1, stm));
		System.out.println("update account_quizesTaken to id=5 (should return 1)");
		assertEquals(1, manager.addQuizesTaken(5, stm));
		
		System.out.println("-----------------------");
		System.out.println("change state of account with id=1 to admin");
		assertEquals(0, manager.makeAccountAdmin(1, stm));
		System.out.println("change state of account with id=2 to user");
		assertEquals(0, manager.makeAccountUser(2, stm));
		System.out.println("change state of account with id=5 to admin (should return 1)");
		assertEquals(1, manager.makeAccountAdmin(5, stm));
		System.out.println("change state of account with id=5 to user (should return 1)");
		assertEquals(1, manager.makeAccountUser(5, stm));
		
	*/
		
		
	}
	
	@Test
	void testQuizLiteManager() {
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS QuizLiteManager");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		
		
		Statement stm = createStatement();
		QuizLiteManager manager = new QuizLiteManager();
		System.out.println("testing getAllQuizNumber:");
		System.out.println("Should be 22: " + manager.getAllQuizNumber(stm));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		Pair< ArrayList<QuizLite>, Integer> result = null;
		System.out.println("testing searchQuizLites:");
		System.out.println("-----------------------");
		System.out.println("getting quizLites (null, null, 0, 100, stm) -");
		result = manager.searchQuizLites(null, null, 0, 100, stm);
		ArrayList<QuizLite> quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites (null, 1, 0, 10, stm) -");
		result = manager.searchQuizLites(null, 1, 0, 10, stm);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites ('1', null, 0, 10, stm) -");
		result = manager.searchQuizLites("1", null, 0, 10, stm);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites ('1', 1, 0, 10, stm) -");
		result = manager.searchQuizLites("1", 1, 0, 10, stm);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites ('SimpleQuiz21', null, 0, 10, stm) -");
		result = manager.searchQuizLites("SimpleQuiz21", null, 0, 10, stm);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
	}
	
	@Test
	void testQuestionManager() {
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS QuestionManager");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		
		Statement stm = createStatement();
		QuestionManager manager = new QuestionManager();
		
		System.out.println("testing getQuestionType:");
		assertEquals("Question_Response_type", manager.getQuestionType(1, stm));
		assertEquals("Picture_Response_type", manager.getQuestionType(2, stm));
		System.out.println("Correct return values on getQuestionType method");
		System.out.println("-----------------------");
		System.out.println("////////////////////////////");
		
		System.out.println("testing getQuestion:");
		System.out.println("getting Question with id 1:");
		System.out.println(manager.getQuestion(1, stm));
		System.out.println("getting Question with id 2:");
		System.out.println(manager.getQuestion(2, stm));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing addQuestion:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		/*

			ArrayList<Answer> testAnswers = new ArrayList<>();
			testAnswers.add(new Answer(0, 0, 1, "test"));
			
			System.out.println("addQuestion(1,null,null) with testAnswers");
			Question newQuestion = new Question(0, 1, null, null, testAnswers);
			int newQuestionID = manager.addQuestion(newQuestion, stm);
			System.out.println("added Question - ");
			System.out.println(manager.getQuestion(newQuestionID, stm));
		
		*/
		
		
	}
	
	@Test
	void testAnswerManager() {
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS AnswerManager");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
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
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS QuizManager");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		
		
		Statement stm = createStatement();
		
		QuestionManager questionManager = new QuestionManager();
		QuizManager manager = new QuizManager(questionManager);
		
		System.out.println("testing getQuiz:");
		System.out.println("getting quiz with id = 1");
		System.out.println(manager.getQuiz(1, stm));
		System.out.println("-----------------------");
		
		System.out.println("getting quiz with id = 2");
		System.out.println(manager.getQuiz(2, stm));
		System.out.println("-----------------------");
		
		System.out.println("////////////////////////////");
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
		
		System.out.println("-----------------------");
		
		
	}
	
	

}
