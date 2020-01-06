<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<sql:setDataSource driver="com.mysql.jdbc.Driver" url="jdbc:mysql://34.67.32.70:3306/db" password="root" user="root" var="con"/>
	
	<sql:query var="rs" dataSource="${con}">
		select * from books where status='A'
	</sql:query>
	
	
	<table class="table table-striped">
		<tr>
			<th>Book Id</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>Price</th>
			<th>Edit | Delete</th>
		</tr>
		<c:forEach items="${rs.rows}" var="row">
			<tr>
				<td>${row.bookid}</td>
				<td>${row.bookname}</td>
				<td>${row.author}</td>
				<td>${row.price}</td>
				<th><a href="EditController?bookid=${row.bookid}" class="btn btn-primary">Edit</a> | <a href="DeleteController?bookid=${row.bookid}" class="btn btn-danger">Delete</a></th>
			</tr>
		</c:forEach>
	</table>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>