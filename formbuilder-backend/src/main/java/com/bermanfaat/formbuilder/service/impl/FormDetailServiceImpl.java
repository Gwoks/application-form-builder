package com.bermanfaat.formbuilder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.FormDetailDao;
import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.service.FormDetailService;

@Service
public class FormDetailServiceImpl implements FormDetailService {

	@Autowired
	private FormDetailDao formDetailDao;

	@Override
	public List<FormDetail> getAll() {
		List<FormDetail> formDetails = formDetailDao.getAll();
		formDetails.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		return formDetails;
	}

}
