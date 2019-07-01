package search;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class searchListener
 *
 */
@WebListener
public class searchListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public searchListener() {
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
    	cont.setAttribute("searchListener", null);
    }
	
}
