package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.*;

public class WardASDAO{
	
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	} 
	
	//Validate WardAS Login
	public int validateWardAS(String email, String password) {
			String sql = "SELECT count(*) FROM wardas where wardas_email=? and wardas_password=?";
			return template.queryForObject(sql, Integer.class, email, password);
	}
	
	//get a ward or village administrtor by email and password
	public WardAS getWardAS(String wardas_email,String wardas_password) { 
			 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
			 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village.ward_or_village_name,township.township_name,state_or_region.state_or_region_name"
			 		+ " FROM wardas,ward_or_village,township,state_or_region WHERE wardas.ward_or_village_id= ward_or_village.ward_or_village_id "
			 		+ "and ward_or_village.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
			 		+ "wardas_email=? and wardas_password=?";
			 
			 return template.queryForObject(sql, new RowMapper<WardAS>(){
				 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
					 WardAS w = new WardAS();
					 w.setWardASId(rs.getInt("wardas_id"));
					 w.setWardASName(rs.getString("wardas_name"));
					 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
					 w.setWardASPosition(rs.getString("wardas_position"));
					 w.setWardASEmail(rs.getString("wardas_email"));
					 w.setWardASPassword(rs.getString("wardas_password"));
					 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
					 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
					 return w;
				 }
			 },wardas_email,wardas_password);
	}	
	
	public WardAS getWardAS(String ward_or_village_name,String township_name,String state_or_region_name) { 
			 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
			 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village.ward_or_village_name,township.township_name,state_or_region.state_or_region_name"
			 		+ " FROM wardas,ward_or_village,township,state_or_region WHERE wardas.ward_or_village_id= ward_or_village.ward_or_village_id "
			 		+ "and ward_or_village.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
			 		+ "ward_or_village_name=? and township_name=? and state_or_region_name=?";
			 
			 return template.queryForObject(sql, new RowMapper<WardAS>(){
				 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
					 WardAS w = new WardAS();
					 w.setWardASId(rs.getInt("wardas_id"));
					 w.setWardASName(rs.getString("wardas_name"));
					 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
					 w.setWardASPosition(rs.getString("wardas_position"));
					 w.setWardASEmail(rs.getString("wardas_email"));
					 w.setWardASPassword(rs.getString("wardas_password"));
					 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
					 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
					 return w;
				 }
			 },ward_or_village_name,township_name,state_or_region_name);
	   }
	
	//get a ward or village administrtor by wardas_id
		public WardAS getWardAS(int wardas_id) { 
				 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
				 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village.ward_or_village_name,township.township_name,state_or_region.state_or_region_name"
				 		+ " FROM wardas,ward_or_village,township,state_or_region WHERE wardas.ward_or_village_id= ward_or_village.ward_or_village_id "
				 		+ "and ward_or_village.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
				 		+ "wardas_id=?";
				 
				 return template.queryForObject(sql, new RowMapper<WardAS>(){
					 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
						 WardAS w = new WardAS();
						 w.setWardASId(rs.getInt("wardas_id"));
						 w.setWardASName(rs.getString("wardas_name"));
						 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
						 w.setWardASPosition(rs.getString("wardas_position"));
						 w.setWardASEmail(rs.getString("wardas_email"));
						 w.setWardASPassword(rs.getString("wardas_password"));
						 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
						 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
						 return w;
					 }
				 },wardas_id);
		}	
		//get a ward or village administrtor by ward_or_village_id
		public WardAS getWardASForLetter(int ward_or_village_id) { 
				 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
				 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village.ward_or_village_name,township.township_name,state_or_region.state_or_region_name"
				 		+ " FROM wardas,ward_or_village,township,state_or_region WHERE wardas.ward_or_village_id= ward_or_village.ward_or_village_id "
				 		+ "and ward_or_village.township_id=township.township_id and township.state_or_region_id=state_or_region.state_or_region_id and "
				 		+ "ward_or_village.ward_or_village_id=? and wardas_position not like ?";
				 
				 return template.queryForObject(sql, new RowMapper<WardAS>(){
					 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
						 WardAS w = new WardAS();
						 w.setWardASId(rs.getInt("wardas_id"));
						 w.setWardASName(rs.getString("wardas_name"));
						 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
						 w.setWardASPosition(rs.getString("wardas_position"));
						 w.setWardASEmail(rs.getString("wardas_email"));
						 w.setWardASPassword(rs.getString("wardas_password"));
						 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
						 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
						 return w;
					 }
				 },ward_or_village_id,"Ward/Village Tract staff");
		}
//-------------------------------------Admin Start----------------------------------------------------//
		//********************************Ward Administrator Management Start*******************//
		
		//delete wardas
		public int deleteWardAS(int wardasId) {
			String sql = "delete from wardas where wardas_id="+wardasId+""; 
			return template.update(sql);
		}
		
		//add a wardas
		public int saveWardAS(WardAS w) {
			String sql = "insert into wardas (wardas_name,wardas_employee_no,wardas_position,wardas_email,"
					+ "wardas_password,wardas_phone_no,ward_or_village_id) values ('"+w.getWardASName()+"','"+w.getWardASEmployeeNo()+"','"+w.getWardASPosition()+"','"+w.getWardASEmail()+"',"
							+ "'"+w.getWardASPassword()+"','"+w.getWardASPhoneNo()+"',"+w.getWardOrVillageId()+")";
			return template.update(sql);
		}
		
		//update a wardas
			public int updateWardAS(WardAS w) {
				String sql = "update wardas set wardas_name='"+w.getWardASName()+"',"
								+ "wardas_employee_no='"+w.getWardASEmployeeNo()+"',wardas_position='"+w.getWardASPosition()+"',"
										+ "wardas_email='"+w.getWardASEmail()+"',wardas_password='"+w.getWardASPassword()+"',wardas_phone_no='"+w.getWardASPhoneNo()+"',"
												+ "ward_or_village_id="+w.getWardOrVillageId()+" where wardas_id="+w.getWardASId()+"";
				return template.update(sql); 
			}
	//********************************Ward Administrator Management End*******************//
			
		
	//get  ward or village administrtor list of Myanmar by admin
	public List<WardAS> getWardOrVillageASListByAdmin() { 
			 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
			 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village where "
			 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id";
			 
			 return template.query(sql, new RowMapper<WardAS>(){
				 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
					 WardAS w = new WardAS();
					 w.setWardASId(rs.getInt("wardas_id"));
					 w.setWardASName(rs.getString("wardas_name"));
					 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
					 w.setWardASPosition(rs.getString("wardas_position"));
					 w.setWardASEmail(rs.getString("wardas_email"));
					 w.setWardASPassword(rs.getString("wardas_password"));
					 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
					 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));					 
					 return w;
				 }
			 });
	}	
	
	//get  ward administrtor list of Myanmar by admin
		public List<WardAS> getWardASListByAdmin() { 
				 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
				 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village where "
				 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village_name like ?";
				 
				 return template.query(sql, new RowMapper<WardAS>(){
					 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
						 WardAS w = new WardAS();
						 w.setWardASId(rs.getInt("wardas_id"));
						 w.setWardASName(rs.getString("wardas_name"));
						 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
						 w.setWardASPosition(rs.getString("wardas_position"));
						 w.setWardASEmail(rs.getString("wardas_email"));
						 w.setWardASPassword(rs.getString("wardas_password"));
						 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
						 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));					 
						 return w;
					 }
				 },"%Ward");
		}	
		
		//get  village administrtor list of Myanmar by admin
				public List<WardAS> getVillageASListByAdmin() { 
						 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
						 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village where "
						 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village_name like ?";
						 
						 return template.query(sql, new RowMapper<WardAS>(){
							 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
								 WardAS w = new WardAS();
								 w.setWardASId(rs.getInt("wardas_id"));
								 w.setWardASName(rs.getString("wardas_name"));
								 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
								 w.setWardASPosition(rs.getString("wardas_position"));
								 w.setWardASEmail(rs.getString("wardas_email"));
								 w.setWardASPassword(rs.getString("wardas_password"));
								 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
								 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
								 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
								 return w;
							 }
						 },"%Village");
				}	
	
	//get number of ward or village administrators in Myanmar by admin
	public int getWardOrVillageASCountByAdmin() {
		String sql = "select count(*) from wardas";
		return template.queryForObject(sql, Integer.class);
	}
	public int getWardASCountByAdmin() {
		String sql = "select count(*) from wardas,ward_or_village where wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village_name like ?";
		return template.queryForObject(sql, Integer.class,"%Ward");
	}
	public int getVillageASCountByAdmin() {
		String sql = "select count(*) from wardas,ward_or_village where wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village_name like ?";
		return template.queryForObject(sql, Integer.class,"%Village");
	}
	//----------------------------------------------Admin End----------------------------------------------------//
	
	//----------------------------------------------Sub Admin Start----------------------------------------------//
	//get number of ward or village administrators in a state/region by sub admin
	public int getWardOrVillageASCountBySAdmin(int state_or_region_id) {
		String sql = "select count(*) from wardas w, ward_or_village v,township t, state_or_region r, sadmin s where w.ward_or_village_id"
				+ "= v.ward_or_village_id and v.township_id = t.township_id and t.state_or_region_id = r.state_or_region_id and "
				+ "r.state_or_region_id = s.state_or_region_id and s.state_or_region_id = ?";
		return template.queryForObject(sql, Integer.class, state_or_region_id);
	}
	public int getWardASCountBySAdmin(int state_or_region_id) {
		String sql = "select count(*) from wardas w, ward_or_village v,township t, state_or_region r, sadmin s where w.ward_or_village_id"
				+ "= v.ward_or_village_id and v.township_id = t.township_id and t.state_or_region_id = r.state_or_region_id and "
				+ "r.state_or_region_id = s.state_or_region_id and s.state_or_region_id = ? and  ward_or_village_name like ?";
		return template.queryForObject(sql, Integer.class, state_or_region_id,"%Ward");
	}
	
	public int getVillageASCountBySAdmin(int state_or_region_id) {
		String sql = "select count(*) from wardas w, ward_or_village v,township t, state_or_region r, sadmin s where w.ward_or_village_id"
				+ "= v.ward_or_village_id and v.township_id = t.township_id and t.state_or_region_id = r.state_or_region_id and "
				+ "r.state_or_region_id = s.state_or_region_id and s.state_or_region_id = ? and  ward_or_village_name like ?";
		return template.queryForObject(sql, Integer.class, state_or_region_id,"%Village");
	}
	
	//get  ward or village administrtor list of a state/region by sub admin
		public List<WardAS> getWardOrVillageASListBySAdmin(int state_or_region_id) { 
			 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
				 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village,township,state_or_region where "
				 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id = township.township_id "
				 		+ "and township.state_or_region_id = state_or_region.state_or_region_id and state_or_region.state_or_region_id = ?";
				 
				 return template.query(sql, new RowMapper<WardAS>(){
					 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
						 WardAS w = new WardAS();
						 w.setWardASId(rs.getInt("wardas_id"));
						 w.setWardASName(rs.getString("wardas_name"));
						 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
						 w.setWardASPosition(rs.getString("wardas_position"));
						 w.setWardASEmail(rs.getString("wardas_email"));
						 w.setWardASPassword(rs.getString("wardas_password"));
						 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
						 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return w;
					 }
				 },state_or_region_id);
		}	
		
		//get  ward administrtor list of of a state/region by sub admin
			public List<WardAS> getWardASListBySAdmin(int state_or_region_id) { 
					 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
					 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village,township,state_or_region where "
					 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id = township.township_id "
					 		+ "and township.state_or_region_id = state_or_region.state_or_region_id and state_or_region.state_or_region_id = ? and  ward_or_village_name like ?";
					 
					 return template.query(sql, new RowMapper<WardAS>(){
						 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
							 WardAS w = new WardAS();
							 w.setWardASId(rs.getInt("wardas_id"));
							 w.setWardASName(rs.getString("wardas_name"));
							 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
							 w.setWardASPosition(rs.getString("wardas_position"));
							 w.setWardASEmail(rs.getString("wardas_email"));
							 w.setWardASPassword(rs.getString("wardas_password"));
							 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
							 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return w;
						 }
					 },state_or_region_id,"%Ward");
			}	
			
	//get  village administrtor list of of a state/region by sub admin
		public List<WardAS> getVillageASListBySAdmin(int stateOrRegionId) { 
			String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
			 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village,township,state_or_region where "
			 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id = township.township_id "
			 		+ "and township.state_or_region_id = state_or_region.state_or_region_id and state_or_region.state_or_region_id = ? and  ward_or_village_name like ?";
				 
				 return template.query(sql, new RowMapper<WardAS>(){
					 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
						 WardAS w = new WardAS();
						 w.setWardASId(rs.getInt("wardas_id"));
						 w.setWardASName(rs.getString("wardas_name"));
						 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
						 w.setWardASPosition(rs.getString("wardas_position"));
						 w.setWardASEmail(rs.getString("wardas_email"));
						 w.setWardASPassword(rs.getString("wardas_password"));
						 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
						 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return w;
					 }
				 },stateOrRegionId,"%Village");
		}
					
	//--------------------------------------------------Sub Admin End----------------------------------------------------------//	
	//--------------------------------------------------GAS Start--------------------------------------------------------------//
	//get number of ward administrator in a township by general administrative staff
	public int getWardOrVillageASCountByGAS(int township_id) {
		String sql = "select count(*) from wardas w, ward_or_village v,township t, gas g where w.ward_or_village_id"
				+ "= v.ward_or_village_id and v.township_id = t.township_id and t.township_id = g.township_id and "
				+ "g.township_id = ?";
		return template.queryForObject(sql, Integer.class, township_id);
	}
	public int getWardASCountByGAS(int township_id) {
		String sql = "select count(*) from wardas w, ward_or_village v,township t, gas g where w.ward_or_village_id"
				+ "= v.ward_or_village_id and v.township_id = t.township_id and t.township_id = g.township_id and ward_or_village_name like ? and"
				+ " g.township_id = ?";
		return template.queryForObject(sql, Integer.class,"%Ward", township_id);
	}
	public int getVillageASCountByGAS(int township_id) {
		String sql = "select count(*) from wardas w, ward_or_village v,township t, gas g where w.ward_or_village_id"
				+ "= v.ward_or_village_id and v.township_id = t.township_id and t.township_id = g.township_id and ward_or_village_name like ? and"
				+ " g.township_id = ?";
		return template.queryForObject(sql, Integer.class,"%Village", township_id);
	}
	//get  ward or village administrtor list of township by gas
			public List<WardAS> getWardOrVillageASListByGAS(int township_id) { 
				 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
					 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village where "
					 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id =?";
					 
					 return template.query(sql, new RowMapper<WardAS>(){
						 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
							 WardAS w = new WardAS();
							 w.setWardASId(rs.getInt("wardas_id"));
							 w.setWardASName(rs.getString("wardas_name"));
							 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
							 w.setWardASPosition(rs.getString("wardas_position"));
							 w.setWardASEmail(rs.getString("wardas_email"));
							 w.setWardASPassword(rs.getString("wardas_password"));
							 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
							 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return w;
						 }
					 },township_id);
			}	
			
			//get  ward administrtor list of township by gas
				public List<WardAS> getWardASListByGAS(int township_id) { 
						 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
						 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village where "
						 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id = ? and  ward_or_village_name like ?";
						 
						 return template.query(sql, new RowMapper<WardAS>(){
							 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
								 WardAS w = new WardAS();
								 w.setWardASId(rs.getInt("wardas_id"));
								 w.setWardASName(rs.getString("wardas_name"));
								 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
								 w.setWardASPosition(rs.getString("wardas_position"));
								 w.setWardASEmail(rs.getString("wardas_email"));
								 w.setWardASPassword(rs.getString("wardas_password"));
								 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
								 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
								 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
								 return w;
							 }
						 },township_id,"%Ward");
				}	
				
		//get  village administrtor list of township by gas
			public List<WardAS> getVillageASListByGAS(int township_id) { 
				 String sql = "SELECT wardas_id,wardas_name,wardas_employee_no,wardas_position,wardas_email,wardas_password,"
					 		+ "wardas_phone_no,wardas.ward_or_village_id,ward_or_village_name FROM wardas,ward_or_village where "
					 		+ "wardas.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id = ? and  ward_or_village_name like ?";
					 return template.query(sql, new RowMapper<WardAS>(){
						 public WardAS mapRow(ResultSet rs, int row) throws SQLException {
							 WardAS w = new WardAS();
							 w.setWardASId(rs.getInt("wardas_id"));
							 w.setWardASName(rs.getString("wardas_name"));
							 w.setWardASEmployeeNo(rs.getString("wardas_employee_no"));
							 w.setWardASPosition(rs.getString("wardas_position"));
							 w.setWardASEmail(rs.getString("wardas_email"));
							 w.setWardASPassword(rs.getString("wardas_password"));
							 w.setWardASPhoneNo(rs.getString("wardas_phone_no"));
							 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 w.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return w;
						 }
					 },township_id, "%Village");
			}
	//---------------------------------------------------GAS Finish--------------------------------------------------------------//
}
