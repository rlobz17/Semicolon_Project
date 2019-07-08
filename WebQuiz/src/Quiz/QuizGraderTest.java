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
	
	@Before
	public void bef() {
		qg = new QuizGrader();
	}
	
	@Test
	public void testQuestionGrader() {
		ArrayList<Answer> cA = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "a");
		Answer ans2 = new Answer(0, 0, 0, "b");
		cA.add(ans1);
		cA.add(ans2);
		
		Question q = new Question(0, 0, null, null, null, cA, null);
		
		Answer an1 = new Answer(0, 0, 0, "a");
		Answer an2 = new Answer(0, 0, 0, "c");
		ArrayList<Answer> uA = new ArrayList<Answer>();
		uA.add(an1);
		uA.add(an2);
		
		QuestionGrade qGrade = qg.gradeQuestion(q, uA);
		assertEquals("0.5/1.00", qGrade.getScore());
		ArrayList<String> arr = qGrade.getCorrectAnswers();
		assertEquals(arr.size(), cA.size());
	}
	
//	@Test
//	public void testQuestionGrader2() {
//
//		ArrayList<Answer> cA2 = new ArrayList<Answer>();
//		Answer ans11 = new Answer(0, 0, 0, "3");
//		Answer ans22 = new Answer(0, 0, 0, "5");
//		Answer ans23 = new Answer(0, 0, 0, "4");
//		Answer ans24 = new Answer(0, 0, 0, "8");
//		Answer ans25 = new Answer(0, 0, 0, "9");
//		cA2.add(ans11);
//		cA2.add(ans22);
//		cA2.add(ans23);
//		cA2.add(ans24);
//		cA2.add(ans25);
//		
//		
//		Question q2 = new Question(0, 0, null, null, null, cA2, null);
//		
//		Answer an11 = new Answer(0, 0, 0, "2");
//		Answer an22 = new Answer(0, 0, 0, "3");
//		Answer an23 = new Answer(0, 0, 0, "5");
//		Answer an24 = new Answer(0, 0, 0, "8");
//		Answer an25 = new Answer(0, 0, 0, "10");
//		ArrayList<Answer> uA2 = new ArrayList<Answer>();
//		uA1.add(an11);
//		uA1.add(an22);
//		uA1.add(an23);
//		uA1.add(an24);
//		uA1.add(an25);
//		
//		QuestionGrade qGrade = qg.gradeQuestion(q, uA);
//		assertEquals("0.5/1.00", qGrade.getScore());
//		ArrayList<String> arr = qGrade.getCorrectAnswers();
//		assertEquals(arr.size(), cA.size());
//	}
//	
	
	
	@Test
	public void testQuizGrader() {
		ArrayList<Answer> cA1 = new ArrayList<Answer>();
		Answer ans1 = new Answer(0, 0, 0, "a");
		Answer ans2 = new Answer(0, 0, 0, "b");
		cA1.add(ans1);
		cA1.add(ans2);
		
		Question q1 = new Question(0, 0, null, null, null, cA1, null);
		
		Answer an1 = new Answer(0, 0, 0, "a");
		Answer an2 = new Answer(0, 0, 0, "c");
		ArrayList<Answer> uA1 = new ArrayList<Answer>();
		uA1.add(an1);
		uA1.add(an2);
		
		/////////////////////////////////////////
		
		ArrayList<Answer> cA2 = new ArrayList<Answer>();
		Answer ans11 = new Answer(0, 0, 0, "3");
		Answer ans22 = new Answer(0, 0, 0, "5");
		Answer ans23 = new Answer(0, 0, 0, "4");
		Answer ans24 = new Answer(0, 0, 0, "8");
		Answer ans25 = new Answer(0, 0, 0, "9");
		cA2.add(ans11);
		cA2.add(ans22);
		cA2.add(ans23);
		cA2.add(ans24);
		cA2.add(ans25);
		
		
		Question q2 = new Question(0, 0, null, null, null, cA2, null);
		
		Answer an11 = new Answer(0, 0, 0, "2");
		Answer an22 = new Answer(0, 0, 0, "3");
		Answer an23 = new Answer(0, 0, 0, "5");
		Answer an24 = new Answer(0, 0, 0, "8");
		Answer an25 = new Answer(0, 0, 0, "10");
		ArrayList<Answer> uA2 = new ArrayList<Answer>();
		uA2.add(an11);
		uA2.add(an22);
		uA2.add(an23);
		uA2.add(an24);
		uA2.add(an25);
		
		Map<Question, ArrayList<Answer>> userAnswers = new HashMap<Question, ArrayList<Answer>>();
		userAnswers.put(q1, uA1);
		userAnswers.put(q2, uA2);
		
		ArrayList<Question> qs = new ArrayList<Question>();
		qs.add(q1);
		qs.add(q2);
		
		Quiz q = new Quiz(0, null, qs, 0, null, null, 0, 0, null);
		
		QuizGrade qGrade = qg.getQuizGrade(q, userAnswers);
		assertEquals("1.1", qGrade.getUserScore());
		
		assertEquals("55%", qGrade.getPrecentage());
		
		ArrayList<String> eachQS = qGrade.getEachQuestionScore();
		assertEquals(2, eachQS.size());
		
		assertEquals("0.5/1.00", eachQS.get(0));
		assertEquals("0.6/1.00", eachQS.get(1));
	}

}
