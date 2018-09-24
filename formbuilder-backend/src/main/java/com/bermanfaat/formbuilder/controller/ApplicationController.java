package com.bermanfaat.formbuilder.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.service.ApplicationService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("app")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping(path = "/{applicationid}/{idrow}", produces = MediaType.APPLICATION_JSON_VALUE)
	private String getApplicationFromXML(@PathVariable("applicationid") String applicationID,
			@PathVariable("idrow") String idRow) {

		JSONObject jsonObject = new JSONObject();

		List<JSONObject> listJSONObject = new ArrayList<JSONObject>();

		System.out.println("applicationID = " + applicationID + " idRow = " + idRow);
		try {
			jsonObject = applicationService.getApplicationFromXML(applicationID, idRow);
			listJSONObject.add(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listJSONObject.toString();
	}

	@GetMapping(path = "/{applicationid}", produces = MediaType.APPLICATION_JSON_VALUE)
	private String getApplicationFromXMLOnly(@PathVariable("applicationid") String applicationID) {

		JSONObject jsonObject = new JSONObject();

		List<JSONObject> listJSONObject = new ArrayList<JSONObject>();

		System.out.println("applicationID = " + applicationID);
		try {
			jsonObject = applicationService.getApplicationFromXML(applicationID, null);
			listJSONObject.add(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listJSONObject.toString();
	}

}