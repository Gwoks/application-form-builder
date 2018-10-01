package com.bermanfaat.formbuilder.util;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SQLGenerator {

	// [INSERT] generate sql ke string
	public static String insert(Map keyValueMap, String tableName, List<String> encryptedFields,
			List<String> typeFileUploads, String idForm) {
		StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
		StringBuilder values = new StringBuilder();

		// Loop each data
		for (Iterator<Map.Entry<String, String>> i = keyValueMap.entrySet().iterator(); i.hasNext();) {

			Map.Entry<String, String> data = i.next();
			String key = data.getKey();

			sql.append(key);

			// di parse ke string karena tipe map nya <string, string>
			String value = String.valueOf(data.getValue());

			// input values dari kolom ke string value, kalo kosong, input null
			if (!value.equals("null") && !value.equals("")) {

				// If field type is file upload
				if (typeFileUploads.contains(key)) {

					// value yg diinput ke db lokasi sub foldernya(ex: images/asd/asd) <= ini
					// dimasukin ke db
					// lokas direktori (ex: C:/bermanfaat)
					value = UtilBase64Image.decode(value, idForm, true, key);
				}

				// If field type is encrypted
				if (encryptedFields.contains(key)) {
					try {
						// Bisa pilih md5 atau bcrypt
						value = EncodingUtils.generateMD5(value);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
			}
			value = CommonUtils.isStringNullOrBlank(value) ? "null" : "'" + value + "'";
			values.append(value);

			if (i.hasNext()) {
				sql.append(", ");
				values.append(", ");
			}
		}

		// gabungin isi dari key dan value
		sql.append(") VALUES (").append(values).append(")");

		System.out.println(sql.toString());
		return sql.toString();
	}

	// Generate String SQL Update
	public static String update(Map keyValueMap, String tableName, String idRow, List<String> encryptedFields,
			List<String> typeFileUploads, String idForm, String state) {

		StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");

		for (Iterator<Map.Entry<String, String>> i = keyValueMap.entrySet().iterator(); i.hasNext();) {
			Map.Entry<String, String> data = i.next();

			String key = data.getKey();

			// value = (ex: nama = 'namaQ') atau (nama = null)
			// di parse ke string karena tipe map nya <string, string>
			String value = String.valueOf(data.getValue());

			if (!value.equals("null") && !value.equals("")) {

				// If field type is file upload
				if (typeFileUploads.contains(key)) {

					// value yg diinput ke db lokasi sub foldernya(ex: images/asd/asd) <= ini
					// dimasukin ke db
					// lokas direktori (ex: C:/bermanfaat)
					value = UtilBase64Image.decode(value, idForm, true, key);
				}

				// If field type is encrypted
				if (encryptedFields.contains(key)) {
					try {
						// Bisa pilih md5 atau bcrypt
						value = EncodingUtils.generateMD5(value);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
			}

			value = CommonUtils.isStringNullOrBlank(value) ? "null" : "'" + value + "'";
			sql.append(key).append(" = ").append(value);

			if (i.hasNext()) {
				sql.append(", ");
			}
		}

		sql.append(" WHERE ").append(state).append(" = '" + idRow + "'");
		return sql.toString();
	}

	public static String searchJoinSql(List<String> searchFieldName) {
		String selectColumn = getSelectedColumn(searchFieldName);

		List<String> tableList = getSearchTableList(searchFieldName);

		String innerJoin = getSearchInnerJoin(tableList);

		String res = "SELECT " + selectColumn + " FROM " + tableList.get(0) + " " + innerJoin;
		// System.out.println(res);
		return res;
	}

	public static String searchJoinSqlSearch(String sqlJoin, Map<String, String> additionString) {
		String additionalWhere = generateWhere(additionString);
		String res = sqlJoin + additionalWhere;
		System.out.println(res);
		return res;
	}

	private static String generateWhere(Map<String, String> data) {

		String temp = "";

		if (data.toString() != "{}") {
			for (Iterator<String> iter = data.keySet().iterator(); iter.hasNext();) {
				String key = iter.next();
				String value = data.get(key);
				if (iter.hasNext()) {
					temp = temp + key + " = '" + value + "' AND ";
				} else {
					temp = temp + key + " = '" + value + "' ";
				}
			}

			String res = " WHERE " + temp;
			return res;
		} else {
			return temp;
		}

	}

	private static String getSelectedColumn(List<String> searchFieldName) {
		String res = "";
		for (int i = 0; i < searchFieldName.size(); i++) {
			if (i != searchFieldName.size() - 1) {
				res = res + searchFieldName.get(i) + ",";
			} else {
				res = res + searchFieldName.get(i);
			}
		}
		return res;
	}

	private static List<String> getSearchTableList(List<String> searchFieldName) {
		List<String> res = new ArrayList<>();

		Set<String> set = new LinkedHashSet<>();

		for (String srch : searchFieldName) {
			set.add(srch.substring(0, srch.lastIndexOf(".")));
		}

		res.addAll(set);
		return res;
	}

	private static String getSearchInnerJoin(List<String> tableList) {
		String res = "";
		// dimulai dari 1 bukan nol
		for (int i = 1; i < tableList.size(); i++) {
			res = res + "LEFT JOIN " + tableList.get(i) + " ON " + tableList.get(i) + ".id = " + tableList.get(0)
					+ ".id ";
		}
		return res;
	}

}
