package Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class StartQuizServlet
 */
@WebServlet("/Quiz/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
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
				
		String quizID = request.getParameter("quiz_id");
		String questionID = request.getParameter("question_id");
				
		String url = "/StartQuiz.jsp?id=" + quizID;
		
		url += "&question=" + questionID;
		
		HttpSession session = request.getSession();
		
		if(!questionID.equals("result")) {
			if(Integer.parseInt(questionID)==0) {
				session.setAttribute("ResultsMap", new HashMap<Integer, ArrayList<String>>());
			}
		}
		
		Object obj = session.getAttribute("ResultsMap");
		HashMap<Integer, ArrayList<String>> mp = (HashMap<Integer, ArrayList<String>>)obj;
				
		String FieldAnswersNum = request.getParameter("FieldAnswersNum");
		
		if(FieldAnswersNum!=null) {
			int n = Integer.parseInt(FieldAnswersNum);
			ArrayList<String> answers = new ArrayList<String>();
			
			for(int i=0; i<n; i++) {
				String answ = request.getParameter("AnswerField" + i);
				answers.add(answ);
				//System.out.println(answ);
			}
			
			//System.out.println(answers.size());
			
			mp.put(mp.size(), answers);
			//System.out.println(mp.size());
		}
		
		String MultiAnswersMultiNum = request.getParameter("MultiAnswersMultiNum");
		
		if(MultiAnswersMultiNum!=null) {
			int n = Integer.parseInt(MultiAnswersMultiNum);
			ArrayList<String> answers = new ArrayList<String>();
						
			for(int i=0; i<n; i++) {
				String on = request.getParameter("AnswerPossible" + i);
				
				String answ = request.getParameter("currAns" + i);
				
				if(on!=null) {
					answers.add(answ);
					//System.out.println(answ);
				}
				
			}
			
			mp.put(mp.size(), answers);
			//System.out.println(mp.size());
		}
		
		String MultiAnswersNum = request.getParameter("MultiAnswersNum");
		
		if(MultiAnswersNum!=null) {
			ArrayList<String> answers = new ArrayList<String>();
						
			String answ = request.getParameter("AnswerPossible");
			
			if(answ!=null) {
				answers.add(answ);
				//System.out.println(answ);
			}
			
			mp.put(mp.size(), answers);
			//System.out.println(mp.size());
		}
		
		
		if(questionID.equals("result")) {
			request.setAttribute("answers", mp);
		}
		
		session.setAttribute("ResultsMap", mp);
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
