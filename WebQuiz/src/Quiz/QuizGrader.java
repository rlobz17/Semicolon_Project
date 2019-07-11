package Quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Temp.Answer;
import Temp.Question;
import Temp.QuestionTypes;
import Temp.Quiz;

public class QuizGrader {
	
	public QuizGrade getQuizGrade(Quiz q, Map<Integer, ArrayList<String>> userAnswers) {
		double totalScore = 0;
		ArrayList<String> eachQuestionScore = new ArrayList<String>();
		for (Integer id : userAnswers.keySet()) {
			Question quest = q.getQuestions().get(id);
			ArrayList<Answer> answ = quest.getCorrectAnswers();
			ArrayList<String> corrAnsws = new ArrayList<String>();
			for(Answer a : answ) {
				corrAnsws.add(a.getAnswerDetail());
			}
			
			QuestionTypes t = new QuestionTypes();
			
			int k = quest.getQuestionType();
			String type = t.getMultiAnswerType();
			
			if( k == 2) {
				type = t.getMultipleChoiceType();
			} else if(k == 3) {
				type = t.getMultipleChoiceWithMultipleAnswersType();
			} else if (k == 4) {
				type = t.getMatchingType();
			}
			
			int size = -1;
			if(quest.getPossibleAnswers() != null) {
				size = quest.getPossibleAnswers().size();
			}
			
			double questionScore = questionScore(size,type, corrAnsws, userAnswers.get(id), quest.getQuestionAnswerOrder());
			String userScore = questionScore+"";
			if(userScore.equals("1.0")) {
				userScore = "1.00";
			} else if (userScore.length()>4) {
				userScore = userScore.substring(0,4);
			}
			eachQuestionScore.add(userScore+"/1.00");
			totalScore += questionScore;
		}
		return new QuizGrade(totalScore, userAnswers.size(), eachQuestionScore);
	}
	
	
	
	private double questionScore(int allAnswers,String questionTask,ArrayList<String> corAnswers, ArrayList<String> userAnswers, boolean questionAnswerOrder) {
		if(userAnswers.isEmpty()) return 0;
	// no answers
		
		if(questionTask.equals("Multiple_Choice_type")) {
			 gradeMultipleChoice(corAnswers, userAnswers);
		} else if (questionTask.equals( "Multiple_Choice_With_Multiple_Answers_type")) {
			return gradeMultipleChoiceAndAnswerQ(allAnswers,corAnswers, userAnswers);
		} 
		return gradeFillInAnswerAndMatching(corAnswers, userAnswers, questionAnswerOrder);
	}
	
	private double gradeFillInAnswerAndMatching(ArrayList<String> corAnswers, ArrayList<String> userAnswers, boolean order) {
		int score = 0;
		int maxScore = 0;
		List<String> correctAnswers = new ArrayList<>(corAnswers);
		
		maxScore = userAnswers.size();
		if(order) {
			for (int i = 0; i < userAnswers.size(); i++) {
				if(userAnswers.get(i).length() >0) {
					if(userAnswers.get(i).equals(correctAnswers.get(i))) score++;
				}
			}				
		} else {
			for(String cur : userAnswers) {
				if(cur.length() > 0 ) {
					if(correctAnswers.contains(cur)) {
						score++;
						correctAnswers.remove(cur);
					}
				}
			}
		}
		
		return (double)score/maxScore;
	}
	
	private double gradeMultipleChoice(ArrayList<String> corAnswers, ArrayList<String> userAnswers) {
		if(corAnswers.get(0).equals(userAnswers.get(0))) {
			return 1;
		}
		return 0;
	}
	
	private double gradeMultipleChoiceAndAnswerQ(int allAnswers,ArrayList<String> corAnswers, ArrayList<String> userAnswers) {
		int score = 0;
		int maxScore = 0;
		int mistake = 0;
		List<String> correctAnswers = new ArrayList<>(corAnswers);
		
		if(correctAnswers.size() == 1) {
			if(correctAnswers.get(0).equals(userAnswers.get(0))) {
				score = 1;
			}
			maxScore = 1;
		} else {
			maxScore = correctAnswers.size();
			
			for(String cur : userAnswers) {
				if(correctAnswers.contains(cur)) {
					score++;
					correctAnswers.remove(cur);
				}else {
					mistake++;
				}
			}
		}
		
		return (double)score/maxScore * (double)(allAnswers-maxScore-mistake)/(allAnswers-maxScore) ;
	}
	
	public QuestionGrade gradeQuestion(Question q, ArrayList<String> userAnswers) {
		ArrayList<String> correctAnswersString = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<Answer> correctAnswers = q.getCorrectAnswers();
		
		for (Answer cur : correctAnswers) {
			correctAnswersString.add(cur.getAnswerDetail());
			temp.add(cur.getAnswerDetail());
		}
		
		QuestionTypes t = new QuestionTypes();
		
		int k = q.getQuestionType();
		String type = t.getMultiAnswerType();
		
		if( k == 2) {
			type = t.getMultipleChoiceType();
		} else if(k == 3) {
			type = t.getMultipleChoiceWithMultipleAnswersType();
		} else if (k == 4) {
			type = t.getMatchingType();
		}
		

		return new QuestionGrade(questionScore(q.getPossibleAnswers().size(),type,temp, userAnswers,q.getQuestionAnswerOrder()), correctAnswersString);
	}
	

}
