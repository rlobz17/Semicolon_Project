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
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the quitTaken
	 */
	public int getQuitTaken() {
		return quizTaken;
	}

	/**
	 * @param quitTaken the quitTaken to set
	 */
	public void setQuitTaken(int quitTaken) {
		this.quizTaken = quitTaken;
	}

	/**
	 * @return the creationData
	 */
	public Date getCreationData() {
		return CreationDate;
	}

	/**
	 * @param creationData the creationData to set
	 */
	public void setCreationData(Date creationData) {
		CreationDate = creationData;
	}

	/**
	 * @return the creator
	 */
	public Account getCreator() {
		return Creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Account creator) {
		Creator = creator;
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
	 * @param question the question to set
	 */
	public void setQuestion(ArrayList<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return the quizID
	 */
	public int getQuizID() {
		return quizID;
	}

	/**
	 * @param quizID the quizID to set
	 */
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return UpdatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}
	
	
	
}
