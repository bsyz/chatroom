package imgdownload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conndb.conndb;

/**
 * Servlet implementation class imgdownload
 */
@WebServlet("/imgdownload")
public class imgdownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imgdownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("img/jpeg");
		response.addHeader("Cache_Control", "no-cache");
		response.addHeader("Pragma", "no-cache");
		response.addDateHeader("Expires", 0);
		String user_name="";
//		response.setHeader("content-disposition", "attachmebnt;filename="+filename );
		conndb c=new conndb();

		user_name=request.getParameter("user_name");
		
		System.out.println("用户名称是：");
		System.out.println(user_name);
		java.sql.Blob blob=c.getimgs(user_name);
		try {
			if(blob.length()==0||blob==null)
			{
				System.out.println("图片为空");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		InputStream inputStream = null;
		try {
			inputStream = blob.getBinaryStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStream osOutputStream=response.getOutputStream();
		
		byte[] bs=new byte[1024];
		int i=0;
		while((i=inputStream.read(bs))!=-1)
		{
			osOutputStream.write(bs, 0, i);
			
		}
		osOutputStream.flush();
//		WebUtil.writeToFile(inputStream,osOutputStream);
		osOutputStream.close();
		inputStream.close();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);


	}
}


