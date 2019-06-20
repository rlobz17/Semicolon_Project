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
		Date registrationDate;
		Account result = null;
		String userID, mail, userName, imgUrl, firstName, lastName;
		int quizesTaken, quizesCreated;
		boolean isAdmin;
		try{  
			
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stm.executeQuery(selectUsername);
			
			if(rs.next()) {
				userID = rs.getString("account_id");
				
				
				try {
					registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("account_created"));
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
				mail = rs.getString("account_mail");
				userName = rs.getString("account_username");
				imgUrl = rs.getString("account_imgUrl");
				firstName = rs.getString("account_first_name");
				lastName = rs.getString("account_last_name");
				quizesTaken = rs.getInt("account_quizesTaken");
				isAdmin = rs.getBoolean("account_isAdmin");
								
			}else {
				return null;
			}
			
			if(rs.next()) {
				return null;
			}
			
			String getQuizesCount = "Select count(*) from quizes q ";
			getQuizesCount += "where q.quiz_publisherId = " + userID + ";";
			
			
			rs = stm.executeQuery(getQuizesCount);
			if(rs.next()) {
				quizesCreated = rs.getInt(1);
			}else {
				quizesCreated = 0;
			}
			
			result = new Account(Integer.parseInt(userID), registrationDate, mail, userName, imgUrl, firstName, lastName, quizesCreated, quizesTaken, isAdmin);

			

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
}
