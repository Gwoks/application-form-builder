package com.bermanfaat.formbuilder.dao;

import com.bermanfaat.formbuilder.model.APIs;

public interface APIsDao {
	APIs getAPIsByAPIsId(String apisId);
}
