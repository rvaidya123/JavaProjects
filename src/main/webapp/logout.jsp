<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout Page</title>
</head>
<body>
<%
session.invalidate();
response.setContentType("text/html");
PrintWriter oWriter=response.getWriter();
oWriter.print("<h1>Successfully Log out!!!</h1>");
response.sendRedirect("home.jsp");

%>
</body>
</html>