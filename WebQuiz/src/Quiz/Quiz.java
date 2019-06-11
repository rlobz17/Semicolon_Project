package Quiz;

import java.sql.Date;
import java.util.ArrayList;

import account.Account;

public class Quiz {
	private int quizID;
	private Account Creator;
	private Date CreationDate;
	private Date UpdatedDate;
	private int quizTaken; // how many tames this quiz was ....
	private String imgUrl;
	private ArrayList<Question> questions;
	
	public Quiz(int quizID,ArrayList<Question> questions,Account Creator,Date UpdatedDate,Date CreationData,int quizTaken,String imgUrl) {
		this.quizID = quizID;
		this.UpdatedDate = UpdatedDate;
		this.Creator = Creator;
		this.CreationDate = CreationData;
		this.quizTaken = quizTaken;
		this.imgUrl = imgUrl;
		this.questions = questions;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}



	/**
	 * @return the quitTaken
	 */
	public int getQuitTaken() {
		return quizTaken;
	}



	/**
	 * @return the creationData
	 */
	public Date getCreationData() {
		return CreationDate;
	}



	/**
	 * @return the creator
	 */
	public Account getCreator() {
		return Creator;
	}


	/**
	 * @param Question
	 * added this Question to current Quize
	 * */
	
	public void addQuestion(Question q) {
		this.questions.add(q);
	}
	
	
	/**
	 * @return the question
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}



	/**
	 * @return the quizID
	 */
	public int getQuizID() {
		return quizID;
	}


	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return UpdatedDate;
	}

}
