package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.Driver;

import Database.DataBaseINFO;

public class UserDB {
	public static void addUser(User user) {
		try{  
			// Build Path -> add extrenal archives -> "mysql-connector-java-8.0.16.jar"
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ DataBaseINFO.MYSQL_DATABASE_SERVER, DataBaseINFO.MYSQL_USERNAME, DataBaseINFO.MYSQL_PASSWORD);

			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			
			String insert = "INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES (";
			
			stmt.executeUpdate(insert);

			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static boolean searchUser(String username, String mail) {
		return true;
	}
}
