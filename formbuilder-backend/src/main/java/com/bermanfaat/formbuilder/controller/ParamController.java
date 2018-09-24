package com.bermanfaat.formbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.model.LookUp;
import com.bermanfaat.formbuilder.service.LookUpService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("param")
public class ParamController {

	@Autowired
	private LookUpService lookUpService;

	@GetMapping("/{idlkp}/{parentid}")
	private List<LookUp> getFormById(@PathVariable("idlkp") String idlkp, @PathVariable("parentid") String parentid) {
		return lookUpService.getLookUpByIdLkp(idlkp, parentid);
	}

	@GetMapping("/{idlkp}")
	private List<LookUp> getFormById(@PathVariable("idlkp") String idlkp) {
		return lookUpService.getLookUpByIdLkp(idlkp);
	}

}