package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.beans.Birth;
import com.java.beans.WardOrVillage;

public class BirthDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//get a Birth object by birth_id
		public Birth getBirth(int birthId) { 
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,ward_or_village.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village where birth_certificate_registration.ward_or_village_id = ward_or_village.ward_or_village_id and birth_certificate_registration_id=?";
				 
				 return template.queryForObject(sql, new RowMapper<Birth>(){
					 public Birth mapRow(ResultSet rs, int row) throws SQLException {
						 Birth b = new Birth();
						 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
						 b.setPlaceOfBirth(rs.getString("place_of_birth"));
						 b.setDateOfBirth(rs.getDate("date_of_birth"));
						 b.setChildGender(rs.getString("child_gender"));
						 b.setChildFatherName(rs.getString("child_father_name"));
						 b.setChildMotherName(rs.getString("child_mother_name"));
						 b.setDateOfRegistration(rs.getDate("date_of_registration"));
						 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
						 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return b;
					 }
				 },birthId);
		}
		//delete a Birth obj 
		public int deleteBirth(int birthId) {
			String sql = "delete from birth_certificate_registration where birth_certificate_registration_id="+birthId+""; 
			return template.update(sql);
		}
		
		//add a Birth obj 
		public int saveBirth(Birth b) {
			String sql = "insert into birth_certificate_registration (place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,date_of_registration,birth_certificate_registration_note,ward_or_village_id) values "
					+ "('"+b.getPlaceOfBirth()+"','"+b.getDateOfBirth()+"','"+b.getChildGender()+"','"+b.getChildFatherName()+"','"+b.getChildMotherName()+"','"+b.getDateOfRegistration()+"','"+b.getBirthNote()+"',"+b.getWardOrVillageId()+")";
			return template.update(sql);
		}
		
		//update a Birth obj 
		public int updateBirth(Birth b) {
			String sql = "update birth_certificate_registration set place_of_birth='"+b.getPlaceOfBirth()+"',date_of_birth='"+b.getDateOfBirth()+"',"
					+ "child_gender='"+b.getChildGender()+"',child_father_name='"+b.getChildFatherName()+"',child_mother_name='"+b.getChildMotherName()+"',date_of_registration"
							+ "='"+b.getDateOfRegistration()+"',birth_certificate_registration_note='"+b.getBirthNote()+"',ward_or_village_id="+b.getWardOrVillageId()+" where birth_certificate_registration_id="+b.getBirthId()+"";
			return template.update(sql); 
		}
//-------------------------------------Admin Start---------------------------------------------------------//
		
		public List<Birth> getBirthListByAdmin(){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,birth_certificate_registration.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village where birth_certificate_registration.ward_or_village_id = ward_or_village.ward_or_village_id";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 });
		}
		
		//Admin View Female Birth List
		public List<Birth> getFemaleBirthListByAdmin(){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,ward_or_village_id"
					+ " from birth_certificate_registration where child_gender=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 return b;
				 }
			 },"Female");
		}
		
		//Admin View Male Birth List
		public List<Birth> getMaleBirthListByAdmin(){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,ward_or_village_id"
					+ " from birth_certificate_registration where child_gender=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 return b;
				 }
			 },"Male");
		}
    //-------------------------------------------------Admin Finish------------------------------------------//
	//------------------------------------------------Sub Admin Start---------------------------------------------------------//
		public List<Birth> getBirthListBySAdmin(int state_or_region_id){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,birth_certificate_registration.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village,township where birth_certificate_registration.ward_or_village_id ="
					+ " ward_or_village.ward_or_village_id and township.township_id = ward_or_village.township_id and township.state_or_region_id=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 },state_or_region_id);
		}
		
		//Admin View Female Birth List
		public List<Birth> getFemaleBirthListBySAdmin(int state_or_region_id){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,ward_or_village.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village,township where birth_certificate_registration.ward_or_village_id"
					+ "=ward_or_village.ward_or_village_id and township.township_id = ward_or_village.township_id and township.state_or_region_id=? and child_gender=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 },state_or_region_id,"Female");
		}
		
		//Admin View Male Birth List
		public List<Birth> getMaleBirthListBySAdmin(int state_or_region_id){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,ward_or_village.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village,township where birth_certificate_registration.ward_or_village_id"
					+ "=ward_or_village.ward_or_village_id and township.township_id = ward_or_village.township_id and township.state_or_region_id=? and child_gender=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 },state_or_region_id,"Male");
		}
		//-------------------------------------Sub Admin Finish---------------------------------------------------------//
		//-------------------------------------GAS Start----------------------------------------------------------------//
		public List<Birth> getBirthListByGAS(int township_id){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,birth_certificate_registration.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village where birth_certificate_registration.ward_or_village_id ="
					+ " ward_or_village.ward_or_village_id and township_id=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 },township_id);
		}
		
		public List<Birth> getFemaleBirthListByGAS(int township_id){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,birth_certificate_registration.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village where birth_certificate_registration.ward_or_village_id ="
					+ " ward_or_village.ward_or_village_id and township_id=? and child_gender=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 },township_id,"Female");
		}
		
		public List<Birth> getMaleBirthListByGAS(int township_id){
			String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
					+ "date_of_registration,birth_certificate_registration_note,birth_certificate_registration.ward_or_village_id,ward_or_village_name"
					+ " from birth_certificate_registration,ward_or_village where birth_certificate_registration.ward_or_village_id ="
					+ " ward_or_village.ward_or_village_id and township_id=? and child_gender=?";
			return template.query(sql, new RowMapper<Birth>(){
				 public Birth mapRow(ResultSet rs, int row) throws SQLException {
					 Birth b = new Birth();
					 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
					 b.setPlaceOfBirth(rs.getString("place_of_birth"));
					 b.setDateOfBirth(rs.getDate("date_of_birth"));
					 b.setChildGender(rs.getString("child_gender"));
					 b.setChildFatherName(rs.getString("child_father_name"));
					 b.setChildMotherName(rs.getString("child_mother_name"));
					 b.setDateOfRegistration(rs.getDate("date_of_registration"));
					 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
					 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
					 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
					 return b;
				 }
			 },township_id,"Male");
		}
		//-------------------------------------GAS Finish---------------------------------------------------------------//
		//-------------------------------------------------Ward AS Start-----------------------------------------------//		

			//Get birth list born in a ward/village by wardas
			public List<Birth> getBirthListByWardAS(int ward_or_village_id){
				String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
						+ "date_of_registration,birth_certificate_registration_note,ward_or_village.ward_or_village_id,ward_or_village_name"
						+ " from birth_certificate_registration,ward_or_village where ward_or_village.ward_or_village_id="
						+ "birth_certificate_registration.ward_or_village_id and ward_or_village.ward_or_village_id=?";
				return template.query(sql, new RowMapper<Birth>(){
					 public Birth mapRow(ResultSet rs, int row) throws SQLException {
						 Birth b = new Birth();
						 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
						 b.setPlaceOfBirth(rs.getString("place_of_birth"));
						 b.setDateOfBirth(rs.getDate("date_of_birth"));
						 b.setChildGender(rs.getString("child_gender"));
						 b.setChildFatherName(rs.getString("child_father_name"));
						 b.setChildMotherName(rs.getString("child_mother_name"));
						 b.setDateOfRegistration(rs.getDate("date_of_registration"));
						 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
						 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return b;
					 }
				 },ward_or_village_id);
			}
			public List<Birth> getFemaleBirthListByWardAS(int ward_or_village_id){
				String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
						+ "date_of_registration,birth_certificate_registration_note,ward_or_village.ward_or_village_id,ward_or_village_name"
						+ " from birth_certificate_registration,ward_or_village where ward_or_village.ward_or_village_id="
						+ "birth_certificate_registration.ward_or_village_id and ward_or_village.ward_or_village_id=? and child_gender=?";
				return template.query(sql, new RowMapper<Birth>(){
					 public Birth mapRow(ResultSet rs, int row) throws SQLException {
						 Birth b = new Birth();
						 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
						 b.setPlaceOfBirth(rs.getString("place_of_birth"));
						 b.setDateOfBirth(rs.getDate("date_of_birth"));
						 b.setChildGender(rs.getString("child_gender"));
						 b.setChildFatherName(rs.getString("child_father_name"));
						 b.setChildMotherName(rs.getString("child_mother_name"));
						 b.setDateOfRegistration(rs.getDate("date_of_registration"));
						 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
						 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return b;
					 }
				 },ward_or_village_id,"Female");
			}
			
			public List<Birth> getMaleBirthListByWardAS(int ward_or_village_id){
				String sql = "select birth_certificate_registration_id,place_of_birth,date_of_birth,child_gender,child_father_name,child_mother_name,"
						+ "date_of_registration,birth_certificate_registration_note,ward_or_village.ward_or_village_id,ward_or_village_name"
						+ " from birth_certificate_registration,ward_or_village where ward_or_village.ward_or_village_id="
						+ "birth_certificate_registration.ward_or_village_id and ward_or_village.ward_or_village_id=? and child_gender=?";
				return template.query(sql, new RowMapper<Birth>(){
					 public Birth mapRow(ResultSet rs, int row) throws SQLException {
						 Birth b = new Birth();
						 b.setBirthId(rs.getInt("birth_certificate_registration_id"));
						 b.setPlaceOfBirth(rs.getString("place_of_birth"));
						 b.setDateOfBirth(rs.getDate("date_of_birth"));
						 b.setChildGender(rs.getString("child_gender"));
						 b.setChildFatherName(rs.getString("child_father_name"));
						 b.setChildMotherName(rs.getString("child_mother_name"));
						 b.setDateOfRegistration(rs.getDate("date_of_registration"));
						 b.setBirthNote(rs.getString("birth_certificate_registration_note"));
						 b.setWardOrVillageId(rs.getInt("ward_or_village_id"));
						 b.setWardOrVillage(new WardOrVillage(rs.getString("ward_or_village_name")));
						 return b;
					 }
				 },ward_or_village_id,"Male");
			}
	//-------------------------------------Ward AS Finish----------------------------------------------------//
}
