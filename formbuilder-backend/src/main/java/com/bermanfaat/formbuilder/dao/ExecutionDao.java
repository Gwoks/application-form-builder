package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.Execution;

public interface ExecutionDao {
	List<Execution> getListExecutionByExecAPIsId(String execAPIsId);
}
