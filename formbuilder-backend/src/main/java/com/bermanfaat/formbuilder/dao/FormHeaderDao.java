package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.FormHeader;

public interface FormHeaderDao {
	List<FormHeader> getAll();

	FormHeader getFormById(String idForm);
}