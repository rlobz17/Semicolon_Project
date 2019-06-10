package users;

public class AccountManager {
	public AccountManager() {
		
	}
	
	public void addNewAccount(String firstname, String lastname, String username, String password, String mail) {
		UserDB.addUser(firstname, lastname, username, password, mail);
	}
	
	public String containsAccount(String username, String mail) {
		int x = UserDB.searchUser(username, mail);
		if(x==1) return "ლოგინი უკვე გამოყენებულია";
		if(x==2) return "მეილი უკვე გამოყენებულია";
		if(x==3) return "ლოგინი და მეილი უკვე გამოყენებულია";
		return "0";
	}
	
	public boolean doLogin(String username, String password) {
		
		return true;
	}
}
