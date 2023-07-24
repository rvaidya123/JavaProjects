package com.onlineshopping.item;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editItem2
 */
@WebServlet("/editItem2")
public class editItem2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);
		String name = request.getParameter("name");
		String limitperorder = request.getParameter("limit_per_order");
		String iprice = request.getParameter("price");
		Double price=Double.parseDouble(iprice);
		Item i=new Item();
		i.setId(id);
		i.setName(name);  
        i.setLimitperorder(limitperorder);  
        i.setPrice(price);  
        int status=itemDAO.update(i);  
        if(status>0){  
            response.sendRedirect("viewItemToUpdate.jsp");  
        }else{  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close();
	}

}
