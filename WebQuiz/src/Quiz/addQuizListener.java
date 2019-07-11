package Quiz;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Temp.Quiz;

/**
 * Application Lifecycle Listener implementation class addQuizListener
 *
 */
@WebListener
public class addQuizListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public addQuizListener() {
        // Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
         // Auto-generated method stub
    	HttpSession sess = se.getSession();
    	
    	Quiz quiz = new Quiz();
    	sess.setAttribute("AddQuiz", quiz);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // Auto-generated method stub
    }
	
}
