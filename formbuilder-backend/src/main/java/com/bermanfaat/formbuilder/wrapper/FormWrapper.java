package com.bermanfaat.formbuilder.wrapper;

import java.util.List;

import com.bermanfaat.formbuilder.model.FormDetail;
import com.bermanfaat.formbuilder.model.FormHeader;
import com.bermanfaat.formbuilder.model.FormVersion;

public class FormWrapper {
	private FormHeader formHeader;
	private FormVersion formVersion;
	private List<FormDetail> listFormDetail;

	public FormWrapper(FormHeader formHeader, FormVersion formVersion, List<FormDetail> listFormDetail) {
		super();
		this.formHeader = formHeader;
		this.formVersion = formVersion;
		this.listFormDetail = listFormDetail;
	}

	public FormWrapper() {
		super();
	}

	public FormHeader getFormHeader() {
		return formHeader;
	}

	public void setFormHeader(FormHeader formHeader) {
		this.formHeader = formHeader;
	}

	public FormVersion getFormVersion() {
		return formVersion;
	}

	public void setFormVersion(FormVersion formVersion) {
		this.formVersion = formVersion;
	}

	public List<FormDetail> getListFormDetail() {
		return listFormDetail;
	}

	public void setListFormDetail(List<FormDetail> listFormDetail) {
		this.listFormDetail = listFormDetail;
	}

	@Override
	public String toString() {
		return "" + formHeader + "," + formVersion + "," + listFormDetail + "";
	}

}
