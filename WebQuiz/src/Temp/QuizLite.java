package Temp;

import java.util.Date;

public class QuizLite {
	
	private int quiz_id;
	private String title;
	private String publisher; 
	private String imgurl;
	private Date date;
	private int quizDone;
	private double quizAverage;
	private int quizCategoryID;
	
	public QuizLite(int quiz_id, String title, String publisher, String imgurl, Date date,int quizCategoryID, int quizDone, double quizAverage) {
		this.quiz_id = quiz_id;
		this.title = title;
		this.publisher = publisher;
		this.imgurl = imgurl;
		this.date = date;
		this.quizDone = quizDone;
		this.quizAverage = quizAverage;
		this.quizCategoryID = quizCategoryID;
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
	public String getPublisher() {
		return publisher;
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
	 * @return the quiz average score
	 */
	public double getQuizAverage() {
		return quizAverage;
	}
	
	
	/**
	 * @return the imgurl
	 */
	public String getImgurl() {
		return imgurl;
	}
	
	/**
	 * @return the quizCategoryID
	 */
	public int getQuizCategoryID() {
		return quizCategoryID;
	}

	
	@Override
	public String toString() {
		return "ID " + quiz_id +" | title "+title + " | publisher " + publisher + 
				" | imgurl " + imgurl +" | date "+ date + " | quizCategoryID "+ quizCategoryID + " | quizDone " + quizDone + " | quizAverage " + quizAverage;
	}

}
