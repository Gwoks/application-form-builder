package com.bermanfaat.formbuilder.util;

import java.io.IOException;
import java.util.*;

import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.wrapper.FormDataWrapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

	// Convert JSON string to List Rest
	public static List<FormDataWrapper> convertJSONtoListRest(String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// Prepare object
		List<FormDataWrapper> formDataWrappers = new ArrayList<FormDataWrapper>();

		try {
			formDataWrappers = mapper.readValue(data, new TypeReference<List<FormDataWrapper>>() {
			});

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return formDataWrappers;
	}

	// Convert JSON string to List Rest
	public static List<KeyValue> convertJSONtoListKeyValue(String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// Prepare object
		List<KeyValue> formDataKeyValue = new ArrayList<KeyValue>();

		try {
			formDataKeyValue = mapper.readValue(data, new TypeReference<List<KeyValue>>() {
			});

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return formDataKeyValue;
	}

	public static Map<String, String> convertJSONtoStringMap(String data) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		try {
			map = mapper.readValue(data, new TypeReference<Map<String, String>>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static boolean isStringNullOrBlank(String s) {
		return (s == null || s.trim().equals("") || s.equals("null"));
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			// System.out.println("Current time = " + UniqueID.get());
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("Current time milis = " + System.nanoTime());
		}

	}

}