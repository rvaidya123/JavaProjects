<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
ResultSet resultset;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="/includes/head.jsp"%>
<title>Registration</title>
</head>
<style>
html {
	height: 100%;
}

body {
	height: 100%;
}

.global-container {
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: #f5f5f5;
}

form {
	padding-top: 2px;
	font-size: 14px;
	margin-top: 30px;
}

.card-title {
	font-weight: 100;
}

.btn {
	font-size: 14px;
	margin-top: 20px;
}

.register-form {
	width: 380px;
	margin: 20px;
}
</style>
<body>

	<%
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb", "root",
		"Renuka#123");

		Statement statement = connection.createStatement();

		resultset = statement.executeQuery("select * from user_type");
	%>

	<%@include file="/includes/navbar.jsp"%>
	<div class="pt-5">
		<div class="global-container">
			<div class="card register-form">
				<div class="card-body">
					
						<h3 style="color: green" class="card-title text-center">User
							Register Form</h3>
					
					<div class="card-text">
						<form action="registrationDAO" method="post" class="form-horizontal">
						
									<div class="form-group">

										<label>First Name:</label> <input type="text"
											name="first_name" class="form-control form-control-sm" />

									</div>


									<div class="form-group">
										<label>Last Name:</label> <input type="text" name="last_name"
											class="form-control form-control-sm" />

									</div>

									<div class="form-group">
										<label>UserName:</label> <input type="text" name="username"
											class="form-control form-control-sm" />
									</div>


									<div class="form-group">
										<label>Password:</label> <input type="password"
											name="password" class="form-control form-control-sm" />
									</div>


									<div class="form-group">
										<label>UserType:</label> <select name="user_type_id"
											class="form-control">
											<option value="select">Select</option>
											<%
											while (resultset.next()) {
											%>

											<option value="<%=resultset.getString(1)%>"><%=resultset.getString(2)%></option>

											<%
											}
											%>
										</select>
									</div>
									<%
									} catch (Exception e) {
									out.println("wrong entry" + e);
									}
									%>
									<button class="btn btn-success type=" submit" value="submit" />
									Submit
									</form>
									</div>
									<%@include file="/includes/footer.jsp"%>

									</div>
									</div>
									</div>
									</div>
</body>
</html>