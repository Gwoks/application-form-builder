package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.LookUp;

public class LookUpMapper implements RowMapper<LookUp> {

	@Override
	public LookUp mapRow(ResultSet rs, int rowNum) throws SQLException {
		LookUp lookUp = new LookUp();

		lookUp.setKey(rs.getString("key"));
		lookUp.setValue(rs.getString("value"));

		return lookUp;
	}

}
