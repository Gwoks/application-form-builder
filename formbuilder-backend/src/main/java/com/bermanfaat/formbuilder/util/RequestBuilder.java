package com.bermanfaat.formbuilder.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

import com.bermanfaat.formbuilder.wrapper.FormDataWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBuilder {

	public static String replaceTokens(String text, String source, String sourceType) throws JSONException {
		Pattern pattern = Pattern.compile("\\<(.+?)\\>");
		Matcher matcher = pattern.matcher(text);
		StringBuffer buffer = new StringBuffer();

		if (sourceType.equals("formbuilder")) {
			List<FormDataWrapper> convertedData = CommonUtils.convertJSONtoListRest(source);

			while (matcher.find()) {
				String tokens = matcher.group(1);
				String[] token = tokens.split("\\.");

				if (token[0].equals("all")) {
					return source;
				} else if (token[0].equals("none")) {
					return null;
				} else {
					for (FormDataWrapper formDataWrapper : convertedData) {
						if (formDataWrapper.getIdform().equals(token[0])) {
							Map<String, String> data = new HashMap<>();

							for (Object listKeyValue : formDataWrapper.getData()) {
								Map map = (Map) listKeyValue;
								for (Object keyValueRow : map.keySet()) {
									data.put((String) keyValueRow, (String) map.get(keyValueRow));
								}
							}

							// Map<String, String> data = formDataWrapper.getData();

							for (Map.Entry<String, String> entry : data.entrySet()) {
								if (entry.getKey().equals(token[1])) {
									String tokenz = entry.getValue();
									matcher.appendReplacement(buffer, "");
									buffer.append(tokenz);
								}
							}

						}
					}
				}
			}

		} else {
			JSONObject jsonObj = new JSONObject(source);

			while (matcher.find()) {
				String tokens = matcher.group(1);
				String[] token = tokens.split("\\.");

				if (token[0].equals("f")) {
					String tokenz = jsonObj.getString(token[1]);
					matcher.appendReplacement(buffer, "");
					buffer.append(tokenz);
				} else if (token[0].equals("all")) {
					return source;
				} else if (token[0].equals("none")) {
					return null;
				}
			}
		}

		matcher.appendTail(buffer);

		System.out.println("buffer " + buffer.toString());

		if (buffer.length() == 0) {
			return text;
		} else {
			return buffer.toString();
		}
	}
}
