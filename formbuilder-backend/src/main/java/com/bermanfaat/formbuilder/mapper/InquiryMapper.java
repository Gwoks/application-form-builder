package com.bermanfaat.formbuilder.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.Inquiry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InquiryMapper implements RowMapper<Inquiry> {
	@Override
	public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
		Inquiry inquiry = new Inquiry();

		inquiry.setId(rs.getString("id"));
		inquiry.setInquiryId(rs.getString("inquiry_id"));
		inquiry.setLabel(rs.getString("label"));
		inquiry.setSourceField(rs.getString("source_field"));
		inquiry.setType(rs.getString("type"));
		inquiry.setOrderNo(rs.getInt("order_no"));
		return inquiry;
	}
}
