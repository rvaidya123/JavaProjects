<%@ page import="java.sql.*"  language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="/includes/head.jsp"%>
<title>Stock Page</title>
</head>
<body>
<%
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb", "root",
		"Renuka#123");

		Statement statement = connection.createStatement();

		ResultSet resultset = statement.executeQuery("select * from item");
	%>
	
<%@include file="/includes/navbar.jsp"%>
<center>
		<h1 style="color: green">Add Stocks Here</h1>
	</center>
	<form action="addStock" method="post">
	<table style="with: 20%" align="center">
			<tr>
				<td>Item Id</td>
				<td><select name="item_id" >
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
				<td class="form-group">Quantity</td>
				<td><input type="text" name="quantity" /></td>
			</tr>
			<tr>

				<td></td>

				<td><button class="btn btn-info" type="submit" value="submit" />SUBMIT</td>
				<td><a class="btn btn-secondary" href="viewItemToUpdate.jsp">Back</a></td>
			</tr>
		</table>
	</form>
</body>
</html>