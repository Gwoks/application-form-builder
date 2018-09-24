package com.bermanfaat.formbuilder.service;

import java.util.List;

import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.wrapper.FormWrapper;

public interface FormHeaderService {

	List<FormHeader> getAll();

	FormWrapper getFormById(String idForm, String idData);

}
