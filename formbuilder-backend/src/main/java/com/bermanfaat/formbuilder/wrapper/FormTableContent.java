package com.bermanfaat.formbuilder.wrapper;

import java.util.List;

import com.bermanfaat.formbuilder.model.KeyValue;

public class FormTableContent {

	private int rows;
	private List<KeyValue> listContent;

	public FormTableContent(int rows, List<KeyValue> listContent) {
		this.rows = rows;
		this.listContent = listContent;
	}

	public FormTableContent() {
		super();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<KeyValue> getListContent() {
		return listContent;
	}

	public void setListContent(List<KeyValue> listContent) {
		this.listContent = listContent;
	}
}
