package Temp;



import java.util.ArrayList;
import java.util.Date;

public class Quiz {
	private int quizID;
	private String quiz_name;
	private int Creator_id;
	private Date CreationDate;
	private Date UpdatedDate;
	private int quizTaken; // how many tames this quiz was taken
	private double quizAverage; // average score of this quiz
	private String imgUrl;
	private Integer quizCategoryID;
	private ArrayList<Question> questions;
	
	public Quiz(int quizID, String quiz_name, ArrayList<Question> questions,int Creator_id,Date UpdatedDate,Date CreationDate,Integer quizCategoryID, int quizTaken, double quizAverage,String imgUrl) {
		this.quizID = quizID;
		this.quiz_name = quiz_name;
		this.UpdatedDate = UpdatedDate;
		this.Creator_id = Creator_id;
		this.CreationDate = CreationDate;
		this.quizTaken = quizTaken;
		this.quizAverage = quizAverage;
		this.imgUrl = imgUrl;
		this.questions = questions;
		this.quizCategoryID = quizCategoryID;
	}
	
	public Quiz() {
		this.questions = new ArrayList<Question>();
		this.UpdatedDate = null;
		this.quizAverage = 0;
		this.quizTaken = 0;
		this.imgUrl = null;
		this.quizID = 0;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/**
	 * @return the imgUrl
	 */
	public String getQuizName() {
		return quiz_name;
	}



	/**
	 * @return the quitTaken
	 */
	public int getQuizTaken() {
		return quizTaken;
	}
	
	
	/**
	 * @return the quizAverage
	 */
	public double getQuizAverage() {
		return quizAverage;
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
	public int getCreatorID() {
		return Creator_id;
	}


	/**
	 * @param Question
	 * adds this Question to current Quiz
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
	
	
	/**
	 * @return the quizCategoryID
	 */
	public Integer getQuizCategoryID() {
		return quizCategoryID;
	}
	
	
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	
	public void setQuizName(String quiz_name) {
		this.quiz_name = quiz_name;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public void setQuizCategoryID(int quizCategoryID) {
		this.quizCategoryID = quizCategoryID;
	}
	
	public void setCreatorID(int Creator_id) {
		this.Creator_id = Creator_id;
	}
	
	
	@Override
	public String toString() {
		String quests = "\n";
		for(int i=0; i<questions.size(); i++) {
			quests += "Question "+(i+1)+")\n" + questions.get(i).toString() + "\n";
		}
		return "quizID " + quizID +" | quiz_name "+quiz_name + " | CreationDate "+CreationDate + " | UpdatedDate " + UpdatedDate + 
				" | Creator_id " + Creator_id +" | quizTaken "+ quizTaken +" | quizAverage "+ quizAverage+" | quizCategoryID "+ quizCategoryID+ " | imgUrl "+ imgUrl + quests;
	}
}
