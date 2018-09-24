package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.FormVersion;

public class FormVersionMapper implements RowMapper<FormVersion> {

	@Override
	public FormVersion mapRow(ResultSet rs, int rowNum) throws SQLException {
		FormVersion formVersion = new FormVersion();

		formVersion.setGuid(rs.getString("id"));
		formVersion.setFormHeaderId(rs.getString("frmh_id"));
		formVersion.setVersion(rs.getString("version"));
		formVersion.setActivated(rs.getBoolean("activated"));

		return formVersion;
	}

}
