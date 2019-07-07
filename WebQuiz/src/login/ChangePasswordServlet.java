package login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import History.AccountHistoryManager;
import Temp.Account;
import Temp.AccountManager;
import account.PasswordManager;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/login/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		AccountManager accManager = (AccountManager)obj;
		
		String username = request.getParameter("user");
		String oldPassword = request.getParameter("old_password");
		String password = request.getParameter("new_password");
		String repeat = request.getParameter("repeatPass");
		
		AccountHistoryManager accHistory = (AccountHistoryManager)cont.getAttribute("AccHistory");
		
		Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
	    
	    Connection con = d.getConnection();
		
		Account acc = accManager.getAccount(username, accHistory, con);
		
		int userID = acc.getUserID();
		
		String profileURL = "/Profile.jsp?username=" + username;
		
		PasswordManager m = new PasswordManager();
		String hashPassword = null;
		
		try {
			hashPassword = m.hashPassword(oldPassword);
		} catch (NoSuchAlgorithmException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(accManager.doLogin(username, hashPassword, con)==0) {
			if(password.equals(repeat)) {
				
				if(password.length()<6) {
					profileURL += "&result=1";
					
					RequestDispatcher dispatch = request.getRequestDispatcher(profileURL);
					dispatch.forward(request, response);
				} else {
					String pass = null;
					
					try {
						pass = m.hashPassword(password);
					} catch (NoSuchAlgorithmException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}
					
					accManager.changePassword(userID, pass, con);
					
					profileURL += "&result=0";
					
					RequestDispatcher dispatch = request.getRequestDispatcher(profileURL);
					dispatch.forward(request, response);
				}
				
			} else {
				profileURL += "&result=1";
				
				RequestDispatcher dispatch = request.getRequestDispatcher(profileURL);
				dispatch.forward(request, response);
			}
		} else{
			profileURL += "&result=2";
			
			RequestDispatcher dispatch = request.getRequestDispatcher(profileURL);
			dispatch.forward(request, response);
		}
		
		
	}

}
