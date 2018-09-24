package com.bermanfaat.formbuilder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bermanfaat.formbuilder.model.KeyValue;

public class KeyValueUtil {
	public static List<KeyValue> generateKeyValue(List keyValues) {
		List<KeyValue> temp = new ArrayList<KeyValue>();

		for (Object keyValue : keyValues) {
			Map map = (Map) keyValue;
			for (Object key : map.keySet()) {
				// buat rest baru, untuk kemudian dimasukan ke restList
				KeyValue rest = new KeyValue();

				// set key
				rest.setKey(key.toString());

				// cek apakah valuenya null atau tidak? kalau null, tidak setValue ke rest
				// kalau tidak, convert to string kemudian setValue
				if ((map.get(key) != null)) {
					rest.setValue(map.get(key).toString());
				}
				// masuk rest baru ke restList
				temp.add(rest);
			}
		}

		return temp;
	}

	public List generateNestedListKeyValue(List keyValues) {
		System.out.println(keyValues);
		List<List<KeyValue>> result = new ArrayList<>();

		for (Object keyValue : keyValues) {
			Map map = (Map) keyValue;
			for (Object key : map.keySet()) {
				// buat rest baru, untuk kemudian dimasukan ke restList
				KeyValue rest = new KeyValue();

				// set key
				rest.setKey(key.toString());

				// cek apakah valuenya null atau tidak? kalau null, tidak setValue ke rest
				// kalau tidak, convert to string kemudian setValue
				if ((map.get(key) != null)) {
					rest.setValue(map.get(key).toString());
				}
				// masuk rest baru ke restList
				// result.add(rest);
			}
		}
		return result;
	}

	// List<List<InquiryDTO>> generateInquiryDTO(List inquiryValue, List<Inquiry>
	// inquiryList) {
	// List<List<InquiryDTO>> result = new ArrayList<>();
	// inquiryList.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
	//
	// for (Object inqValue : inquiryValue) {
	// Map map = (Map) inqValue;
	// List<InquiryDTO> subRes = new ArrayList<>();
	//
	// for (Inquiry inq : inquiryList) {
	// InquiryDTO inqDTO = new InquiryDTO();
	// inqDTO.setType(inq.getType());
	// inqDTO.setLabel(inq.getLabel());
	//
	// for (Object key : map.keySet()) {
	// String[] temp1 = inq.getSourceField().split("\\.");
	// String temp = temp1[temp1.length - 1];
	// if (key.toString().equals(temp) && map.get(key) != null) {
	// inqDTO.setValue(map.get(key).toString());
	// }
	// if (!inqDTO.getType().equals("string")) {
	// inqDTO.setValue(inq.getSourceField());
	// }
	// }
	//
	// subRes.add(inqDTO);
	// }
	//
	// result.add(subRes);
	// }
	//
	// return result;
	// }
}
