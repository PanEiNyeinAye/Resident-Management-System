package com.java.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.java.beans.Death;
import com.java.beans.Resident;

public class DeathDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	//get a Death object by death_id
	public Death getDeath(int deathId) { 
		String sql = "select death_registration_id,person_informed,place_of_death,date_of_death,resident.resident_id,nrc_no"
				+ " from death_registration,resident where death_registration.resident_id = resident.resident_id and death_registration_id=?";
			 
			 return template.queryForObject(sql, new RowMapper<Death>(){
				 public Death mapRow(ResultSet rs, int row) throws SQLException {
					 Death d = new Death();
					 d.setDeathId(rs.getInt("death_registration_id"));
					 d.setPersonInformed(rs.getString("person_informed"));
					 d.setPlaceOfDeath(rs.getString("place_of_death"));
					 d.setDateOfDeath(rs.getDate("date_of_death"));
					 d.setResident(new Resident(rs.getInt("resident_id"),rs.getString("nrc_no")));
					 d.setResidentId(rs.getInt("resident_id"));;
					 return d;
				 }
			 },deathId);
	}
	
	//delete a Death obj 
			/*public int deleteDeath(int deathId) {
				String sql = "delete from death_registration where death_registration_id="+deathId+""; 
				return template.update(sql);
			}*/
			
			//add a Death obj 
			public int saveDeath(Death d) {
				String sql = "insert into death_registration (person_informed,place_of_death,date_of_death,resident_id) values ('"+d.getPersonInformed()+"','"+d.getPlaceOfDeath()+"',"
								+ "'"+d.getDateOfDeath()+"',"+d.getResidentId()+")";
				return template.update(sql);
			}
			
			//update a Death obj 
			public int updateDeath(Death d) {
				String sql = "update death_registration set person_informed='"+d.getPersonInformed()+"',"
						+ "place_of_death='"+d.getPlaceOfDeath()+"',date_of_death='"+d.getDateOfDeath()+"',resident_id="+d.getResidentId()+" where death_registration_id="+d.getDeathId()+"";
				return template.update(sql); 
			}
	
	//---------------------------------------------------Admin Start--------------------------------------------//	
		public List<Death> getDeathListByAdmin(){
			String sql = "select death_registration_id,person_informed,place_of_death,date_of_death,resident.resident_id,nrc_no"
					+ " from death_registration,resident where death_registration.resident_id = resident.resident_id";
			return template.query(sql, new RowMapper<Death>(){
				 public Death mapRow(ResultSet rs, int row) throws SQLException {
					 Death d = new Death();
					 d.setDeathId(rs.getInt("death_registration_id"));
					 d.setPersonInformed(rs.getString("person_informed"));
					 d.setPlaceOfDeath(rs.getString("place_of_death"));
					 d.setDateOfDeath(rs.getDate("date_of_death"));
					 d.setResident(new Resident(rs.getInt("resident_id"),rs.getString("nrc_no")));
					 d.setResidentId(rs.getInt("resident_id"));
					 return d;
				 }
			 });
		}
		
		//------------------------------------------------------Admin Finish---------------------------------------------------//
		//------------------------------------------------------Sub Admin Start---------------------------------------------------//
		public List<Death> getDeathListBySAdmin(int state_or_region_id){
			String sql = "select death_registration_id,person_informed,place_of_death,date_of_death,resident.resident_id,nrc_no"
					+ " from death_registration,resident,ward_or_village,township where resident.resident_id = death_registration.resident_id and resident.ward_or_village_id = ward_or_village.ward_or_village_id and township.township_id="
					+ "ward_or_village.township_id and township.state_or_region_id=?";
			
			return template.query(sql, new RowMapper<Death>(){
				 public Death mapRow(ResultSet rs, int row) throws SQLException {
					 Death d = new Death();
					 d.setDeathId(rs.getInt("death_registration_id"));
					 d.setPersonInformed(rs.getString("person_informed"));
					 d.setPlaceOfDeath(rs.getString("place_of_death"));
					 d.setDateOfDeath(rs.getDate("date_of_death"));
					 d.setResident(new Resident(rs.getInt("resident_id"),rs.getString("nrc_no")));
					 d.setResidentId(rs.getInt("resident_id"));
					 return d;
				 }
			 },state_or_region_id);
		}
		//------------------------------------------------------Sub Admin Finish---------------------------------------------------//
		//------------------------------------------------------GAS Start----------------------------------------------------------//
		public List<Death> getDeathListByGAS(int township_id){
			String sql = "select death_registration_id,person_informed,place_of_death,date_of_death,resident.resident_id,nrc_no"
					+ " from death_registration,resident,ward_or_village where resident.resident_id = death_registration.resident_id and resident.ward_or_village_id = ward_or_village.ward_or_village_id and ward_or_village.township_id=?";
			
			return template.query(sql, new RowMapper<Death>(){
				 public Death mapRow(ResultSet rs, int row) throws SQLException {
					 Death d = new Death();
					 d.setDeathId(rs.getInt("death_registration_id"));
					 d.setPersonInformed(rs.getString("person_informed"));
					 d.setPlaceOfDeath(rs.getString("place_of_death"));
					 d.setDateOfDeath(rs.getDate("date_of_death"));
					 d.setResident(new Resident(rs.getInt("resident_id"),rs.getString("nrc_no")));
					 d.setResidentId(rs.getInt("resident_id"));
					 return d;
				 }
			 },township_id);
		}
		
		
		//------------------------------------------------------GAS Finish---------------------------------------------------------//
		//--------------------------------------Ward AS Start--------------------------------------------------//
		
			public List<Death> getDeathListByWardAS(int ward_or_village_id){
				String sql = "select death_registration_id,person_informed,place_of_death,date_of_death,resident.resident_id,nrc_no"
						+ " from death_registration,resident where resident.resident_id = death_registration.resident_id and resident.ward_or_village_id=?";
				return template.query(sql, new RowMapper<Death>(){
					 public Death mapRow(ResultSet rs, int row) throws SQLException {
						 Death d = new Death();
						 d.setDeathId(rs.getInt("death_registration_id"));
						 d.setPersonInformed(rs.getString("person_informed"));
						 d.setPlaceOfDeath(rs.getString("place_of_death"));
						 d.setDateOfDeath(rs.getDate("date_of_death"));
						 d.setResident(new Resident(rs.getInt("resident_id"),rs.getString("nrc_no")));
						 d.setResidentId(rs.getInt("resident_id"));
						 return d;
					 }
				 },ward_or_village_id);
			}
		
			
		//--------------------------------------------------Ward AS Finish---------------------------------------//	
}
