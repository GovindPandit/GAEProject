package demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int bookid=Integer.parseInt(req.getParameter("bookid"));
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("update books set status='D' where bookid=?");
			ps.setInt(1, bookid); 
			ps.executeUpdate();
			resp.sendRedirect("books.jsp");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
