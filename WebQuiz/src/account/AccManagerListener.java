package account;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Database.DateBaseManager;
import Temp.AccountManager;

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
    	
    	ServletContext cont = sce.getServletContext();
    	try {
			DateBaseManager baseManager = new DateBaseManager();
			cont.setAttribute("baseManager", baseManager);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	AccountManager acc = new AccountManager();
    	
    	cont.setAttribute("manager", acc);
    }
	
}
