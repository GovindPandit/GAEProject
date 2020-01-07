package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrationController",urlPatterns = "/RegistrationController")
public class RegistrationController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		User u=new User();
		u.setUsername(req.getParameter("username"));
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.executeUpdate();
			
			Test t=new Test(u.getEmail(), "Registered Successfully!!!", "Welcome to Library App!!!!");
			t.sendEmail();
			resp.sendRedirect("login.jsp");
			
			PrintWriter out=resp.getWriter();
			out.println("<script>"
					+ "alert('Registered successfully!!!');"
							+ "window.location='login.jsp';"
					+ "</script>");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
