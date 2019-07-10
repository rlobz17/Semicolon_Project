package Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartQuizServlet
 */
@WebServlet("/Quiz/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// key: questionID, value: user's answers
	private HashMap<Integer, ArrayList<String>> mp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
        super();
        // Auto-generated constructor stub
        
        mp = new HashMap<Integer, ArrayList<String>>();
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
				
		String quizID = request.getParameter("quiz_id");
		String questionID = request.getParameter("question_id");
				
		String url = "/StartQuiz.jsp?id=" + quizID;
		
		url += "&question=" + questionID;
		
		String answersNum = request.getParameter("answersNum");
		
		if(answersNum!=null) {
			int n = Integer.parseInt(answersNum);
			ArrayList<String> answers = new ArrayList<String>();
			
			for(int i=0; i<n; i++) {
				String answ = request.getParameter("MultiAnswerField" + i);
				answers.add(answ);
				//System.out.println(answ);
			}
			
			int k = mp.size();
			mp.put(k, answers);
		}
		
		if(questionID.equals("result")) {
			request.setAttribute("answers", mp);
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
