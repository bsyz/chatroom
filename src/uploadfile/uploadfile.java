package uploadfile;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import conndb.conndb;

/**
 * Servlet implementation class uploadimg
 */
@WebServlet("/uploadfile")
public class uploadfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadfile() {
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
//		ServletInputStream in=request.getInputStream();
//		int length=request.getContentLength();
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String names=(String)session.getAttribute("name");

		String savepath="/Users/yuzhengwang/Desktop/test";
		String ids=(String)session.getAttribute("addr");
//		String savepath=this.getServletContext().getRealPath("/WEB-INF/upload");
		File file=new File(savepath);
		if(!file.exists()&&!file.isDirectory())
		{
			System.out.println(savepath+"目录不存在，需要创建");
			file.mkdir();
		}
		
		String message="";
		try
		{
			DiskFileItemFactory factory=new DiskFileItemFactory();
			factory.setSizeThreshold(1024*100);
			factory.setRepository(file);
			ServletFileUpload upload=new ServletFileUpload(factory);
			upload.setProgressListener(new ProgressListener() {
				
				@Override
				public void update(long arg0, long arg1, int arg2) {
					// TODO Auto-generated method stub
					System.out.println("文件大小为："+arg1+"，当前已处理："+arg0);
					
				}
			});
			upload.setHeaderEncoding("UTF-8");
			if(!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			upload.setFileSizeMax(1024*100*1024);
			upload.setSizeMax(1024*1024*100);
			java.util.List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list)
			{
				if(item.isFormField())
				{
					String name=item.getFieldName();
					String value=item.getString("UTF-8");
					System.out.println(name+"="+value);
				}
				else 
				{
					
					String filename=item.getName();
					System.out.println(names+"上传了"+filename);

					if(filename==null||filename.trim().equals(""))
					{
						continue;
					}
					filename=filename.substring(filename.lastIndexOf("/")+1);
					InputStream in=item.getInputStream();
					filename=names+"_"+filename;
					String path=savepath+"/"+filename;
					FileOutputStream outputStream=new FileOutputStream(path);
					byte buffer[]=new byte[1024];
					int len=0;
					while((len=in.read(buffer))>0)
					{
						outputStream.write(buffer,0,len);
					}
					conndb co=new conndb();
					co.insertfiles(ids, filename, path);
					in.close();
					outputStream.close();
					item.delete();
					message="上传成功";
				}
			}
		}
		catch(FileUploadBase.FileSizeLimitExceededException e)
		{
			e.printStackTrace();
			message="单个文件超过最大值！";
			
		}
		catch(FileUploadBase.SizeLimitExceededException e)
		{
			e.printStackTrace();
			message="上传文件总大小超过最大值！";
			
		}
		catch (Exception e)
		{
			message="上传失败";
			e.printStackTrace();
			
		}
		request.setAttribute("message", message);
//		System.out.println("the end!");
//		request.getRequestDispatcher("/message.jsp").forward(request, response);
//		out.println(message);
		response.sendRedirect("inputframe.jsp");
//		response.setHeader("refresh", "3,inputframe.jsp");
	}

}
