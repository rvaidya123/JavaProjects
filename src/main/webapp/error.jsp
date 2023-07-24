<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="/includes/head.jsp"%>
<title>Error Page</title>
</head>
<body>
<%
response.setContentType("text/html");
PrintWriter oWriter=response.getWriter();
oWriter.print("<center><div class=\"alert alert-danger\">Something went wrong!</div></center>");

%>
<center><a href="home.jsp">Login Again...</a></center>
<%@include file="/includes/footer.jsp"%>
</body>
</html>