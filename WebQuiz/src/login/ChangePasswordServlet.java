package login;

import java.io.IOException;
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
		
		if(accManager.doLogin(username, oldPassword, con)==0) {
			if(password.equals(repeat)) {
				
			}
		} else{
			
		}
		
		
	}

}
