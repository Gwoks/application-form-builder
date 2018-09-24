package com.bermanfaat.formbuilder.util;

public class ResponseUtil {
	public static String response(int row) {
		String result = "{\"response\" : \"inserted: " + row + "\"}";
		return result;
	}

}
