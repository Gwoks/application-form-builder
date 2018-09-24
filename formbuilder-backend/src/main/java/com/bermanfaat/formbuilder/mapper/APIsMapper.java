package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.APIs;

public class APIsMapper implements RowMapper<APIs> {

	@Override
	public APIs mapRow(ResultSet rs, int rowNum) throws SQLException {

		APIs apis = new APIs();

		apis.setApisId(rs.getString("apis_id"));
		apis.setUrl(rs.getString("url"));
		apis.setMethod(rs.getString("method"));
		apis.setAuthentication(rs.getBoolean("authentication"));
		apis.setUsername(rs.getString("username"));
		apis.setPassword(rs.getString("password"));

		return apis;
	}

}
