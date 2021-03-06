package com.bermanfaat.formbuilder.dto;

public class SearchDTO {
	private String label;
	private String type;
	private String value;

	public SearchDTO(String label, String type, String value) {
		this.label = label;
		this.type = type;
		this.value = value;
	}

	public SearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SearchDTO [label=" + label + ", type=" + type + ", value=" + value + ", getLabel()=" + getLabel()
				+ ", getType()=" + getType() + ", getValue()=" + getValue() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}