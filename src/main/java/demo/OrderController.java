package demo;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;

@WebServlet("/OrderController")
public class OrderController  extends  HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		 HttpSession hs=req.getSession();
	
		 if(hs.getAttribute("u")==null)
		 {
			 resp.sendRedirect("login.jsp");
		 }
		 else
		 {
			 User u=(User)hs.getAttribute("u");

			 try 
	         {
	           ApiContext context = ApiContext.create("test_BaRDDGvbDNj0ZcudrrjqHnGKHtQqkC6iiDF", "test_5qikY6U2ikPAqCCcje7IZjXDG7yHMKG8nnLnibJXaZHiO8kmW8ayuXFyxGwOzqqc4u0PhrkqG4jfJ3RjXGEefqSdIwnnKfPiCvegi0qmjM62DX97ZHNFIWmqcyV", ApiContext.Mode.TEST);
	           Instamojo api = new InstamojoImpl(context);

	           PaymentOrder order = new PaymentOrder();
	           order.setName(u.getUsername());
	           order.setEmail(u.getEmail());
	           order.setPhone("7977518582");
	           order.setCurrency("INR");
	           order.setAmount((double)11);
	           order.setDescription("This is a test transaction.");
	           order.setRedirectUrl("https://gaeniit-1to3.appspot.com/books.jsp");
	           order.setWebhookUrl("https://gaeniit-1to3.appspot.com/");
	           order.setTransactionId(UUID.randomUUID().toString());

	           PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
	           resp.sendRedirect(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
	       }
	       catch (Exception e) 
	       {
	           System.out.println(e);
	       }
		 }
	}
}
