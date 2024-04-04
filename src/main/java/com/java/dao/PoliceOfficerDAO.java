package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.PoliceOfficer;
import com.java.beans.StateOrRegion;
import com.java.beans.Township;

public class PoliceOfficerDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//Validate Police Officer Login
	public int validatePoliceOfficer(String email, String password) {
			String sql = "SELECT count(*) FROM police_officer where police_officer_email=? and police_officer_password=?";
			return template.queryForObject(sql, Integer.class, email, password);
	}
	
	//get a Police Officer by email and password
	public PoliceOfficer getPoliceOfficer(String p_email,String p_password) { 
			 String sql = "SELECT police_officer_id,police_officer_name,police_employee_no,police_officer_position,"
			 		+ "police_officer_email,police_officer_password,township.township_id,township.township_name,state_or_region.state_or_region_name"
			 		+ " FROM police_officer,township,state_or_region WHERE "
			 		+ "police_officer.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
			 		+ "police_officer_email=? and police_officer_password=?";
			 
			 return template.queryForObject(sql, new RowMapper<PoliceOfficer>(){
				 public PoliceOfficer mapRow(ResultSet rs, int row) throws SQLException {
					 PoliceOfficer p = new PoliceOfficer();
					 p.setPoliceOfficerId(rs.getInt("police_officer_id"));
					 p.setPoliceOfficerName(rs.getString("police_officer_name"));
					 p.setPoliceEmployeeNo(rs.getString("police_employee_no"));
					 p.setPoliceOfficerPosition(rs.getString("police_officer_position"));
					 p.setPoliceOfficerEmail(rs.getString("police_officer_email"));
					 p.setPoliceOfficerPassword(rs.getString("police_officer_password"));
					 p.setTownshipId(rs.getInt("township_id"));
					 p.setTownship(new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name")));
					 return p;
				 }
			 },p_email,p_password);
	}
	
	//get a Police Officer by policeOfficerId
		public PoliceOfficer getPoliceOfficer(int policeOfficerId) { 
				 String sql = "SELECT police_officer_id,police_officer_name,police_employee_no,police_officer_position,"
				 		+ "police_officer_email,police_officer_password,township.township_id,township_name,state_or_region.state_or_region_name"
				 		+ " FROM police_officer,township,state_or_region WHERE "
				 		+ "police_officer.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
				 		+ "police_officer_id=?";
				 
				 return template.queryForObject(sql, new RowMapper<PoliceOfficer>(){
					 public PoliceOfficer mapRow(ResultSet rs, int row) throws SQLException {
						 PoliceOfficer p = new PoliceOfficer();
						 p.setPoliceOfficerId(rs.getInt("police_officer_id"));
						 p.setPoliceOfficerName(rs.getString("police_officer_name"));
						 p.setPoliceEmployeeNo(rs.getString("police_employee_no"));
						 p.setPoliceOfficerPosition(rs.getString("police_officer_position"));
						 p.setPoliceOfficerEmail(rs.getString("police_officer_email"));
						 p.setPoliceOfficerPassword(rs.getString("police_officer_password"));
						 p.setTownshipId(rs.getInt("township_id"));
						 p.setTownship(new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name")));
						 return p;
					 }
				 },policeOfficerId);
		}
		//get a Police Officer by townshipId
		public PoliceOfficer getPoliceOfficerByTownshipId(int townshipId) { 
				 String sql = "SELECT police_officer_id,police_officer_name,police_employee_no,police_officer_position,"
				 		+ "police_officer_email,police_officer_password,township.township_id,township_name,state_or_region.state_or_region_name"
				 		+ " FROM police_officer,township,state_or_region WHERE "
				 		+ "police_officer.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
				 		+ "police_officer.township_id=? and police_officer_position=?";
				 
				 return template.queryForObject(sql, new RowMapper<PoliceOfficer>(){
					 public PoliceOfficer mapRow(ResultSet rs, int row) throws SQLException {
						 PoliceOfficer p = new PoliceOfficer();
						 p.setPoliceOfficerId(rs.getInt("police_officer_id"));
						 p.setPoliceOfficerName(rs.getString("police_officer_name"));
						 p.setPoliceEmployeeNo(rs.getString("police_employee_no"));
						 p.setPoliceOfficerPosition(rs.getString("police_officer_position"));
						 p.setPoliceOfficerEmail(rs.getString("police_officer_email"));
						 p.setPoliceOfficerPassword(rs.getString("police_officer_password"));
						 p.setTownshipId(rs.getInt("township_id"));
						 p.setTownship(new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name")));
						 return p;
					 }
				 },townshipId,"Township Officer");
		}
	//----------------------------Admin start---------------------------//
	//get number of police officers in Myanmar by admin
	public int getPoliceOfficerCountByAdmin() {
		String sql = "select count(*) from police_officer";
		return template.queryForObject(sql, Integer.class);
	}

	
	//get police officer list of Myanmar by admin
	public List<PoliceOfficer> getPoliceOfficerListByAdmin() { 
			 String sql = "SELECT police_officer_id,police_officer_name,police_employee_no,police_officer_position,police_officer_email,police_officer_password,"
			 		+ "township.township_id,township_name FROM police_officer,township where police_officer.township_id = township.township_id";
			 
			 return template.query(sql, new RowMapper<PoliceOfficer>(){
				 public PoliceOfficer mapRow(ResultSet rs, int row) throws SQLException {
					 PoliceOfficer w = new PoliceOfficer();
					 w.setPoliceOfficerId(rs.getInt("police_officer_id"));
					 w.setPoliceOfficerName(rs.getString("police_officer_name"));
					 w.setPoliceEmployeeNo(rs.getString("police_employee_no"));
					 w.setPoliceOfficerPosition(rs.getString("police_officer_position"));
					 w.setPoliceOfficerEmail(rs.getString("police_officer_email"));
					 w.setPoliceOfficerPassword(rs.getString("police_officer_password"));
					 w.setTownshipId(rs.getInt("township_id"));
					 w.setTownship(new Township(rs.getString("township_name")));
					 return w;
				 }
			 });
	}
	//------------------------------Admin finish----------------------------//
	
	
	//-------------------------------Sub Admin Start--------------------------//
	
	//****Police Officer Management Start******//
	//get number of police officers in a state/region by sub admin
		public int getPoliceOfficerCountBySAdmin(int state_or_region_id) {
			String sql = "select count(*) from police_officer p,township t,state_or_region r,sadmin s where p.township_id = t.township_id"
					+ " and t.state_or_region_id = r.state_or_region_id and r.state_or_region_id = s.state_or_region_id and"
					+ " s.state_or_region_id = ?";
			return template.queryForObject(sql, Integer.class,state_or_region_id);
		}	
		
		//get police officer list of a state/region by sub admin
		public List<PoliceOfficer> getPoliceOfficerListBySAdmin(int stateOrRegionId) { 
				 String sql = "SELECT police_officer_id,police_officer_name,police_employee_no,police_officer_position,police_officer_email,police_officer_password,"
				 		+ "police_officer.township_id,township_name FROM police_officer,township,state_or_region where police_officer.township_id = township.township_id and "
				 		+ "township.state_or_region_id = state_or_region.state_or_region_id and state_or_region.state_or_region_id = ?";
				 
				 return template.query(sql, new RowMapper<PoliceOfficer>(){
					 public PoliceOfficer mapRow(ResultSet rs, int row) throws SQLException {
						 PoliceOfficer w = new PoliceOfficer();
						 w.setPoliceOfficerId(rs.getInt("police_officer_id"));
						 w.setPoliceOfficerName(rs.getString("police_officer_name"));
						 w.setPoliceEmployeeNo(rs.getString("police_employee_no"));
						 w.setPoliceOfficerPosition(rs.getString("police_officer_position"));
						 w.setPoliceOfficerEmail(rs.getString("police_officer_email"));
						 w.setPoliceOfficerPassword(rs.getString("police_officer_password"));
						 w.setTownshipId(rs.getInt("township_id"));
						 w.setTownship(new Township(rs.getString("township_name")));
						 return w;
					 }
				 },stateOrRegionId);
		}
		
		//delete a police officer by sub admin by police_officer_id
		public int deletePoliceOfficer(int police_officer_id) {
			String sql = "delete from police_officer where police_officer_id="+police_officer_id+""; 
			return template.update(sql);
		}
		
		//add a police officer by sub admin
		public int savePoliceOfficer(PoliceOfficer p) {
			String sql = "insert into police_officer (police_officer_name,police_employee_no,police_officer_position,police_officer_email,police_officer_password,township_id) values ('"+p.getPoliceOfficerName()+"','"+p.getPoliceEmployeeNo()+"','"+p.getPoliceOfficerPosition()+"',"
							+ "'"+p.getPoliceOfficerEmail()+"','"+p.getPoliceOfficerPassword()+"',"+p.getTownshipId()+")";
			return template.update(sql);
		}
		
		//update a police officer by sub admin
		public int updatePoliceOfficer(PoliceOfficer p) {
			String sql = "update police_officer set police_officer_name='"+p.getPoliceOfficerName()+"',police_employee_no='"+p.getPoliceEmployeeNo()+"',"
					+ "police_officer_position='"+p.getPoliceOfficerPosition()+"',police_officer_email='"+p.getPoliceOfficerEmail()+"',police_officer_password='"+p.getPoliceOfficerPassword()+"',township_id="+p.getTownshipId()+" where police_officer_id="+p.getPoliceOfficerId()+"";
			return template.update(sql); 
		}
		//****Police Officer Management Finish******//
}
