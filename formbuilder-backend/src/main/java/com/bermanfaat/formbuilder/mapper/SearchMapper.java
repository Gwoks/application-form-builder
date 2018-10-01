package com.bermanfaat.formbuilder.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.Search;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMapper implements RowMapper<Search> {
	@Override
	public Search mapRow(ResultSet rs, int rowNum) throws SQLException {
		Search search = new Search();

		search.setId(rs.getString("id"));
		search.setSearchId(rs.getString("search_id"));
		search.setLabel(rs.getString("label"));
		search.setSourceField(rs.getString("source_field"));
		search.setType(rs.getString("type"));
		search.setOrderNo(rs.getInt("order_no"));
		return search;
	}
}
