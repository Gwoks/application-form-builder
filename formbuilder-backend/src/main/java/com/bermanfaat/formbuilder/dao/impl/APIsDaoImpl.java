package com.bermanfaat.formbuilder.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.APIsDao;
import com.bermanfaat.formbuilder.mapper.APIsMapper;
import com.bermanfaat.formbuilder.model.APIs;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class APIsDaoImpl implements APIsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public APIs getAPIsByAPIsId(String apisId) {

		String sql = "SELECT apis_id, url, method, authentication, username, password FROM formconfig.apis "
				+ "WHERE apis_id = ?";

		APIs apis;
		try {
			apis = jdbcTemplate.queryForObject(sql, new Object[] { apisId }, new APIsMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			apis = new APIs();
		}

		CustomLogger.logSQL(sql);
		return apis;
	}

}
