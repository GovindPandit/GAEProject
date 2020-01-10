<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">
<link href="displaybook.css" rel="stylesheet"/>
</head>
<body>
	<sql:setDataSource
	 driver="com.mysql.jdbc.Driver"
	  url="jdbc:mysql://34.67.32.70:3306/db"
	   password="root" 
	   user="root"
	    var="con"/>
	    
	<sql:query var="rs" dataSource="${con}">
		select * from books where bookid=? and status='A'
		<sql:param>${param.bookid}</sql:param>	
	</sql:query>
	
	   
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<c:forEach items="${rs.rows}" var="row">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-12">
						
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="ImageServlet?bookid=${row.bookid}" />
						  </div>
						  
						 </div>
				
						<h3 class="product-title">${row.bookname}</h3>
						
						<p class="product-description">Suspendisse quos? Tempus cras iure temporibus? Eu laudantium cubilia sem sem! Repudiandae et! Massa senectus enim minim sociosqu delectus posuere.</p>
						<h4 class="price">current price: <span>Rs. ${row.price}</span></h4>
						<p class="vote"><strong>91%</strong> of buyers enjoyed this product! <strong>(87 votes)</strong></p>
						
							<button class="btn btn-primary btn-block" type="button">Add to cart</button>
							<a href="DeleteController?bookid=${row.bookid}" class="btn btn-warning btn-block" >Delete</a>
							<a href="EditController?bookid=${row.bookid}" class="btn btn-secondary btn-block"  >Edit</a>
							<a class="btn btn-danger btn-block" href="OrderController">Buy</a>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>