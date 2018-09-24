package com.bermanfaat.formbuilder.model;

import java.util.List;

public class Input {
	private String key;
	private String label;
	private String value;
	private boolean required;
	private Integer order;
	private String type;
	private String controlType;
	private List<Option> options;

	public Input(String key, String label, String value, boolean required, Integer order, String type,
			String controlType, List<Option> options) {
		super();
		this.key = key;
		this.label = label;
		this.value = value;
		this.required = required;
		this.order = order;
		this.type = type;
		this.controlType = controlType;
		this.options = options;
	}

	public Input() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Input [key=" + key + ", label=" + label + ", value=" + value + ", required=" + required + ", order="
				+ order + ", type=" + type + ", controlType=" + controlType + ", options=" + options + "]";
	}

}
