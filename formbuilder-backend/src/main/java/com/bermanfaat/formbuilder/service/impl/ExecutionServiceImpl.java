package com.bermanfaat.formbuilder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bermanfaat.formbuilder.dao.APIsDao;
import com.bermanfaat.formbuilder.dao.ExecutionDao;
import com.bermanfaat.formbuilder.model.APIs;
import com.bermanfaat.formbuilder.model.Execution;
import com.bermanfaat.formbuilder.service.ExecutionService;
import com.bermanfaat.formbuilder.util.RequestBuilder;
import com.bermanfaat.formbuilder.util.RestUtils;

@Service
public class ExecutionServiceImpl implements ExecutionService {

	@Autowired
	private APIsDao apisDao;

	@Autowired
	private ExecutionDao executionDao;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public String execute(String execAPIsId, String data) {

		System.out.println("id execution service = " + execAPIsId);
		List<Execution> listExecution = executionDao.getListExecutionByExecAPIsId(execAPIsId);

		System.out.println("total api = " + listExecution.size());

		// Holds response from previous calls
		RestUtils restUtils = new RestUtils();
		List<String> responseList = new ArrayList<String>();
		responseList.add(data);

		for (Execution execution : listExecution) {
			System.out.println("id API = " + execution.getApisId());

			APIs apis = apisDao.getAPIsByAPIsId(execution.getApisId());

			String url = apis.getUrl();
			String paramTokens = execution.getParamTokens();
			String bodyTokens = execution.getBodyTokens();
			String method = apis.getMethod();
			String sourceType = execution.getSourceType();

			int executionOrder = execution.getExecOrder();
			int sourceOrder = execution.getSourceOrder();

			System.out.println("url = " + url);
			System.out.println("param tokens = " + paramTokens);
			System.out.println("body tokens = " + bodyTokens);
			System.out.println("data = " + responseList.get(sourceOrder));
			System.out.println("method = " + method);
			System.out.println("source type = " + sourceType);

			System.out.println("execution order = " + executionOrder);

			try {
				String bodyValue = RequestBuilder.replaceTokens(bodyTokens, responseList.get(sourceOrder), sourceType);
				String paramValue = RequestBuilder.replaceTokens(paramTokens, responseList.get(sourceOrder),
						sourceType);

				switch (method) {
				case "POST":
					data = restUtils.send2(url, paramValue, bodyValue);
					break;

				case "PUT":
					restUtils.sendPut(url, paramValue, bodyValue);
					break;

				case "DELETE":
					restUtils.sendDelete(url, paramValue, bodyValue);
					break;

				case "GET":
					break;

				default:
					break;
				}

				responseList.add(data);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		int i = 1;
		for (String test : responseList) {
			System.out.println("INDEX = " + i);
			System.out.println("VALUE = " + test);
			i++;
		}

		return data;
	}

	// @Override
	// public Execution getExecutionByExecId(String execId) {
	// return executionDao.getListExecutionByexecServId(execServId)
	// }

}
