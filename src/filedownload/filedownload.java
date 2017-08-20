package filedownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Blob;

import conndb.conndb;
import inputting.inputting;

/**
 * Servlet implementation class filedownload
 */
@WebServlet("/filedownload")
public class filedownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filedownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8,pageEncoding=UTF-8");
		String filename="";
		conndb c=new conndb();
		
		filename=request.getParameter("filename");
		response.setHeader("content-disposition", "attachmebnt;filename="+filename );

		System.out.println("文件名称是：");
		System.out.println(filename);
		java.sql.Blob blob=c.getfiles(filename);
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
		
//		WebUtil.writeToFile(inputStream,osOutputStream);
		osOutputStream.close();
		inputStream.close();
		}
	

}
