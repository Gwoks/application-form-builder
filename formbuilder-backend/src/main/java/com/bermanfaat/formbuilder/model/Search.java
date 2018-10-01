package com.bermanfaat.formbuilder.model;

public class Search {
	private String id;
	private String searchId;
	private String label;
	private String sourceField;
	private String type;
	private Integer orderNo;

	public Search(String id, String searchId, String label, String sourceField, String type, Integer orderNo) {
		super();
		this.id = id;
		this.searchId = searchId;
		this.label = label;
		this.sourceField = sourceField;
		this.type = type;
		this.orderNo = orderNo;
	}

	public Search() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "Search [id=" + id + ", searchId=" + searchId + ", label=" + label + ", sourceField=" + sourceField
				+ ", type=" + type + ", orderNo=" + orderNo + ", getId()=" + getId() + ", getSearchId()="
				+ getSearchId() + ", getLabel()=" + getLabel() + ", getSourceField()=" + getSourceField()
				+ ", getType()=" + getType() + ", getOrderNo()=" + getOrderNo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
