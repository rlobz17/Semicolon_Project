package Temp;

import java.sql.Connection;
import java.util.ArrayList;

import History.AccountHistoryManager;
import javafx.util.Pair;


public class AccountManager {
	AccountManagerDao dao; 
	
	public AccountManager() {
		dao = new AccountManagerDao();
	}
	
	/**
	 * @return
	 * 0 - if done without any problem,
	 * 1 - if username is in use, 
	 * 2 - if mail is in use, 
	 * 3 - if username and mail both in use,
	 * 4 - if username or mail field is empty,
	 * -1 - if sql Error
	 */
	public int addNewAccount(String firstname, String lastname, String username, String password, String mail,Connection con) {
		int result = containsAccount(username, mail, con);
		if(result == 0) {
			return dao.addNewAccount(firstname, lastname, username, password, mail, con);
		}
		return result;
	}
	
	
	/**
	 * @return 
	 * 0 - if username and mail both available,
	 * 1 - if username is in use, 
	 * 2 - if mail is in use, 
	 * 3 - if username and mail both in use,
	 * -1 - for sql Error 
	 */
	public int containsAccount(String username, String mail, Connection con) {
		return dao.containsAccount(username, mail, con);
	}
	
	/**
	 * @return 
	 * 0 - successfully matched username and password,
	 * 1 - if username not in use,
	 * 2 - if password is incorrect,
	 * -1 - for sql Error 
	 */
	public int doLogin(String username, String password, Connection con) {
		return dao.checkPassword(username, password, con);
	}
	
	
	/**
	 * This method is used for testing.
	 * @return 
	 * ArrayList<String> - list of all acounts
	 * null - for sql Error
	 */
	public ArrayList<String> listOfAccounts(Connection con) {
		return dao.listOfAccounts(con);
	}
	
	
	/**
	 * @return 
	 * Account - found account with this username
	 * null - for sql Error 
	 */
	public Account getAccount(String username, AccountHistoryManager historyManager, Connection con) {
		return dao.getAccount(username, historyManager, con);
	}
	
	/**
	 * @return 
	 * Account - found account with this userID
	 * null - for sql Error 
	 */
	public Account getAccount(int userID, AccountHistoryManager historyManager, Connection con) {
		return dao.getAccount(userID, historyManager, con);
	}
	
	/**
	 * @return 
	 * String - username of account with this id
	 * null - for sql Error 
	 */
	public String getAccountUsername(int userID, Connection con) {
		return dao.getAccountUsername(userID, con);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed first name of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeFirstName(int accountId, String newFirstName, Connection con) {
		return dao.changeFirstName(accountId, newFirstName, con);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed last name of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeLastName(int accountId, String newLastName, Connection con) {
		return dao.changeLastName(accountId, newLastName, con);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed username of account with this id,
	 *  1 - account with this id was not found,
	 *  2 - if username is already in use,
	 * -1 - for sql Error 
	 */
	public int changeUsername(int accountId, String newUsername, Connection con) {
		return dao.changeUsername(accountId, newUsername, con);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed mail of account with this id,
	 *  1 - account with this id was not found
	 *  2 - if mail is already in use,
	 * -1 - for sql Error 
	 */
	public int changeMail(int accountId, String newMail, Connection con) {
		return dao.changeMail(accountId, newMail, con);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed profile picture of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeImg(int accountId, String newImgUrl, Connection con) {
		return dao.changeImg(accountId, newImgUrl, con);
	}
	
	
//	/**
//	 * @return 
//	 *  0 - successfully added number of quizes taken,
//	 *  1 - account with this id was not found
//	 * -1 - for sql Error 
//	 */
//	public int addQuizesTaken(int accountId, Connection con) {
//		return dao.addQuizesTaken(accountId, con);
//	}
	
	
	/**
	 * @return 
	 *  0 - successfully changed state of account with this id to admin
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int makeAccountAdmin(int accountId ,Connection con) {
		return dao.changeAccountState(accountId, AccountManagerDao.ADMIN, con);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed state of account with this id to user
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int makeAccountUser(int accountId ,Connection con) {
		return dao.changeAccountState(accountId, AccountManagerDao.USER, con);
	}
	
	
	/**
	 * @return 
	 * Pair<ArrayList<Account>, Integer> - ArrayList<Account> is searched, ordered and limited account list, Integer is full number of accounts found in this search.
	 * null - for sql Error 
	 */
	public  Pair<ArrayList<Account>, Integer> searchAccounts(String search, int beginIndex, int count, AccountHistoryManager historyManager, Connection con) {
		return dao.searchAccounts(search, beginIndex, count, historyManager, con);
	}
}	
