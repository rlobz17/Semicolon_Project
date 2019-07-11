package Quiz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
	}

}
