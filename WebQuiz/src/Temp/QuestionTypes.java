package Temp;

public class QuestionTypes {


	
	private static final String Multi_Answer_type = "Multi_Answer_type"; // 1 ან რამდენიმე, შესავსები ან ბოლოში დასაწერი სწორი პასუხები.
	private static final String Multiple_Choice_type = "Multiple_Choice_type"; // რამდენიმე სავარაუდო პასუხიდან მხოლოდ 1ის არჩევა
	private static final String Multiple_Choice_With_Multiple_Answers_type = "Multiple_Choice_With_Multiple_Answers_type"; // რამდენიმე სავარაუდო პასუხიდან რამდენიმეს არჩევა
	private static final String Matching_type = "Mathcing_type"; // შესაბამისობა

	

	/**
	 * @return the multipleChoiceType
	 */
	public String getMultipleChoiceType() {
		return Multiple_Choice_type;
	}


	/**
	 * @return the multiAnswerType
	 */
	public String getMultiAnswerType() {
		return Multi_Answer_type;
	}

	/**
	 * @return the multipleChoiceWithMultipleAnswersType
	 */
	public String getMultipleChoiceWithMultipleAnswersType() {
		return Multiple_Choice_With_Multiple_Answers_type;
	}


	/**
	 * @return the matchingTtype
	 */
	public String getMatchingType() {
		return Matching_type;
	}

	
}
