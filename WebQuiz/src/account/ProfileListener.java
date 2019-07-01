package account;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Temp.AccountManager;

/**
 * Application Lifecycle Listener implementation class ProfileListener
 *
 */
@WebListener
public class ProfileListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ProfileListener() {
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
    	AccountManager acM = new AccountManager();
    	ServletContext cont = sce.getServletContext();
    	cont.setAttribute("profile", acM);
    }
	
}
