package Quiz;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Question.QuestionCreator;
import Temp.Answer;
import Temp.Question;
import Temp.QuestionManager;
import Temp.QuestionTypes;
import Temp.Quiz;
import Temp.QuizManager;

/**
 * Servlet implementation class PassQuestionServlet
 */
@WebServlet("/Quiz/PassQuestionServlet")
public class PassQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassQuestionServlet() {
        super();
        // Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Auto-generated method stub
		String type = request.getParameter("QuestionType");
		
		HttpSession session = request.getSession();
		
		ServletContext cont = getServletContext();
	    Object obj = cont.getAttribute("Question");

	    QuestionManager questionManager = (QuestionManager)obj;
	    
	    Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
	    Connection con = d.getConnection();
	    
	    int typeID = questionManager.getQuestionType(type, con);
	    
	    QuestionTypes qTypes = new QuestionTypes();
	    
	    String QuestionDetail = request.getParameter("QuestionDetail");
	    String imgURL = request.getParameter("imgURL");
	    
	    String AnswerNum = request.getParameter("AnswerNum");
	    int answersSize = Integer.parseInt(AnswerNum);
	    
	    if(type.equals(qTypes.getMultiAnswerType())) {
	    	ArrayList<String> answers = new ArrayList<String>();
	    	boolean order = false;
	    	
	    	for(int i=0; i<answersSize; i++) {
	    		String MultiAnswer = request.getParameter("MultiAnswer" + i);
	    		answers.add(MultiAnswer);
	    		//System.out.println(MultiAnswer);
	    	}
	    	
	    	String orderInput = request.getParameter("order");
	    	if(orderInput!=null) {
	    		order = true;
	    	}
	    	
	    	//System.out.println(order);
	    	
	    	QuestionCreator creator = new QuestionCreator();
	    	Question question = creator.createMultiAnswerQ(typeID, QuestionDetail, imgURL, answers, order);
	    	
	    	Quiz quiz = (Quiz)session.getAttribute("AddQuiz");
	    	
	    	quiz.addQuestion(question);
	    	
	    	session.setAttribute("AddQuiz", quiz);
	    		    	
	    } else if(type.equals(qTypes.getMultipleChoiceType())) {
	    	ArrayList<String> answers = new ArrayList<String>();
	    	
	    	for(int i=0; i<answersSize; i++) {
	    		String PossibleAnswer = request.getParameter("PossibleAnswer" + i);
	    		answers.add(PossibleAnswer);
	    		//System.out.println(PossibleAnswer);
	    	}
	    	
	    	String correct = request.getParameter("correctAnswer");
	    	
	    	int correctIdx = Integer.parseInt(correct);
	    	correct = answers.get(correctIdx);
	    	
	    	QuestionCreator creator = new QuestionCreator();
	    	Question question = creator.createMultipleChoiceQ(typeID, QuestionDetail, imgURL, answers, correct);
	    	
	    	Quiz quiz = (Quiz)session.getAttribute("AddQuiz");
	    	
	    	quiz.addQuestion(question);
	    	
	    	session.setAttribute("AddQuiz", quiz);
	    		    	
	    } else if(type.equals(qTypes.getMultipleChoiceWithMultipleAnswersType())) {
	    	ArrayList<String> PossibleAnswers = new ArrayList<String>();
	    	ArrayList<String> correctAnswers = new ArrayList<String>();
	    	
	    	for(int i=0; i<answersSize; i++) {
	    		String PossibleAnswer = request.getParameter("PossibleAnswer" + i);
	    		PossibleAnswers.add(PossibleAnswer);
	    		//System.out.println(PossibleAnswer);
	    		
	    		String correctAnswer = request.getParameter("AnswerCheck" + i);
	    		if(correctAnswer!=null) {
	    			correctAnswers.add(PossibleAnswer);
	    			//System.out.println("correct: "+PossibleAnswer);
	    		}
	    	}
	    	
	    	QuestionCreator creator = new QuestionCreator();
	    	Question question = creator.createMultipleChoiceAndAnswerQ(typeID, QuestionDetail, imgURL, PossibleAnswers, correctAnswers);
	    	
	    	Quiz quiz = (Quiz)session.getAttribute("AddQuiz");
	    	
	    	quiz.addQuestion(question);
	    	
	    	session.setAttribute("AddQuiz", quiz);
	    		    	
	    } else if(type.equals(qTypes.getMatchingType())) {
	    	ArrayList<String> left = new ArrayList<String>();
	    	ArrayList<String> right = new ArrayList<String>();
	    		    	
	    	for(int i=0; i<answersSize; i++) {
	    		String LeftAnswer = request.getParameter("LeftAnswer" + i);
	    		String RightAnswer = request.getParameter("RightAnswer" + i);
	    		
	    		left.add(LeftAnswer);
	    		right.add(RightAnswer);
	    		
	    	}

	    	QuestionCreator creator = new QuestionCreator();
	    	Question question = creator.createMatchingQuestionQ(typeID, QuestionDetail, imgURL, left, right);
	    	
	    	Quiz quiz = (Quiz)session.getAttribute("AddQuiz");
	    	
	    	quiz.addQuestion(question);
	    	
	    	session.setAttribute("AddQuiz", quiz);
	    }
	    
	    String butt = request.getParameter("butt1");
    	
    	if(butt==null) {
    		// დასრულება და ქვიზის ბაზაში დამატება
    		Quiz quiz = (Quiz)session.getAttribute("AddQuiz");
    		QuizManager qManager = (QuizManager)cont.getAttribute("Quiz");
    		
    		System.out.println(quiz.toString());
    		
    		qManager.addQuiz(quiz, con);
    	} else {
    		// კიდევ დამატება
    		RequestDispatcher dispatch = request.getRequestDispatcher("/QuestionForm.jsp");
			dispatch.forward(request, response);
    	}
	    
	}

}
