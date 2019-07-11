package Quiz;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Temp.Answer;
import Temp.Question;
import Temp.Quiz;

public class QuizGraderTest {
	private QuizGrader qg;
	
//	 (1,"Multi_Answer_type", "Fill in with answers"),
//		(2,"Multiple_Choice_type", "Select one correct answer"),
//	    (3,"Multiple_Choice_With_Multiple_Answers_type", "Select Correct answers" ),
//	    (4,"Mathcing_type" , "Match correct pairs")
	
	@Before
	public void bef() {
		qg = new QuizGrader();
	}
	
	@Test
	public void testMultpChoice() {
		ArrayList<Answer> 	questions = new ArrayList<Answer>();
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "a");
		cA.add(ans1);
		
		Question q = new Question(0, 0, null, "Multiple_Choice_type", null, cA, questions, false);
		
		
	
		ArrayList<String> uA = new ArrayList<String>();
		uA.add("a");
		
		QuestionGrade qGrade = qg.gradeQuestion(q, uA);
		assertEquals("1.00/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("b");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.0/1.00", qGrade.getScore());
		
		
	}
	
	@Test
	public void testMatching() {
		ArrayList<Answer> 	questions = new ArrayList<Answer>();
		
		
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "5");
		Answer ans2 = new Answer(0, 0, 0, "4");
		Answer ans3 = new Answer(0, 0, 0, "1");
		cA.add(ans1);
		cA.add(ans2);
		cA.add(ans3);
		
		Question q = new Question(0, 0, null, "Mathcing_type", null, cA, questions, false);
		

		ArrayList<String> uA = new ArrayList<String>();
		uA.add("5");
		uA.add("4");
		uA.add("1");
		
		
		QuestionGrade qGrade = qg.gradeQuestion(q, uA);
		assertEquals("1.00/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("5");
		uA.add("1");
		uA.add("4");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("1.00/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("3");
		uA.add("1");
		uA.add("4");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.66/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("3");
		uA.add("1");
		uA.add("9");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.33/1.00", qGrade.getScore());
		
		
		////!!!!!!!!!!!! order is set to true
		q = new Question(0, 0, null, "Mathcing_type", null, cA, questions, true);
		
		uA = new ArrayList<String>();
		uA.add("5");
		uA.add("4");
		uA.add("1");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("1.00/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("5");
		uA.add("8");
		uA.add("6");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.33/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("1");
		uA.add("5");
		uA.add("4");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.0/1.00", qGrade.getScore());
		
	}
	
	@Test
	public void testMultiAnswer() {
		ArrayList<Answer> 	questions = new ArrayList<Answer>();
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "a");
		Answer ans2 = new Answer(0, 0, 0, "b");
		Answer ans3 = new Answer(0, 0, 0, "c");
		cA.add(ans1);
		cA.add(ans2);
		cA.add(ans3);
		
		Question q = new Question(0, 0, null, "Multi_Answer_type", null, cA, questions, false);
		

		ArrayList<String> uA = new ArrayList<String>();
		uA.add("a");
		uA.add("b");
		uA.add("c");
		
		
		QuestionGrade qGrade = qg.gradeQuestion(q, uA);
		assertEquals("1.00/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("a");
		uA.add("v");
		uA.add("b");
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.66/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("a");
		uA.add("v");
		uA.add("n");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.33/1.00", qGrade.getScore());
		
		/////!!!! order is set to true
		
		q = new Question(0, 0, null, "Multi_Answer_type", null, cA, questions, true);
		
		uA = new ArrayList<String>();
		uA.add("a");
		uA.add("v");
		uA.add("b");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.33/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("k");
		uA.add("v");
		uA.add("b");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.0/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("v");
		uA.add("b");
		uA.add("a");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.33/1.00", qGrade.getScore());
		
	}
	
	@Test
	public void testMultipleChoiceMultipleAnswer() {
		ArrayList<Answer> 	questions = new ArrayList<Answer>();
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "a");
		Answer ans2 = new Answer(0, 0, 0, "b");
		Answer ans3 = new Answer(0, 0, 0, "c");
		cA.add(ans1);
		cA.add(ans2);
		cA.add(ans3);
		
		for (int i = 0; i < 7; i++) {
			questions.add(new Answer(i, 0, 0, null));
		}
		
		Question q = new Question(0, 0, null, "Multiple_Choice_With_Multiple_Answers_type", null, cA, questions, false);
		
		ArrayList<String> uA = new ArrayList<String>();
		uA.add("a");
		uA.add("b");
		uA.add("c");
		
		
		QuestionGrade qGrade = qg.gradeQuestion(q, uA);
		assertEquals("1.00/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("v");
		uA.add("k");
		uA.add("a");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.33/1.00", qGrade.getScore());
		
		uA = new ArrayList<String>();
		uA.add("v");
		uA.add("b");
		uA.add("a");
		
		qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.66/1.00", qGrade.getScore());
		
	}
	
	

	
	
	@Test
	public void testQuizGrader() {
		//matching
		ArrayList<Answer> 	possibleAnswers = new ArrayList<Answer>();
		
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "5");
		Answer ans2 = new Answer(0, 0, 0, "4");
		Answer ans3 = new Answer(0, 0, 0, "1");
		cA.add(ans1);
		cA.add(ans2);
		cA.add(ans3);
		
		Question matching = new Question(0, 0, null, "Mathcing_type", null, cA, possibleAnswers, false);
		

		ArrayList<String> uA = new ArrayList<String>();
		uA.add("5");
		uA.add("4");
		uA.add("1");
		
		
		
		/////////////////////////////////////////
		
		//mutliple
		

		ArrayList<Answer> caMult = new ArrayList<Answer>();
		Answer mult1 = new Answer(0, 0, 0, "a");
		caMult.add(mult1);
		
		Question multiple = new Question(0, 0, null, "Multiple_Choice_type", null, caMult, possibleAnswers, false);
		
		
	
		ArrayList<String> muAnswers = new ArrayList<String>();
		muAnswers.add("a");
		
		///////////
		
		//multi answer
		ArrayList<Answer> multi = new ArrayList<Answer>();
		Answer multi1 = new Answer(0, 0, 0, "a");
		Answer multi2 = new Answer(0, 0, 0, "b");
		Answer multi3 = new Answer(0, 0, 0, "c");
		multi.add(multi1);
		multi.add(multi2);
		multi.add(multi3);
		
		Question mult = new Question(0, 0, null, "Multi_Answer_type", null, multi, possibleAnswers, false);
		

		ArrayList<String> multiAns = new ArrayList<String>();
		multiAns.add("a");
		multiAns.add("b");
		multiAns.add("c");
				
		//////////////
		
		
		ArrayList<Answer> 	questions = new ArrayList<Answer>();
		ArrayList<Answer> choice = new ArrayList<Answer>();
		Answer cho1 = new Answer(0, 0, 0, "a");
		Answer cho2 = new Answer(0, 0, 0, "b");
		Answer cho3 = new Answer(0, 0, 0, "c");
		choice.add(cho1);
		choice.add(cho2);
		choice.add(cho3);
		
		for (int i = 0; i < 7; i++) {
			questions.add(new Answer(i, 0, 0, null));
		}
		
		Question choi = new Question(0, 0, null, "Multiple_Choice_With_Multiple_Answers_type", null, choice, questions, false);
		
		ArrayList<String> choA = new ArrayList<String>();
		choA.add("a");
		choA.add("b");
		choA.add("c");
		//
		
		
		ArrayList<Question> qs = new ArrayList<Question>();
		qs.add(matching);
		qs.add(multiple);
		qs.add(mult);
		qs.add(choi);
		
		Quiz q = new Quiz(0, null, qs, 0, null, null, null, 0, 0, null);
		
		Map<Integer, ArrayList<String>> userAnswers = new HashMap<Integer, ArrayList<String>>();
		userAnswers.put(new Integer(0), uA);
		userAnswers.put(new Integer(1), muAnswers);
		userAnswers.put(new Integer(2), multiAns);
		userAnswers.put(new Integer(3), choA);
//		
		QuizGrade qGrade = qg.getQuizGrade(q, userAnswers);
		assertEquals("4.0", qGrade.getUserScore());
//		
		assertEquals("100%", qGrade.getPrecentage());
		
		ArrayList<String> eachQS = qGrade.getEachQuestionScore();
		assertEquals(4, eachQS.size());
	
		assertEquals("1.00/1.00", eachQS.get(0));
		assertEquals("1.00/1.00", eachQS.get(1));
	}
	
	
	@Test
	public void tesQuizGrader2() {
		//matching
		ArrayList<Answer> 	possibleAnswers = new ArrayList<Answer>();
		
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "5");
		Answer ans2 = new Answer(0, 0, 0, "4");
		Answer ans3 = new Answer(0, 0, 0, "1");
		cA.add(ans1);
		cA.add(ans2);
		cA.add(ans3);
		
		Question matching = new Question(0, 0, null, "Mathcing_type", null, cA, possibleAnswers, false);
		

		ArrayList<String> uA = new ArrayList<String>();
		uA.add("5");
		uA.add("6");
		uA.add("1");
		
		
		// 2/3
		/////////////////////////////////////////
		
		//mutliple
		

		ArrayList<Answer> caMult = new ArrayList<Answer>();
		Answer mult1 = new Answer(0, 0, 0, "a");
		caMult.add(mult1);
		
		Question multiple = new Question(0, 0, null, "Multiple_Choice_type", null, caMult, possibleAnswers, false);
		
		
	
		ArrayList<String> muAnswers = new ArrayList<String>();
		muAnswers.add("a");
		
		//1
		///////////
		
		//multi answer
		ArrayList<Answer> multi = new ArrayList<Answer>();
		Answer multi1 = new Answer(0, 0, 0, "a");
		Answer multi2 = new Answer(0, 0, 0, "b");
		Answer multi3 = new Answer(0, 0, 0, "c");
		multi.add(multi1);
		multi.add(multi2);
		multi.add(multi3);
		
		Question mult = new Question(0, 0, null, "Multi_Answer_type", null, multi, possibleAnswers, true);
		

		ArrayList<String> multiAns = new ArrayList<String>();
		multiAns.add("a");
		multiAns.add("c");
		multiAns.add("b");
				
		// 1/3
		//////////////
		
		
		ArrayList<Answer> 	questions = new ArrayList<Answer>();
		ArrayList<Answer> choice = new ArrayList<Answer>();
		Answer cho1 = new Answer(0, 0, 0, "a");
		Answer cho2 = new Answer(0, 0, 0, "b");
		Answer cho3 = new Answer(0, 0, 0, "c");
		choice.add(cho1);
		choice.add(cho2);
		choice.add(cho3);
		
		for (int i = 0; i < 7; i++) {
			questions.add(new Answer(i, 0, 0, null));
		}
		
		Question choi = new Question(0, 0, null, "Multiple_Choice_With_Multiple_Answers_type", null, choice, questions, false);
		
		ArrayList<String> choA = new ArrayList<String>();
		choA.add("a");
		choA.add("e");
		choA.add("h");
		
		
		
		ArrayList<Question> qs = new ArrayList<Question>();
		qs.add(matching);
		qs.add(multiple);
		qs.add(mult);
		qs.add(choi);
		
		
		//1/6
		/////////////////////////////////////
		
		Quiz q = new Quiz(0, null, qs, 0, null, null, null, 0, 0, null);
		
		Map<Integer, ArrayList<String>> userAnswers = new HashMap<Integer, ArrayList<String>>();
		userAnswers.put(new Integer(0), uA);
		userAnswers.put(new Integer(1), muAnswers);
		userAnswers.put(new Integer(2), multiAns);
		userAnswers.put(new Integer(3), choA);
//		
		QuizGrade qGrade = qg.getQuizGrade(q, userAnswers);
		assertEquals("2.33", qGrade.getUserScore());
//		
		assertEquals("58%", qGrade.getPrecentage());
		
		ArrayList<String> eachQS = qGrade.getEachQuestionScore();
		assertEquals(4, eachQS.size());
	
		assertEquals("0.66/1.00", eachQS.get(0));
		assertEquals("1.00/1.00", eachQS.get(1));
	}


}
