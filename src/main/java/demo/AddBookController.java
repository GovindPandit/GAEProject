package demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


@WebServlet(name="AddBookController",urlPatterns = {"/AddBookController"})
@MultipartConfig(maxFileSize = 999999999999999L)
public class AddBookController extends HttpServlet
{
	  private static Storage storage = null;

	  static 
	  {
	    storage = StorageOptions.getDefaultInstance().getService();
	  }
	  
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Book b=new Book();
		b.setBookname(req.getParameter("bookname"));
		b.setAuthor(req.getParameter("author"));
		b.setPrice(Float.parseFloat(req.getParameter("price")));
		Part part=req.getPart("image");
		InputStream is=part.getInputStream();
		
		 
		try 
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://34.67.32.70:3306/db","root","root");
			PreparedStatement ps=con.prepareStatement("insert into books (bookname,author,price,status,image) values(?,?,?,?,?)");
			ps.setString(1,b.getBookname());
			ps.setString(2,b.getAuthor());
			ps.setFloat(3,b.getPrice());
			ps.setString(4,"A");
			ps.setBlob(5, is);
			ps.executeUpdate();

			BlobInfo blobInfo =
			        storage.create(
			            BlobInfo
			                .newBuilder("gaeniit-1to3.appspot.com", b.getBookid()+".jpg")
			                // Modify access list to allow all users with link to read file
			                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
			                .build(),
			            is);
			
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
