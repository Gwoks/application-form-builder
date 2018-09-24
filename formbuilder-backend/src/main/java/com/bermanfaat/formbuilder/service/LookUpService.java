package com.bermanfaat.formbuilder.service;

import java.util.List;

import com.bermanfaat.formbuilder.model.LookUp;

public interface LookUpService {
	List<LookUp> getLookUpByIdLkp(String idLkp);

	List<LookUp> getLookUpByIdLkp(String idLkp, String parentid);
}
