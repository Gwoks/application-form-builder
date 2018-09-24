package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.FormDetail;

public interface FormDetailDao {
	List<FormDetail> getAll();

	List<FormDetail> getFormDetailByIdFormVersion(String idFormVersion);
}
