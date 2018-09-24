package com.bermanfaat.formbuilder.wrapper;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FormDataWrapper {
	@JsonProperty
	private String idform;

	@JsonProperty
	private String idrow;

	@JsonProperty
	private String type;

	@JsonProperty
	private List data;

	public FormDataWrapper(String idform, String idrow, String type, List data) {
		super();
		this.idform = idform;
		this.idrow = idrow;
		this.type = type;
		this.data = data;
	}

	public FormDataWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdform() {
		return idform;
	}

	public void setIdform(String idform) {
		this.idform = idform;
	}

	public String getIdrow() {
		return idrow;
	}

	public void setIdrow(String idrow) {
		this.idrow = idrow;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FormDataWrapper [idform=" + idform + ", idrow=" + idrow + ", type=" + type + ", data=" + data + "]";
	}

}
