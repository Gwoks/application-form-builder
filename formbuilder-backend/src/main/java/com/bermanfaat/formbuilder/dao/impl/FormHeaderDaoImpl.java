package com.bermanfaat.formbuilder.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.FormHeaderDao;
import com.bermanfaat.formbuilder.mapper.FormHeaderMapper;
import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class FormHeaderDaoImpl implements FormHeaderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FormHeader> getAll() {
		String sql = "SELECT id, table_name, label_name, description, active_version, type FROM formconfig.frmh";

		List<FormHeader> listFormHeader = jdbcTemplate.query(sql, new FormHeaderMapper());

		CustomLogger.logSQL(sql);

		return listFormHeader;
	}

	@Override
	public FormHeader getFormById(String idForm) {
		String sql = "SELECT id, table_name, label_name, description, active_version, type "
				+ "FROM formconfig.frmh where id = ?";

		FormHeader formHeader;
		try {
			formHeader = jdbcTemplate.queryForObject(sql, new Object[] { idForm }, new FormHeaderMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			formHeader = new FormHeader();
		}

		CustomLogger.logSQL(sql);
		return formHeader;
	}

}
