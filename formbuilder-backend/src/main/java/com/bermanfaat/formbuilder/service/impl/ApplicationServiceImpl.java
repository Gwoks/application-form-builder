package com.bermanfaat.formbuilder.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.bermanfaat.formbuilder.dao.DataDao;
import com.bermanfaat.formbuilder.dao.FormDetailDao;
import com.bermanfaat.formbuilder.dao.FormHeaderDao;
import com.bermanfaat.formbuilder.dao.FormVersionDao;
import com.bermanfaat.formbuilder.dao.SearchDao;
import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.model.FormVersion;
import com.bermanfaat.formbuilder.model.Search;
import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.service.ApplicationService;
import com.bermanfaat.formbuilder.util.CommonUtils;
import com.bermanfaat.formbuilder.util.UtilBase64Image;
import com.bermanfaat.formbuilder.wrapper.FormWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	static final String ATTRIBUTE_TYPE = "type";
	static final String ATTRIBUTE_ID = "id";
	static final String ATTRIBUTE_NAME = "name";
	static final String ATTRIBUTE_CHILDS = "childs";
	static final String ATTRIBUTE_EXECUTION = "execution";
	static final String ATTRIBUTE_VALUE = "value";
	static final String ATTRIBUTE_IS_READONLY = "is_readonly";
	static final String ATTRIBUTE_VALIDATE_FIRST = "validate_first";
	static final String ATTRIBUTE_STATE = "state";
	static final String ATTRIBUTE_PAGE_ACTION = "page_action";
	static final String ATTRIBUTE_TABLE_HEADER = "header_value";

	private boolean isFormHeader = false;

	@Value("${xml.location.base}")
	private String xmlLocationBase;

	@Value("${company.name}")
	private String companyName;

	@Value("${modul.name}")
	private String modulName;

	@Autowired
	private FormHeaderDao formHeaderDao;

	@Autowired
	private FormVersionDao formVersionDao;

	@Autowired
	private FormDetailDao formDetailDao;

	@Autowired
	private DataDao dataDao;

	@Autowired
	private SearchDao searchDao;

	@Override
	public JSONObject getApplicationFromXML(String applicationID, String idRow) throws JSONException {
		
		// Please jangan di hardcode, config aja di application.properties
		String filePath = xmlLocationBase + File.separator + companyName + File.separator + modulName + File.separator
				+ applicationID + ".xml" + File.separator;
		
		File xmlFile = new File(filePath);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		// Initial JSON Object
		JSONObject jsonObj = new JSONObject();

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			recursive(doc.getDocumentElement(), jsonObj, idRow, null);

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

		// System.out.println(jsonObj.toString());
		return jsonObj;
	}

	private void recursive(Element element, JSONObject jsonObj, String idRow, String idChild)
			throws JSONException, JsonProcessingException {

		String type = element.getNodeName();
		String id = element.getAttribute(ATTRIBUTE_ID);
		String name = element.getAttribute(ATTRIBUTE_NAME);

		jsonObj.put(ATTRIBUTE_TYPE, type);
		jsonObj.put(ATTRIBUTE_ID, id);
		jsonObj.put(ATTRIBUTE_NAME, name);

		JSONArray array = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();

		if (type.equals("form")) {

			FormWrapper formWrapper = new FormWrapper();

			if (!CommonUtils.isStringNullOrBlank(id)) {
				recursiveForm(formWrapper, id, idRow, idChild, type);
			}

			String mapperFormWrapper = mapper.writeValueAsString(formWrapper);
			String isReadonly = element.getAttribute(ATTRIBUTE_IS_READONLY);

			jsonObj.put(ATTRIBUTE_VALUE, new JSONObject(mapperFormWrapper));
			jsonObj.put(ATTRIBUTE_IS_READONLY, isReadonly);
			getChildNode(element, array, idRow, idChild);

		} else if (type.equals("form-detail")) {

			// Get child yg udah pasti form
			NodeList nodeList = element.getChildNodes();

			List<String> listIDofFormDetail = new ArrayList<String>();

			// Add to list how many data contains in form detail
			if (!CommonUtils.isStringNullOrBlank(idRow)) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node currentNode = nodeList.item(i);
						Element elementChild = (Element) currentNode;

						String typeChild = elementChild.getNodeName();
						String idChildx = elementChild.getAttribute(ATTRIBUTE_ID);

						if (typeChild.equals("form")) {
							List<String> listIDofCurrentForm = dataDao.getListIdByIdForm(idChildx, idRow);
							System.out.println("id of forms = " + idChildx);
							for (String currentId : listIDofCurrentForm) {
								if (!listIDofFormDetail.contains(currentId)) {
									listIDofFormDetail.add(currentId);
								}
							}
						}
					}
				}
				
				if (listIDofFormDetail.size() == 0) {
					listIDofFormDetail.add(0, null);
				}

			} else {
				listIDofFormDetail.add(0, null);
			}

			// Loop based on how many data
			for (String idDetail : listIDofFormDetail) {

				JSONObject jsonObjx = new JSONObject();

				// masuk recursive
				jsonObjx.put(ATTRIBUTE_TYPE, "forms");
				jsonObjx.put(ATTRIBUTE_ID, "FRMDTL" + idDetail);
				jsonObjx.put(ATTRIBUTE_NAME, "FRMDTL" + idDetail);

				JSONArray arrayBelow = new JSONArray();

				isFormHeader = true;
				getChildNode(element, arrayBelow, idRow, idDetail);
				isFormHeader = false;

				jsonObjx.put(ATTRIBUTE_CHILDS, arrayBelow);

				array.put(jsonObjx);
			}

		} else if (type.equals("table")) {
			List<String> result = generateListForTable(searchDao.getSearchById(id));

			String mapperTable = mapper.writeValueAsString(result);
			jsonObj.put(ATTRIBUTE_TABLE_HEADER, mapperTable);
			getChildNode(element, array, idRow, idChild);

		} else if (type.equals("buttons")) {
			String stateButton = element.getAttribute(ATTRIBUTE_STATE);

			jsonObj.put(ATTRIBUTE_STATE, stateButton);

			getChildNode(element, array, idRow, idChild);
		} else if (type.equals("button")) {
			String execution = element.getAttribute(ATTRIBUTE_EXECUTION);
			String validate_first = element.getAttribute(ATTRIBUTE_VALIDATE_FIRST);
			String page_action = element.getAttribute(ATTRIBUTE_PAGE_ACTION);

			jsonObj.put(ATTRIBUTE_EXECUTION, execution);
			jsonObj.put(ATTRIBUTE_VALIDATE_FIRST, validate_first);
			jsonObj.put(ATTRIBUTE_PAGE_ACTION, page_action);
			getChildNode(element, array, idRow, idChild);

		} else if (type.equals("header-detail")) {
			FormWrapper formWrapper = new FormWrapper();

			if (!CommonUtils.isStringNullOrBlank(id)) {
				recursiveForm(formWrapper, id, idRow, idChild, type);
			}

			String mapperFormWrapper = mapper.writeValueAsString(formWrapper);
			String isReadonly = element.getAttribute(ATTRIBUTE_IS_READONLY);

			jsonObj.put(ATTRIBUTE_VALUE, mapperFormWrapper);
			jsonObj.put(ATTRIBUTE_IS_READONLY, isReadonly);
			getChildNode(element, array, idRow, idChild);

		} else {
			getChildNode(element, array, idRow, idChild);

		}
		jsonObj.put(ATTRIBUTE_CHILDS, array);
	}

	private void getChildNode(Element element, JSONArray array, String idRow, String idChild)
			throws JsonProcessingException, JSONException {
		NodeList nodeList = element.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Node currentNode = nodeList.item(i);
				Element elementChild = (Element) currentNode;
				JSONObject jsonObjx = new JSONObject();
				recursive(elementChild, jsonObjx, idRow, idChild);
				array.put(jsonObjx);

			}
		}
	}

	private void recursiveForm(FormWrapper formWrapper, String id, String idRow, String idChild, String type) {
		FormHeader formHeader = formHeaderDao.getFormById(id);
		formWrapper.setFormHeader(formHeader);

		FormVersion formVersion = formVersionDao.getFormVersionByVersion(id, formHeader.getActiveVersion());
		formWrapper.setFormVersion(formVersion);

		List<FormDetail> listFormDetails = formDetailDao.getFormDetailByIdFormVersion(formVersion.getGuid());

		List<KeyValue> listValueFormDetails = new ArrayList<KeyValue>();

		if (isFormHeader) {
			if (!CommonUtils.isStringNullOrBlank(idChild)) {
				listValueFormDetails = dataDao.getValueFormDetail(id, idChild);
			}
		} else {
			if (!CommonUtils.isStringNullOrBlank(idRow)) {
				listValueFormDetails = dataDao.getValueFormDetail(id, idRow);
			}
		}

		System.out.println("ID = " + id + ", id row = " + idRow);

		for (FormDetail listformDetail : listFormDetails) {
			for (KeyValue listValueFormDetail : listValueFormDetails) {

				if (listformDetail.getFieldName().equals(listValueFormDetail.getKey())) {
					if (listformDetail.getControlType().equals("fileupload")
							&& listValueFormDetail.getValue() != null) {
						listformDetail.setValue(UtilBase64Image.encode(listValueFormDetail.getValue(), null,
								listformDetail.getFieldName()));
					} else {
						listformDetail.setValue(listValueFormDetail.getValue());
					}
				}

			}
		}

		listFormDetails.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		formWrapper.setListFormDetail(listFormDetails);
		// return formWrapper;
	}

	private List<String> generateListForTable(List<Search> searchById) {
		List<String> result = new ArrayList<String>();

		searchById.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		for (Search search : searchById) {
			result.add(search.getLabel());
		}

		return result;
	}

}
