package Temp;

import java.util.Date;

public class QuizLite {
	
	private int quiz_id;
	private String title;
	private int publisher_id; 
	private String imgurl;
	private Date date;
	private int quizDone;
	
	public QuizLite(int quiz_id, String title, int publisher_id, String imgurl, Date date, int quizDone) {
		this.quiz_id = quiz_id;
		this.title = title;
		this.publisher_id = publisher_id;
		this.imgurl = imgurl;
		this.date = date;
		this.quizDone = quizDone;
	}
	
	/**
	 * @return the quiz_id
	 */
	public int getQuizID() {
		return quiz_id;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the publisher
	 */
	public int getPublisherID() {
		return publisher_id;
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
