package Quiz;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Temp.QuestionManager;
import Temp.QuestionTypes;

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
	    	
	    	String butt = request.getParameter("butt1");
	    	
	    	if(butt==null) {
	    		// დასრულება და ქვიზის ბაზაში დამატება
	    	} else {
	    		// კიდევ დამატება
	    	}
	    	
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
	    	
	    	String butt = request.getParameter("butt1");
	    	
	    	if(butt==null) {
	    		// დასრულება და ქვიზის ბაზაში დამატება
	    	} else {
	    		// კიდევ დამატება
	    	}
	    	
	    } else if(type.equals(qTypes.getMultipleChoiceWithMultipleAnswersType())) {
	    	
	    } else if(type.equals(qTypes.getMatchingType())) {
	    	
	    }
	    
	}

}
