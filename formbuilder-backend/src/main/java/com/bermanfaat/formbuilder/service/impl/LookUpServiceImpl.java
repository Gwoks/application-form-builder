package com.bermanfaat.formbuilder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.LookUpDao;
import com.bermanfaat.formbuilder.model.LookUp;
import com.bermanfaat.formbuilder.service.LookUpService;

@Service
public class LookUpServiceImpl implements LookUpService {

	@Autowired
	private LookUpDao lookUpDao;

	@Override
	public List<LookUp> getLookUpByIdLkp(String idLkp) {
		return lookUpDao.getLookUpByIdLkp(idLkp);
	}

	@Override
	public List<LookUp> getLookUpByIdLkp(String idLkp, String parentid) {
		return lookUpDao.getLookUpByIdLkp(idLkp, parentid);
	}

}
