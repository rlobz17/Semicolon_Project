package Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Database.*;
import Temp.AccountManager;
import Temp.AccountManagerDao;

class TestManagers {
	
	private String database = DataBaseINFO.MYSQL_DATABASE_NAME;
	private String account = DataBaseINFO.MYSQL_USERNAME;
	private String password = DataBaseINFO.MYSQL_PASSWORD;
	private String server = DataBaseINFO.MYSQL_DATABASE_SERVER;
	
	public Statement createStatement() {		
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
		Statement stm = createStatement();
		AccountManager manager = new AccountManager();
		ArrayList<String> list = manager.listOfAccounts(stm);
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
		
		System.out.println("testing checkPassword:");
		System.out.println("Should be 0: " + manager.doLogin("test", "test", stm));
		System.out.println("Should be 1: " + manager.doLogin("0", "test", stm));
		System.out.println("Should be 2: " + manager.doLogin("test", "0", stm));
		System.out.println("Should be 1: " + manager.doLogin("", "", stm));
		System.out.println("-----------------------");
		
		System.out.println("testing containsAccount:");
		System.out.println("Should be 0: " + manager.containsAccount("test1", "test1", stm));
		System.out.println("Should be 1: " + manager.containsAccount("test", "test1", stm));
		System.out.println("Should be 2: " + manager.containsAccount("test1", "test", stm));
		System.out.println("Should be 3: " + manager.containsAccount("test", "test", stm));
		System.out.println("-----------------------");
		
	}

}
