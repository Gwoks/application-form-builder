package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.model.Response;

public interface DataDao {

	Response addData(String data);

	Response updateData(String data);

	List<KeyValue> getValueFormDetail(String idForm, String idData);

	List<List<KeyValue>> getValueFormDetailByIdParent(String idForm, String idData);

	List getAllTableContent(String idForm);

	List<String> getEncryptedFields(String idForm);

	List<String> getControlTypeFileUpload(String idform);

	List<String> getListIdByIdForm(String idForm, String idParent);

	List<String> getListTableName(List<String> listFormDetail);

	int deleteListFormTableName(List<String> listFormTableName, String rowid);

	int deleListFormDetailTableName(List<String> listFormDetailTableName, String rowid);
}
