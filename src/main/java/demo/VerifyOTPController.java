package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VerifyOTPController")
public class VerifyOTPController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int otp1=Integer.parseInt(req.getParameter("otp"));
		
		HttpSession hs=req.getSession();
		int otp2=(Integer)hs.getAttribute("randomnumber");
		
		if(otp1==otp2)
		{
			resp.sendRedirect("setnewpassword.jsp");
		}
		else
		{
			PrintWriter out=resp.getWriter();
			out.println("<script>"
					+ "alert('Wrong OTP!!!');"
							+ "window.location='enterotp.jsp';"
					+ "</script>");
		}
	}
}
