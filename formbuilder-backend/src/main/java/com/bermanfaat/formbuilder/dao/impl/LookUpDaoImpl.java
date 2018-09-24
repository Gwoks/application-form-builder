package com.bermanfaat.formbuilder.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.LookUpDao;
import com.bermanfaat.formbuilder.mapper.LookUpMapper;
import com.bermanfaat.formbuilder.model.LookUp;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class LookUpDaoImpl implements LookUpDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<LookUp> getLookUpByIdLkp(String idLkp) {
		String sql = "SELECT key, value FROM param." + idLkp + " ORDER BY value ASC";
		List<LookUp> listLookUp = jdbcTemplate.query(sql, new LookUpMapper());
		CustomLogger.logSQL(sql);
		return listLookUp;
	}

	@Override
	public List<LookUp> getLookUpByIdLkp(String idLkp, String parentid) {
		String sql = "SELECT key, value FROM param." + idLkp + " WHERE parentid = ? " + " ORDER BY value ASC";
		List<LookUp> listLookUp = jdbcTemplate.query(sql, new Object[] { parentid }, new LookUpMapper());
		CustomLogger.logSQL(sql);
		return listLookUp;
	}

}
