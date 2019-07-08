package Quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Temp.Answer;
import Temp.Question;
import Temp.Quiz;

public class QuizGrader {
	
	public QuizGrade getQuizGrade(Quiz q, Map<Question, ArrayList<Answer>> userAnswers) {
		double totalScore = 0;
		ArrayList<String> eachQuestionScore = new ArrayList<String>();
		for (Question quest : userAnswers.keySet()) {
			double questionScore = questionScore(quest.getCorrectAnswers(), userAnswers.get(quest));
			eachQuestionScore.add(questionScore+"/1.00");
			totalScore += questionScore;
		}
		return new QuizGrade(totalScore, userAnswers.size(), eachQuestionScore);
	}
	
	private double questionScore(ArrayList<Answer> corAnswers, ArrayList<Answer> userAnswers) {
		int score = 0;
		int maxScore = 0;
		List<Answer> correctAnswers = new ArrayList<>(corAnswers);
		
		if(correctAnswers.size() == 1) {
			if(correctAnswers.get(0).equals(userAnswers.get(0))) {
				score = 1;
			}
			maxScore = 1;
		} else {
			maxScore = correctAnswers.size();
			for(Answer cur : userAnswers) {
				if(correctAnswers.contains(cur)) {
					score++;
					correctAnswers.remove(cur);
				}
			}
		}
		return (double)score/maxScore;
	}
	
	public QuestionGrade gradeQuestion(Question q, ArrayList<Answer> userAnswers) {
		ArrayList<String> correctAnswersString = new ArrayList<String>();
		ArrayList<Answer> correctAnswers = q.getCorrectAnswers();
		
		for (Answer cur : correctAnswers) {
			correctAnswersString.add(cur.getAnswerDetail());
		}
		
		return new QuestionGrade(questionScore(correctAnswers, userAnswers), correctAnswersString);
	}
	

}
