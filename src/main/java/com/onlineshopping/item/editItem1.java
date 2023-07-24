package com.onlineshopping.item;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editItem1
 */
@WebServlet("/editItem1")
public class editItem1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();     
        String sid=request.getParameter("id"); 
        int id=Integer.parseInt(sid);
        Item i=itemDAO.getItemById(id);
        out.println("<center><h1 style=\"color: green\">Update Item Here</h1></center>");  
        out.print("<form action='editItem2' method='post'>");  
        out.print("<table align='center'>");  
        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+i.getId()+"'/></td></tr>");  
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+i.getName()+"'/></td></tr>");  
        out.print("<tr><td>Limit Per Order:</td><td><input type='text' name='limit_per_order' value='"+i.getLimitperorder()+"'/> </td></tr>");  
        out.print("<tr><td>Price:</td><td><input type='text' name='price' value='"+i.getPrice()+"'/></td></tr>"); 
        out.print("<br>");
        out.println("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
        out.print("</table>");  
        out.print("</form>");         
        out.close();  
	}

}
