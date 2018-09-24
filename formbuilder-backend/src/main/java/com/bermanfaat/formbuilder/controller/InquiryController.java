package com.bermanfaat.formbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.dto.InquiryDTO;
import com.bermanfaat.formbuilder.service.InquiryService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("inqr")
public class InquiryController {
	@Autowired
	private InquiryService inquiryService;

	@GetMapping("/{idInqr}")
	public List<List<InquiryDTO>> getInquiryById(@PathVariable("idInqr") String idInqr) {
		try {
			return inquiryService.getInquiryList(idInqr);
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/{idInqr}")
	public List<List<InquiryDTO>> getInquiryByJoin(@PathVariable("idInqr") String idInqr, @RequestBody String data) {
		try {
			List<List<InquiryDTO>> res = inquiryService.getInquiryJoin(idInqr, data);
			return res;
		} catch (Exception e) {
			return null;
		}
	}
}