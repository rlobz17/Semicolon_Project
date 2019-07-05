package Temp;

public class QuestionTypes {

	private static final String Question_Response_type = "Question_Response_type"; // ღია კითხვა
	private static final String Fill_In_The_Blank_type = "Fill_In_The_Blank_type"; // გამოტოვებული სიტყვა
	private static final String Multiple_Choice_type = "Multiple_Choice_type"; // რამდენიმე სავარაუდოდან 1ის არჩევა
	private static final String Multi_Answer_type = "Multi_Answer_type"; // რამდენიმე შესავსები ველი
	private static final String Multiple_Choice_With_Multiple_Answers_type = "Multiple_Choice_With_Multiple_Answers_type"; // რამდენიმე სავარაუდოდა რამდენიმეს არჩევა
	private static final String Matching_type = "Matching_type"; // შესაბამისობა

	
	/**
	 * @return the questionResponseType
	 */
	public static String getQuestionResponseType() {
		return Question_Response_type;
	}
	
	/**
	 * @return the fillInTheBlankType
	 */
	public static String getFillInTheBlankType() {
		return Fill_In_The_Blank_type;
	}

	/**
	 * @return the multipleChoiceType
	 */
	public static String getMultipleChoiceType() {
		return Multiple_Choice_type;
	}


	/**
	 * @return the multiAnswerType
	 */
	public static String getMultiAnswerType() {
		return Multi_Answer_type;
	}

	/**
	 * @return the multipleChoiceWithMultipleAnswersType
	 */
	public static String getMultipleChoiceWithMultipleAnswersType() {
		return Multiple_Choice_With_Multiple_Answers_type;
	}


	/**
	 * @return the matchingTtype
	 */
	public static String getMatchingType() {
		return Matching_type;
	}

	
}
