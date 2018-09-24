package com.bermanfaat.formbuilder.model;

public class FormHeader {
	private String guid;
	private String tableName;
	private String labelName;
	private String description;
	private String activeVersion;
	private String type;

	public FormHeader(String guid, String tableName, String labelName, String description, String activeVersion,
			String type) {
		super();
		this.guid = guid;
		this.tableName = tableName;
		this.labelName = labelName;
		this.description = description;
		this.activeVersion = activeVersion;
		this.type = type;
	}

	public FormHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActiveVersion() {
		return activeVersion;
	}

	public void setActiveVersion(String activeVersion) {
		this.activeVersion = activeVersion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FormHeader [guid=" + guid + ", tableName=" + tableName + ", labelName=" + labelName + ", description="
				+ description + ", activeVersion=" + activeVersion + ", type=" + type + "]";
	}

}
