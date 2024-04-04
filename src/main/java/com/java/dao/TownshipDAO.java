package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.StateOrRegion;
import com.java.beans.Township;

public class TownshipDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	@SuppressWarnings("deprecation") 
	public Township getTownshipByResident(int resident_id) {
		String sql = "select township.township_id,township_name,state_or_region_id from resident,ward_or_village,township where resident.ward_or_village_id="
				+ "ward_or_village.ward_or_village_id and ward_or_village.township_id=township.township_id and resident_id=?"; 
		return template.queryForObject(sql, new Object[] {resident_id},new BeanPropertyRowMapper<Township>(Township.class));
		
	}
	
	//get townships acrosss the country by admin
	public List<Township>getTownshipListByAdmin(){
		String sql = "select township_id,township_name,township.state_or_region_id,state_or_region_name from township,state_or_region where "
				+ "township.state_or_region_id = state_or_region.state_or_region_id";
		return template.query(sql, new RowMapper<Township>(){
			 public Township mapRow(ResultSet rs, int row) throws SQLException {
				 Township t = new Township();
				 t.setTownshipId(rs.getInt(1));
				 t.setTownshipName(rs.getString(2));
				 t.setStateOrRegionId(rs.getInt(3));
				 t.setStateOrRegion(new StateOrRegion(rs.getString(4)));
				 return t;
			 }
		 });
	}
	
	//get townships in a state/region by sadmin
	public List<Township>getTownshipListBySAdmin(int state_or_region_id){
		return template.query("select * from township where state_or_region_id=?", new RowMapper<Township>(){
			 public Township mapRow(ResultSet rs, int row) throws SQLException {
				 Township t = new Township();
				 t.setTownshipId(rs.getInt(1));
				 t.setTownshipName(rs.getString(2));
				 t.setStateOrRegionId(rs.getInt(3));
				 
				 return t;
			 }
		 },state_or_region_id);
	}
}
