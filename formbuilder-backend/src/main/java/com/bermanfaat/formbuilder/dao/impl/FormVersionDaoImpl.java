package com.bermanfaat.formbuilder.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.FormVersionDao;
import com.bermanfaat.formbuilder.mapper.FormVersionMapper;
import com.bermanfaat.formbuilder.model.FormVersion;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class FormVersionDaoImpl implements FormVersionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FormVersion> getAll() {
		String sql = "SELECT id, frmh_id, version, activated FROM formconfig.frmv";

		List<FormVersion> listFormVersion = jdbcTemplate.query(sql, new FormVersionMapper());
		return listFormVersion;
	}

	@Override
	public List<FormVersion> getListFormVersionByIdFormHeader(String idFormHeader) {
		String sql = "SELECT id, frmh_id, version, activated FROM formconfig.frmv where frmh_id = ?";
		List<FormVersion> listFormVersion = jdbcTemplate.query(sql, new Object[] { idFormHeader },
				new FormVersionMapper());

		CustomLogger.logSQL(sql);
		return listFormVersion;
	}

	@Override
	public FormVersion getFormVersionByVersion(String idFormHeader, String version) {
		String sql = "SELECT id, frmh_id, version, activated  "
				+ "FROM formconfig.frmv where frmh_id = ? and version = ?";

		FormVersion formVersion;
		try {
			formVersion = jdbcTemplate.queryForObject(sql, new Object[] { idFormHeader, version },
					new FormVersionMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			formVersion = new FormVersion();
		}

		CustomLogger.logSQL(sql);
		return formVersion;
	}

}
