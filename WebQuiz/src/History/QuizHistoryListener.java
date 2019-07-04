package History;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Database.DateBaseManager;

/**
 * Application Lifecycle Listener implementation class QuizHistoryListener
 *
 */
@WebListener
public class QuizHistoryListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public QuizHistoryListener() {
        // Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // Auto-generated method stub
    	ServletContext cont = sce.getServletContext();
    	
    	try {
			DateBaseManager baseManager = new DateBaseManager();
			cont.setAttribute("baseManager", baseManager);
		} catch (ClassNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    	QuizHistoryManager quizM = new QuizHistoryManager();
    	
    	cont.setAttribute("QuizHistory", quizM);
    }
	
}
