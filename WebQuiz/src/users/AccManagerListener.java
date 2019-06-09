package users;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AccManagerListener
 *
 */
@WebListener
public class AccManagerListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AccManagerListener() {
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
    	AccountManager acc = new AccountManager();
    	ServletContext cont = sce.getServletContext();
    	cont.setAttribute("manager", acc);
    }
	
}
