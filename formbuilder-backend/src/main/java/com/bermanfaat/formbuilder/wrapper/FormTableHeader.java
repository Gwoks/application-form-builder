package com.bermanfaat.formbuilder.wrapper;

import java.util.List;

import com.bermanfaat.formbuilder.model.FormHeader;

public class FormTableHeader {

	private FormHeader formHeader;
	private List<FormTableContent> listFormTableContent;

	public FormTableHeader(FormHeader formHeader, List<FormTableContent> listFormTableContent) {
		super();
		this.formHeader = formHeader;
		this.listFormTableContent = listFormTableContent;
	}

	public FormTableHeader() {
		super();
	}

	public FormHeader getFormHeader() {
		return formHeader;
	}

	public void setFormHeader(FormHeader formHeader) {
		this.formHeader = formHeader;
	}

	public List<FormTableContent> getListFormTableContent() {
		return listFormTableContent;
	}

	public void setListFormTableContent(List<FormTableContent> listFormTableContent) {
		this.listFormTableContent = listFormTableContent;
	}

}
