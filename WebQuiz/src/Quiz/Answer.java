package Quiz;

public class Answer {
	private int answerID;
	private int questionID;
	private int answerIndex;
	private String answerDetail;
	
	public Answer(int answerID, int questionID,int answerIndex, String answerDetail) {
		this.answerDetail = answerDetail;
		this.answerID = answerID;
		this.answerIndex = answerIndex;
		this.answerDetail = answerDetail;
	}
	
	/**
	 * @return the answerID
	 */
	public int getAnswerID() {
		return answerID;
	}


	/**
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	}


	/**
	 * @return the answerIndex
	 */
	public int getAnswerIndex() {
		return answerIndex;
	}


	/**
	 * @return the answerDetail
	 */
	public String getAnswerDetail() {
		return answerDetail;
	}


	
}
