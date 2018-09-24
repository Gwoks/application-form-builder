package com.bermanfaat.formbuilder.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.FormDetailDao;
import com.bermanfaat.formbuilder.mapper.FormDetailMapper;
import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class FormDetailDaoImpl implements FormDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FormDetail> getAll() {
		String sql = "SELECT * FROM formconfig.frmd;";

		List<FormDetail> listFormDetail = jdbcTemplate.query(sql, new FormDetailMapper());
		return listFormDetail;
	}

	@Override
	public List<FormDetail> getFormDetailByIdFormVersion(String idFormVersion) {

		String sql = "SELECT * FROM formconfig.frmd WHERE frmv_id = ?";

		List<FormDetail> listFormDetail = jdbcTemplate.query(sql, new Object[] { idFormVersion },
				new FormDetailMapper());

		CustomLogger.logSQL(sql + idFormVersion);
		return listFormDetail;
	}

}
