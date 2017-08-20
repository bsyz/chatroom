package namecheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conndb.conndb;

/**
 * Servlet implementation class namecheck
 */
@WebServlet("/namecheck")
public class namecheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public namecheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8,pageEncoding=UTF-8");
//		response.getWriter().append("from:").append("addr");
		PrintWriter out=response.getWriter();
//		out.print(name);
		HttpSession session=request.getSession(true);
		
		conndb c;
		c=new conndb();
		String addr=request.getRemoteAddr();
		String name=request.getParameter("loginname");

		int result=0;
		result=c.checknames(addr, name);
		if(result==1)
			out.print("false");
		else
			out.print("true");
		
		out.flush();
		out.close();
//		if(result>0)
//		{
////			out.println("登录成功");
//			session.setAttribute("addr", addr);
//			session.setAttribute("name", name);
//		
////			response.setHeader("refresh", "3;url=index.jsp");
////			response.sendRedirect("index.jsp");
//		}
//		else
//		{
//			out.println("该用户名已被使用，请重新输入");
//			response.setHeader("refresh", "3;url=login.html");
//		}
//		}	//}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
