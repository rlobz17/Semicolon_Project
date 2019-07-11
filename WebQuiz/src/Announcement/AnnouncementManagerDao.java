package Announcement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseINFO;

public class AnnouncementManagerDao {
	
	public int addAnnouncement(Announcement an, Connection con) {
		int result = 0;
		try {
			Statement stm =  con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			String addAnnouncement = "INSERT INTO announcements (announcement_name ,announcement_text,announcement_publisherId) VALUES";
			addAnnouncement += "('"+ an.getTitle()+"'";
			addAnnouncement += ",'" + an.getFullStory()+"'";
			addAnnouncement += "," + an.getAutor()+")";
			
			stm.executeUpdate(addAnnouncement);
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	public ArrayList<Announcement> getLatestAnnouncements(Connection con){
		ArrayList<Announcement> res = new ArrayList<Announcement>();
		try {
			Statement stm = con.createStatement();
			stm.executeQuery("USE " + DataBaseINFO.MYSQL_DATABASE_NAME);
			String statement = "SELECT * FROM announcements   ORDER BY announcement_id  DESC LIMIT 5;";
			ResultSet rs = stm.executeQuery(statement);
			
			
			while(rs.next()) {
				int anId = rs.getInt("announcement_id");
				String title = rs.getString("announcement_name");
				String text = rs.getString("announcement_text");
				int author = rs.getInt("announcement_publisherId");
				Date createDate;
				try {
					createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("announcement_created"));
				} catch (ParseException e) {
					stm.close();
					return null;
				}
				res.add(new Announcement(anId, title, text, author, createDate));
			}
			
			
		} catch (SQLException e) {
			res = null;
		}
		
		return res;
	}
	
}
