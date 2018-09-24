package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.FormHeader;

public class FormHeaderMapper implements RowMapper<FormHeader> {

	@Override
	public FormHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
		FormHeader formHeader = new FormHeader();

		formHeader.setGuid(rs.getString("id"));
		formHeader.setTableName(rs.getString("table_name"));
		formHeader.setLabelName(rs.getString("label_name"));
		formHeader.setDescription(rs.getString("description"));
		formHeader.setActiveVersion(rs.getString("active_version"));
		formHeader.setType(rs.getString("type"));

		return formHeader;
	}

}
