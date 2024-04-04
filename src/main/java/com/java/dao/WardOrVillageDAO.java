package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.java.beans.Township;
import com.java.beans.WardOrVillage;

public class WardOrVillageDAO {
	
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//get ward and village across the country by admin 
	public List<WardOrVillage> getWardOrVillageListByAdmin(){
		String sql = "select ward_or_village_id,ward_or_village_name,township.township_id,township_name from ward_or_village,"
				+ "township where ward_or_village.township_id = township.township_id";
		return template.query(sql, new RowMapper<WardOrVillage>(){
			 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
				 WardOrVillage w = new WardOrVillage();
				 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
				 w.setTownshipId(rs.getInt("township_id"));
				 w.setTownship(new Township(rs.getString("township_name")));
				 return w;
			 }
		 });
		
	}
	
	//get wards across the country by admin
	public List<WardOrVillage> getWardListByAdmin(){
		String sql = "select ward_or_village_id,ward_or_village_name,township.township_id,township_name from ward_or_village,"
				+ "township where ward_or_village.township_id = township.township_id and ward_or_village_name like ?";
		return template.query(sql, new RowMapper<WardOrVillage>(){
			 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
				 WardOrVillage w = new WardOrVillage();
				 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
				 w.setTownshipId(rs.getInt("township_id"));
				 w.setTownship(new Township(rs.getString("township_name")));
				 return w;
			 }
		 },"%Ward");
	}
	
	//get villages across the country by admin
	public List<WardOrVillage> getVillageListByAdmin(){
		String sql = "select ward_or_village_id,ward_or_village_name,township.township_id,township_name from ward_or_village,"
				+ "township where ward_or_village.township_id = township.township_id and ward_or_village_name like ?";
		return template.query(sql, new RowMapper<WardOrVillage>(){
			 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
				 WardOrVillage w = new WardOrVillage();
				 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
				 w.setTownshipId(rs.getInt("township_id"));
				 w.setTownship(new Township(rs.getString("township_name")));
				 return w;
			 }
		 },"%Village");
		
	}
	
	
	/*public List<WardOrVillage> getWardOrVillageListByAdmin(){
		String sql = "SELECT ward_or_village_id,ward_or_village_name,township.township_id,township_name,state_or_region."
				+ "state_or_region_id, state_or_region_name "
				+ "FROM ward_or_village, township, state_or_region where ward_or_village.township_id = township.township_id "
				+ "and township.state_or_region_id=state_or_region.state_or_region_id";
		return template.query(sql, new RowMapper<WardOrVillage>(){
			 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
				 WardOrVillage w = new WardOrVillage();
				 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
				 w.setTownshipId(rs.getInt("township_id"));
				 w.setTownship(new Township(new StateOrRegion(rs.getInt("state_or_region_id"),rs.getString("state_or_region_name")),rs.getString("township_name")));
				 return w;
			 }
		 });
		
	}*/
	//-----------------------------------------------Sub Admin Start---------------------------------------------------------------------//
	//get ward and village across the country by sadmin 
		public List<WardOrVillage> getWardOrVillageListBySAdmin(int state_or_region_id){
			String sql ="select ward_or_village_id,ward_or_village_name,township.township_id from ward_or_village,township,"
					+ " state_or_region where ward_or_village.township_id = township.township_id and township.state_or_region_id=state_or_region."
					+ "state_or_region_id and township.state_or_region_id=?";
			return template.query(sql, new RowMapper<WardOrVillage>(){
				 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
					 WardOrVillage w = new WardOrVillage();
					 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
					 w.setTownshipId(rs.getInt("township_id"));
					 return w;
				 }
			 },state_or_region_id);
			
		}
		
		//get wards across the country by sadmin
		public List<WardOrVillage> getWardListBySAdmin(int state_or_region_id){
			String sql ="select ward_or_village_id,ward_or_village_name,township.township_id from ward_or_village,township,"
					+ " state_or_region where ward_or_village.township_id = township.township_id and township.state_or_region_id=state_or_region."
					+ "state_or_region_id and township.state_or_region_id=? and ward_or_village_name like ?";
			return template.query(sql, new RowMapper<WardOrVillage>(){
				 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
					 WardOrVillage w = new WardOrVillage();
					 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
					 w.setTownshipId(rs.getInt("township_id"));
					 return w;
				 }
			 },state_or_region_id, "%Ward");
		}
		
		//get villages across the country by sadmin
		public List<WardOrVillage> getVillageListBySAdmin(int state_or_region_id){
			String sql ="select ward_or_village_id,ward_or_village_name,township.township_id from ward_or_village,township,"
					+ " state_or_region where ward_or_village.township_id = township.township_id and township.state_or_region_id=state_or_region."
					+ "state_or_region_id and township.state_or_region_id=? and ward_or_village_name like ?";
			return template.query(sql, new RowMapper<WardOrVillage>(){
				 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
					 WardOrVillage w = new WardOrVillage();
					 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
					 w.setTownshipId(rs.getInt("township_id"));
					 return w;
				 }
			 },state_or_region_id,"%Village");
			
		}
		//-----------------------------------------------Sub Admin Finish---------------------------------------------------------------------//
		//-----------------------------------------------GAS Start---------------------------------------------------------------------//
		//get ward and village in a township by gas
				public List<WardOrVillage> getWardOrVillageListByGAS(int township_id){
					String sql ="select ward_or_village_id,ward_or_village_name,township.township_id,township_name from ward_or_village,township"
							+ " where ward_or_village.township_id = township.township_id and township.township_id=?";
					return template.query(sql, new RowMapper<WardOrVillage>(){
						 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
							 WardOrVillage w = new WardOrVillage();
							 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
							 w.setTownshipId(rs.getInt("township_id"));
							 w.setTownship(new Township(rs.getString("township_name")));
							 return w;
						 }
					 },township_id);
					
				}
				
				//get wards in a township by gas
				public List<WardOrVillage> getWardListByGAS(int township_id){
					String sql ="select ward_or_village_id,ward_or_village_name,township.township_id,township_name from ward_or_village,township"
							+ " where ward_or_village.township_id = township.township_id and township.township_id=? and ward_or_village_name like ?";
					return template.query(sql, new RowMapper<WardOrVillage>(){
						 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
							 WardOrVillage w = new WardOrVillage();
							 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
							 w.setTownshipId(rs.getInt("township_id"));
							 w.setTownship(new Township(rs.getString("township_name")));
							 return w;
						 }
					 },township_id,"%Ward");
				}
				
				//get villages in a township by gas
				public List<WardOrVillage> getVillageListByGAS(int township_id){
					String sql ="select ward_or_village_id,ward_or_village_name,township.township_id,township_name from ward_or_village,township"
							+ " where ward_or_village.township_id = township.township_id and township.township_id=? and ward_or_village_name like ?";
					return template.query(sql, new RowMapper<WardOrVillage>(){
						 public WardOrVillage mapRow(ResultSet rs, int row) throws SQLException {
							 WardOrVillage w = new WardOrVillage();
							 w.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 w.setWardOrVillageName(rs.getString("ward_or_village_name"));
							 w.setTownshipId(rs.getInt("township_id"));
							 w.setTownship(new Township(rs.getString("township_name")));
							 return w;
						 }
					 },township_id,"%Village");
					
				}
		//-----------------------------------------------GAS Finish---------------------------------------------------------------------//
}
