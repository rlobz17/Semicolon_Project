package Question;

import java.util.ArrayList;
import java.util.Collections;

import Temp.Answer;
import Temp.Question;


public class QuestionCreator {

	
	/**
	 * @param questionTypeID as int, which question type it is
	 * @param questionDetail as string
	 * @param imgUrl as string, if empty imgurl is set to null
	 * @param possibleAnswersString as all given answers
	 * @param correct answer as string, only one correct answer
	 * @return Question type object with given data
	 * */
	public Question createMultipleChoiceQ(int questionTypeID,String questionDetail, String imgUrl, ArrayList<String> possibleAnswersString,String correctAnswer) {
		ArrayList<Answer> possibleAnswers = new ArrayList<Answer>();
		for (int i = 0; i <possibleAnswersString.size(); i++) {
			possibleAnswers.add(new Answer(0, 0,i, possibleAnswersString.get(i)));
		}
		ArrayList<Answer> correctAnswers = new ArrayList<Answer>();
		correctAnswers.add(new Answer(0, 0, 0, correctAnswer));
		
		if(imgUrl.isEmpty()) imgUrl = null;
		
		return new Question(0, questionTypeID, questionDetail, null, imgUrl, correctAnswers, possibleAnswers, false);
	}
	
	/**
	 * @param questionTypeID as int, which question type it is
	 * @param questionDetail as string
	 * @param imgUrl as string, if empty imgurl is set to null
	 * @param possibleAnswersString as all given answers
	 * @param correct answers arraylist, filled with acceptable answers
	 * @return Question type object with given data
	 * */
	public Question createMultipleChoiceAndAnswerQ(int questionTypeID,String questionDetail, String imgUrl, ArrayList<String> possibleAnswersString,ArrayList<String> correctAnswersString) {
		ArrayList<Answer> possibleAnswers = new ArrayList<Answer>();	
		for (int i = 0; i <possibleAnswersString.size(); i++) {
			possibleAnswers.add(new Answer(0, 0,i, possibleAnswersString.get(i)));
		}
		
		ArrayList<Answer> correctAnswers = new ArrayList<Answer>();
		for (int i = 0; i <correctAnswersString.size(); i++) {
			correctAnswers.add(new Answer(0, 0,i, correctAnswersString.get(i)));
		}
		
		if(imgUrl.isEmpty()) imgUrl = null;
		
		return new Question(0, questionTypeID, questionDetail, null, imgUrl, correctAnswers, possibleAnswers, false);
	}
	
	/**
	 * @param questionTypeID as int, which question type it is
	 * @param questionDetail as string
	 * @param imgUrl as string, if empty imgurl is set to null
	 * @param correct answers arraylist, filled with acceptable answers
	 * @param bool order, represents if order is important for question
	 * @return Question type object with given data
	 * */
	public Question createMultiAnswerQ(int questionTypeID,String questionDetail, String imgUrl,ArrayList<String> correctAnswersString, boolean order) {
		ArrayList<Answer> possibleAnswers = new ArrayList<Answer>();	
		
		ArrayList<Answer> correctAnswers = new ArrayList<Answer>();
		for (int i = 0; i <correctAnswersString.size(); i++) {
			correctAnswers.add(new Answer(0, 0,i, correctAnswersString.get(i)));
		}
		
		if(imgUrl.isEmpty()) imgUrl = null;
		
		return new Question(0, questionTypeID, questionDetail, null, imgUrl, correctAnswers, possibleAnswers, order);
	}
	
	/**
	 * 
	 * @param questionTypeID
	 * @param questionDetail
	 * @param imgURL
	 * @param left
	 * @param right
	 * @return Question type object with given data
	 */
	public Question createMatchingQuestionQ(int questionTypeID, String questionDetail, String imgURL, ArrayList<String> left, ArrayList<String> right) {
		int size = left.size();

		
		for(int i=0; i<size; i++) {
			questionDetail += "\n ("+(i+1)+") "+right.get(i);
		}
		
		ArrayList<Answer> correctAnswers = new ArrayList<>();
		ArrayList<Answer> possibleAnswers = new ArrayList<>();

		for(int i=0; i<size; i++) {
			Answer newCorrect = new Answer(0, 0, i+1, Integer.toString(i+1));
			correctAnswers.add(newCorrect);
			Answer newPossible = new Answer(0, 0, i+1, left.get(i));
			possibleAnswers.add(newPossible);
		}
		
		Question result = new Question(0, questionTypeID, questionDetail, null, imgURL, correctAnswers, possibleAnswers, true);
		
		return result;
	}
}
