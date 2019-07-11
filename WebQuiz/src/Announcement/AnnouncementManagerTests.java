package Announcement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



import Database.DataBaseINFO;


class AnnouncementManagerTests  {
	private static String database = DataBaseINFO.MYSQL_DATABASE_NAME;
	private static String account = DataBaseINFO.MYSQL_USERNAME;
	private static String password = DataBaseINFO.MYSQL_PASSWORD;
	private static String server = DataBaseINFO.MYSQL_DATABASE_SERVER;
	
	public static Connection createConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://"+server+"?autoReconnect=true&useSSL=false", account, password);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
//	public static void main(String[] args) {
//		AnnouncementManager man = new AnnouncementManager();
//		Connection con = createConnection();
//		
//		Announcement an = new Announcement(0, "wyali mevida", "dagibrunda wyaali", 1, new Date());
//		man.addAnnouncement(an, con);
//		
//		ArrayList<Announcement> a = man.getLatestAnnouncements(con);
//		for(int i=0; i<a.size(); i++) {
//			System.out.println(a.get(i));
//		}
//	}

}
