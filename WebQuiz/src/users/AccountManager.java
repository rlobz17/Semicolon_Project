package users;

public class AccountManager {
	public AccountManager() {}
	
	public void addNewAccount(String firstname, String lastname, String username, String password, String mail) {
		UserDB.addUser(firstname, lastname, username, password, mail);
	}
	
	public int containsAccount(String username, String mail) {
		int x = UserDB.searchUser(username, mail);
		return x;
	}
	
	public boolean doLogin(String username, String password) {
		
		return true;
	}
}
