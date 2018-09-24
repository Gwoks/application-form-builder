package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.KeyValue;

public interface TableContentDao {
	List<KeyValue> getAllContent(String schemaTableName);

	List<KeyValue> getLovByQuery(String idLov);

	String getlovcQueryByName(String name);
}
