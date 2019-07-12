package Quiz;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import History.AccountHistoryManager;
import Temp.Account;
import Temp.AccountManager;
import Temp.QuestionTypes;
import Temp.Quiz;

/**
 * Servlet implementation class addQuizServlet
 */
@WebServlet("/Quiz/addQuizServlet")
public class addQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Quiz quiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addQuizServlet() {
        super();
        // Auto-generated constructor stub
        quiz = new Quiz();
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
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		String quizTiTle = request.getParameter("quizTiTle");
		String quizImg = request.getParameter("quizImg");
				
		//System.out.println(category);
		
		ServletContext cont = getServletContext();
	    Object obj = cont.getAttribute("manager");
	    AccountManager accMan = (AccountManager)obj;
	    
	    Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
	    Connection con = d.getConnection();
	    
	    Object obj2 = cont.getAttribute("AccHistory");
	    AccountHistoryManager histMan = (AccountHistoryManager)obj2;
	    
		HttpSession session = request.getSession();
		
		if(category!=null) {
			if(quizTiTle.length() < 1 || quizImg.length() < 1) {
				response.sendRedirect("/WebQuizProject/index.jsp");
				return;
			}
			
			Quiz q = new Quiz();
			q.setQuizCategoryID(Integer.parseInt(category));
			
			String user = (String)session.getAttribute("username");
			Account account = accMan.getAccount(user, histMan, con);
			int Creator_id = account.getUserID();
			
			q.setCreatorID(Creator_id);
			q.setQuizName(quizTiTle);
			q.setImgUrl(quizImg);
			session.setAttribute("AddQuiz", q);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/QuestionForm.jsp");
			dispatch.forward(request, response);
			return;
		}
		
		String type = request.getParameter("type");
		if(type!=null) {
			String answersNum = request.getParameter("answersNum");
			
			boolean answerFormat = true;
			for(int i=0; i<answersNum.length(); i++) {
				if(answersNum.charAt(i) < '0' || answersNum.charAt(i) > '9') {
					answerFormat = false;
					break;
				}
			}
			
			if(answersNum.length()<1) {
				answerFormat = false;
			}
			
			//System.out.println(type);
			//System.out.println(answersNum);
			if(!answerFormat) {
				response.sendRedirect("/WebQuizProject/index.jsp");
			} else {
				QuestionTypes t = new QuestionTypes();
				if(type.equals(t.getMultiAnswerType())){
					RequestDispatcher dispatch = request.getRequestDispatcher("/MultiAnswer.jsp");
					response.addHeader("answersNum", answersNum);
					dispatch.forward(request, response);
				} else if(type.equals(t.getMultipleChoiceType())) {
					RequestDispatcher dispatch = request.getRequestDispatcher("/MultiChoice.jsp");
					response.addHeader("answersNum", answersNum);
					dispatch.forward(request, response);
				} else if(type.equals(t.getMultipleChoiceWithMultipleAnswersType())) {
					RequestDispatcher dispatch = request.getRequestDispatcher("/MultiChoiceAnsw.jsp");
					response.addHeader("answersNum", answersNum);
					dispatch.forward(request, response);
				} else if(type.equals(t.getMatchingType())) {
					RequestDispatcher dispatch = request.getRequestDispatcher("/Matching.jsp");
					response.addHeader("answersNum", answersNum);
					dispatch.forward(request, response);
				}
			}
		}
		
	}

}
