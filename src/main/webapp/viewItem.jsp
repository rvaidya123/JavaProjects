<%@page import="com.onlineshopping.registration.*"%>
<%@page import="com.onlineshopping.order.CartItem"%>
<%@page import="com.onlineshopping.item.Item"%>
<%@page import="com.onlineshopping.item.itemDAO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
itemDAO item = new itemDAO();
List<Item> list = item.getAllItems();
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
<title>List Items</title>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Items</div>
	<table class="table table-light">
			<thead>
		<tr>
			<th scope="col">Id</th>
			<th scope="col">Name</th>
			<th scope="col">Price</th>
			<th scope="col">Add</th>
			<th scope="col">Place Order</th>
		</tr>
	</thead>
			<tbody>
	<%
	if (!list.isEmpty()) {
		for (Item i : list) {
	%>
			<tr>
			<td value="<%=   i.getId()%>">     <%=   i.getId()%></td>
			<td  value=" <%=   i.getName()%>">     <%=   i.getName()%></td>
			<td value=" <%= i.getPrice()%>">     Rs<%= i.getPrice()%></td>
			<td><a class="btn btn-dark" href="add-to-cart?id=<%=i.getId()%>">Add to Cart</a></td>
			<td><a class="btn btn-primary"
				href="order-now?quantity=1&id=<%=i.getId()%>">Buy Now</a></td>
		</tr>

		<%
		}
		} else {
		out.println("There is no such products");
		}
		%>
		</tbody>
		</table>
	</div>
	<%@include file="/includes/footer.jsp"%>
		
	</table>
</body>
</html>