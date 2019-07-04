package Temp;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;
import History.AccountHistoryManager;
import javafx.util.Pair;

public class AccountManagerDao {

	
	/**
	 * This method is used for testing.
	 * @return 
	 * ArrayList<String> - list of all acounts
	 * null - for sql Error
	 */
	public ArrayList<String> listOfAccounts(Connection con) {
		ArrayList <String> result = new ArrayList<String>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			ResultSet rs = stm.executeQuery("SELECT e.account_username FROM accounts e");
			while(rs.next()) {
				result.add(rs.getString(1));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
		return result;
	}
	
	
	/**
	 * @return
	 * 0 - if done without any problem,
	 * 1 - if username is in use, 
	 * 2 - if mail is in use, 
	 * 3 - if username and mail both in use,
	 * 4 - if username or mail field is empty,
	 * -1 - if sql Error
	 */
	public int addNewAccount(String firstname, String lastname, String username, String password, String mail,Connection con) {
		
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			String insert = "INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES (";
			if(firstname == "") {insert += "null, ";} else {insert += "\"" + firstname + "\", ";}
			if(lastname == "")  {insert += "null, ";} else {insert += "\"" + lastname + "\", ";}
			insert += "\"" + username + "\", ";
			insert += "\"" + mail + "\", ";
			insert += "\"" + password + "\");";
						
			stm.executeUpdate(insert);
			stm.close();
			
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
	}
	
	
	/**
	 * @return 
	 * 0 - if username and mail both not in use,
	 * 1 - if username is in use, 
	 * 2 - if mail is in use, 
	 * 3 - if username and mail both in use,
	 * -1 - for sql Error 
	 */
	public int containsAccount(String username, String mail, Connection con) {
		int result = 0;
		try{  
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stm.executeQuery(selectUsername);
						
			if(rs.next()) {
				result = 1;
			}
			
			String selectMail = "Select * from accounts ";
			selectMail += "where account_mail = \"" + mail + "\";";
			
			rs = stm.executeQuery(selectMail);
			if(rs.next()) {
				if(result==1) {
					result = 3;
				} else result = 2;
			}
			
			stm.close();

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
	public int checkPassword(String username, String password, Connection con) {
		int result = 0;
		try{  
			Statement stm = con.createStatement();
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
			
			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	
	/**
	 * @return 
	 * Account - found account with this username
	 * null - for sql Error 
	 */
	public Account getAccount(String username, AccountHistoryManager historyManager, Connection con) {
		Date registrationDate;
		Account result = null;
		String mail, userName, imgUrl, firstName, lastName;
		int userID;
		int quizesCreated;
		boolean isAdmin;
		try{  
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stm.executeQuery(selectUsername);
			
			if(rs.next()) {
				userID = rs.getInt("account_id");
				
				
				try {
					registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("account_created"));
				} catch (ParseException e) {
					e.printStackTrace();
					stm.close();
					return null;
				}
				mail = rs.getString("account_mail");
				userName = rs.getString("account_username");
				imgUrl = rs.getString("account_imgUrl");
				firstName = rs.getString("account_first_name");
				lastName = rs.getString("account_last_name");
				isAdmin = rs.getBoolean("account_isAdmin");
								
			}else {
				stm.close();
				return null;
			}
			
			if(rs.next()) {
				stm.close();
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
			
			int quizesTaken = historyManager.getAccountQuizTakenCount(userID, con);
			double quizesAverage = historyManager.getAccountAverageScore(userID, con);
			
			result = new Account(userID, registrationDate, mail, userName, imgUrl, firstName, lastName, quizesCreated, quizesTaken, quizesAverage, isAdmin);

			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @return 
	 * String - username of account with this id
	 * null - for sql Error 
	 */
	public String getAccountUsername(int userID, Connection con) {
		String result = null;		
	
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select account_username from accounts ";
			selectUsername += "where account_id = " + userID;
						
			ResultSet rs = stm.executeQuery(selectUsername);
			
			if(rs.next()) {
				result = rs.getString(1);
				if(rs.next()) {
					stm.close();
					return null;
				}
			}else {
				stm.close();
				return null;
			}
			
			stm.close();
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return result;

	}
	
	/**
	 * @return 
	 * Account - found account with this userID
	 * null - for sql Error 
	 */
	public Account getAccount(int userID, AccountHistoryManager historyManager, Connection con) {
		Date registrationDate;
		Account result = null;
		String mail, userName, imgUrl, firstName, lastName;
		int quizesCreated;
		boolean isAdmin;
		try{ 
			
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_id =  "+ userID;
						
			ResultSet rs = stm.executeQuery(selectUsername);
			
			if(rs.next()) {
				
				try {
					registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("account_created"));
				} catch (ParseException e) {
					e.printStackTrace();
					stm.close();
					return null;
				}
				mail = rs.getString("account_mail");
				userName = rs.getString("account_username");
				imgUrl = rs.getString("account_imgUrl");
				firstName = rs.getString("account_first_name");
				lastName = rs.getString("account_last_name");
				isAdmin = rs.getBoolean("account_isAdmin");
								
			}else {
				stm.close();
				return null;
			}
			
			if(rs.next()) {
				stm.close();
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
			
			int quizesTaken = historyManager.getAccountQuizTakenCount(userID, con);
			double quizesAverage = historyManager.getAccountAverageScore(userID, con);
			
			
			result = new Account(userID, registrationDate, mail, userName, imgUrl, firstName, lastName, quizesCreated, quizesTaken,quizesAverage, isAdmin);

			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * @return 
	 * Pair<ArrayList<Account>, Integer> - ArrayList<Account> is searched, ordered and limited account list, Integer is full number of accounts found in this search.
	 * null - for sql Error 
	 */
	public  Pair<ArrayList<Account>, Integer> searchAccounts(String search, int beginIndex, int count, AccountHistoryManager historyManager, Connection con) {
		ArrayList<Account> result = new ArrayList<>();
		int allFoundCount = 0;
		try {
			Statement stm = con.createStatement();
			String fullTextSearch ="", likeClauseSearch = "%";
			if(search!=null) {
				for(int i=0; i<search.length(); i++) {
					char current = search.charAt(i);
					fullTextSearch += current + "*";
					likeClauseSearch += current + "%";
				}
			}
			stm.executeQuery("USE "+DataBaseINFO.MYSQL_DATABASE_NAME);
			String query = " SELECT  account_id,";
			query += " MATCH (account_first_name, account_last_name, account_username, account_mail) AGAINST('"+fullTextSearch+"' IN BOOLEAN MODE) as score";
			query += " from accounts a";
			query += " where (account_first_name like '"+likeClauseSearch+"' or  (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')) or";
			query += "  account_last_name like '"+likeClauseSearch+"' or (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')) or";
			query += "  account_username like '"+likeClauseSearch+"' or (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')) or";
			query += " account_mail like '"+likeClauseSearch+"' or (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')))";
			query += " order by score desc";
			query += " limit "+beginIndex+", "+ count +";";
			ResultSet rs = stm.executeQuery(query);		

			
		
			while(rs.next()) {
				Account acc = getAccount(rs.getInt(1),historyManager, con);
				if(acc==null) {
					return null;
				}else {
					result.add(acc);
				}			
			}
			
			query = " SELECT  count(1)";
			query += " from accounts a";
			query += " where (account_first_name like '"+likeClauseSearch+"' or  (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')) or";
			query += "  account_last_name like '"+likeClauseSearch+"' or (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')) or";
			query += "  account_username like '"+likeClauseSearch+"' or (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')) or";
			query += " account_mail like '"+likeClauseSearch+"' or (soundex(a.account_first_name) like soundex('"+likeClauseSearch+"')))";
			rs = stm.executeQuery(query);	

			if(rs.next()) {
				allFoundCount = rs.getInt(1);
			}else {
				stm.close();
				return null;
			}
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
		return new Pair<ArrayList<Account>, Integer>(result, allFoundCount);

	}
	
	/**
	 * @return 
	 *  0 - successfully changed first name of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeFirstName(int accountId, String newFirstName, Connection con) {
		int result = -1;
		
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String findAccount = "Select 1 from accounts ";
			findAccount += "where account_id = " + accountId;
						
			ResultSet rs = stm.executeQuery(findAccount);
			
			if(!rs.next()) {
				stm.close();
				return 1;
			}else {
				if(rs.next()) {
					stm.close();
					return -1;
				}
			}
			
			
			String updateString = "Update accounts ";
			updateString += " set account_first_name = '" + newFirstName + "'";
			updateString += " where account_id = " + accountId;


			stm.executeUpdate(updateString);
			result = 0;

			stm.close();
		}catch(SQLException e){
			e.printStackTrace();
			
			return -1;
		}
		
		return result;
	}
	
	/**
	 * @return 
	 *  0 - successfully changed last name of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeLastName(int accountId, String newLastName, Connection con) {
		int result = -1;
		
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String findAccount = "Select 1 from accounts ";
			findAccount += "where account_id = " + accountId;
						
			ResultSet rs = stm.executeQuery(findAccount);
			
			if(!rs.next()) {
				stm.close();
				return 1;
			}else {
				if(rs.next()) {
					stm.close();
					return -1;
				}
			}
			
			
			String updateString = "Update accounts ";
			updateString += " set account_last_name = '" + newLastName+ "'";
			updateString += " where account_id = " + accountId;

						
			stm.executeUpdate(updateString);
			result = 0;
			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	/**
	 * @return 
	 *  0 - successfully changed username of account with this id,
	 *  1 - account with this id was not found,
	 *  2 - if username is already in use,
	 * -1 - for sql Error 
	 */
	public int changeUsername(int accountId, String newUsername, Connection con) {
		int result = -1;
		
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String findAccount = "Select 1 from accounts ";
			findAccount += "where account_id = " + accountId;
						
			ResultSet rs = stm.executeQuery(findAccount);
			
			if(!rs.next()) {
				stm.close();
				return 1;
			}else {
				if(rs.next()) {
					stm.close();
					return -1;
				}
			}
			
			String findThisUsername = "Select * from accounts ";
			findThisUsername += "where account_username = '" + newUsername+ "'";
						
			rs = stm.executeQuery(findThisUsername);
			
			if(rs.next()) {
				stm.close();
				return 2;
			}
			
			String updateString = "Update accounts ";
			updateString += " set account_username = '" + newUsername+ "'";
			updateString += " where account_id = " + accountId;

						
			stm.executeUpdate(updateString);
			result = 0;
			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	/**
	 * @return 
	 *  0 - successfully changed mail of account with this id,
	 *  1 - account with this id was not found
	 *  2 - if mail is already in use,
	 * -1 - for sql Error 
	 */
	public int changeMail(int accountId, String newMail, Connection con) {

		int result = -1;
		
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String findAccount = "Select 1 from accounts ";
			findAccount += "where account_id = " + accountId;
						
			ResultSet rs = stm.executeQuery(findAccount);
						
			if(!rs.next()) {
				stm.close();
				return 1;
			}else {
				if(rs.next()) {
					stm.close();
					return -1;
				}
			}
			
			String findThisMail = "Select * from accounts ";
			findThisMail += "where account_mail = '" + newMail+ "'";
						
			rs = stm.executeQuery(findThisMail);
			
			if(rs.next()) {
				stm.close();
				return 2;
			}
			
			
			
			String updateString = "Update accounts ";
			updateString += " set account_mail = '" + newMail+ "'";
			updateString += " where account_id = " + accountId;

						
			stm.executeUpdate(updateString);
			result = 0;
			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	/**
	 * @return 
	 *  0 - successfully changed profile picture of account with this id,
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeImg(int accountId, String newImgUrl, Connection con) {
		
		int result = -1;
		
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String findAccount = "Select 1 from accounts ";
			findAccount += "where account_id = " + accountId;
						
			ResultSet rs = stm.executeQuery(findAccount);
			
			if(!rs.next()) {
				stm.close();
				return 1;
			}else {
				if(rs.next()) {
					stm.close();
					return -1;
				}
			}
			
			
			String updateString = "Update accounts ";
			updateString += " set account_imgUrl = '" + newImgUrl+ "'";
			updateString += " where account_id = " + accountId;

						
			stm.executeUpdate(updateString);
			result = 0;
			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	
//	/**
//	 * @return 
//	 *  0 - successfully added number of quizes taken,
//	 *  1 - account with this id was not found
//	 * -1 - for sql Error 
//	 */
//	public int addQuizesTaken(int accountId, Connection con) {
//
//		int result = -1;
//		try{ 
//			Statement stm = con.createStatement();
//			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
//			
//			String findAccount = "Select 1 from accounts ";
//			findAccount += "where account_id = " + accountId;
//						
//			ResultSet rs = stm.executeQuery(findAccount);
//			
//			if(!rs.next()) {
//				return 1;
//			}else {
//				if(rs.next()) {
//					return -1;
//				}
//			}
//			
//			
//			String addTaken = "Update accounts ";
//			addTaken += " set account_quizesTaken = account_quizesTaken +1";
//			addTaken += " where account_id = " + accountId;
//
//						
//			stm.executeUpdate(addTaken);
//			result = 0;
//
//		}catch(SQLException e){
//			e.printStackTrace();
//			return -1;
//		}
//		
//		return result;
//	}
//	
	
	public static final int ADMIN = 0;
	public static final int USER = 1;
	
	
	/**
	 * @return 
	 *  0 - successfully changed state of account with this id
	 *  1 - account with this id was not found
	 * -1 - for sql Error 
	 */
	public int changeAccountState(int accountId, int task ,Connection con) {
		int result = -1;
		try{ 
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String findAccount = "Select 1 from accounts ";
			findAccount += "where account_id = " + accountId;
						
			ResultSet rs = stm.executeQuery(findAccount);
			
			if(!rs.next()) {
				stm.close();
				return 1;
			}else {
				if(rs.next()) {
					stm.close();
					return -1;
				}
			}
			
			
			String changeState = "Update accounts ";
			if(task==ADMIN) {changeState += " set account_isAdmin = true";}
			else { changeState += " set account_isAdmin = false"; } 
			changeState += " where account_id = " + accountId;

						
			stm.executeUpdate(changeState);
			result = 0;
			stm.close();

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
}
