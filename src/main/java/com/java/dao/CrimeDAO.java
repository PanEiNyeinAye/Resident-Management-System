package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.Crime;
import com.java.beans.Resident;

public class CrimeDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//get a crime  info by crime_id
	public Crime getCrime(int crime_id) {
		String sql = "select criminal_record_and_resident_id,crime,criminal_record_date,police_officer_sign,crime_scene_place,result_of_the_trial,"
				+ "resident.resident_id,nrc_no from criminal_record_and_resident,resident where criminal_record_and_resident.resident_id = resident.resident_id and criminal_record_and_resident_id=?";
		 return template.queryForObject(sql, new RowMapper<Crime>(){
			 public Crime mapRow(ResultSet rs, int row) throws SQLException {
				 Crime c = new Crime();
				 c.setCrimeId(rs.getInt(1));
				 c.setCrimeName(rs.getString(2));
				 c.setCriminalRecordDate(rs.getDate(3));
				 c.setPoliceOfficerSign(rs.getString(4));
				 c.setCrimeScenePlace(rs.getString(5));
				 c.setResultOfTheTrial(rs.getInt(6));
				 c.setResidentId(rs.getInt(7));
				 c.setResident(new Resident(rs.getString(8)));
				 return c;
			 }
		 },crime_id);
	}
	
	//get a crime  info by crime_id
		public Crime getCrimeByResidentId(int resident_id) {
			try
			{ String sql = "select criminal_record_and_resident_id,crime,criminal_record_date,police_officer_sign,crime_scene_place,result_of_the_trial,"
					+ "resident.resident_id,nrc_no from criminal_record_and_resident,resident where criminal_record_and_resident.resident_id = resident.resident_id and resident.resident_id=?";
			 return template.queryForObject(sql, new RowMapper<Crime>(){
				 public Crime mapRow(ResultSet rs, int row) throws SQLException {
					 Crime c = new Crime();
					 c.setCrimeId(rs.getInt(1));
					 c.setCrimeName(rs.getString(2));
					 c.setCriminalRecordDate(rs.getDate(3));
					 c.setPoliceOfficerSign(rs.getString(4));
					 c.setCrimeScenePlace(rs.getString(5));
					 c.setResultOfTheTrial(rs.getInt(6));
					 c.setResidentId(rs.getInt(7));
					 c.setResident(new Resident(rs.getString(8)));
					 return c;
				 }
			 },resident_id);}
			catch (DataAccessException e)
			{
			    return null;
			}
		}	
		
		
	//get criminal records in a township
	public List<Crime> getCriminalRecordListByPoliceOfficer(int township_id){
		String sql = "select criminal_record_and_resident_id,crime,criminal_record_date,police_officer_sign,crime_scene_place,result_of_the_trial,"
				+ "resident.resident_id,nrc_no from criminal_record_and_resident,resident where criminal_record_and_resident.resident_id = resident.resident_id and township_id=?";
		return template.query(sql, new RowMapper<Crime>(){
			 public Crime mapRow(ResultSet rs, int row) throws SQLException {
				 Crime c = new Crime();
				 c.setCrimeId(rs.getInt(1));
				 c.setCrimeName(rs.getString(2));
				 c.setCriminalRecordDate(rs.getDate(3));
				 c.setPoliceOfficerSign(rs.getString(4));
				 c.setCrimeScenePlace(rs.getString(5));
				 c.setResultOfTheTrial(rs.getInt(6));
				 c.setResidentId(rs.getInt(7));
				 c.setResident(new Resident(rs.getString(8)));
				 return c;
			 }
		 },township_id);
	}
	//delete a crime 
		public int deleteCrime(int id) {
			String sql = "delete from criminal_record_and_resident where criminal_record_and_resident_id="+id+""; 
			return template.update(sql);
		}
		
		//add a crime
		public int saveCrime(Crime c) {
			String sql = "insert into criminal_record_and_resident (crime,criminal_record_date,police_officer_sign,crime_scene_place,result_of_the_trial,resident_id,township_id) values "
					+ "('"+c.getCrimeName()+"','"+c.getCriminalRecordDate()+"','"+c.getPoliceOfficerSign()+"',"
							+ "'"+c.getCrimeScenePlace()+"',"+c.getResultOfTheTrial()+","+c.getResidentId()+","+c.getTownshipId()+")";
			return template.update(sql);
		}
		
		//update a crime
		public int updateCrime(Crime c) {
			String sql = "update criminal_record_and_resident set crime='"+c.getCrimeName()+"',criminal_record_date='"+c.getCriminalRecordDate()+"',"
					+ "police_officer_sign='"+c.getPoliceOfficerSign()+"',crime_scene_place='"+c.getCrimeScenePlace()+"',result_of_the_trial="+c.getResultOfTheTrial()+",resident_id="+c.getResidentId()+" where criminal_record_and_resident_id="+c.getCrimeId()+"";
			return template.update(sql); 
		}
}
