package login;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Temp.AccountManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		ServletContext cont = getServletContext();
		Object obj = cont.getAttribute("manager");
		AccountManager acc = (AccountManager)obj;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
		Statement stm = null;
		try {
			stm = d.getConnection().createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		int result = acc.doLogin(username, password, stm);
		
		if(result==0) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/welcome.jsp");
			dispatch.forward(request, response);
		} else if(result==1){
			RequestDispatcher dispatch = request.getRequestDispatcher("/login_error1.jsp");
			dispatch.forward(request, response);
		} else if(result==2) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/login_error2.jsp");
			dispatch.forward(request, response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/login_error3.jsp");
			dispatch.forward(request, response);
		}
	}

}
