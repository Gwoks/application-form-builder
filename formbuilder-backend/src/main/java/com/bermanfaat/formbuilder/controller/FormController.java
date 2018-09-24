package com.bermanfaat.formbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.service.FormDetailService;
import com.bermanfaat.formbuilder.service.FormHeaderService;
import com.bermanfaat.formbuilder.wrapper.FormWrapper;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("form")
public class FormController {

	@Autowired
	private FormHeaderService formHeaderService;

	@Autowired
	private FormDetailService formDetailService;

	@GetMapping("/")
	private List<FormHeader> getAll() {
		return formHeaderService.getAll();
	}

	@GetMapping("/{idform}")
	private FormWrapper getFormById(@PathVariable("idform") String idForm) {
		return formHeaderService.getFormById(idForm, null);
	}

	@GetMapping("/{idform}/{iddata}")
	private FormWrapper getFormDetailById(@PathVariable("idform") String idForm,
			@PathVariable("iddata") String idData) {
		return formHeaderService.getFormById(idForm, idData);
	}

	@GetMapping("/test")
	private List<FormDetail> getTest() {
		return formDetailService.getAll();
	}
}
