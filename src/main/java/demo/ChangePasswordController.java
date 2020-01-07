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
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePasswordController")
public class ChangePasswordController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String pass=req.getParameter("pwd");
		String cpass=req.getParameter("cpwd");
		
		HttpSession hs=req.getSession();
		String email=(String)hs.getAttribute("useremail");
		
		if(pass.equals(cpass))
		{
			try 
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
				PreparedStatement ps=con.prepareStatement("update users set password=? where email=?");
				ps.setString(1, pass);
				ps.setString(2, email);
				ps.executeUpdate();

				PrintWriter out=resp.getWriter();
				out.println("<script>"
						+ "alert('Password Changed!!!!');"
								+ "window.location='login.jsp';"
						+ "</script>");
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}
}
