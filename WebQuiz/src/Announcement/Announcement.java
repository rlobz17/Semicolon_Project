package Announcement;

import java.util.Date;

public class Announcement {
	
	private int id;
	private String title;
	private int author;//account_id;
	private String fullStory;
	private Date date;
	
	public Announcement(int id,String title, String fullStory, int author, Date date) {
		this.id = id;
		this.title = title;
		this.fullStory = fullStory;
		this.author = author;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}


	public int getAutor() {
		return author;
	}


	public String getFullStory() {
		return fullStory;
	}


	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "Id: " + id +" || Title: "+title+" || FullStory: "+fullStory+" || Author: "+author+" || Date: "+date; 
	}

}
