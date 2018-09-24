package com.bermanfaat.formbuilder.model;

public class Inquiry {
	private String id;
	private String inquiryId;
	private String label;
	private String sourceField;
	private String type;
	private Integer orderNo;

	public Inquiry(String id, String inquiryId, String label, String sourceField, String type, Integer orderNo) {
		super();
		this.id = id;
		this.inquiryId = inquiryId;
		this.label = label;
		this.sourceField = sourceField;
		this.type = type;
		this.orderNo = orderNo;
	}

	public Inquiry() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(String inquiryId) {
		this.inquiryId = inquiryId;
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
		return "Inquiry [id=" + id + ", inquiryId=" + inquiryId + ", label=" + label + ", sourceField=" + sourceField
				+ ", type=" + type + ", orderNo=" + orderNo + ", getId()=" + getId() + ", getInquiryId()="
				+ getInquiryId() + ", getLabel()=" + getLabel() + ", getSourceField()=" + getSourceField()
				+ ", getType()=" + getType() + ", getOrderNo()=" + getOrderNo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
