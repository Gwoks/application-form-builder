package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.FormVersion;

public interface FormVersionDao {
	List<FormVersion> getAll();

	FormVersion getFormVersionByVersion(String idFormHeader, String version);

	List<FormVersion> getListFormVersionByIdFormHeader(String idFormHeader);
}
