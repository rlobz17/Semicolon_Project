package Temp;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Database.DateBaseManager;
import History.QuizHistoryListener;
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
    	
    	QuestionListener questionListener = new QuestionListener();
    	
    	questionListener.contextInitialized(sce);
    	
    	Object obj = cont.getAttribute("Question");
    	
    	QuestionManager questionManager = (QuestionManager)obj;
    	
    	QuizHistoryListener quizHistoryListener = new QuizHistoryListener();
    	
    	quizHistoryListener.contextInitialized(sce);
    	
    	Object obj2 = cont.getAttribute("QuizHistory");
    	
    	QuizHistoryManager quizHistory = (QuizHistoryManager)obj2;
    	
    	cont.setAttribute("QuizHistory", quizHistory);
    	    	
    	QuizManager manager = new QuizManager(questionManager, quizHistory);
    	cont.setAttribute("Quiz", manager);
    }
	
}
