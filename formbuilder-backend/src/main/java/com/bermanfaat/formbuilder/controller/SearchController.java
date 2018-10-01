package com.bermanfaat.formbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.dto.SearchDTO;
import com.bermanfaat.formbuilder.service.SearchService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("srch")
public class SearchController {
	@Autowired
	private SearchService searchService;

	@GetMapping("/{idSrch}")
	public List<List<SearchDTO>> getSearchById(@PathVariable("idSrch") String idSrch) {
		try {
			return searchService.getSearchList(idSrch);
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/{idSrch}")
	public List<List<SearchDTO>> getSearchByJoin(@PathVariable("idSrch") String idSrch, @RequestBody String data) {
		try {
			List<List<SearchDTO>> res = searchService.getSearchJoin(idSrch, data);
			return res;
		} catch (Exception e) {
			return null;
		}
	}
}