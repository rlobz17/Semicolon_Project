package account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

//import com.mysql.jdbc.Driver;

import Database.DataBaseINFO;

public class AccountDB {
	public static void addUser(String firstname, String lastname, String username, String password, String mail) {
		try{
			// Build Path -> add extrenal archives -> "mysql-connector-java-8.0.16.jar"
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ DataBaseINFO.MYSQL_DATABASE_SERVER, DataBaseINFO.MYSQL_USERNAME, DataBaseINFO.MYSQL_PASSWORD);

			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String insert = "INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES (";
			insert += "\"" + firstname + "\", ";
			insert += "\"" + lastname + "\", ";
			insert += "\"" + username + "\", ";
			insert += "\"" + mail + "\", ";
			insert += "\"" + password + "\");";
						
			stmt.executeUpdate(insert);
			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * @return 1 - if username is in use, 2 - if mail is in use, 
	 * 3 - if username and mail both in use, 0 - otherwise
	 */
	public static int searchUser(String username, String mail) {
		int result = 0;
		try{  
			// Build Path -> add extrenal archives -> "mysql-connector-java-8.0.16.jar"
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ DataBaseINFO.MYSQL_DATABASE_SERVER, DataBaseINFO.MYSQL_USERNAME, DataBaseINFO.MYSQL_PASSWORD);

			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String selectUsername = "Select * from accounts ";
			selectUsername += "where account_username = \"" + username + "\";";
						
			ResultSet rs = stmt.executeQuery(selectUsername);
			int count = 0;
			while (rs.next()) count++;
			if(count>0) result = 1;
			
			stmt.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			String selectMail = "Select * from accounts ";
			selectMail += "where account_mail = \"" + mail + "\";";
			
			rs = stmt.executeQuery(selectMail);
			count = 0;
			while (rs.next()) count++;
			if(count>0) {
				if(result==1) {
					result = 3;
				} else result = 2;
			}
			
			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return result;
	}
	
}
