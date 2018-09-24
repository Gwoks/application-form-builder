package com.bermanfaat.formbuilder.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.TableContentDao;
import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class TableContentDaoImpl implements TableContentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<KeyValue> getAllContent(String schemaTableName) {
		String sql = "SELECT * FROM " + schemaTableName;
		List results = jdbcTemplate.queryForList(sql);
		CustomLogger.logSQL(sql);
		return results;
	}

	@Override
	public List getLovByQuery(String query) {
		List results = jdbcTemplate.queryForList(query);
		return results;
	}

	@Override
	public String getlovcQueryByName(String lovName) {

		String sql = "SELECT query_string FROM formconfig.lovc WHERE lov_name = ?";
		CustomLogger.logSQL(sql + lovName);

		return jdbcTemplate.queryForObject(sql, new Object[] { lovName }, String.class);
	}

}
