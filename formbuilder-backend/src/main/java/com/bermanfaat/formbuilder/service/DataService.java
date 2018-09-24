package com.bermanfaat.formbuilder.service;

import com.bermanfaat.formbuilder.model.Response;
import com.bermanfaat.formbuilder.wrapper.FormTableHeader;

public interface DataService {

	Response addData(String data);

	Response updateData(String data);

	FormTableHeader getFormTableContent(String idForm);

	Response deleteData(String aplicationid, String rowid);

	// List<String> getEncryptedFields(String idForm);
	//
	// List<String> getControlTypeFileUpload(String idform);

}
