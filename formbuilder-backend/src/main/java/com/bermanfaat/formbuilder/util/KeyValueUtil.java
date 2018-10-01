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
}
