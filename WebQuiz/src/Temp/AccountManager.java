package Temp;

import java.sql.Statement;


public class AccountManager {
	public AccountManager() {}
	
	public void addNewAccount(String firstname, String lastname, String username, String password, String mail,Statement stm) {
		
	}
	
	public int containsAccount(String username, String mail, Statement stm) {
		return 0;
	}
	
	public boolean doLogin(String username, String password, Statement stm) {
		return false;
	}
}
