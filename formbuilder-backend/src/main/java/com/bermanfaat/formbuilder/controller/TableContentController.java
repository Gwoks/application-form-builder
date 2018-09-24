package com.bermanfaat.formbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.service.DataService;
import com.bermanfaat.formbuilder.service.TableContentService;
import com.bermanfaat.formbuilder.wrapper.FormTableContent;
import com.bermanfaat.formbuilder.wrapper.FormTableHeader;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("table")
public class TableContentController {

	@Autowired
	private DataService dataService;

	@Autowired
	private TableContentService tableContentService;

	@GetMapping("schematable/{schemaTableName}/")
	private List<FormTableContent> getTablecontent(@PathVariable("schemaTableName") String schemaTableName) {
		try {
			return tableContentService.getShcemaTableContent(schemaTableName);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@PostMapping("lov/{idLov}/")
	private List<FormTableContent> getLov(@PathVariable("idLov") String idLov, @RequestBody List<KeyValue> params) {
		try {
			return tableContentService.getLovByQuery(idLov, params);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("formtable/{idForm}/")
	private FormTableHeader getAllContent(@PathVariable("idForm") String idForm) {
		try {
			return dataService.getFormTableContent(idForm);
		} catch (Exception e) {
			// return dataService.getAllTableContent(idForm);
			return null;
		}
	}
}