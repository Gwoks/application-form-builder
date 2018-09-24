package com.bermanfaat.formbuilder.model;

public class LookUp {
	private String key;
	private String value;

	public LookUp(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public LookUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LookUp [key=" + key + ", value=" + value + "]";
	}

}
