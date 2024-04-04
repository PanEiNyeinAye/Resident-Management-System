package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.SAdmin;
import com.java.beans.StateOrRegion;

public class SAdminDAO {
	
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//Validate sub admin Login
	public int validateSAdmin(String email, String password) {
			String sql = "SELECT count(*) FROM sadmin where sadmin_email=? and sadmin_password=?";
			return template.queryForObject(sql, Integer.class, email, password);
	}
	
	//get a specific sub admin info by sadmin_id
		public SAdmin getSAdmin(int sadmin_id) {
			 try
				{String sql = "SELECT sadmin_id,sadmin_name,sadmin_employee_no,sadmin_email,sadmin_password,sadmin.state_or_region_id,state_or_region_name FROM sadmin,state_or_region"
			 		+ " WHERE sadmin.state_or_region_id = state_or_region.state_or_region_id and sadmin_id=?";
			 return template.queryForObject(sql, new RowMapper<SAdmin>(){
				 public SAdmin mapRow(ResultSet rs, int row) throws SQLException {
					 SAdmin s = new SAdmin();
					 s.setSadminId(rs.getInt("sadmin_id"));
					 s.setSadminName(rs.getString("sadmin_name"));
					 s.setSadminEmployeeNo(rs.getString("sadmin_employee_no"));
					 s.setSadminEmail(rs.getString("sadmin_email"));
					 s.setSadminPassword(rs.getString("sadmin_password"));
					 s.setStateOrRegionId(rs.getInt("state_or_region_id"));
					 s.setStateOrRegion(new StateOrRegion(rs.getString("state_or_region_name")));
					 return s;
				 }
			 },sadmin_id);}
				catch (DataAccessException e)
				{
				    return null;
				}
		}
		
	//get a sub_admin by email and password
	public  SAdmin getSAdmin(String email, String password) { 
			 String sql = "SELECT sadmin_id,sadmin_name,sadmin_employee_no,sadmin_email,sadmin_password,sadmin.state_or_region_id,state_or_region_name FROM sadmin,state_or_region"
			 		+ " WHERE sadmin.state_or_region_id = state_or_region.state_or_region_id and sadmin_email=? and sadmin_password=?";
			 return template.queryForObject(sql, new RowMapper<SAdmin>(){
				 public SAdmin mapRow(ResultSet rs, int row) throws SQLException {
					 SAdmin s = new SAdmin();
					 s.setSadminId(rs.getInt("sadmin_id"));
					 s.setSadminName(rs.getString("sadmin_name"));
					 s.setSadminEmployeeNo(rs.getString("sadmin_employee_no"));
					 s.setSadminEmail(rs.getString("sadmin_email"));
					 s.setSadminPassword(rs.getString("sadmin_password"));
					 s.setStateOrRegionId(rs.getInt("state_or_region_id"));
					 s.setStateOrRegion(new StateOrRegion(rs.getString("state_or_region_name")));
					 return s;
				 }
			 },email,password);
	}
	
	//Get total number of sub admin in Myanmar
	public  int getSAdminCountByAdmin(){
			String sql = "SELECT count(*) FROM sadmin";
			return template.queryForObject(sql,Integer.class);
	};
	//***Sub Admin Management start***/
	
	//get sub admin list of Myanmar by admin
			public List<SAdmin> getSAdminListByAdmin() { 
					 String sql = "SELECT sadmin_id,sadmin_name,sadmin_employee_no,sadmin_email,sadmin_password,"
					 		+ "sadmin.state_or_region_id,state_or_region_name FROM sadmin,state_or_region where sadmin.state_or_region_id="
					 		+ "state_or_region.state_or_region_id";
					 
					 return template.query(sql, new RowMapper<SAdmin>(){
						 public SAdmin mapRow(ResultSet rs, int row) throws SQLException {
							 SAdmin w = new SAdmin();
							 w.setSadminId(rs.getInt("sadmin_id"));
							 w.setSadminName(rs.getString("sadmin_name"));
							 w.setSadminEmployeeNo(rs.getString("sadmin_employee_no"));
							 w.setSadminEmail(rs.getString("sadmin_email"));
							 w.setSadminPassword(rs.getString("sadmin_password"));
							 w.setStateOrRegionId(rs.getInt("state_or_region_id"));
							 w.setStateOrRegion(new StateOrRegion(rs.getString("state_or_region_name")));
							 return w;
						 }
					 });
			}
			
	//delete sub admin
	public int deleteSAdmin(int id) {
		String sql = "delete from sadmin where sadmin_id="+id+""; 
		return template.update(sql);
	}
	
	//add a sub admin
	public int saveSAdmin(SAdmin s) {
		String sql = "insert into sadmin (sadmin_name,sadmin_employee_no,sadmin_email,"
				+ "sadmin_password,state_or_region_id) values ('"+s.getSadminName()+"','"+s.getSadminEmployeeNo()+"','"+s.getSadminEmail()+"',"
						+ "'"+s.getSadminPassword()+"',"+s.getStateOrRegionId()+")";
		return template.update(sql);
	}
	
	//update a sub admin
		public int updateSAdmin(SAdmin s) {
			String sql = "update sadmin set sadmin_name='"+s.getSadminName()+"',"
							+ "sadmin_employee_no='"+s.getSadminEmployeeNo()+"',"
									+ "sadmin_email='"+s.getSadminEmail()+"',sadmin_password='"+s.getSadminPassword()+"',"
											+ "state_or_region_id="+s.getStateOrRegionId()+" where sadmin_id="+s.getSadminId()+"";
			return template.update(sql); 
		}
		//*****Sub Admin Management Finish***//
}
