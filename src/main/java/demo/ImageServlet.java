package demo;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int bookid=Integer.parseInt(req.getParameter("bookid"));//1
		
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("select image from books where bookid=?");
			ps.setInt(1, bookid);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Blob blob=rs.getBlob("image");
				byte[] arr=blob.getBytes(1, (int)blob.length());
				resp.getOutputStream().write(arr);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
