<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="formcss.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<form class="col-lg-6 col-md-6 col-sm-12 col-xs-12 myform" action="VerifyOTPController" method="post">
			<div class="form-group">
				<label>Enter Email</label>
				<input type="text" name="otp" class="form-control" placeholder="Enter OTP"/>
			</div>
			<div class="form-group">
				<input type="submit" value="Enter Otp" class="btn btn-primary btn-block"/>
				<input type="reset" value="Reset" class="btn btn-danger btn-block"/>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>