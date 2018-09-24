package com.bermanfaat.formbuilder.dao;

import java.util.List;
import java.util.Map;

import com.bermanfaat.formbuilder.model.Inquiry;

public interface InquiryDao {
	List<Inquiry> getInquiryById(String inquiryId);

	List getInquiryValue(List<String> inquiryFieldName);

	List getInquiryJoinSearch(String idInqr, Map<String, String> additionString, List<String> inquiryListFieldName);
}
