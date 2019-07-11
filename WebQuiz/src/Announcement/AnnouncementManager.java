package Announcement;

import java.sql.Connection;
import java.util.ArrayList;

public class AnnouncementManager {
	private AnnouncementManagerDao dao;
	
	public AnnouncementManager() {
		dao = new AnnouncementManagerDao();
	}
	
	
	/**
	 * @param Announcement to add as an
	 * @param connecntion to database
	 * adds given announcement in the database
	 * @return 0 if success, -1 if there was exception
	 * */
	public int addAnnouncement(Announcement an, Connection con) {
		return dao.addAnnouncement(an, con);
	}
	
	/**
	 * @param connection to database
	 * @returns 5 latests Announcements
	 * null if exception
	 * */
	public ArrayList<Announcement> getLatestAnnouncements(Connection con){
		return dao.getLatestAnnouncements(con);
	}
	
}
