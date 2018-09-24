package com.bermanfaat.formbuilder.model;

public class FormVersion {

	private String guid;
	private String formHeaderId;
	private String version;
	private boolean activated;

	public FormVersion(String guid, String formHeaderId, String version, boolean activated) {
		super();
		this.guid = guid;
		this.formHeaderId = formHeaderId;
		this.version = version;
		this.activated = activated;
	}

	public FormVersion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getFormHeaderId() {
		return formHeaderId;
	}

	public void setFormHeaderId(String formHeaderId) {
		this.formHeaderId = formHeaderId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "FormVersion [guid=" + guid + ", formHeaderId=" + formHeaderId + ", version=" + version + ", activated="
				+ activated + "]";
	}

}
