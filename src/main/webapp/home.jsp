
<%@page import="com.onlineshopping.registration.*"%>
<%@page import="com.onlineshopping.order.OrderDAO"%>
<%@page import="com.onlineshopping.order.Order"%>
<%@page import="com.onlineshopping.order.CartItem"%>
<%@page import="com.onlineshopping.item.Item"%>
<%@page import="com.onlineshopping.item.itemDAO"%>
<%@page import="java.util.*"%>
<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("home.jsp");
}
ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="/includes/head.jsp"%>
<title>Home</title>
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
	padding-top: 10px;
	font-size: 14px;
	margin-top: 30px;
}

.card-title {
	font-weight: 300;
}

.btn {
	font-size: 14px;
	margin-top: 20px;
}

.login-form {
	width: 330px;
	margin: 20px;
}
</style>
<body>

	<%
	Connection con = null;

	PreparedStatement ps = null;

	ResultSet rs = null;

	String driverName = "com.mysql.jdbc.Driver";

	String url = "jdbc:mysql://localhost:3306/onlineshoppingdb";

	String user = "root";

	String password = "Renuka#123";

	String sql = "select * from user_type";

	try {

		Class.forName(driverName);

		con = DriverManager.getConnection(url, user, password);

		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();
	%>
	<%@include file="/includes/navbar.jsp"%>
	<div class="pt-5">
		<div class="global-container">
			<div class="card login-form">
				<div class="card-body">
					<h1 style="color: green" class="card-title text-center">Login
						Here</h1>

					<div class="card-text">
						<form method="post" action="user-login" class="form-horizontal">

							<div class="form-group">

								<label for="email">Enter Your UserName :</label> <input
									type="text" name="email" class="form-control form-control-sm" />

							</div>

							<div class="form-group">
								<label for="pwd">Enter Your Password :</label> <input
									type="password" name="password"
									class="form-control form-control-sm" />

							</div>


							<div class="form-group">
								<label>Select UserType</label> <select name="user_type"
									class="form-control">

									<option value="select">Select</option>

									<%
									while (rs.next())

									{

										String usertype = rs.getString(1);
									%>

									<option value=<%=usertype%>><%=rs.getString(2)%></option>

									<%
									}

									}

									catch (SQLException sqe)

									{

									out.println("home" + sqe);

									}
									%>

								</select>
							</div>

							<button class="btn btn-success" type="submit" value="submit" />
							Submit
						</form>
					</div>
					<%@include file="/includes/footer.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<center>
		<a href="register.jsp">You don't have an account ?<b>Register
				Here</b></a>
	</center>
</body>


</html>