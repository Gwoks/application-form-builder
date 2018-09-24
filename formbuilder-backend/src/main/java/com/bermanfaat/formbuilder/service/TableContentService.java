package com.bermanfaat.formbuilder.service;

import java.util.List;

import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.wrapper.FormTableContent;

public interface TableContentService {

	List<FormTableContent> getShcemaTableContent(String schemaTableName);

	List<FormTableContent> getLovByQuery(String idLov, List<KeyValue> params);

}
