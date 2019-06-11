package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Temp.AccountManager;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/login/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		String mail = request.getParameter("mail");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		int res = acc.containsAccount(username, mail, null);
		
		if(res==0) {
			acc.addNewAccount(firstname, lastname, username, password, mail, null);
			RequestDispatcher dispatch = request.getRequestDispatcher("/daregistrirda");
			dispatch.forward(request, response);
		} else if(res==1){
			RequestDispatcher dispatch = request.getRequestDispatcher("/userdakavebulia");
			dispatch.forward(request, response);
		} else if(res==2) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/maildakavebulia");
			dispatch.forward(request, response);
		} else if(res==3) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/orivedakavebulia");
			dispatch.forward(request, response);
		}
	}

}
