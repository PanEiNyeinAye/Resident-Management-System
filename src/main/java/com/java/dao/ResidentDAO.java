package com.java.dao;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import com.java.beans.*;
public class ResidentDAO {
	
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	} 
	
//Validate Resident Login
	public int validateResident(String email,String password) {
				String sql = "SELECT count(*) FROM resident where resident_email=? and resident_password=?";
				return template.queryForObject(sql, Integer.class, email, password);
	}
	
//Check Nrc Duplicate
	public int checkNrcDuplication(String nrc) {
		String sql = "select count(*) from resident where nrc_no=?";
		return template.queryForObject(sql, Integer.class,nrc);
	}
		
	//get a specific resident info by resident_id
	public Resident getResident(int residentId) {
		String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
				+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
				+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name,township_name,state_or_region_name from resident,ward_or_village,township,state_or_region where "
				+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id = township."
				+ "township_id and township.state_or_region_id = state_or_region.state_or_region_id and deceased=false and resident.resident_id=? ";
		 return template.queryForObject(sql, new RowMapper<Resident>(){
			 public Resident mapRow(ResultSet rs, int row) throws SQLException {
				 Resident r = new Resident();
				 r.setResidentId(rs.getInt("resident_id"));	
				 r.setResidentName(rs.getString("resident_name"));
				 r.setGender(rs.getString("gender"));
				 r.setDob(rs.getDate("dob"));
				 r.setNrcNo(rs.getString("nrc_no"));
				 r.setFatherName(rs.getString("father_name"));
				 r.setMotherName(rs.getString("mother_name"));
				 r.setEthnicity(rs.getString("ethnicity"));
				 r.setNote(rs.getString("note"));
				 r.setResidentEmail(rs.getString("resident_email"));
				 r.setResidentPassword(rs.getString("resident_password"));
				 r.setBcg(rs.getBoolean("bcg"));
				 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
				 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
				 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
				 r.setVitaminA(rs.getBoolean("vitamin_a"));
				 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));
				 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
				 return r;
			 }
		 },residentId);	 
			 
	}
	//get a specific resident(Death or Alive) info by resident_id
	public Resident getDeceasedAliveResident(int resident_id) {
		String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
				+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
				+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name,township_name,state_or_region_name from resident,ward_or_village,township,state_or_region where "
				+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id = township."
				+ "township_id and township.state_or_region_id = state_or_region.state_or_region_id and resident.resident_id=? ";
		 return template.queryForObject(sql, new RowMapper<Resident>(){
			 public Resident mapRow(ResultSet rs, int row) throws SQLException {
				 Resident r = new Resident();
				 r.setResidentId(rs.getInt("resident_id"));	
				 r.setResidentName(rs.getString("resident_name"));
				 r.setGender(rs.getString("gender"));
				 r.setDob(rs.getDate("dob"));
				 r.setNrcNo(rs.getString("nrc_no"));
				 r.setFatherName(rs.getString("father_name"));
				 r.setMotherName(rs.getString("mother_name"));
				 r.setEthnicity(rs.getString("ethnicity"));
				 r.setNote(rs.getString("note"));
				 r.setResidentEmail(rs.getString("resident_email"));
				 r.setResidentPassword(rs.getString("resident_password"));
				 r.setBcg(rs.getBoolean("bcg"));
				 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
				 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
				 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
				 r.setVitaminA(rs.getBoolean("vitamin_a"));
				 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));
				 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
				 return r;
			 }
		 },resident_id);	 
			 
	}
	//get a specific resident info by nrc number
		public Resident getResident(String nrcNo) {
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
					+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
					+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name,township_name,state_or_region_name from resident,ward_or_village,township,state_or_region where "
					+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id = township."
					+ "township_id and township.state_or_region_id = state_or_region.state_or_region_id and deceased=false and nrc_no=? ";
			 return template.queryForObject(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt("resident_id"));	
					 r.setResidentName(rs.getString("resident_name"));
					 r.setGender(rs.getString("gender"));
					 r.setDob(rs.getDate("dob"));
					 r.setNrcNo(rs.getString("nrc_no"));
					 r.setFatherName(rs.getString("father_name"));
					 r.setMotherName(rs.getString("mother_name"));
					 r.setEthnicity(rs.getString("ethnicity"));
					 r.setNote(rs.getString("note"));
					 r.setResidentEmail(rs.getString("resident_email"));
					 r.setResidentPassword(rs.getString("resident_password"));
					 r.setBcg(rs.getBoolean("bcg"));
					 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
					 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
					 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
					 r.setVitaminA(rs.getBoolean("vitamin_a"));
					 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));
					 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
					 return r;
				 }
			 },nrcNo);	 
				 
		}
	//get a specific resident info by email and password
	public Resident getResident(String email,String password) {
		String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
				+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
				+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name,township_name,state_or_region_name from resident,ward_or_village,township,state_or_region where "
				+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id = township."
				+ "township_id and township.state_or_region_id = state_or_region.state_or_region_id and deceased=false and resident_email= ? and resident_password=? ";
		 return template.queryForObject(sql, new RowMapper<Resident>(){
			 public Resident mapRow(ResultSet rs, int row) throws SQLException {
				 Resident r = new Resident();
				 r.setResidentId(rs.getInt("resident_id"));	
				 r.setResidentName(rs.getString("resident_name"));
				 r.setGender(rs.getString("gender"));
				 r.setDob(rs.getDate("dob"));
				 r.setNrcNo(rs.getString("nrc_no"));
				 r.setFatherName(rs.getString("father_name"));
				 r.setMotherName(rs.getString("mother_name"));
				 r.setEthnicity(rs.getString("ethnicity"));
				 r.setNote(rs.getString("note"));
				 r.setResidentEmail(rs.getString("resident_email"));
				 r.setResidentPassword(rs.getString("resident_password"));
				 r.setBcg(rs.getBoolean("bcg"));
				 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
				 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
				 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
				 r.setVitaminA(rs.getBoolean("vitamin_a"));
				 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));
				 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
				 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name"),new Township(new StateOrRegion(rs.getString("state_or_region_name")),rs.getString("township_name"))));
				 return r;
			 }
		 },email,password);
	}
		
		
//----------------------------------------------Admin Start---------------------------------------//	
	//Get resident list living in Myanmar
	public List<Resident> getResidentListByAdmin(){
		String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
				+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
				+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name from resident,ward_or_village where "
				+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false";
		return template.query(sql, new RowMapper<Resident>(){
			 public Resident mapRow(ResultSet rs, int row) throws SQLException {
				 Resident r = new Resident();
				 r.setResidentId(rs.getInt(1));	
				 r.setResidentName(rs.getString(2));
				 r.setGender(rs.getString(3));
				 r.setDob(rs.getDate(4));
				 r.setNrcNo(rs.getString(5));
				 r.setFatherName(rs.getString(6));
				 r.setMotherName(rs.getString(7));
				 r.setEthnicity(rs.getString(8));
				 r.setNote(rs.getString(9));
				 r.setResidentEmail(rs.getString(10));
				 r.setResidentPassword(rs.getString(11));
				 r.setBcg(rs.getBoolean(12));
				 r.setFiveVaccines1(rs.getBoolean(13));
				 r.setFiveVaccines2(rs.getBoolean(14));
				 r.setFiveVaccines3(rs.getBoolean(15));
				 r.setVitaminA(rs.getBoolean(16));
				 r.setHpvVaccine(rs.getBoolean(17));
				 r.setWardOrVillageId(rs.getInt(18));
				 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
				 return r;
			 }
		 });
	}
	//Get resident list (both death and alive) of Myanmar
	public List<Resident> getDeceasedAliveResidentListByAdmin(){
		String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
				+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
				+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name from resident,ward_or_village where "
				+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id";
		return template.query(sql, new RowMapper<Resident>(){
			 public Resident mapRow(ResultSet rs, int row) throws SQLException {
				 Resident r = new Resident();
				 r.setResidentId(rs.getInt(1));	
				 r.setResidentName(rs.getString(2));
				 r.setGender(rs.getString(3));
				 r.setDob(rs.getDate(4));
				 r.setNrcNo(rs.getString(5));
				 r.setFatherName(rs.getString(6));
				 r.setMotherName(rs.getString(7));
				 r.setEthnicity(rs.getString(8));
				 r.setNote(rs.getString(9));
				 r.setResidentEmail(rs.getString(10));
				 r.setResidentPassword(rs.getString(11));
				 r.setBcg(rs.getBoolean(12));
				 r.setFiveVaccines1(rs.getBoolean(13));
				 r.setFiveVaccines2(rs.getBoolean(14));
				 r.setFiveVaccines3(rs.getBoolean(15));
				 r.setVitaminA(rs.getBoolean(16));
				 r.setHpvVaccine(rs.getBoolean(17));
				 r.setWardOrVillageId(rs.getInt(18));
				 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
				 return r;
			 }
		 });
	}
	//Get female resident list living in Myanmar
		public List<Resident> getFemaleResidentListByAdmin(){
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
					+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
					+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name from resident,ward_or_village where "
					+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and gender=?";
			return template.query(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt(1));	
					 r.setResidentName(rs.getString(2));
					 r.setGender(rs.getString(3));
					 r.setDob(rs.getDate(4));
					 r.setNrcNo(rs.getString(5));
					 r.setFatherName(rs.getString(6));
					 r.setMotherName(rs.getString(7));
					 r.setEthnicity(rs.getString(8));
					 r.setNote(rs.getString(9));
					 r.setResidentEmail(rs.getString(10));
					 r.setResidentPassword(rs.getString(11));
					 r.setBcg(rs.getBoolean(12));
					 r.setFiveVaccines1(rs.getBoolean(13));
					 r.setFiveVaccines2(rs.getBoolean(14));
					 r.setFiveVaccines3(rs.getBoolean(15));
					 r.setVitaminA(rs.getBoolean(16));
					 r.setHpvVaccine(rs.getBoolean(17));
					 r.setWardOrVillageId(rs.getInt(18));
					 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
					 return r;
				 }
			 },"Female");
		}
		
		//Get male resident list living in Myanmar
				public List<Resident> getMaleResidentListByAdmin(){
					String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
							+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
							+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name from resident,ward_or_village where "
							+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and gender=?";
					return template.query(sql, new RowMapper<Resident>(){
						 public Resident mapRow(ResultSet rs, int row) throws SQLException {
							 Resident r = new Resident();
							 r.setResidentId(rs.getInt(1));	
							 r.setResidentName(rs.getString(2));
							 r.setGender(rs.getString(3));
							 r.setDob(rs.getDate(4));
							 r.setNrcNo(rs.getString(5));
							 r.setFatherName(rs.getString(6));
							 r.setMotherName(rs.getString(7));
							 r.setEthnicity(rs.getString(8));
							 r.setNote(rs.getString(9));
							 r.setResidentEmail(rs.getString(10));
							 r.setResidentPassword(rs.getString(11));
							 r.setBcg(rs.getBoolean(12));
							 r.setFiveVaccines1(rs.getBoolean(13));
							 r.setFiveVaccines2(rs.getBoolean(14));
							 r.setFiveVaccines3(rs.getBoolean(15));
							 r.setVitaminA(rs.getBoolean(16));
							 r.setHpvVaccine(rs.getBoolean(17));
							 r.setWardOrVillageId(rs.getInt(18));
							 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
							 return r;
						 }
					 },"Male");
				}
	
	//get number of residents in Myanmar by admin
		public int getResidentCountByAdmin() {
			String sql = "select count(*) from resident where deceased=false";
			return template.queryForObject(sql, Integer.class);
		}
		public int getFemaleResidentCountByAdmin() {
			String sql = "select count(*) from resident where deceased=false and gender=?";
			return template.queryForObject(sql, Integer.class,"Female");
		}
		public int getMaleResidentCountByAdmin() {
			String sql = "select count(*) from resident where deceased=false and gender=?";
			return template.queryForObject(sql, Integer.class,"Male");
		}
 //-----------------------------------------------------Admin End------------------------------------------------------------------//

//----------------------------------------------------Sub Admin Start----------------------------------------------------------//		
		//get number of residents in a state/region by sub admin
		public int getResidentCountBySAdmin(int state_or_region_id) {
			String sql = "select count(*) from resident re, ward_or_village v,township t, state_or_region r, sadmin s where re.ward_or_village_id"
					+ "= v.ward_or_village_id and v.township_id = t.township_id and t.state_or_region_id = r.state_or_region_id and "
					+ "r.state_or_region_id = s.state_or_region_id and deceased=false and s.state_or_region_id = ?";
			return template.queryForObject(sql, Integer.class, state_or_region_id);
		}
		
		public int getFemaleResidentCountBySAdmin(int state_or_region_id) {
			String sql = "select count(*) from resident re, ward_or_village v,township t, state_or_region r, sadmin s where re.ward_or_village_id"
					+ "= v.ward_or_village_id and v.township_id = t.township_id and t.state_or_region_id = r.state_or_region_id and "
					+ "r.state_or_region_id = s.state_or_region_id and deceased=false and s.state_or_region_id = ? and gender=?";
			return template.queryForObject(sql, Integer.class, state_or_region_id,"Female");
		}
		public int getMaleResidentCountBySAdmin(int state_or_region_id) {
			String sql = "select count(*) from resident re, ward_or_village v,township t, state_or_region r, sadmin s where re.ward_or_village_id"
					+ "= v.ward_or_village_id and v.township_id = t.township_id and t.state_or_region_id = r.state_or_region_id and "
					+ "r.state_or_region_id = s.state_or_region_id and deceased=false and s.state_or_region_id = ? and gender=?";
			return template.queryForObject(sql, Integer.class, state_or_region_id,"Male");
		}
		//Get resident list living in a state/region by sub admin
		public List<Resident> getResidentListBySAdmin(int stateOrRegionId){
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
					+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,resident.ward_or_village_id,ward_or_village_name"
					+ " from resident,ward_or_village,township where resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id=township."
					+ "township_id and deceased=false and township.state_or_region_id=?";
			return template.query(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt(1));	
					 r.setResidentName(rs.getString(2));
					 r.setGender(rs.getString(3));
					 r.setDob(rs.getDate(4));
					 r.setNrcNo(rs.getString(5));
					 r.setFatherName(rs.getString(6));
					 r.setMotherName(rs.getString(7));
					 r.setEthnicity(rs.getString(8));
					 r.setNote(rs.getString(9));
					 r.setResidentEmail(rs.getString(10));
					 r.setResidentPassword(rs.getString(11));
					 r.setBcg(rs.getBoolean(12));
					 r.setFiveVaccines1(rs.getBoolean(13));
					 r.setFiveVaccines2(rs.getBoolean(14));
					 r.setFiveVaccines3(rs.getBoolean(15));
					 r.setVitaminA(rs.getBoolean(16));
					 r.setHpvVaccine(rs.getBoolean(17));
					 r.setWardOrVillageId(rs.getInt(18));
					 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
					 return r;
				 }
			 },stateOrRegionId);
		}
		
		//Get bothe deceased and alive resident list of a state/region by sub admin
				public List<Resident> getDeceasedAliveResidentListBySAdmin(int state_or_region_id){
					String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
							+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,resident.ward_or_village_id,ward_or_village_name"
							+ " from resident,ward_or_village,township where resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id=township."
							+ "township_id and township.state_or_region_id=?";
					return template.query(sql, new RowMapper<Resident>(){
						 public Resident mapRow(ResultSet rs, int row) throws SQLException {
							 Resident r = new Resident();
							 r.setResidentId(rs.getInt(1));	
							 r.setResidentName(rs.getString(2));
							 r.setGender(rs.getString(3));
							 r.setDob(rs.getDate(4));
							 r.setNrcNo(rs.getString(5));
							 r.setFatherName(rs.getString(6));
							 r.setMotherName(rs.getString(7));
							 r.setEthnicity(rs.getString(8));
							 r.setNote(rs.getString(9));
							 r.setResidentEmail(rs.getString(10));
							 r.setResidentPassword(rs.getString(11));
							 r.setBcg(rs.getBoolean(12));
							 r.setFiveVaccines1(rs.getBoolean(13));
							 r.setFiveVaccines2(rs.getBoolean(14));
							 r.setFiveVaccines3(rs.getBoolean(15));
							 r.setVitaminA(rs.getBoolean(16));
							 r.setHpvVaccine(rs.getBoolean(17));
							 r.setWardOrVillageId(rs.getInt(18));
							 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
							 return r;
						 }
					 },state_or_region_id);
				}
				
		//Get female resident list living in a state/region by sub admin
			public List<Resident> getFemaleResidentListBySAdmin(int state_or_region_id){
				String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,"
						+ "ethnicity,note,resident_email,resident_password,resident.ward_or_village_id,ward_or_village_name"
						+ " from resident,ward_or_village,township where resident.ward_or_village_id=ward_or_village."
						+ "ward_or_village_id and deceased=false and ward_or_village.township_id=township.township_id and township.state_or_region_id=? and gender=?";
				return template.query(sql, new RowMapper<Resident>(){
					 public Resident mapRow(ResultSet rs, int row) throws SQLException {
						 Resident r = new Resident();
						 r.setResidentId(rs.getInt("resident_id"));	
						 r.setResidentName(rs.getString("resident_name"));
						 r.setGender(rs.getString("gender"));
						 r.setDob(rs.getDate("dob"));
						 r.setNrcNo(rs.getString("nrc_no"));
						 r.setFatherName(rs.getString("father_name"));
						 r.setMotherName(rs.getString("mother_name"));
						 r.setEthnicity(rs.getString("ethnicity"));
						 r.setNote(rs.getString("note"));
						 r.setResidentEmail(rs.getString("resident_email"));
						 r.setResidentPassword(rs.getString("resident_password"));
						 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return r;
					 }
				 },state_or_region_id,"Female");
			}
			
			//Get male resident list living in a state/region by sub admin
			public List<Resident> getMaleResidentListBySAdmin(int state_or_region_id){
				String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,"
						+ "ethnicity,note,resident_email,resident_password,resident.ward_or_village_id,ward_or_village_name"
						+ " from resident,ward_or_village,township where resident.ward_or_village_id=ward_or_village."
						+ "ward_or_village_id and deceased=false and ward_or_village.township_id=township.township_id and township.state_or_region_id=? and gender=?";
				return template.query(sql, new RowMapper<Resident>(){
					 public Resident mapRow(ResultSet rs, int row) throws SQLException {
						 Resident r = new Resident();
						 r.setResidentId(rs.getInt("resident_id"));	
						 r.setResidentName(rs.getString("resident_name"));
						 r.setGender(rs.getString("gender"));
						 r.setDob(rs.getDate("dob"));
						 r.setNrcNo(rs.getString("nrc_no"));
						 r.setFatherName(rs.getString("father_name"));
						 r.setMotherName(rs.getString("mother_name"));
						 r.setEthnicity(rs.getString("ethnicity"));
						 r.setNote(rs.getString("note"));
						 r.setResidentEmail(rs.getString("resident_email"));
						 r.setResidentPassword(rs.getString("resident_password"));
						 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return r;
					 }
				 },state_or_region_id,"Male");
			}
		
//-----------------------------------------------------Sub Admin End------------------------------------------------------------//		
//----------------------------------------------------Police Officer Start------------------------------------------------------//
			public List<Resident> getResidentByNrc(String nrc,int townshipId) {
				try
				{ String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
						+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
						+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name from resident,ward_or_village where "
						+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and nrc_no=? and ward_or_village.township_id=?";
				 return template.query(sql, new RowMapper<Resident>(){
					 public Resident mapRow(ResultSet rs, int row) throws SQLException {
						 Resident r = new Resident();
						 r.setResidentId(rs.getInt("resident_id"));	
						 r.setResidentName(rs.getString("resident_name"));
						 r.setGender(rs.getString("gender"));
						 r.setDob(rs.getDate("dob"));
						 r.setNrcNo(rs.getString("nrc_no"));
						 r.setFatherName(rs.getString("father_name"));
						 r.setMotherName(rs.getString("mother_name"));
						 r.setEthnicity(rs.getString("ethnicity"));
						 r.setNote(rs.getString("note"));
						 r.setResidentEmail(rs.getString("resident_email"));
						 r.setResidentPassword(rs.getString("resident_password"));
						 r.setBcg(rs.getBoolean("bcg"));
						 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
						 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
						 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
						 r.setVitaminA(rs.getBoolean("vitamin_a"));
						 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));;
						 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return r;
					 }
				 },nrc,townshipId);}
				catch (DataAccessException e)
				{
				    return null;
				}
			}
			
			public List<Resident> getResidentByName(String name,int townshipId) {
				 try
					{String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,"
						+ "resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,"
						+ "hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name from resident,ward_or_village where "
						+ "resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and resident_name=? and township_id=?";
				 return template.query(sql, new RowMapper<Resident>(){
					 public Resident mapRow(ResultSet rs, int row) throws SQLException {
						 Resident r = new Resident();
						 r.setResidentId(rs.getInt("resident_id"));	
						 r.setResidentName(rs.getString("resident_name"));
						 r.setGender(rs.getString("gender"));
						 r.setDob(rs.getDate("dob"));
						 r.setNrcNo(rs.getString("nrc_no"));
						 r.setFatherName(rs.getString("father_name"));
						 r.setMotherName(rs.getString("mother_name"));
						 r.setEthnicity(rs.getString("ethnicity"));
						 r.setNote(rs.getString("note"));
						 r.setResidentEmail(rs.getString("resident_email"));
						 r.setResidentPassword(rs.getString("resident_password"));
						 r.setBcg(rs.getBoolean("bcg"));
						 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
						 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
						 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
						 r.setVitaminA(rs.getBoolean("vitamin_a"));
						 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));;
						 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return r;
					 }
				 },name,townshipId);}
					catch (DataAccessException e)
					{
					    return null;
					}
			}
//----------------------------------------------------Police Officer End------------------------------------------------------//
//----------------------------------------------------------GAS Start------------------------------------------------------//
			//Get total number of residents in a township by general administrative staff
		public int getResidentCountByGAS(int township_id) {
			String sql = "select count(*) from resident r,ward_or_village w,township t,gas g where r.ward_or_village_id"
					+ "=w.ward_or_village_id and w.township_id = t.township_id and t.township_id = g.township_id and deceased=false and "
					+ "g.township_id=?";
			return template.queryForObject(sql, Integer.class,township_id);
		}
		public int getFemaleResidentCountByGAS(int township_id) {
			String sql = "select count(*) from resident r,ward_or_village w,township t,gas g where r.ward_or_village_id"
					+ "=w.ward_or_village_id and w.township_id = t.township_id and t.township_id = g.township_id and deceased=false and "
					+ "g.township_id=? and gender=?";
			return template.queryForObject(sql, Integer.class,township_id,"Female");
		}
		public int getMaleResidentCountByGAS(int township_id) {
			String sql = "select count(*) from resident r,ward_or_village w,township t,gas g where r.ward_or_village_id"
					+ "=w.ward_or_village_id and w.township_id = t.township_id and t.township_id = g.township_id and deceased=false and "
					+ "g.township_id=? and gender=?";
			return template.queryForObject(sql, Integer.class,township_id,"Male");
		}
		
		//Get resident list living in a township by gas
		public List<Resident> getResidentListByGAS(int township_id){
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
					+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,resident.ward_or_village_id,ward_or_village_name"
					+ " from resident,ward_or_village where resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and ward_or_village.township_id=?";
			return template.query(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt(1));	
					 r.setResidentName(rs.getString(2));
					 r.setGender(rs.getString(3));
					 r.setDob(rs.getDate(4));
					 r.setNrcNo(rs.getString(5));
					 r.setFatherName(rs.getString(6));
					 r.setMotherName(rs.getString(7));
					 r.setEthnicity(rs.getString(8));
					 r.setNote(rs.getString(9));
					 r.setResidentEmail(rs.getString(10));
					 r.setResidentPassword(rs.getString(11));
					 r.setBcg(rs.getBoolean(12));
					 r.setFiveVaccines1(rs.getBoolean(13));
					 r.setFiveVaccines2(rs.getBoolean(14));
					 r.setFiveVaccines3(rs.getBoolean(15));
					 r.setVitaminA(rs.getBoolean(16));
					 r.setHpvVaccine(rs.getBoolean(17));
					 r.setWardOrVillageId(rs.getInt(18));
					 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
					 return r;
				 }
			 },township_id);
		}
		//Get both dead and alive resident list of a township by gas
		public List<Resident> getDeceasedAliveResidentListByGAS(int township_id){
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
					+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,resident.ward_or_village_id,ward_or_village_name"
					+ " from resident,ward_or_village where resident.ward_or_village_id=ward_or_village.ward_or_village_id and ward_or_village.township_id=?";
			return template.query(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt(1));	
					 r.setResidentName(rs.getString(2));
					 r.setGender(rs.getString(3));
					 r.setDob(rs.getDate(4));
					 r.setNrcNo(rs.getString(5));
					 r.setFatherName(rs.getString(6));
					 r.setMotherName(rs.getString(7));
					 r.setEthnicity(rs.getString(8));
					 r.setNote(rs.getString(9));
					 r.setResidentEmail(rs.getString(10));
					 r.setResidentPassword(rs.getString(11));
					 r.setBcg(rs.getBoolean(12));
					 r.setFiveVaccines1(rs.getBoolean(13));
					 r.setFiveVaccines2(rs.getBoolean(14));
					 r.setFiveVaccines3(rs.getBoolean(15));
					 r.setVitaminA(rs.getBoolean(16));
					 r.setHpvVaccine(rs.getBoolean(17));
					 r.setWardOrVillageId(rs.getInt(18));
					 r.setWardOrVillage(new WardOrVillage(rs.getString(19)));
					 return r;
				 }
			 },township_id);
		}
		//Get female resident list living in a township by gas
		public List<Resident> getFemaleResidentListByGAS(int township_id){
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
					+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,resident.ward_or_village_id,ward_or_village_name"
					+ " from resident,ward_or_village where resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and ward_or_village.township_id=? and gender=?";
			return template.query(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt("resident_id"));	
					 r.setResidentName(rs.getString("resident_name"));
					 r.setGender(rs.getString("gender"));
					 r.setDob(rs.getDate("dob"));
					 r.setNrcNo(rs.getString("nrc_no"));
					 r.setFatherName(rs.getString("father_name"));
					 r.setMotherName(rs.getString("mother_name"));
					 r.setEthnicity(rs.getString("ethnicity"));
					 r.setNote(rs.getString("note"));
					 r.setResidentEmail(rs.getString("resident_email"));
					 r.setResidentPassword(rs.getString("resident_password"));
					 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return r;
				 }
			 },township_id,"Female");
		}
		
		//Get male resident list living in a township by gas
		public List<Resident> getMaleResidentListByGAS(int township_id){
			String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
					+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,resident.ward_or_village_id,ward_or_village_name"
					+ " from resident,ward_or_village where resident.ward_or_village_id=ward_or_village.ward_or_village_id and deceased=false and ward_or_village.township_id=? and gender=?";
			return template.query(sql, new RowMapper<Resident>(){
				 public Resident mapRow(ResultSet rs, int row) throws SQLException {
					 Resident r = new Resident();
					 r.setResidentId(rs.getInt("resident_id"));	
					 r.setResidentName(rs.getString("resident_name"));
					 r.setGender(rs.getString("gender"));
					 r.setDob(rs.getDate("dob"));
					 r.setNrcNo(rs.getString("nrc_no"));
					 r.setFatherName(rs.getString("father_name"));
					 r.setMotherName(rs.getString("mother_name"));
					 r.setEthnicity(rs.getString("ethnicity"));
					 r.setNote(rs.getString("note"));
					 r.setResidentEmail(rs.getString("resident_email"));
					 r.setResidentPassword(rs.getString("resident_password"));
					 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return r;
				 }
			 },township_id,"Male");
		}
//-----------------------------------------------------------GAS End------------------------------------------------------//
//------------------------------------------------------------WardAS Start-------------------------------------------------//
		//Get total number of residents in a ward/village by wardas
		public int getResidentCountByWardAS(int ward_or_village_id){
				 String sql = "SELECT count(*) FROM resident where deceased=false and ward_or_village_id=?";
				 return template.queryForObject(sql,Integer.class,ward_or_village_id);
		};
		public int getMaleResidentCountByWardAS(int ward_or_village_id){
			 String sql = "SELECT count(*) FROM resident where deceased=false and ward_or_village_id=? and gender=?";
			 return template.queryForObject(sql,Integer.class,ward_or_village_id,"Male");
		};
		public int getFemaleResidentCountByWardAS(int ward_or_village_id){
			 String sql = "SELECT count(*) FROM resident where deceased=false and ward_or_village_id=? and gender=?";
			 return template.queryForObject(sql,Integer.class,ward_or_village_id,"Female");
		};
		//Get male resident list living in a ward/village by wardas
				public List<Resident> getMaleResidentListByWardAS(int ward_or_village_id){
					String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,"
							+ "ethnicity,note,resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name"
							+ " from resident,ward_or_village where resident.ward_or_village_id = ward_or_village.ward_or_village_id and deceased=false and gender=? and resident.ward_or_village_id=?";
					return template.query(sql, new RowMapper<Resident>(){
						 public Resident mapRow(ResultSet rs, int row) throws SQLException {
							 Resident r = new Resident();
							 r.setResidentId(rs.getInt("resident_id"));	
							 r.setResidentName(rs.getString("resident_name"));
							 r.setGender(rs.getString("gender"));
							 r.setDob(rs.getDate("dob"));
							 r.setNrcNo(rs.getString("nrc_no"));
							 r.setFatherName(rs.getString("father_name"));
							 r.setMotherName(rs.getString("mother_name"));
							 r.setEthnicity(rs.getString("ethnicity"));
							 r.setNote(rs.getString("note"));
							 r.setResidentEmail(rs.getString("resident_email"));
							 r.setResidentPassword(rs.getString("resident_password"));
							 r.setBcg(rs.getBoolean("bcg"));
							 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
							 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
							 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
							 r.setVitaminA(rs.getBoolean("vitamin_a"));
							 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));
							 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return r;
						 }
					 },"Male",ward_or_village_id);
				}
				
				//Get female resident list living in a ward/village by wardas
				public List<Resident> getFemaleResidentListByWardAS(int ward_or_village_id){
					String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,"
							+ "ethnicity,note,resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name"
							+ " from resident, ward_or_village where resident.ward_or_village_id = ward_or_village.ward_or_village_id and deceased=false and gender=? and resident.ward_or_village_id=?";
					return template.query(sql, new RowMapper<Resident>(){
						 public Resident mapRow(ResultSet rs, int row) throws SQLException {
							 Resident r = new Resident();
							 r.setResidentId(rs.getInt("resident_id"));	
							 r.setResidentName(rs.getString("resident_name"));
							 r.setGender(rs.getString("gender"));
							 r.setDob(rs.getDate("dob"));
							 r.setNrcNo(rs.getString("nrc_no"));
							 r.setFatherName(rs.getString("father_name"));
							 r.setMotherName(rs.getString("mother_name"));
							 r.setEthnicity(rs.getString("ethnicity"));
							 r.setNote(rs.getString("note"));
							 r.setResidentEmail(rs.getString("resident_email"));
							 r.setResidentPassword(rs.getString("resident_password"));
							 r.setBcg(rs.getBoolean("bcg"));
							 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
							 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
							 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
							 r.setVitaminA(rs.getBoolean("vitamin_a"));
							 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));
							 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return r;
						 }
					 },"Female",ward_or_village_id);
				}
				
				//Get resident list living in a ward/village by wardas
				public List<Resident> getResidentListByWardAS(int ward_or_village_id){
					String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,"
							+ "ethnicity,note,resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name"
							+ " from resident, ward_or_village where resident.ward_or_village_id = ward_or_village.ward_or_village_id and deceased=false and resident.ward_or_village_id=?";
					return template.query(sql, new RowMapper<Resident>(){
						 public Resident mapRow(ResultSet rs, int row) throws SQLException {
							 Resident r = new Resident();
							 r.setResidentId(rs.getInt("resident_id"));	
							 r.setResidentName(rs.getString("resident_name"));
							 r.setGender(rs.getString("gender"));
							 r.setDob(rs.getDate("dob"));
							 r.setNrcNo(rs.getString("nrc_no"));
							 r.setFatherName(rs.getString("father_name"));
							 r.setMotherName(rs.getString("mother_name"));
							 r.setEthnicity(rs.getString("ethnicity"));
							 r.setNote(rs.getString("note"));
							 r.setResidentEmail(rs.getString("resident_email"));
							 r.setResidentPassword(rs.getString("resident_password"));
							 r.setBcg(rs.getBoolean("bcg"));
							 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
							 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
							 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
							 r.setVitaminA(rs.getBoolean("vitamin_a"));
							 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));;
							 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return r;
						 }
					 },ward_or_village_id);
				}
				//Get dead and alive resident list of a ward/village by wardas
				public List<Resident> getDeceasedAliveResidentListByWardAS(int ward_or_village_id){
					String sql = "select resident_id,resident_name,gender,dob,nrc_no,father_name,mother_name,"
							+ "ethnicity,note,resident_email,resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,ward_or_village.ward_or_village_id,ward_or_village_name"
							+ " from resident, ward_or_village where resident.ward_or_village_id = ward_or_village.ward_or_village_id and resident.ward_or_village_id=?";
					return template.query(sql, new RowMapper<Resident>(){
						 public Resident mapRow(ResultSet rs, int row) throws SQLException {
							 Resident r = new Resident();
							 r.setResidentId(rs.getInt("resident_id"));	
							 r.setResidentName(rs.getString("resident_name"));
							 r.setGender(rs.getString("gender"));
							 r.setDob(rs.getDate("dob"));
							 r.setNrcNo(rs.getString("nrc_no"));
							 r.setFatherName(rs.getString("father_name"));
							 r.setMotherName(rs.getString("mother_name"));
							 r.setEthnicity(rs.getString("ethnicity"));
							 r.setNote(rs.getString("note"));
							 r.setResidentEmail(rs.getString("resident_email"));
							 r.setResidentPassword(rs.getString("resident_password"));
							 r.setBcg(rs.getBoolean("bcg"));
							 r.setFiveVaccines1(rs.getBoolean("five_vaccines1"));
							 r.setFiveVaccines2(rs.getBoolean("five_vaccines2"));
							 r.setFiveVaccines3(rs.getBoolean("five_vaccines3"));
							 r.setVitaminA(rs.getBoolean("vitamin_a"));
							 r.setHpvVaccine(rs.getBoolean("hpv_vaccine"));;
							 r.setWardOrVillageId(rs.getInt("ward_or_village_id"));
							 r.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
							 return r;
						 }
					 },ward_or_village_id);
				}
//------------------------------------------------------------WardAS End-------------------------------------------------//		
	
	//delete a resident 
	public int deleteResident(int id) {
		String sql = "delete from resident where resident_id="+id+""; 
		return template.update(sql);
	}
	
	//add a resident
	public int saveResident(Resident r) {
		String sql = "insert into resident (resident_name,gender,dob,nrc_no,father_name,mother_name,ethnicity,note,resident_email,"
				+ "resident_password,bcg,five_vaccines1,five_vaccines2,five_vaccines3,vitamin_a,hpv_vaccine,deceased,ward_or_village_id) values ('"+r.getResidentName()+"','"+r.getGender()+"','"+r.getDob()+"',"
						+ "'"+r.getNrcNo()+"','"+r.getFatherName()+"','"+r.getMotherName()+"','"+r.getEthnicity()+"','"+r.getNote()+"',"
								+ "'"+r.getResidentEmail()+"','"+r.getResidentPassword()+"',"+r.getBcg()+","+r.getFiveVaccines1()+","+r.getFiveVaccines2()+","+r.getFiveVaccines3()+","+r.getVitaminA()+","+r.getHpvVaccine()+","+r.getDeceased()+","+r.getWardOrVillageId()+")";
		return template.update(sql);
	}
	
	//update a resident
	public int updateResident(Resident r) {
		String sql = "update resident set resident_name='"+r.getResidentName()+"',gender='"+r.getGender()+"',"
				+ "dob='"+r.getDob()+"',nrc_no='"+r.getNrcNo()+"',father_name='"+r.getFatherName()+"',"
						+ "mother_name='"+r.getMotherName()+"',ethnicity='"+r.getEthnicity()+"',note='"+r.getNote()+"',"
								+ "resident_email='"+r.getResidentEmail()+"',resident_password='"+r.getResidentPassword()+"',bcg="+r.getBcg()+",five_vaccines1="+r.getFiveVaccines1()+",five_vaccines2="+r.getFiveVaccines2()+",five_vaccines3="+r.getFiveVaccines3()+","
										+ "vitamin_a="+r.getVitaminA()+",hpv_vaccine="+r.getHpvVaccine()+",deceased="+r.getDeceased()+",ward_or_village_id="+r.getWardOrVillageId()+" where resident_id="+r.getResidentId()+"";
		return template.update(sql); 
	}
	
	
}
