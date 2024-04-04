package com.java.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.java.beans.Receiver;
import com.java.beans.Sender;
import com.java.beans.Township;
import com.java.beans.WardAS;
import com.java.beans.WardOrVillage;

public class ReceiverDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	public int saveReciever(Receiver r) {
		String sql = "insert into receiver (confirm,sender_id,wardas_id) values "
				+ "("+r.getConfirm()+","+r.getSenderId()+","+r.getWardASId()+")";
		return template.update(sql);
	}
	
	public int deleteReciever(int id) {
		String sql = "delete from receiver where sadmin_id="+id+""; 
		return template.update(sql);
	}
		
	public List<Receiver> getNotConfirmedList(int wardas_id) {
		String sql ="select receiver_id,sent_date,confirm,wardas_name,ward_or_village_name,township_name,receiver.sender_id,resident_nrc_no from receiver,sender,wardas,ward_or_village,township where "
				+ "receiver.sender_id = sender.sender_id and wardas.wardas_id = sender.wardas_id and ward_or_village.ward_or_village_id=wardas.ward_or_village_id and "
				+ "ward_or_village.township_id = township.township_id and receiver.wardas_id=? and confirm=?";
		return template.query(sql, new RowMapper<Receiver>() {
			public Receiver mapRow(ResultSet rs, int arg1) throws SQLException {
				Receiver r = new Receiver();
				r.setReceiverId(rs.getInt(1));
				Sender s = new Sender();
				s.setSentDate(rs.getDate(2));
				s.setResidentNrcNo(rs.getString(8));
				r.setConfirm(rs.getInt(3));
				s.setWardAS(new WardAS(rs.getString(4),new WardOrVillage(rs.getString(5),new Township(rs.getString(6)))));
                r.setSender(s);
                r.setSenderId(rs.getInt(7));
				return r;
			}
			
		},wardas_id,3);
	}
	
	public Receiver getReceiver(int receiverId) {
		String sql ="select receiver_id,sent_date,confirm,wardas_name,ward_or_village_name,township_name,receiver.sender_id,resident_nrc_no,wardas.ward_or_village_id from receiver,sender,wardas,ward_or_village,township where "
				+ "receiver.sender_id = sender.sender_id and wardas.wardas_id = sender.wardas_id and ward_or_village.ward_or_village_id=wardas.ward_or_village_id and "
				+ "ward_or_village.township_id = township.township_id and receiver_id=?";
		return template.queryForObject(sql, new RowMapper<Receiver>() {
			public Receiver mapRow(ResultSet rs, int arg1) throws SQLException {
				Receiver r = new Receiver();
				r.setReceiverId(rs.getInt(1));
				Sender s = new Sender();
				s.setSentDate(rs.getDate(2));
				s.setResidentNrcNo(rs.getString(8));
				r.setConfirm(rs.getInt(3));
				s.setWardAS(new WardAS(rs.getString(4),new WardOrVillage(rs.getInt(9),rs.getString(5),new Township(rs.getString(6)))));
				r.setSender(s);
                r.setSenderId(rs.getInt(7)); 
				return r;
			}
			
		},receiverId);
	}
	
	
	public int updateReceiver(Receiver r,int receiverId) {
		String sql = "update receiver set confirm="+r.getConfirm()+" where receiver_id=?";
		return template.update(sql, new Object[] { receiverId }); 
	}
	
	public List<Receiver> getSentHistory(int wardas_id) {
		String sql ="select receiver_id,sent_date,confirm,wardas_name,ward_or_village_name,township_name,receiver.sender_id,resident_nrc_no from receiver,sender,wardas,ward_or_village,township where "
				+ "receiver.sender_id = sender.sender_id and wardas.wardas_id = receiver.wardas_id and ward_or_village.ward_or_village_id=wardas.ward_or_village_id and "
				+ "ward_or_village.township_id = township.township_id and sender.wardas_id=? order by sent_date desc";
		return template.query(sql, new RowMapper<Receiver>() {
			public Receiver mapRow(ResultSet rs, int arg1) throws SQLException {
				Receiver r = new Receiver();
				r.setReceiverId(rs.getInt(1));
				Sender s = new Sender();
				s.setSentDate(rs.getDate(2));
				s.setResidentNrcNo(rs.getString(8));
				r.setConfirm(rs.getInt(3));
				s.setWardAS(new WardAS(rs.getString(4),new WardOrVillage(rs.getString(5),new Township(rs.getString(6)))));
                r.setSender(s);
                r.setSenderId(rs.getInt(7));
                System.out.println("Inside get sent history method: receiverId"+r.getReceiverId());
                System.out.println("Inside get sent history method: senderId"+r.getSenderId());
				return r;
			}
			
		},wardas_id);
	}
}
