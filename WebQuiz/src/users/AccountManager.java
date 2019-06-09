package users;

public class AccountManager {
	public AccountManager() {
		
	}
	
	public void addNewAccount(String firstname, String lastname, String username, String password, String mail) {
		User user = null;
		UserDB.addUser(user);
	}
	
	public boolean containsAccount(String username, String mail) {
		return UserDB.searchUser(username, mail);
	}
	
	public boolean doLogin(String username, String password) {
		
		return true;
	}
}
