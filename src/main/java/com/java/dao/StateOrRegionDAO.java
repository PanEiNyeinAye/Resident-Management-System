package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.StateOrRegion;

public class StateOrRegionDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	public List<StateOrRegion> getStateOrRegionList(){
		return template.query("select * from state_or_region", new RowMapper<StateOrRegion>(){
			 public StateOrRegion mapRow(ResultSet rs, int row) throws SQLException {
				 StateOrRegion sr = new StateOrRegion();
				 sr.setStateOrRegionId(rs.getInt(1));
				 sr.setStateOrRegionName(rs.getString(2));
				 return sr;
			 }
		 });
	}
}
