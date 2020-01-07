package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SendOTPController")
public class SendOTPController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email=req.getParameter("email");
		
		try 
		{

			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			
			if(!rs.next())
			{
				PrintWriter out=resp.getWriter();
				out.println("<script>"
						+ "alert('Please register first, your email id is not found in our database!!!');"
								+ "window.location='register.jsp';"
						+ "</script>");
			}
			else
			{
				Random r=new Random();
				int randomnumber=r.nextInt(8999)+1000;
				
				HttpSession hs=req.getSession();
				hs.setAttribute("randomnumber", randomnumber);
				hs.setAttribute("useremail", email);
				
				try 
				{
					Test t=new Test(email,"OTP","OTP is: "+randomnumber);
					t.sendEmail();
					resp.sendRedirect("enterotp.jsp");
				}
				catch (Exception e) 
				{
					System.out.println(e);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		
	}
}
