package Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Database.*;
import History.AccountHistoryManager;
import History.AccountHistoryManagerDao;
import History.QuizHistoryManager;
import History.Story;
import Temp.Account;
import Temp.AccountManager;
import Temp.AccountManagerDao;
import Temp.Answer;
import Temp.AnswerManager;
import Temp.Question;
import Temp.QuestionManager;
import Temp.Quiz;
import Temp.QuizLite;
import Temp.QuizLiteManager;
import Temp.QuizManager;
import javafx.util.Pair;

class TestManagers {
	
	private String database = DataBaseINFO.MYSQL_DATABASE_NAME;
	private String account = DataBaseINFO.MYSQL_USERNAME;
	private String password = DataBaseINFO.MYSQL_PASSWORD;
	private String server = DataBaseINFO.MYSQL_DATABASE_SERVER;
	
	
	public Connection createConnection() {
		
		// if you want to test managers with 5 second delay uncomment below.
		
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
			return con;
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
		
		
		Connection con = createConnection();
		AccountManager manager = new AccountManager();
		AccountHistoryManager historyManager = new AccountHistoryManager();
		
		System.out.println("testing addNewAccount:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		
		/*
		ArrayList<String> list = manager.listOfAccounts(con);
		System.out.println("Before Test------------");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("-----------------------");
		System.out.println("After Test-------------");
		manager.addNewAccount("test", "test", "test", "test", "test", con);
		list = manager.listOfAccounts(con);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("-----------------------");
		
		System.out.println("////////////////////////////");
		System.out.println("testing checkPassword:");
		System.out.println("Should be 0: " + manager.doLogin("test", "test", con));
		System.out.println("Should be 1: " + manager.doLogin("0", "test", con));
		System.out.println("Should be 2: " + manager.doLogin("test", "0", con));
		System.out.println("Should be 1: " + manager.doLogin("", "", con));
		System.out.println("-----------------------");
		
		*/
		
		System.out.println("////////////////////////////");
		System.out.println("testing containsAccount:");
		System.out.println("testing is correct. i just put it in comments because, it needed addNewAccountTest to be runned");
		
		/*
			System.out.println("Should be 0: " + manager.containsAccount("test1", "test1", con));
			System.out.println("Should be 1: " + manager.containsAccount("test", "test1", con));
			System.out.println("Should be 2: " + manager.containsAccount("test1", "test", con));
			System.out.println("Should be 3: " + manager.containsAccount("test", "test", con));
		*/
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing getAccount methods (2 with username, 2 with id):");
		System.out.println("rlobz17: " + manager.getAccount("rlobz17",historyManager, con));
		System.out.println("id = 2: " + manager.getAccount(2,historyManager, con));
		System.out.println("dpopk17: " + manager.getAccount("dpopk17",historyManager, con));
		System.out.println("id = 4: " + manager.getAccount(4,historyManager, con));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing getAccountUsername method:");
		System.out.println("id = 1: " + manager.getAccountUsername(1, con));
		System.out.println("id = 2: " + manager.getAccountUsername(2, con));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		Pair< ArrayList<Account>, Integer> result = null;
		System.out.println("testing searchAccounts:");
		
		System.out.println("-----------------------");
		System.out.println("getting account (null, 0, 2, historyManager, con) -");
		result = manager.searchAccounts(null, 0, 2, historyManager, con);
		ArrayList<Account> accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting account (rlobz, 0, 100, historyManager, con) -");
		result = manager.searchAccounts("rlobz", 0, 100, historyManager, con);
		accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting account (l, 0, 100, historyManager, con) -");
		result = manager.searchAccounts("l", 0, 100, historyManager, con);
		accounts = result.getKey();
		for(int i=0; i<accounts.size(); i++) {
			Account account = accounts.get(i);
			System.out.println((i+1)+") "+ account);
		}
		System.out.println("All Accounts found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting account (rexi, 0, 100, historyManager, con) -");
		result = manager.searchAccounts("rexi", 0, 100, historyManager, con);
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
		assertEquals(0, manager.changeFirstName(1, "rezi1", con));
		System.out.println("update account_first_name to rezi1 with id=5 (should return 1)");
		assertEquals(1, manager.changeFirstName(5, "rezi1", con));
		
		System.out.println("-----------------------");
		System.out.println("update account_last_name to lobzhanidze1 with id=1");
		assertEquals(0, manager.changeLastName(1, "lobzhanidze1", con));
		System.out.println("update account_last_name to lobzhanidze1 with id=5 (should return 1)");
		assertEquals(1, manager.changeLastName(5, "lobzhanidze1", con));
		
		System.out.println("-----------------------");
		System.out.println("update account_username to rezgo with id=1");
		assertEquals(0, manager.changeUsername(1, "rezgo", con));
		System.out.println("update account_username to rezi1 with id=5 (should return 1)");
		assertEquals(1, manager.changeUsername(5, "rezi1", con));
		System.out.println("update account_username to snoza17 with id=1 (should return 2)");
		assertEquals(2, manager.changeUsername(1, "snoza17", con));
		
		System.out.println("-----------------------");
		System.out.println("update account_mail to rezi.lobzhanidze@gmail.com with id=1");
		assertEquals(0, manager.changeMail(1, "rezi.lobzhanidze@gmail.com", con));
		System.out.println("update account_mail to rezi.lobzhanidze@gmail.com with id=5 (should return 1)");
		assertEquals(1, manager.changeMail(5, "rezi.lobzhanidze@gmail.com", con));
		System.out.println("update account_mail to snoza17@freeuni.edu.ge with id=1 (should return 2)");
		assertEquals(2, manager.changeMail(1, "snoza17@freeuni.edu.ge", con));
		
		System.out.println("-----------------------");
		System.out.println("update account_imgUrl to someImageURL with id=1");
		assertEquals(0, manager.changeImg(1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeMLoLFFu0SpECMrDVcyDNMr5lxhAVTbl48UJ_pWqu62-FFQmr2Q", con));
		System.out.println("update account_imgUrl to someImageURL with id=5 (should return 1)");
		assertEquals(1, manager.changeImg(5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeMLoLFFu0SpECMrDVcyDNMr5lxhAVTbl48UJ_pWqu62-FFQmr2Q", con));
		
		
		System.out.println("-----------------------");
		System.out.println("change state of account with id=1 to admin");
		assertEquals(0, manager.makeAccountAdmin(1, con));
		System.out.println("change state of account with id=2 to user");
		assertEquals(0, manager.makeAccountUser(2, con));
		System.out.println("change state of account with id=5 to admin (should return 1)");
		assertEquals(1, manager.makeAccountAdmin(5, con));
		System.out.println("change state of account with id=5 to user (should return 1)");
		assertEquals(1, manager.makeAccountUser(5, con));
		
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
		
		
		Connection con = createConnection();
		QuizLiteManager manager = new QuizLiteManager();
		System.out.println("testing getAllQuizNumber:");
		System.out.println("Should be 22: " + manager.getAllQuizNumber(con));
		System.out.println("-----------------------");
		
		
		System.out.println("////////////////////////////");
		Pair< ArrayList<QuizLite>, Integer> result = null;
		System.out.println("testing searchQuizLites:");
		System.out.println("-----------------------");
		System.out.println("getting quizLites (null, null, 0, 100, con) -");
		result = manager.searchQuizLites(null, null, 0, 100, con);
		ArrayList<QuizLite> quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites (null, 1, 0, 10, con) -");
		result = manager.searchQuizLites(null, 1, 0, 10, con);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites ('1', null, 0, 10, con) -");
		result = manager.searchQuizLites("1", null, 0, 10, con);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites ('1', 1, 0, 10, con) -");
		result = manager.searchQuizLites("1", 1, 0, 10, con);
		quizes = result.getKey();
		for(int i=0; i<quizes.size(); i++) {
			QuizLite quizLite = quizes.get(i);
			System.out.println((i+1)+") "+ quizLite);
		}
		System.out.println("All Quizes found in this search: " + result.getValue());
		System.out.println("-----------------------");
		
		System.out.println("getting quizLites ('SimpleQuiz21', null, 0, 10, con) -");
		result = manager.searchQuizLites("SimpleQuiz21", null, 0, 10, con);
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
		
		Connection con = createConnection();
		QuestionManager manager = new QuestionManager();
		
		System.out.println("testing getQuestionType:");
		assertEquals("Question_Response_type", manager.getQuestionType(1, con));
		assertEquals("Picture_Response_type", manager.getQuestionType(2, con));
		System.out.println("Correct return values on getQuestionType method");
	
		
		System.out.println("////////////////////////////");
		System.out.println("testing getQuestion:");
		System.out.println("getting Question with id 1:");
		System.out.println(manager.getQuestion(1, con));
		System.out.println("-----------------------");
		System.out.println("getting Question with id 2:");
		System.out.println(manager.getQuestion(2, con));
		
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing addQuestion:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		/*

			ArrayList<Answer> testAnswers = new ArrayList<>();
			testAnswers.add(new Answer(0, 0, 1, "test"));
			
			System.out.println("addQuestion(1,null,null) with testAnswers");
			Question newQuestion = new Question(0, 1, null, null, testAnswers);
			int newQuestionID = manager.addQuestion(newQuestion, con);
			System.out.println("added Question - ");
			System.out.println(manager.getQuestion(newQuestionID, con));
		
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
		
		
		Connection con = createConnection();
		AnswerManager manager = new AnswerManager();
		
		System.out.println("testing getAllAnswer:");
		System.out.println("getting all answers on questionId = 1");
		ArrayList<Answer> result = manager.getAllAnswer(1, con);
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
		
		System.out.println("-----------------------");
		System.out.println("getting all answers on questionId = 2");
		result = manager.getAllAnswer(2, con);
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
		
		
		Connection con = createConnection();
		
		QuestionManager questionManager = new QuestionManager();
		QuizHistoryManager historyManager = new QuizHistoryManager();
		QuizManager manager = new QuizManager(questionManager, historyManager);
		
		System.out.println("testing getQuiz:");
		System.out.println("getting quiz with id = 1");
		System.out.println(manager.getQuiz(1, con));
		System.out.println("-----------------------");
		
		System.out.println("getting quiz with id = 2");
		System.out.println(manager.getQuiz(2, con));
		System.out.println("-----------------------");
		
		System.out.println("testing addQuiz method:");
		System.out.println("testing is correct. i just put it in comments because, it needed reseting the database afterwards");
		/*
			ArrayList<Question> testQuestions = new ArrayList<>();
			ArrayList<Answer> testAnswers = new ArrayList<>();
			testAnswers.add(new Answer(0, 0, 1, "test"));
			Question newQuestion = new Question(0, 1, null, null, testAnswers);
			testQuestions.add(newQuestion);
			
			System.out.println("adding new quiz with testQuestions");
			Quiz newQuiz = new Quiz(0, "testQuiz", testQuestions, 1, null, null, 0,0, "https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/ce2ece60-9b32-11e6-95ab-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg");
			int result = manager.addQuiz(newQuiz,con);
			System.out.println(manager.getQuiz(result, con));
		*/
		
		
	}
	
	
	@Test
	void testHistory() {
		
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("testing CLASS AccountHistoryManager and CLASS QuizHistoryManager");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		System.out.println("////////////////////////////");
		
		Connection con = createConnection();
		
		AccountHistoryManager accountHistory = new AccountHistoryManager();
		QuizHistoryManager quizHistory = new QuizHistoryManager();
		
		
		System.out.println("testing QuizHistoryManager:");
		System.out.println("////////////////////////////");
		System.out.println("testing getQuizAverageScore method:");
		
		System.out.println("getting average from quiz with id(1) - " + quizHistory.getQuizAverageScore(1, con));
		System.out.println("getting average from quiz with id(2) - " + quizHistory.getQuizAverageScore(2, con));
		
		System.out.println("-----------------------");
		System.out.println("testing getQuizTakenCount method:");
		System.out.println("getting taken count from quiz with id(1) - " + quizHistory.getQuizTakenCount(1, con));
		System.out.println("getting taken count from quiz with id(2) - " + quizHistory.getQuizTakenCount(2, con));
		
		System.out.println("-----------------------");
		System.out.println("testing getQuizHistory method:");
		ArrayList<Story> history = null;
		System.out.println("getting history for quiz with id(1)");
		history = quizHistory.getQuizHistory(1, con);
		for(int i=0; i<history.size(); i++) {
			System.out.println("\tstory " + (i+1) + ") "+ history.get(i));
		}
		System.out.println("getting history for quiz with id(2)");
		history = quizHistory.getQuizHistory(2, con);
		for(int i=0; i<history.size(); i++) {
			System.out.println("\tstory " + (i+1) + ") "+ history.get(i));
		}
		
		
		
		System.out.println("////////////////////////////");
		System.out.println("testing AccountHistoryManager:");
		System.out.println("////////////////////////////");
		System.out.println("testing getAccountAverageScore method:");
		
		System.out.println("getting average from account with id(1) - " + accountHistory.getAccountAverageScore(1, con));
		System.out.println("getting average from account with id(2) - " + accountHistory.getAccountAverageScore(2, con));
		System.out.println("getting average from account with id(3) - " + accountHistory.getAccountAverageScore(3, con));
		System.out.println("getting average from account with id(4) - " + accountHistory.getAccountAverageScore(4, con));
		
		System.out.println("-----------------------");
		System.out.println("testing getAccountQuizTakenCount method:");
		System.out.println("getting taken quiz count from account with id(1) - " + accountHistory.getAccountQuizTakenCount(1, con));
		System.out.println("getting taken quiz count from account with id(2) - " + accountHistory.getAccountQuizTakenCount(2, con));
		System.out.println("getting taken quiz count from account with id(3) - " + accountHistory.getAccountQuizTakenCount(3, con));
		System.out.println("getting taken quiz count from account with id(4) - " + accountHistory.getAccountQuizTakenCount(4, con));
		
		System.out.println("-----------------------");
		System.out.println("testing getAccountHistory method:");
		history = null;
		System.out.println("getting history for account with id(1)");
		history = accountHistory.getAccountHistory(1, con);
		for(int i=0; i<history.size(); i++) {
			System.out.println("\tstory " + (i+1) + ") "+ history.get(i));
		}
		System.out.println("getting history for account with id(2)");
		history = accountHistory.getAccountHistory(2, con);
		for(int i=0; i<history.size(); i++) {
			System.out.println("\tstory " + (i+1) + ") "+ history.get(i));
		}
		System.out.println("getting history for account with id(3)");
		history = accountHistory.getAccountHistory(3, con);
		for(int i=0; i<history.size(); i++) {
			System.out.println("\tstory " + (i+1) + ") "+ history.get(i));
		}
		System.out.println("getting history for account with id(4)");
		history = accountHistory.getAccountHistory(4, con);
		for(int i=0; i<history.size(); i++) {
			System.out.println("\tstory " + (i+1) + ") "+ history.get(i));
		}
		
	}
	

}
