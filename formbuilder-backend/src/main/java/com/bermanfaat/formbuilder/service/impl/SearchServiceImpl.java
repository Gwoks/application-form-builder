package com.bermanfaat.formbuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.SearchDao;
import com.bermanfaat.formbuilder.dto.SearchDTO;
import com.bermanfaat.formbuilder.model.Search;
import com.bermanfaat.formbuilder.service.SearchService;
import com.bermanfaat.formbuilder.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;

	// init
	@Override
	public List<List<SearchDTO>> getSearchList(String idSearch) {
		List<Search> searchList = searchDao.getSearchById(idSearch);
		List searchValue = searchDao.getSearchValue(getSearchListFieldName(searchList));
		List<List<SearchDTO>> res = generateSearchDTO(searchValue, searchList);
		return res;
	}

	// ketika search
	@Override
	public List<List<SearchDTO>> getSearchJoin(String idSrch, String data) {
		Map<String, String> additionString = CommonUtils.convertJSONtoStringMap(data);
		List<Search> searchList = searchDao.getSearchById(idSrch);
		List searchValue = searchDao.getSearchJoinSearch(idSrch, additionString,
				getSearchListFieldName(searchList));
		List<List<SearchDTO>> res = generateSearchDTO(searchValue, searchList);
		return res;
	}

	@Override
	public List<String> getSrchTableHeader(List<Search> searchById) {
		List<String> result = new ArrayList<String>();

		searchById.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		for (Search search : searchById) {
			result.add(search.getLabel());
		}

		return result;
	}

	private List<String> getSearchListFieldName(List<Search> searchList) {
		List<String> searchListFieldName = new ArrayList<>();
		for (Search srch : searchList) {
			if (srch.getType().equals("string")) {
				searchListFieldName.add(srch.getSourceField());
			}
		}
		return searchListFieldName;
	}

	List<List<SearchDTO>> generateSearchDTO(List searchValue, List<Search> searchList) {
		List<List<SearchDTO>> result = new ArrayList<>();
		searchList.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));

		for (Object srchValue : searchValue) {
			Map map = (Map) srchValue;
			List<SearchDTO> subRes = new ArrayList<>();

			for (Search srch : searchList) {
				SearchDTO srchDTO = new SearchDTO();
				srchDTO.setType(srch.getType());
				srchDTO.setLabel(srch.getLabel());

				for (Object key : map.keySet()) {
					String[] temp1 = srch.getSourceField().split("\\.");
					String temp = temp1[temp1.length - 1];
					if (key.toString().equals(temp) && map.get(key) != null) {
						srchDTO.setValue(map.get(key).toString());
					}
					if (!srchDTO.getType().equals("string")) {
						srchDTO.setValue(srch.getSourceField());
					}
				}

				subRes.add(srchDTO);
			}

			result.add(subRes);
		}

		return result;
	}
}
