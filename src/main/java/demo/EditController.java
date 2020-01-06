package demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EditController")
public class EditController extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int bookid=Integer.parseInt(req.getParameter("bookid"));
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("select * from books where bookid=?");
			ps.setInt(1,bookid);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Book b=new Book();
				b.setBookid(rs.getInt("bookid"));
				b.setBookname(rs.getString("bookname"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getFloat("price"));
				
				req.setAttribute("book", b);
				RequestDispatcher rd=req.getRequestDispatcher("addbook.jsp");
				rd.forward(req, resp);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Book b=new Book();
		b.setBookid(Integer.parseInt(req.getParameter("bookid")));
		b.setBookname(req.getParameter("bookname"));
		b.setAuthor(req.getParameter("author"));
		b.setPrice(Float.parseFloat(req.getParameter("price")));
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("update books set bookname=?, author=?, price=? where bookid=?");
			ps.setString(1,b.getBookname());
			ps.setString(2,b.getAuthor());
			ps.setFloat(3,b.getPrice());
			ps.setInt(4,b.getBookid());
			ps.executeUpdate();
			resp.sendRedirect("books.jsp");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
