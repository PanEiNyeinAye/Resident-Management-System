package com.java.dao;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.java.beans.Sender;
import com.java.beans.WardAS;
import com.java.beans.WardOrVillage;

public class SenderDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	public void saveSender(Sender s) throws DataAccessException, IOException{
		String sql = "insert into sender (sent_date,wardas_id,resident_nrc_no) values ('"+s.getSentDate()+"',"+s.getWardASId()+",'"+s.getResidentNrcNo()+"')";
		template.update(sql);
	}
	
	public Sender getLatestSenderByWardAS(int wardas_id) {
		String sql = "select sender_id,sent_date,sender.wardas_id,resident_nrc_no,wardas_name,ward_or_village_name"
				+ " from sender,wardas,ward_or_village where wardas.wardas_id = sender.wardas_id and ward_or_village.ward_or_village_id=wardas.ward_or_village_id and sender.wardas_id=? order by sender_id desc limit 1";
		return template.queryForObject(sql, new RowMapper<Sender>(){
			 public Sender mapRow(ResultSet rs, int row) throws SQLException {
				 Sender s = new Sender();
				 s.setSenderId(rs.getInt(1));
				 s.setSentDate(rs.getDate(2));
				 s.setWardASId(3);
				 s.setResidentNrcNo(rs.getString(4));
				 s.setWardAS(new WardAS(rs.getString(5),new WardOrVillage(rs.getString(6))));
				 System.out.println("Inside get latest sender method: senderId"+s.getSenderId());
				 return s;
				
			 }
		 },wardas_id);
	}
}
