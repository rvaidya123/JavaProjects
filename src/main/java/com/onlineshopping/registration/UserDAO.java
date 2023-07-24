package com.onlineshopping.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    public UserDAO(Connection con) {
		this.con = con;
	}
	public User userLogin(String email, String password, String usertype) {
		User user = null;
        try {
            query = "select * from user where email=? and password=? and user_type_id=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, usertype);

            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setEmail(rs.getString("email"));
            	user.setPassword(rs.getString("password"));
            	user.setUsertypeid(rs.getInt("user_type_id"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
}
