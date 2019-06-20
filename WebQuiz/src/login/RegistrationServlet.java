package login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DataBaseINFO;
import Temp.AccountManager;
import account.PasswordManager;

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
		
		for(int i=0; i<username.length(); i++) {
			char ch = username.charAt(i);
			if(!(ch>='a' && ch<='z') && !(ch>='A' && ch<='Z')
					&& !(ch>='0' && ch<='9') && ch!=' ' && ch!='.' && ch!='-') {
				RequestDispatcher dispatch = request.getRequestDispatcher("/tryagain.jsp");
				dispatch.forward(request, response);
				return;
			}
		}
		
		if(username.length() < 1 || password.length() < 1 || mail.length() <1) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/tryagain.jsp");
			dispatch.forward(request, response);
			return;
		}
		
		PasswordManager m = new PasswordManager();
		String hashPassword = null;
		
		try {
			hashPassword = m.hashPassword(password);
		} catch (NoSuchAlgorithmException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}
		
		Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
		Statement stm = null;
		try {
			stm = d.getConnection().createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		int res = acc.containsAccount(username, mail, stm);
		//int res = 3; // for test
		
		if(res==0) {
			acc.addNewAccount(firstname, lastname, username, hashPassword, mail, stm);
			RequestDispatcher dispatch = request.getRequestDispatcher("/registration.jsp?info_id=0");
			dispatch.forward(request, response);
		} else if(res==1){
			RequestDispatcher dispatch = request.getRequestDispatcher("/registration.jsp?info_id=1");
			dispatch.forward(request, response);
		} else if(res==2) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/registration.jsp?info_id=2");
			dispatch.forward(request, response);
		} else if(res==3) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/registration.jsp?info_id=3");
			dispatch.forward(request, response);
		}
	}

}
