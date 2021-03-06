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
 * Servlet implementation class ProfileEditServlet
 */
@WebServlet("/login/ProfileEditServlet")
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileEditServlet() {
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
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mail = request.getParameter("mail");
		String img = request.getParameter("img");
		String status = request.getParameter("status");
		
		AccountHistoryManager accHistory = (AccountHistoryManager)cont.getAttribute("AccHistory");
		
		Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
	    
	    Connection con = d.getConnection();
		
		Account acc = accManager.getAccount(username, accHistory, con);
		
		int userID = acc.getUserID();
		
		String profileURL = "/Profile.jsp?username=" + username;
		
		if(firstname.length()>0) {
			accManager.changeFirstName(userID, firstname, con);
		}
		
		if(lastname.length()>0) {
			accManager.changeLastName(userID, lastname, con);
		}
		
		if(mail.length()>0) {
			int res = accManager.containsAccount("", mail, con);
			
			if(res==2 || res==-1) {
				profileURL += "&result=3";
				
				RequestDispatcher dispatch = request.getRequestDispatcher(profileURL);
				dispatch.forward(request, response);
				return;
			} else {
				accManager.changeMail(userID, mail, con);
			}
		}
		
		if(img.length()>0) {
			accManager.changeImg(userID, img, con);
		}
		
		if(status!=null) {
			
			if(acc.isAdmin()) {
				accManager.makeAccountUser(userID, con);
			} else {
				accManager.makeAccountAdmin(userID, con);
			}
			
		}
		
		profileURL += "&result=0";
		
		RequestDispatcher dispatch = request.getRequestDispatcher(profileURL);
		dispatch.forward(request, response);
	}

}
