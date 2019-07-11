package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ResultListener
 *
 */
@WebListener
public class ResultListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public ResultListener() {
        // Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // Auto-generated method stub
    	HttpSession sess = se.getSession();
    	    	
    	HashMap<Integer, ArrayList<String>> mp = new HashMap<Integer, ArrayList<String>>();
    	sess.setAttribute("ResultsMap", mp);
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // Auto-generated method stub
    	
    }
	
}
