package Temp;

import java.sql.Statement;
import java.util.ArrayList;


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
	 * */
	public int addNewAccount(String firstname, String lastname, String username, String password, String mail,Statement stm) {
		int result = containsAccount(username, mail, stm);
		if(result == 0) {
			return dao.addNewAccount(firstname, lastname, username, password, mail, stm);
		}
		return result;
	}
	
	
	/**
	 * @return 
	 * 0 - if username and mail both not in use,
	 * 1 - if username is in use, 
	 * 2 - if mail is in use, 
	 * 3 - if username and mail both in use,
	 * -1 - for sql Error 
	 */
	public int containsAccount(String username, String mail, Statement stm) {
		return dao.searchUser(username, mail, stm);
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
	
	public ArrayList<String> listOfAccounts(Statement stm) {
		return dao.listOfAccounts(stm);
	}
	
		
	public Account getAccount(String username, Statement stm) {
		return dao.getAccount(username, stm);
	}
	
	
}
