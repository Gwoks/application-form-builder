package com.bermanfaat.formbuilder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyValue {

	@JsonProperty
	private String key;

	@JsonProperty
	private String value;

	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public KeyValue() {
		super();
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
