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
<title>Add Item</title>
</head>
<body>
	<%
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb", "root",
		"Renuka#123");

		Statement statement = connection.createStatement();

		resultset = statement.executeQuery("select * from category");
	%>
	
<%@include file="/includes/navbar.jsp"%>
	<center>
		<h1 style="color: green">Add Items Here</h1>
	</center>
	<form action="addItem" method="post">
		<table style="with: 20%" align="center">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>CategoryType</td>
				<td><select name="category">
						<option value="select">select</option>
						<%
						while (resultset.next()) {
						%>
						<option value="<%=resultset.getString(1)%>"><%=resultset.getString(2)%></option>
						<%
						}
						%>
				</select> 
	<%
		 } catch (Exception e) {
		 out.println("wrong entry" + e);
		 }
	%>
 </td>
			</tr>
			<tr>
				<td>Limit Per Order</td>
				<td><input type="text" name="limit_per_order" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="text" name="price" /></td>
			</tr>
			
			<tr>

				<td></td>

				<td><input type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
	<br>
		<center><a href="viewItemToUpdate.jsp">Click Here To View Item List</a></center>
			<%@include file="/includes/footer.jsp"%>
</body>
</html>