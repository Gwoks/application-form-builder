package com.bermanfaat.formbuilder.service;

import java.util.List;

import com.bermanfaat.formbuilder.dto.SearchDTO;
import com.bermanfaat.formbuilder.model.Search;

public interface SearchService {
	List<List<SearchDTO>> getSearchList(String idSearch);

	List<List<SearchDTO>> getSearchJoin(String idSrch, String data);

	List<String> getSrchTableHeader(List<Search> searchById);
}
