package Temp;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Database.DateBaseManager;
import History.QuizHistoryManager;

/**
 * Application Lifecycle Listener implementation class QuizListener
 *
 */
@WebListener
public class QuizListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public QuizListener() {
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
    	
    	QuestionManager questionManager = new QuestionManager();
    	
    	cont.setAttribute("Question", questionManager);
    	
    	QuizHistoryManager quizM = new QuizHistoryManager();
    	
    	cont.setAttribute("QuizHistory", quizM);
    	    	
    	QuizManager manager = new QuizManager(questionManager, quizM);
    	cont.setAttribute("Quiz", manager);
    }
	
}
