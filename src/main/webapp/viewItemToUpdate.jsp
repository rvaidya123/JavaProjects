<%@page import="com.onlineshopping.order.CartItem"%>
<%@page import="com.onlineshopping.item.Item"%>
<%@page import="com.onlineshopping.item.itemDAO"%>
<%@page import="java.util.*"%>

<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%
itemDAO item = new itemDAO();
List<Item> list = item.getAllItems();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="/includes/head.jsp"%>
<title>List Items</title>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3"> Items List <a class="btn btn-secondary" href="additem.jsp">Add New Item</a> <a class="btn btn-secondary" href="addStock.jsp">Add Stock Item</a>
		</div>
		
	<table class="table table-light">
			<thead>
		<tr>
			<th scope="col">Id</th>
			<th scope="col">Name</th>
			<th scope="col">Limit Per Order</th>
			<th scope="col">Price</th>
			<th scope="col">Edit</th>
			<th scope="col">Delete</th>
		</tr>
	</thead>
			<tbody>
	<%
	if (!list.isEmpty()) {
		for (Item i : list) {
	%>
			<tr>
			<td>     <%=   i.getId()%></td>
			<td>     <%=   i.getName()%></td>
			<td>     <%=   i.getLimitperorder()%></td>
			<td>     Rs<%= i.getPrice()%></td>
			<td><a class="btn btn-primary" href=editItem1?id=<%=i.getId()%>>Edit</a></td>
			<td><a class="btn btn-primary"
				href="deleteItem?id=<%=i.getId()%>">Delete</a></td>
		</tr>

		<%
		}
		} else {
		out.println("There is no proucts");
		}
		%>
		</tbody>
		</table>
	</div>
	<%@include file="/includes/footer.jsp"%>
		
	</table>
</body>
</html>
	
	
	
<%-- 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>

	<center>
		<h1 style="color: green">Update Items Here</h1>
	</center>
	<form action="updateitemDAO" method="post">
		<table style="with: 20%" align="center">

			<%
	try {
		PreparedStatement pst;
        ResultSet rs;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb", "root",
		"Renuka#123");
		//String id = request.getParameter("id");
		 pst = connection.prepareStatement("select * from item");
         rs = pst.executeQuery();
		while (rs.next()) {
			String id = rs.getString(1);
	%>
			<tr>
				<td>Id</td>
				<td><input type="text" name="id"
					value="<%=id%>" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"
					value="<%=rs.getString("name")%>" /></td>
			</tr>
			<tr>
				<td>Limit Per Order</td>
				<td><input type="text" name="limit_per_order"
					value="<%=rs.getString("limit_per_order")%>" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="text" name="price"
					value="<%=rs.getString("price")%>" /></td>
					
				<%
				}
				%>
			</tr>
			<tr>
				<td>
				<input type="submit" value="Update" />
				</td>
			</tr>

			<%

	connection.close();
	} catch (Exception e) {
	e.printStackTrace();
	}
			%>
			
		</table>
	</form>
</body>
</html>
 --%>
