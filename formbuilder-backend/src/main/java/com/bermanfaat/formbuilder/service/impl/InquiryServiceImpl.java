package com.bermanfaat.formbuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.InquiryDao;
import com.bermanfaat.formbuilder.dto.InquiryDTO;
import com.bermanfaat.formbuilder.model.Inquiry;
import com.bermanfaat.formbuilder.service.InquiryService;
import com.bermanfaat.formbuilder.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	private InquiryDao inquiryDao;

	// init
	@Override
	public List<List<InquiryDTO>> getInquiryList(String idInquiry) {
		List<Inquiry> inquiryList = inquiryDao.getInquiryById(idInquiry);
		List inquiryValue = inquiryDao.getInquiryValue(getInquiryListFieldName(inquiryList));
		List<List<InquiryDTO>> res = generateInquiryDTO(inquiryValue, inquiryList);
		return res;
	}

	// ketika search
	@Override
	public List<List<InquiryDTO>> getInquiryJoin(String idInqr, String data) {
		Map<String, String> additionString = CommonUtils.convertJSONtoStringMap(data);
		List<Inquiry> inquiryList = inquiryDao.getInquiryById(idInqr);
		List inquiryValue = inquiryDao.getInquiryJoinSearch(idInqr, additionString,
				getInquiryListFieldName(inquiryList));
		List<List<InquiryDTO>> res = generateInquiryDTO(inquiryValue, inquiryList);
		return res;
	}

	@Override
	public List<String> getInqrTableHeader(List<Inquiry> inquiryById) {
		List<String> result = new ArrayList<String>();

		inquiryById.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		for (Inquiry inquiry : inquiryById) {
			result.add(inquiry.getLabel());
		}

		return result;
	}

	private List<String> getInquiryListFieldName(List<Inquiry> inquiryList) {
		List<String> inquiryListFieldName = new ArrayList<>();
		for (Inquiry inq : inquiryList) {
			if (inq.getType().equals("string")) {
				inquiryListFieldName.add(inq.getSourceField());
			}
		}
		return inquiryListFieldName;
	}

	List<List<InquiryDTO>> generateInquiryDTO(List inquiryValue, List<Inquiry> inquiryList) {
		List<List<InquiryDTO>> result = new ArrayList<>();
		inquiryList.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));

		for (Object inqValue : inquiryValue) {
			Map map = (Map) inqValue;
			List<InquiryDTO> subRes = new ArrayList<>();

			for (Inquiry inq : inquiryList) {
				InquiryDTO inqDTO = new InquiryDTO();
				inqDTO.setType(inq.getType());
				inqDTO.setLabel(inq.getLabel());

				for (Object key : map.keySet()) {
					String[] temp1 = inq.getSourceField().split("\\.");
					String temp = temp1[temp1.length - 1];
					if (key.toString().equals(temp) && map.get(key) != null) {
						inqDTO.setValue(map.get(key).toString());
					}
					if (!inqDTO.getType().equals("string")) {
						inqDTO.setValue(inq.getSourceField());
					}
				}

				subRes.add(inqDTO);
			}

			result.add(subRes);
		}

		return result;
	}
}
