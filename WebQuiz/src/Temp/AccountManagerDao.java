package Temp;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public int addNewAccount(String firstname, String lastname, String username, String password, String mail,Statement stm) {
		
		try {
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			String insert = "INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES (";
			if(firstname == "") {insert += "null, ";} else {insert += "\"" + firstname + "\", ";}
			if(lastname == "")  {insert += "null, ";} else {insert += "\"" + lastname + "\", ";}
			insert += "\"" + username + "\", ";
			insert += "\"" + mail + "\", ";
			insert += "\"" + password + "\");";
						
			stm.executeUpdate(insert);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
	}
	
	
	
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

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	
	
	/**
	 * @return 
	 * 0 - successfully matched username and password,
	 * 1 - if username not in use,
	 * 2 - if password is incorrect,
	 * -1 - for sql Error 
	 */
	public int checkPassword(String username, String password, Statement stm) {
		int result = 0;
		try{  
			
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stm.executeQuery(selectUsername);
						
			if(rs.next()) {
				if(rs.getString("account_password").equals(password)) {
					result = 0;
				}else {
					result = 2;
				}
			}else {
				result = 1;
			}
			
			if(rs.next()) {
				result = -1;
			}

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	
	public Account getAccount(String username, Statement stm) {
		Account result = null;
		try{  
			
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stm.executeQuery(selectUsername);
			
			if(rs.next()) {
				String userID = rs.getString("account_id");
				
				Date registrationDate;
				try {
					registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("account_created"));
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
				String mail = rs.getString("account_mail");
				String userName = rs.getString("account_username");
				String imgUrl = rs.getString("account_imgUrl");
				String firstName = rs.getString("account_first_name");
				String lastName = rs.getString("account_last_name");
				int quizesCreated = rs.getInt("account_quizesCreated");
				int quizesTaken = rs.getInt("account_quizesTaken");
				boolean isAdmin = rs.getBoolean("account_isAdmin");
				
				result = new Account(Integer.parseInt(userID), registrationDate, mail, userName, imgUrl, firstName, lastName, quizesCreated, quizesTaken, isAdmin);
			}else {
				result = null;
			}
			
			if(rs.next()) {
				result = null;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
}
