package com.bermanfaat.formbuilder.service;

import org.json.JSONException;
import org.json.JSONObject;

public interface ApplicationService {

	JSONObject getApplicationFromXML(String applicationID, String idRow) throws JSONException;

}
