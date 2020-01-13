<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="displaybooks.css" rel="stylesheet"/>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<sql:setDataSource driver="com.mysql.jdbc.Driver" url="jdbc:mysql://34.67.32.70:3306/db" password="root" user="root" var="con"/>
	
	<sql:query var="rs" dataSource="${con}">
		select * from books where status='A'
	</sql:query>
	
	
	<div class="container">
	<div class="row">
	<c:forEach items="${rs.rows}" var="row">
        <div class="col-xs-12 col-sm-4">
            <div class="card">
                <a class="img-card" href="book.jsp?bookid=${row.bookid}">
                	<!-- Display image from database -->
                    <!--<img src="ImageServlet?bookid=${row.bookid}"/>-->
                    
                    <!-- Display image from database link-->
                    <img src="${row.imagelink}"/>
                </a>
                <br />
                <div class="card-content">
                    <h4 class="card-title">
                        <a href="http://www.fostrap.com/">
                            ${row.bookname}
                        </a>
                    </h4>
                    <div class="">
                        ${row.author}
                    </div>
                </div>
                <div>
                    <a class="btn btn-primary btn-block" href="OrderController">Buy</a>
                    <a class="btn btn-secondary btn-block" href="http://www.fostrap.com/">Add To Cart</a>
                </div>
            </div>
        </div>
	</c:forEach>
	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>