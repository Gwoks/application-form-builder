package com.bermanfaat.formbuilder.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.ExecutionDao;
import com.bermanfaat.formbuilder.mapper.ExecutionMapper;
import com.bermanfaat.formbuilder.model.Execution;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class ExecutionDaoImpl implements ExecutionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Execution> getListExecutionByExecAPIsId(String execAPIsId) {

		String sql = "SELECT exec_id, exec_apis_id, apis_id, param_tokens, body_tokens, exec_order, "
				+ "source_type, source_order "
				+ "FROM formconfig.exec " 
				+ "WHERE exec_apis_id = ? "
				+ "ORDER BY exec_order  ";

		List<Execution> listExecution;
		try {
			listExecution = jdbcTemplate.query(sql, new Object[] { execAPIsId }, new ExecutionMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			listExecution = new ArrayList<>();
		}

		CustomLogger.logSQL(sql);
		return listExecution;
	}

}
