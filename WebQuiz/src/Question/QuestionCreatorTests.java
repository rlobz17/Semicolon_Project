package Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Temp.Answer;
import Temp.Question;
import Temp.QuestionManager;
import Temp.QuestionTypes;

public class QuestionCreatorTests {
	private QuestionCreator questionCreator;
	
	@Before
	public void before() {
		questionCreator = new QuestionCreator();
	}
	
	@Test
	public void testMultipleChoiceCrator() {
		int questionTypeID = 2;
		String questionDetail = "Who am I?";
		String imgUrl = "";
		ArrayList<String> possibleAnswersString = new ArrayList<String>();
		possibleAnswersString.add("I");
		possibleAnswersString.add("Me");
		possibleAnswersString.add("You");
		possibleAnswersString.add("She");
		
		String correctAnswer = "You";
		Question result = questionCreator.createMultipleChoiceQ(questionTypeID, questionDetail, imgUrl, possibleAnswersString, correctAnswer);
		ArrayList<Answer> correctAnswers = result.getCorrectAnswers();
		assertEquals(correctAnswer, correctAnswers.get(0).getAnswerDetail());
		
		ArrayList<Answer> possibleAnswers = result.getPossibleAnswers();
		assertEquals(possibleAnswersString.get(0), possibleAnswers.get(0).getAnswerDetail());
		assertEquals(possibleAnswersString.get(1), possibleAnswers.get(1).getAnswerDetail());
		assertEquals(possibleAnswersString.get(2), possibleAnswers.get(2).getAnswerDetail());
		assertEquals(possibleAnswersString.get(3), possibleAnswers.get(3).getAnswerDetail());
		
		assertEquals(null, result.getQuestionImgUrl());
		assertEquals(2,result.getQuestionType());
		assertEquals(false, result.getQuestionAnswerOrder());
	}
	
	@Test
	public void testMultipleChoiceAndAnswerCreator() {
		int questionTypeID = 3;
		String questionDetail = "mark all answers which are < 10";
		String imgUrl = "";
		ArrayList<String> possibleAnswersString = new ArrayList<String>();
		possibleAnswersString.add("14");
		possibleAnswersString.add("4");
		possibleAnswersString.add("3");
		possibleAnswersString.add("7");
		possibleAnswersString.add("9");
		possibleAnswersString.add("10");
		possibleAnswersString.add("1");
		possibleAnswersString.add("18");
		
		
		ArrayList<String> correctAnswersString = new ArrayList<String>();
		correctAnswersString.add("1");
		correctAnswersString.add("3");
		correctAnswersString.add("4");
		correctAnswersString.add("7");
		correctAnswersString.add("9");
		
		Question result = questionCreator.createMultipleChoiceAndAnswerQ(questionTypeID, questionDetail, imgUrl, possibleAnswersString, correctAnswersString);
		ArrayList<Answer> correctAnswers = result.getCorrectAnswers();
		
		assertEquals(correctAnswersString.get(0), correctAnswers.get(0).getAnswerDetail());
		assertEquals(correctAnswersString.get(1), correctAnswers.get(1).getAnswerDetail());
		assertEquals(correctAnswersString.get(2), correctAnswers.get(2).getAnswerDetail());
		assertEquals(correctAnswersString.get(3), correctAnswers.get(3).getAnswerDetail());
		assertEquals(correctAnswersString.get(4), correctAnswers.get(4).getAnswerDetail());
		
		
		
		ArrayList<Answer> possibleAnswers = result.getPossibleAnswers();
		assertEquals(possibleAnswersString.get(0), possibleAnswers.get(0).getAnswerDetail());
		assertEquals(possibleAnswersString.get(1), possibleAnswers.get(1).getAnswerDetail());
		assertEquals(possibleAnswersString.get(2), possibleAnswers.get(2).getAnswerDetail());
		assertEquals(possibleAnswersString.get(3), possibleAnswers.get(3).getAnswerDetail());
		assertEquals(possibleAnswersString.size(), possibleAnswers.size());
		
		assertEquals(false, result.getQuestionAnswerOrder());
	}
	
	
	@Test
	public void testMultiAnswerCreator1() {
		int questionTypeID = 1;
		String questionDetail = "what is 5+5 and 10+10";
		String imgUrl = "";
		
		ArrayList<String> correctAnswersString = new ArrayList<String>();
		correctAnswersString.add("10");
		correctAnswersString.add("20");

		
		Question result = questionCreator.createMultiAnswerQ(questionTypeID, questionDetail, imgUrl, correctAnswersString, true);
		ArrayList<Answer> correctAnswers = result.getCorrectAnswers();
		
		assertEquals(correctAnswersString.get(0), correctAnswers.get(0).getAnswerDetail());
		assertEquals(correctAnswersString.get(1), correctAnswers.get(1).getAnswerDetail());
	
		
		assertEquals(true, result.getQuestionAnswerOrder());
	}
	
	@Test
	public void testMultiAnswerCreator2() {
		int questionTypeID = 1;
		String questionDetail = "what is 5+5 and 10+10";
		String imgUrl = "";
		
		ArrayList<String> correctAnswersString = new ArrayList<String>();
		correctAnswersString.add("10");
		correctAnswersString.add("20");

		
		Question result = questionCreator.createMultiAnswerQ(questionTypeID, questionDetail, imgUrl, correctAnswersString, false);
		ArrayList<Answer> correctAnswers = result.getCorrectAnswers();
		
		assertEquals(correctAnswersString.get(0), correctAnswers.get(0).getAnswerDetail());
		assertEquals(correctAnswersString.get(1), correctAnswers.get(1).getAnswerDetail());
	
		
		assertEquals(false, result.getQuestionAnswerOrder());
	}
	
	
	
	@Test
	public void testMatchingQuestionCreator() {
		System.out.println("################################");
		ArrayList<String> left = new ArrayList<>();
		left.add("x=a");
		left.add("x=b");
		left.add("x=c");
		ArrayList<String> right = new ArrayList<>();
		right.add("a");
		right.add("b");
		right.add("c");
		System.out.println(questionCreator.createMatchingQuestionQ(1,"match", "",left,right));
		System.out.println("################################");
	}
	
	
	
}
