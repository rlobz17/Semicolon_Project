package Temp;

import java.sql.Statement;
import java.util.ArrayList;

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
	public int addNewAccount(String firstname, String lastname, String username, String password, String mail,Statement stm) {
		int result = containsAccount(username, mail, stm);
		if(result == 0) {
			return dao.addNewAccount(firstname, lastname, username, password, mail, stm);
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
	public int containsAccount(String username, String mail, Statement stm) {
		return dao.containsAccount(username, mail, stm);
	}
	
	/**
	 * @return 
	 * 0 - successfully matched username and password,
	 * 1 - if username not in use,
	 * 2 - if password is incorrect,
	 * -1 - for sql Error 
	 */
	public int doLogin(String username, String password, Statement stm) {
		return dao.checkPassword(username, password, stm);
	}
	
	
	/**
	 * This method is used for testing.
	 * @return 
	 * ArrayList<String> - list of all acounts
	 * null - for sql Error
	 */
	public ArrayList<String> listOfAccounts(Statement stm) {
		return dao.listOfAccounts(stm);
	}
	
	
	/**
	 * @return 
	 * Account - found account with this username
	 * null - for sql Error 
	 */
	public Account getAccount(String username, Statement stm) {
		return dao.getAccount(username, stm);
	}
	
	/**
	 * @return 
	 * Account - found account with this userID
	 * null - for sql Error 
	 */
	public Account getAccount(int userID, Statement stm) {
		return dao.getAccount(userID, stm);
	}
	
	/**
	 * @return 
	 * String - username of account with this id
	 * null - for sql Error 
	 */
	public String getAccountUsername(int userID, Statement stm) {
		return dao.getAccountUsername(userID, stm);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed first name of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeFirstName(int accountId, String newFirstName, Statement stm) {
		return dao.changeFirstName(accountId, newFirstName, stm);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed last name of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeLastName(int accountId, String newLastName, Statement stm) {
		return dao.changeLastName(accountId, newLastName, stm);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed username of account with this id,
	 *  1 - account with this id was not found,
	 *  2 - if username is already in use,
	 * -1 - for sql Error 
	 */
	public int changeUsername(int accountId, String newUsername, Statement stm) {
		return dao.changeUsername(accountId, newUsername, stm);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed mail of account with this id,
	 *  1 - account with this id was not found
	 *  2 - if mail is already in use,
	 * -1 - for sql Error 
	 */
	public int changeMail(int accountId, String newMail, Statement stm) {
		return dao.changeMail(accountId, newMail, stm);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed profile picture of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeImg(int accountId, String newImgUrl, Statement stm) {
		return dao.changeImg(accountId, newImgUrl, stm);
	}
	
	
	/**
	 * @return 
	 *  0 - successfully added number of quizes taken,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int addQuizesTaken(int accountId, Statement stm) {
		return dao.addQuizesTaken(accountId, stm);
	}
	
	
	/**
	 * @return 
	 *  0 - successfully changed state of account with this id to admin
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int makeAccountAdmin(int accountId ,Statement stm) {
		return dao.changeAccountState(accountId, AccountManagerDao.ADMIN, stm);
	}
	
	/**
	 * @return 
	 *  0 - successfully changed state of account with this id to user
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int makeAccountUser(int accountId ,Statement stm) {
		return dao.changeAccountState(accountId, AccountManagerDao.USER, stm);
	}
	
	
	/**
	 * @return 
	 * Pair<ArrayList<Account>, Integer> - ArrayList<Account> is searched, ordered and limited account list, Integer is full number of accounts found in this search.
	 * null - for sql Error 
	 */
	public  Pair<ArrayList<Account>, Integer> searchAccounts(String search, int beginIndex, int count, Statement stm) {
		return dao.searchAccounts(search, beginIndex, count, stm);
	}
}	
