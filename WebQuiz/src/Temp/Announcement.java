package Temp;

import java.util.Date;

public class Announcement {
	
	private String title;
	private String author;
	private String fullStory;
	private Date date;
	private String imgURL;
	
	public Announcement(String title, String fullStory, String author, Date date, String imgURL) {
		this.title = title;
		this.fullStory = fullStory;
		this.author = author;
		this.date = date;
		this.imgURL = imgURL;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutor() {
		return author;
	}

	public void setAutor(String autor) {
		this.author = autor;
	}

	public String getFullStory() {
		return fullStory;
	}

	public void setFullStory(String fullStory) {
		this.fullStory = fullStory;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
