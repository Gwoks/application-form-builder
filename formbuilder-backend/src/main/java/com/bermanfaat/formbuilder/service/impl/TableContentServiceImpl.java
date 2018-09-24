package com.bermanfaat.formbuilder.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.TableContentDao;
import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.service.TableContentService;
import com.bermanfaat.formbuilder.util.CustomLogger;
import com.bermanfaat.formbuilder.wrapper.FormTableContent;

@Service
public class TableContentServiceImpl implements TableContentService {
	@Autowired
	private TableContentDao tableContentDao;

	@Override
	public List<FormTableContent> getShcemaTableContent(String schemaTableName) {
		List<FormTableContent> formTableContents = new ArrayList<FormTableContent>();
		List content = tableContentDao.getAllContent(schemaTableName);

		int i = 1;
		for (Object columns : content) {
			Map map = (Map) columns;
			FormTableContent formTableContent = new FormTableContent();

			List<KeyValue> temp = new ArrayList<KeyValue>();

			for (Object key : map.keySet()) {
				KeyValue rest = new KeyValue();

				rest.setKey(key.toString());

				if ((map.get(key) != null)) {
					rest.setValue(map.get(key).toString());
				}
				temp.add(rest);
			}

			formTableContent.setRows(i);
			formTableContent.setListContent(temp);
			formTableContents.add(formTableContent);
			i++;
		}

		return formTableContents;
	}

	@Override
	public List<FormTableContent> getLovByQuery(String idLov, List<KeyValue> params) {
		System.out.println("idLov " + idLov);
		List<FormTableContent> formTableContents = new ArrayList<FormTableContent>();

		String queryString = tableContentDao.getlovcQueryByName(idLov);

		Pattern pattern = Pattern.compile("\\<(.+?)\\>");
		Matcher matcher = pattern.matcher(queryString);
		StringBuffer buffer = new StringBuffer();

		System.out.println("idLov & params " + idLov + String.valueOf(params));
		while (matcher.find()) {
			String token = matcher.group(1);

			for (KeyValue param : params) {
				if (param.getKey().equals(token)) {
					matcher.appendReplacement(buffer, "");
					String valueParam = "'" + param.getValue() + "'";
					buffer.append(valueParam);
				}
			}

		}
		matcher.appendTail(buffer);
		String queryAfter = buffer.toString();

		CustomLogger.logSQL(queryAfter);

		List lovByQueryResult = tableContentDao.getLovByQuery(queryAfter);

		int i = 1;
		for (Object columns : lovByQueryResult) {
			Map map = (Map) columns;
			FormTableContent formTableContent = new FormTableContent();

			List<KeyValue> temp = new ArrayList<KeyValue>();

			for (Object key : map.keySet()) {
				KeyValue rest = new KeyValue();

				rest.setKey(key.toString());
				System.out.println(key + " " + map.get(key));
				if ((map.get(key) != null)) {
					rest.setValue(map.get(key).toString());
				}
				temp.add(rest);
			}

			formTableContent.setRows(i);
			formTableContent.setListContent(temp);
			formTableContents.add(formTableContent);
			i++;
		}

		return formTableContents;
	}

}