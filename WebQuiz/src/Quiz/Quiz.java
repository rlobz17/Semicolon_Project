package Quiz;

import javafx.scene.chart.PieChart.Data;
import users.User;

public class Quiz {
	private int quizID;
	private User Creator;
	private Data CreationData;
	private int quizTaken;
	private String imgUrl;
	private Question question;
	
	public Quiz(int quizID,Question question,User Creator,Data CreationData,int quizTaken,String imgUrl) {
		this.setQuizID(quizID);
		this.Creator = Creator;
		this.CreationData = CreationData;
		this.quizTaken = quizTaken;
		this.imgUrl = imgUrl;
		this.question = question;
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
	public Data getCreationData() {
		return CreationData;
	}

	/**
	 * @param creationData the creationData to set
	 */
	public void setCreationData(Data creationData) {
		CreationData = creationData;
	}

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return Creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		Creator = creator;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
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
	
	
	
}
