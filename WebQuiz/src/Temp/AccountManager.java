package Temp;

import java.sql.Statement;


public class AccountManager {
	AccountManagerDao dao; 
	
	public AccountManager() {
		dao = new AccountManagerDao();
	}
	
	public void addNewAccount(String firstname, String lastname, String username, String password, String mail,Statement stm) {
		dao.addNewAccount(firstname, lastname, username, password, mail, stm);
	}
	
	public int containsAccount(String username, String mail, Statement stm) {
		return dao.searchUser(username, mail, stm);
	}
	
	public boolean doLogin(String username, String password, Statement stm) {
		return false;
	}
}
