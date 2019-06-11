package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccountManagerDAO {
	
	private Statement stm;
	private String database;
	
	public AccountManagerDAO(Statement stm, String database) {
		this.stm = stm;
		this.database = database;
	}
	
	
	public ArrayList<String> listOfAccounts() {
		
		ArrayList <String> result = new ArrayList<String>();
		try {
			stm.executeQuery("USE "+database);
			ResultSet rs = stm.executeQuery("SELECT e.account_username FROM accounts e");
			while(rs.next()) {
				result.add(rs.getString(1));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
	}
	
	
}
