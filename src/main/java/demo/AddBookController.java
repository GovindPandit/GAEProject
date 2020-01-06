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

@WebServlet(name="AddBookController",urlPatterns = {"/AddBookController"})
public class AddBookController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Book b=new Book();
		b.setBookname(req.getParameter("bookname"));
		b.setAuthor(req.getParameter("author"));
		b.setPrice(Float.parseFloat(req.getParameter("price")));
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("insert into books (bookname,author,price,status) values(?,?,?,?)");
			ps.setString(1,b.getBookname());
			ps.setString(2,b.getAuthor());
			ps.setFloat(3,b.getPrice());
			ps.setString(4,"A");
			ps.executeUpdate();
			
			PrintWriter out=resp.getWriter();
			out.println("<script>"
					+ "alert('Book inserted successfully!!!!');"
							+ "window.location='books.jsp';"
					+ "</script>");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
