package com.bermanfaat.formbuilder.model;

public class Execution {

	private String execId;
	private String execAPIsId;
	private String apisId;
	private String paramTokens;
	private String bodyTokens;
	private Integer execOrder;
	private String sourceType;
	private Integer sourceOrder;

	public Execution(String execId, String execAPIsId, String apisId, String paramTokens, String bodyTokens,
			Integer execOrder, String sourceType, Integer sourceOrder) {
		super();
		this.execId = execId;
		this.execAPIsId = execAPIsId;
		this.apisId = apisId;
		this.paramTokens = paramTokens;
		this.bodyTokens = bodyTokens;
		this.execOrder = execOrder;
		this.sourceType = sourceType;
		this.sourceOrder = sourceOrder;
	}

	public Execution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getExecId() {
		return execId;
	}

	public void setExecId(String execId) {
		this.execId = execId;
	}

	public String getExecAPIsId() {
		return execAPIsId;
	}

	public void setExecAPIsId(String execAPIsId) {
		this.execAPIsId = execAPIsId;
	}

	public String getApisId() {
		return apisId;
	}

	public void setApisId(String apisId) {
		this.apisId = apisId;
	}

	public String getParamTokens() {
		return paramTokens;
	}

	public void setParamTokens(String paramTokens) {
		this.paramTokens = paramTokens;
	}

	public String getBodyTokens() {
		return bodyTokens;
	}

	public void setBodyTokens(String bodyTokens) {
		this.bodyTokens = bodyTokens;
	}

	public Integer getExecOrder() {
		return execOrder;
	}

	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getSourceOrder() {
		return sourceOrder;
	}

	public void setSourceOrder(Integer sourceOrder) {
		this.sourceOrder = sourceOrder;
	}

	@Override
	public String toString() {
		return "Execution [execId=" + execId + ", execAPIsId=" + execAPIsId + ", apisId=" + apisId + ", paramTokens="
				+ paramTokens + ", bodyTokens=" + bodyTokens + ", execOrder=" + execOrder + ", sourceType=" + sourceType
				+ ", sourceOrder=" + sourceOrder + "]";
	}

}
