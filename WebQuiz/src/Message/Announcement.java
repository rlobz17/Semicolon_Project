package Message;

import java.util.Date;

public class Announcement {
	
	private String title;
	private int author;//account_id;
	private String fullStory;
	private Date date;
	private String imgURL;
	
	public Announcement(String title, String fullStory, int author, Date date, String imgURL) {
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

	public int getAutor() {
		return author;
	}

	public void setAutor(int autor) {
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
