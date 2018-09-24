package com.bermanfaat.formbuilder.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bermanfaat.formbuilder.dao.DataDao;
import com.bermanfaat.formbuilder.dao.FormDetailDao;
import com.bermanfaat.formbuilder.dao.FormHeaderDao;
import com.bermanfaat.formbuilder.dao.FormVersionDao;
import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.model.FormVersion;
import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.model.Response;
import com.bermanfaat.formbuilder.service.DataService;
import com.bermanfaat.formbuilder.wrapper.FormTableContent;
import com.bermanfaat.formbuilder.wrapper.FormTableHeader;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class DataServiceImpl implements DataService {

	private static final String ATTRIBUTE_ID = "id";

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

	@Override
	public FormTableHeader getFormTableContent(String idForm) {

		FormTableHeader formTableHeader = new FormTableHeader();

		// get form header
		FormHeader formHeader = formHeaderDao.getFormById(idForm);
		formTableHeader.setFormHeader(formHeader);

		// encripted, untuk tidak ditampilkan ke depan
		List<String> encryptedFields = dataDao.getEncryptedFields(idForm);

		FormVersion formVersion = formVersionDao.getFormVersionByVersion(idForm, formHeader.getActiveVersion());

		// untuk mendapatkan labelname
		List<FormDetail> listFormDetail = formDetailDao.getFormDetailByIdFormVersion(formVersion.getGuid());

		// isi valuenya
		List listFormTableContent = dataDao.getAllTableContent(idForm);

		// set list form table content dari method generateListFormTableContent
		formTableHeader.setListFormTableContent(
				generateListFormTableContent(encryptedFields, listFormTableContent, listFormDetail));
		return formTableHeader;
	}

	@Override
	public Response deleteData(String aplicationid, String rowid) {
		Response res = new Response();
		// get list id form
		List<String> listForm = new ArrayList<>();
		List<String> listFormDetail = new ArrayList<>();

		getListFormAndFormDetail(aplicationid, listForm, listFormDetail);

		// get list table name
		List<String> listFormTableName = new ArrayList<>();
		List<String> listFormDetailTableName = new ArrayList<>();

		listFormTableName = dataDao.getListTableName(listForm);
		listFormDetailTableName = dataDao.getListTableName(listFormDetail);

		// buat delete berdasarkan id dan tablename
		int countlistFormTableName = dataDao.deleteListFormTableName(listFormTableName, rowid);
		int countlistFormDetailTableName = dataDao.deleListFormDetailTableName(listFormDetailTableName, rowid);

		res.setInserted(countlistFormDetailTableName + countlistFormTableName);
		res.setRow(rowid);
		return res;
	}

	@Override
	public Response addData(String data) {
		return dataDao.addData(data);
	}

	@Override
	public Response updateData(String data) {
		return dataDao.updateData(data);
	}

	private List<FormTableContent> generateListFormTableContent(List<String> encryptedFields, List listFormTableContent,
			List<FormDetail> listFormDetail) {
		// membuat object list "restList2" baru
		List<FormTableContent> restList2 = new ArrayList<FormTableContent>();

		// variable untuk nilai rownya mulai dari nilai 1
		int i = 1;

		// loop sebanyak jumlah data pada table
		for (Object result : listFormTableContent) {

			// buat object baru restList (dari Rest class model yang dibuat)
			// dan formTableContent (dari FormTableContent class model yang dibuat)
			List<KeyValue> restList = new ArrayList<KeyValue>();
			FormTableContent formTableContent = new FormTableContent();

			// membuat object map baru
			Map map = (Map) result;

			// loop buat mendapatkan data pada tiap kolom dari DB
			for (Object key : map.keySet()) {

				for (FormDetail formDetail : listFormDetail) {

					if (key.toString().equals(formDetail.getFieldName())) {

						// buat object baru "rest" dari kelas Rest
						KeyValue rest = new KeyValue();

						// set key dari label name di form detail
						rest.setKey(formDetail.getLabelName());

						// jika value yang didapat dari tiap value tidak null maka akan mengeset,
						// sebaliknya kalo null maka tdk akan mengeset.
						if ((map.get(key) != null)) {

							if (!encryptedFields.contains(key.toString())) {
								rest.setValue(map.get(key).toString());
							}

						}

						// menambahkan list object "rest" pada object list "restlist"
						restList.add(rest);
					}
				}

			}

			formTableContent.setRows(i);
			formTableContent.setListContent(restList);

			// setelah itu object "formTableContent"
			// akan menambahkannya pada list "restList2" untuk di return
			restList2.add(formTableContent);

			// variable "i" ditambahkan setiap looping
			i++;
		}

		// mengembalikan nilai list "restList2"
		return restList2;
	}

	private void getListFormAndFormDetail(String aplicationid, List<String> listForm, List<String> listFormDetail) {
		String filePath = xmlLocationBase + File.separator + companyName + File.separator + modulName + File.separator
				+ aplicationid + ".xml" + File.separator;

		File xmlFile = new File(filePath);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			recursive(doc.getDocumentElement(), listForm, listFormDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void recursive(Element element, List<String> listForm, List<String> listFormDetail) {
		String type = element.getNodeName();
		String id = element.getAttribute(ATTRIBUTE_ID);

		if (type.equals("form")) {
			listForm.add(id);

			// getChildNode(element, listForm, listFormDetail);
		} else if (type.equals("form-detail")) {
			NodeList nodeList = element.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Node currentNode = nodeList.item(i);
					Element elementChild = (Element) currentNode;

					String typeChild = elementChild.getNodeName();
					String idChildx = elementChild.getAttribute(ATTRIBUTE_ID);

					if (typeChild.equals("form")) {
						listFormDetail.add(idChildx);
					}
				}
			}

		}
		getChildNode(element, listForm, listFormDetail);
	}

	private void getChildNode(Element element, List<String> listForm, List<String> listFormDetail) {
		NodeList nodeList = element.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Node currentNode = nodeList.item(i);
				Element elementChild = (Element) currentNode;
				recursive(elementChild, listForm, listFormDetail);
			}
		}

	}
}
