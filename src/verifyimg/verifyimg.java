package verifyimg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class verifyimg
 */
@WebServlet("/verifyimg")
public class verifyimg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verifyimg() {
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
		
		BufferedImage image=new BufferedImage(150, 50,BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2d=image.createGraphics();
		
		Random random=new Random();
		int a=random.nextInt(10);
		int b=random.nextInt(10);
		int choice=random.nextInt(2)+1;
		int answer=0;
		graphics2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		graphics2d.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		graphics2d.setFont(new Font("宋体", Font.BOLD, 24));
		if(choice==1)
		{
			answer=a+b;
			graphics2d.drawString(a+"+"+b+"=", 50, 30);
		}
		if(choice==2)
		{
			answer=a*b;
			graphics2d.drawString(a+"*"+b+"=", 50, 30);
		}
		HttpSession session=request.getSession();
		session.setAttribute("answer", answer);
		for(int i=0;i<20;i++)
		{
			graphics2d.drawLine(random.nextInt(150), random.nextInt(50), random.nextInt(150), random.nextInt(50));
		}
		
		for(int i=0;i<15;i++)
		{
			graphics2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			graphics2d.drawLine(random.nextInt(150), random.nextInt(50), random.nextInt(150), random.nextInt(50));

		}

		ImageIO.write(image,"jpeg",response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
