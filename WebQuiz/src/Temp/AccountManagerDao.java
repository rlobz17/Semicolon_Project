package Temp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DataBaseINFO;

public class AccountManagerDao {

	public ArrayList<String> listOfAccounts(Statement stm) {
		ArrayList <String> result = new ArrayList<String>();
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT e.account_username FROM accounts e");
			while(rs.next()) {
				result.add(rs.getString(1));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
	}
	
	public void addNewAccount(String firstname, String lastname, String username, String password, String mail,Statement stm) {
		
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			String insert = "INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES (";
			insert += "\"" + firstname + "\", ";
			insert += "\"" + lastname + "\", ";
			insert += "\"" + username + "\", ";
			insert += "\"" + mail + "\", ";
			insert += "\"" + password + "\");";
						
			stm.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * @return 1 - if username is in use, 2 - if mail is in use, 
	 * 3 - if username and mail both in use, 0 - otherwise
	 */
	public int searchUser(String username, String mail, Statement stm) {
		int result = 0;
		try{  
			
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stm.executeQuery(selectUsername);
						
			if(rs.next()) {
				result = 1;
			}
						
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			String selectMail = "Select * from accounts ";
			selectMail += "where account_mail = \"" + mail + "\";";
			
			rs = stm.executeQuery(selectMail);
			if(rs.next()) {
				if(result==1) {
					result = 3;
				} else result = 2;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return result;
	}
	
}