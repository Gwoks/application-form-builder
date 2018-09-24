package com.bermanfaat.formbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.service.ExecutionService;
import com.bermanfaat.formbuilder.util.ResponseUtil;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("execution")
public class ExecutionController {

	@Autowired
	private ExecutionService executionService;

	@PostMapping("/{execAPIsId}")
	public String execute(@RequestBody String data, @PathVariable("execAPIsId") String execAPIsId) {
		try {
			String result = executionService.execute(execAPIsId, data);

			return result;
		} catch (Exception e) {
			return ResponseUtil.response(0);
		}
	}
}