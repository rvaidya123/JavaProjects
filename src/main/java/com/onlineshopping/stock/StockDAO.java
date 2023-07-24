package com.onlineshopping.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockDAO {
	private ResultSet rs;
	public static Connection getConnection(){  
        Connection conn=null;  
        
        try{  
        	Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingdb","root","Renuka#123");
        }catch(Exception e){System.out.println(e);}  
        return conn;  
    }  
	
	 public static int save(Stock stock){  
	        int status=0;  
	        try{  
        	Connection conn=StockDAO.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
            		"insert into stock (item_id,quantity)values(?,?)");
            ps.setString(1,stock.getItemid());
            ps.setString(2,stock.getQuantity());
            status=ps.executeUpdate();  
            
            conn.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
	 
	  public static Stock getStockById(int id){  
	        Stock s=new Stock();  
	        try{  
	            Connection conn=StockDAO.getConnection();  
	            PreparedStatement ps=conn.prepareStatement("select * from stock where id=?");  
	            ps.setInt(1, id); 
	            ResultSet rs=ps.executeQuery();  
	            if(rs.next()){  
	            	s.setItemid(rs.getString(1));
	                s.setQuantity(rs.getString(2));  
	            }  
	            conn.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return s;  
	    }  

}
