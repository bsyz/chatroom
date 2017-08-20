package onlinenumber;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class onlinenumber
 *
 */
@WebListener
public class onlinenumber implements HttpSessionListener, HttpSessionIdListener {

    /**
     * Default constructor. 
     */
    public onlinenumber() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session=arg0.getSession();
    	ServletContext application=session.getServletContext();
    	Integer i=(Integer)application.getAttribute("onlinecount");//必须先判断是否为空！！！！！！！！！！！！！！！！！！！
    	if(i==null||i<0)
    	{
    		i=new Integer(1);
    		application.setAttribute("onlinecount", i);
    	}
    	else 
    	{
    		int count=i.intValue();
    		count++;
    		i=new Integer(count);
    		application.setAttribute("onlinecount", i);
    	}
    	System.out.println("test onlinenumber="+i);
    	
    }
    

	/**
     * @see HttpSessionIdListener#sessionIdChanged(HttpSessionEvent, String)
     */
    public void sessionIdChanged(HttpSessionEvent arg0, String arg1)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session=arg0.getSession();
    	ServletContext application=session.getServletContext();
    	Integer integer=(Integer)application.getAttribute("onlinecount");
    	
    	if(integer==null)
    	{
    		integer=new Integer(0);
    		application.setAttribute("onlinecount", integer);
    	
    	
    	}
    	else
    	{
    		int count=integer.intValue();
    		count--;
        	integer=new Integer(count);
    		application.setAttribute("onlinecount", integer);

    	}
    }
	
}
