package com.bermanfaat.formbuilder.dao;

import java.util.List;
import java.util.Map;

import com.bermanfaat.formbuilder.model.Search;

public interface SearchDao {
	List<Search> getSearchById(String searchId);

	List getSearchValue(List<String> searchFieldName);

	List getSearchJoinSearch(String idSrch, Map<String, String> additionString, List<String> searchListFieldName);
}
