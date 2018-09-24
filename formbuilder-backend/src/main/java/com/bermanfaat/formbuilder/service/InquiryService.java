package com.bermanfaat.formbuilder.service;

import java.util.List;

import com.bermanfaat.formbuilder.dto.InquiryDTO;
import com.bermanfaat.formbuilder.model.Inquiry;

public interface InquiryService {
	List<List<InquiryDTO>> getInquiryList(String idInquiry);

	List<List<InquiryDTO>> getInquiryJoin(String idInqr, String data);

	List<String> getInqrTableHeader(List<Inquiry> inquiryById);
}
