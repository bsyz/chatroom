package webfilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/**
 * Servlet Filter implementation class webfilter
 */
@WebFilter("/webfilter")
public class webfilter implements Filter {

    /**
     * Default constructor. 
     */
    public webfilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request2=(HttpServletRequest)request;
		HttpServletResponse response2=(HttpServletResponse)response;
		HttpSession session=request2.getSession();
		String url=request2.getRequestURI();
		if(session.getAttribute("name")==null)
		{
			if(url.equals("/chating/login.html")||url.equals("/chating/verifyimg")||url.equals("/chating/usenamelogin")||url.equals("/chating/namecheck")||url.equals("/chating/imgs/background2.gif"))
			{
				chain.doFilter(request, response);
			}
			else
			{
				response2.sendRedirect("login.html");
			}
			
		}else
		// pass the request along the filter chain
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
