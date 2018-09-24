package com.bermanfaat.formbuilder.dao;

import java.util.List;

import com.bermanfaat.formbuilder.model.LookUp;

public interface LookUpDao {
	List<LookUp> getLookUpByIdLkp(String idLkp);

	List<LookUp> getLookUpByIdLkp(String idLkp, String parentid);
}
