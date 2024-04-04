package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.java.beans.Admin;

public class AdminDAO {
	
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//Validate admin Login
	public int validateAdmin(String email, String password) {
			String sql = "SELECT count(*) FROM admin where admin_email=? and admin_password=?";
			return template.queryForObject(sql, Integer.class, email, password);
	}
	
	//get an admin by email and password
	public  Admin getAdmin(String email, String password) { 
			 String sql = "SELECT * FROM admin where admin_email=? and admin_password=?";
			 return template.queryForObject(sql, new RowMapper<Admin>(){
				 public Admin mapRow(ResultSet rs, int row) throws SQLException {
					 Admin a = new Admin();
					 a.setAdminId(rs.getInt("admin_id"));
					 a.setAdminEmail(rs.getString("admin_email"));
					 a.setAdminPassword(rs.getString("admin_password"));
					 return a;
				 }
			 },email,password);
	}
	
	public  Admin getAdmin(int adminId) { 
		try
		{ String sql = "SELECT * FROM admin where admin_id=?";
		 return template.queryForObject(sql, new RowMapper<Admin>(){
			 public Admin mapRow(ResultSet rs, int row) throws SQLException {
				 Admin a = new Admin();
				 a.setAdminId(rs.getInt("admin_id"));
				 a.setAdminEmail(rs.getString("admin_email"));
				 a.setAdminPassword(rs.getString("admin_password"));
				 return a;
			 }
		 },adminId);}
		catch (DataAccessException e)
		{
			System.out.println("null");
		    return null;
		}
}
	public int updateAdmin(Admin admin) {
		String sql = "update admin set admin_email='"+admin.getAdminEmail()+"',admin_password='"+admin.getAdminPassword()+"' where admin_id="+admin.getAdminId()+"";
		return template.update(sql); 
	}
	
	//
}
