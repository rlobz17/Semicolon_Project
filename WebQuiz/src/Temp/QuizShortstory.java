package Temp;

import java.util.Date;

public class QuizShortstory {
	
	private String title;
	private String author; 
	private String imgurl;
	private Date date;
	private int quizDone;
	
	public QuizShortstory(String title, String author, String imgurl, Date date, int quizDone) {
		this.title = title;
		this.author = author;
		this.imgurl = imgurl;
		this.date = date;
		this.quizDone = quizDone;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return the quizDone
	 */
	public int getQuizDone() {
		return quizDone;
	}
	
	/**
	 * @return the imgurl
	 */
	public String getImgurl() {
		return imgurl;
	}
}
