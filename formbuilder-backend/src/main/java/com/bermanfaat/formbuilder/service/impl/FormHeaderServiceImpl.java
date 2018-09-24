package com.bermanfaat.formbuilder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.DataDao;
import com.bermanfaat.formbuilder.dao.FormDetailDao;
import com.bermanfaat.formbuilder.dao.FormHeaderDao;
import com.bermanfaat.formbuilder.dao.FormVersionDao;
import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.model.FormVersion;
import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.service.FormHeaderService;
import com.bermanfaat.formbuilder.util.UtilBase64Image;
import com.bermanfaat.formbuilder.wrapper.FormWrapper;

@Service
public class FormHeaderServiceImpl implements FormHeaderService {

	@Autowired
	private FormHeaderDao formHeaderDao;

	@Autowired
	private FormVersionDao formVersionDao;

	@Autowired
	private FormDetailDao formDetailDao;

	@Autowired
	private DataDao dataDao;

	@Override
	public List<FormHeader> getAll() {
		return formHeaderDao.getAll();
	}

	@Override
	public FormWrapper getFormById(String idForm, String idData) {
		FormWrapper formWrapper = new FormWrapper();

		// get form header, kemudian masukan ke wrapper
		FormHeader formHeader = formHeaderDao.getFormById(idForm);
		formWrapper.setFormHeader(formHeader);

		// get form version, kemudian masukan ke wrapper
		FormVersion formVersion = formVersionDao.getFormVersionByVersion(idForm, formHeader.getActiveVersion());
		formWrapper.setFormVersion(formVersion);

		// get form detail, berupa detail dari masing masing baris
		List<FormDetail> listFormDetails = formDetailDao.getFormDetailByIdFormVersion(formVersion.getGuid());
		// formWrapper.setListFormDetail(listFormDetails);

		// get value dari masing maisng kolom (kolom dan value)
		List<KeyValue> listValueFormDetails = dataDao.getValueFormDetail(idForm, idData);

		// input value dari masing masing kolom (di Rest) ke value di FormDetail
		for (FormDetail listformDetail : listFormDetails) {
			// System.out.println( listformDetail.getValue()+"
			// "+listformDetail.isEncrypted());

			// check, kalo fielname(nama kolom) equal with key dari rest, maka dimasukan ke
			// value di form detail
			for (KeyValue listValueFormDetail : listValueFormDetails) {

				// kalo matching, diinputkan valuenya
				if (listformDetail.getFieldName().equals(listValueFormDetail.getKey())) {
					// kalo type nya fileupload, maka dirubah dlu valuenya
					if (listformDetail.getControlType().equals("fileupload")
							&& listValueFormDetail.getValue() != null) {
						listformDetail.setValue(UtilBase64Image.encode(listValueFormDetail.getValue(), idData,
								listformDetail.getFieldName()));
					}
					// else masukin ke listformdetail seperti biasa
					else {
						listformDetail.setValue(listValueFormDetail.getValue());
					}
				}
			}
		}

		// untuk mengurutkan berdasarkan order
		listFormDetails.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		// for(int i=0; i<listFormDetails.size(); i++) {
		// System.out.println(listFormDetails.);
		// }
		formWrapper.setListFormDetail(listFormDetails);
		return formWrapper;
	}

}
