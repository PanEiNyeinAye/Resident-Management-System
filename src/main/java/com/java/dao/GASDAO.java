package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.GAS;
import com.java.beans.StateOrRegion;
import com.java.beans.Township;

public class GASDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//Validate General Administrative Staff Login
	public int validateGAS(String email, String password) {
			String sql = "SELECT count(*) FROM gas where gas_email=? and gas_password=?";
			return template.queryForObject(sql, Integer.class, email, password);
	}
		
		//get a general administrative staff by email and password
		public GAS getGAS(String gas_email,String gas_password) { 
				 String sql = "SELECT gas_id,gas_name,gas_employee_no,gas_position,gas_email,gas_password,"
				 		+ "township.township_id,township_name,state_or_region.state_or_region_name"
				 		+ " FROM gas,township,state_or_region WHERE "
				 		+ "gas.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
				 		+ "gas_email=? and gas_password=?";
				 
				 return template.queryForObject(sql, new RowMapper<GAS>(){
					 public GAS mapRow(ResultSet rs, int row) throws SQLException {
						 GAS g = new GAS();
						 g.setGasId(rs.getInt("gas_id"));
						 g.setGasName(rs.getString("gas_name"));
						 g.setGasEmployeeNo(rs.getString("gas_employee_no"));
						 g.setGasPosition(rs.getString("gas_position"));
						 g.setGasEmail(rs.getString("gas_email"));
						 g.setGasPassword(rs.getString("gas_password"));
						 g.setTownshipId(rs.getInt("township_id"));
						 g.setTownship(new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name")));
						 return g;
					 }
				 },gas_email,gas_password);
		}	
		
		//get a general administrative staff by gas_id
		public GAS getGAS(int gasId) { 
				 String sql = "SELECT gas_id,gas_name,gas_employee_no,gas_position,gas_email,gas_password,township.township_id,"
				 		+ "township.township_name,state_or_region.state_or_region_name"
				 		+ " FROM gas,township,state_or_region WHERE "
				 		+ "gas.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
				 		+ "gas_id=?";
				 
				 return template.queryForObject(sql, new RowMapper<GAS>(){
					 public GAS mapRow(ResultSet rs, int row) throws SQLException {
						 GAS g = new GAS();
						 g.setGasId(rs.getInt("gas_id"));
						 g.setGasName(rs.getString("gas_name"));
						 g.setGasEmployeeNo(rs.getString("gas_employee_no"));
						 g.setGasPosition(rs.getString("gas_position"));
						 g.setGasEmail(rs.getString("gas_email"));
						 g.setGasPassword(rs.getString("gas_password"));
						 g.setTownshipId(rs.getInt("township_id"));
						 g.setTownship(new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name")));
						 return g;
					 }
				 },gasId);
		}	
		
		//delete a general administrative staff 
		public int deleteGAS(int gas_id) {
			String sql = "delete from gas where gas_id="+gas_id+""; 
			return template.update(sql);
		}
		
		//add a general administrative staff 
		public int saveGAS(GAS g) {
			String sql = "insert into gas (gas_name,gas_employee_no,gas_position,gas_email,gas_password,township_id) values ('"+g.getGasName()+"','"+g.getGasEmployeeNo()+"','"+g.getGasPosition()+"',"
							+ "'"+g.getGasEmail()+"','"+g.getGasPassword()+"',"+g.getTownshipId()+")";
			return template.update(sql);
		}
		
		//update a general administrative staff 
		public int updateGAS(GAS g) {
			String sql = "update gas set gas_name='"+g.getGasName()+"',gas_employee_no='"+g.getGasEmployeeNo()+"',"
					+ "gas_position='"+g.getGasPosition()+"',gas_email='"+g.getGasEmail()+"',gas_password='"+g.getGasPassword()+"',township_id="+g.getTownshipId()+" where gas_id="+g.getGasId()+"";
			return template.update(sql); 
		}
				
		//-------------------------Admin Start-------------------------//
		
		//get number of general administrative staffs in Myanmar by admin
		public int getGASCountByAdmin() {
			String sql = "select count(*) from gas";
			return template.queryForObject(sql, Integer.class);
		}
		
		//get  general administrative staff list of Myanmar by admin
		public List<GAS> getGASListByAdmin() { 
				 String sql = "SELECT gas_id,gas_name,gas_employee_no,gas_position,gas_email,gas_password,"
				 		+ "gas.township_id,township_name FROM gas,township where gas.township_id = township.township_id";
				 
				 return template.query(sql, new RowMapper<GAS>(){
					 public GAS mapRow(ResultSet rs, int row) throws SQLException {
						 GAS w = new GAS();
						 w.setGasId(rs.getInt("gas_id"));
						 w.setGasName(rs.getString("gas_name"));
						 w.setGasEmployeeNo(rs.getString("gas_employee_no"));
						 w.setGasPosition(rs.getString("gas_position"));
						 w.setGasEmail(rs.getString("gas_email"));
						 w.setGasPassword(rs.getString("gas_password"));
						 w.setTownshipId(rs.getInt("township_id"));
						 w.setTownship(new Township(rs.getString("township_name")));
						 return w;
					 }
				 });
		}
		
		//-----------------Admin Finish----------------------//
		
		
		//----------------Sub Admin Start --------------------//
		
		//get number of general administrative staffs in a state/region by sub admin
		public int getGASCountBySAdmin(int stateOrRegionId) {
			String sql = "select count(*) from gas g,township t,state_or_region r,sadmin s where g.township_id = t.township_id"
					+ " and t.state_or_region_id = r.state_or_region_id and r.state_or_region_id = s.state_or_region_id and "
					+ "s.state_or_region_id = ?";
			return template.queryForObject(sql, Integer.class, stateOrRegionId);
		}
		
		public List<GAS> getGASListBySAdmin(int state_or_region_id) { 
				 String sql = "SELECT gas_id,gas_name,gas_employee_no,gas_position,gas_email,gas_password,"
				 		+ "gas.township_id,township_name FROM gas,township where gas.township_id=township.township_id and "
				 		+ "township.state_or_region_id = ?";
				 
				 return template.query(sql, new RowMapper<GAS>(){
					 public GAS mapRow(ResultSet rs, int row) throws SQLException {
						 GAS w = new GAS();
						 w.setGasId(rs.getInt("gas_id"));
						 w.setGasName(rs.getString("gas_name"));
						 w.setGasEmployeeNo(rs.getString("gas_employee_no"));
						 w.setGasPosition(rs.getString("gas_position"));
						 w.setGasEmail(rs.getString("gas_email"));
						 w.setGasPassword(rs.getString("gas_password"));
						 w.setTownshipId(rs.getInt("township_id"));
						 w.setTownship(new Township(rs.getString("township_name")));
						 return w;
					 }
				 },state_or_region_id);
		}
	//----------------------------------Sub Admin Finish-------------------------------------------------//	
	
	
}
