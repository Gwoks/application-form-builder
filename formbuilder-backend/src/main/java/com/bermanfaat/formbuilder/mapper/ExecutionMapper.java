package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.Execution;

public class ExecutionMapper implements RowMapper<Execution> {

	@Override
	public Execution mapRow(ResultSet rs, int rowNum) throws SQLException {

		Execution execution = new Execution();

		execution.setExecId(rs.getString("exec_id"));
		execution.setExecAPIsId(rs.getString("exec_apis_id"));
		execution.setApisId(rs.getString("apis_id"));
		execution.setParamTokens(rs.getString("param_tokens"));
		execution.setBodyTokens(rs.getString("body_tokens"));
		execution.setExecOrder(rs.getInt("exec_order"));
		execution.setSourceType(rs.getString("source_type"));
		execution.setSourceOrder(rs.getInt("source_order"));

		return execution;

	}

}
