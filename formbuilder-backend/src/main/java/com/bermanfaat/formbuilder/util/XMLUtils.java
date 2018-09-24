package com.bermanfaat.formbuilder.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtils {

	static final String ATTRIBUTE_TYPE = "type";
	static final String ATTRIBUTE_ID = "id";
	static final String ATTRIBUTE_NAME = "name";
	static final String ATTRIBUTE_CHILDS = "childs";

//	public static void main(String[] args) {
//		try {
//			readXML();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	private static void readXML(String filePath) throws JSONException {

		File xmlFile = new File(filePath);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			// Initial JSON Object
			JSONObject jsonObj = new JSONObject();

			recursive(doc.getDocumentElement(), jsonObj);

			System.out.println(jsonObj);

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

	}

	private static void recursive(Element element, JSONObject jsonObj) throws JSONException {

		String type = element.getNodeName();
		String id = element.getAttribute(ATTRIBUTE_ID);
		String name = element.getAttribute(ATTRIBUTE_NAME);

		jsonObj.put(ATTRIBUTE_TYPE, type);
		jsonObj.put(ATTRIBUTE_ID, id);
		jsonObj.put(ATTRIBUTE_NAME, name);

		JSONArray array = new JSONArray();

		if (type.equals("form")) {
			JSONObject jsonObjForm = new JSONObject();
			jsonObjForm.put("HAHAHAHA", "HAHAHAHA");
			jsonObjForm.put("HAHAHAHA", "HAHAHAHA");

			array.put(jsonObjForm);

		} else {

			NodeList nodeList = element.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Node currentNode = nodeList.item(i);

					Element elementChild = (Element) currentNode;
					JSONObject jsonObjx = new JSONObject();
					recursive(elementChild, jsonObjx);
					array.put(jsonObjx);
				}
			}
		}

		jsonObj.put(ATTRIBUTE_CHILDS, array);

	}

}
