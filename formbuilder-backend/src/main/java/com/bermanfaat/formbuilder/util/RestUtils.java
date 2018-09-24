package com.bermanfaat.formbuilder.util;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bermanfaat.formbuilder.model.KeyValue;

public class RestUtils {
	// https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html#put-java.lang.String-java.lang.Object-java.lang.Object...-

	static RestTemplate restTemplate = new RestTemplate();

	private CommonUtils commonUtils;

	public String send(String url, MultiValueMap<String, String> params, String body, String method) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);

		// headers.

		// request.

		// restTemplate.postForEntity(url, request, String.class);
		//
		// restTemplate.postForObject(url, body, String.class);
		//
		// restTemplate.postForObject(url, request, String.class, uriVariables);

		// Object response = new Object();
		// if (method.equalsIgnoreCase("post")) {
		// response = restTemplate.postForEntity(url, request, String.class);
		// } else if(method.equalsIgnoreCase("get")) {
		//// response = restTemplate.getForEntity
		// }

		return "This is the test object returned by previous execution";
	}

	public String send2(String baseUrl, String params, String body) {

		// Construct url with params
		String url = baseUrl;
		if (params != null) {
			System.out.println("INSIDE URL = " + url);
			url = baseUrl + "?" + params;
		}

		System.out.println("URL BASE = " + url);

		HttpHeaders headers = new HttpHeaders();

		// Set content type
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Set Authorization
		// headers.set("Authorization", "Basic " + "xxxxxxxxxxxx");

		HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);

		String response = restTemplate.postForObject(url, httpEntity, String.class);
		System.out.println(response);

		return response;
	}

	public void sendPut(String baseUrl, String params, String body) {
		// http://www.baeldung.com/rest-template

		String url = baseUrl;
		if (params != null) {
			System.out.println("INSIDE URL = " + url);
			url = baseUrl + "?" + params;
		}

		System.out.println("URL BASE = " + url);

		HttpHeaders headers = new HttpHeaders();

		// Set content type
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Set Authorization
		// headers.set("Authorization", "Basic " + "xxxxxxxxxxxx");

		HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);

		restTemplate.put(url, httpEntity, String.class);
		// return response;
	}

	public void sendDelete(String baseUrl, String params, String body) {
		String url = baseUrl;

		// convert keyvalue ke list keyvalue
		List<KeyValue> pathVariable = commonUtils.convertJSONtoListKeyValue(body);

		String type = "";
		String appid = "";
		String rowid = "";
		// dibentuk ke list string
		for (int i = 0; i < pathVariable.size(); i++) {

			if (pathVariable.get(i).getKey().equals("applicationid")) {
				appid = pathVariable.get(i).getValue();
			} else if (pathVariable.get(i).getKey().equals("rowid")) {
				rowid = pathVariable.get(i).getValue();
			} else if (pathVariable.get(i).getKey().equals("type")) {
				type = pathVariable.get(i).getValue();
			}
			// tempPathVariable = tempPathVariable + pathVariable.get(i).getValue() + "/";
		}

		// if (params != null) {
		// System.out.println("INSIDE URL = " + url);
		// url = baseUrl + "?" + params;
		// }

		url = url + appid + "/" + rowid;
		// url = url + tempPathVariable;

		System.out.println("URL BASE = " + url);

		restTemplate.delete(url);
	}
}
