package Temp;


public class Answer implements Comparable<Answer> {
	private int answerID;
	private int questionID;
	private int answerIndex;
	private String answerDetail;
	
	public Answer(int answerID, int questionID, int answerIndex, String answerDetail) {
		this.answerID = answerID;
		this.questionID = questionID;
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
	 * sets question id to @param questionID
	 */
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}


	/**
	 * @return the answerIndex
	 * null - if answers have no order
	 */
	public Integer getAnswerIndex() {
		return answerIndex;
	}


	/**
	 * @return the answerDetail
	 */
	public String getAnswerDetail() {
		return answerDetail;
	}

	
	@Override
	public int compareTo(Answer o) {		
		int res = (this.getAnswerIndex() > o.getAnswerIndex()) ? 1 : (this.getAnswerIndex() < o.getAnswerIndex()) ? -1 : 0;
		return res;
	}
	
	@Override
	public String toString() {
		return ("Answer Id = "+answerID+", Answer Detail = " + this.answerDetail);
	}
	
	 @Override
	 public boolean equals(Object o) { 
		 Answer cur = (Answer)o;
		 return cur.getAnswerDetail().equals(answerDetail);
	 }

	
}
