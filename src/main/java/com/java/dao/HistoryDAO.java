package com.java.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.java.beans.History;

public class HistoryDAO {
	JdbcTemplate template; 
	public void setTemplate(JdbcTemplate template) { 
		this.template = template; 
	}
	
	public int saveHistory(History h) {
		String sql = "insert into resident_view_letter_history (letter_type,resident_view_letter_date,resident_id) values "
				+ "('"+h.getLetterType()+"','"+h.getResidentViewLetterDate()+"',"+h.getResidentId()+")";
		return template.update(sql);
	}
}
