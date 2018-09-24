package com.bermanfaat.formbuilder.model;


import java.util.List;

public class FormDetail {

	private String guid;
	private String formVersionId;
	private String fieldName;
	private String variantType;
	private Integer length;
	private String labelName;
	private String controlType;
	private String defaultValue;
	private Integer orderNo;
	private boolean encrypted;
	private boolean mandatory;
	private boolean readOnly;
	private boolean visible;
	private String inputType;
	private String lkpValue;
	private String value;
	private List<Option> options;
	private String allowedType;
	private Integer allowedSize;
	private String lkpParent;
	private Integer columnLength;
	private Integer rowNo;
	private boolean calculation;
	private String parameter;
	private List<String> lkpChilds;

	public FormDetail(String guid, String formVersionId, String fieldName, String variantType, Integer length,
			String labelName, String controlType, String defaultValue, Integer orderNo, boolean encrypted,
			boolean mandatory, boolean readOnly, boolean visible, String inputType, String lkpValue, String value,
			List<Option> options, String allowedType, Integer allowedSize, String lkpParent, Integer columnLength,
			Integer rowNo, List<String> lkpChilds, boolean calculation, String parameter) {
		super();
		this.guid = guid;
		this.formVersionId = formVersionId;
		this.fieldName = fieldName;
		this.variantType = variantType;
		this.length = length;
		this.labelName = labelName;
		this.controlType = controlType;
		this.defaultValue = defaultValue;
		this.orderNo = orderNo;
		this.encrypted = encrypted;
		this.mandatory = mandatory;
		this.readOnly = readOnly;
		this.visible = visible;
		this.inputType = inputType;
		this.lkpValue = lkpValue;
		this.value = value;
		this.options = options;
		this.allowedType = allowedType;
		this.allowedSize = allowedSize;
		this.lkpParent = lkpParent;
		this.columnLength = columnLength;
		this.rowNo = rowNo;
		this.lkpChilds = lkpChilds;
		this.calculation = calculation;
		this.parameter = parameter;
	}

	public FormDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getFormVersionId() {
		return formVersionId;
	}

	public void setFormVersionId(String formVersionId) {
		this.formVersionId = formVersionId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getVariantType() {
		return variantType;
	}

	public void setVariantType(String variantType) {
		this.variantType = variantType;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getLkpValue() {
		return lkpValue;
	}

	public void setLkpValue(String lkpValue) {
		this.lkpValue = lkpValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public String getAllowedType() {
		return allowedType;
	}

	public void setAllowedType(String allowedType) {
		this.allowedType = allowedType;
	}

	public Integer getAllowedSize() {
		return allowedSize;
	}

	public void setAllowedSize(Integer allowedSize) {
		this.allowedSize = allowedSize;
	}

	public String getLkpParent() {
		return lkpParent;
	}

	public void setLkpParent(String lkpParent) {
		this.lkpParent = lkpParent;
	}

	public Integer getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(Integer columnLength) {
		this.columnLength = columnLength;
	}

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public List<String> getLkpChilds() {
		return lkpChilds;
	}

	public void setLkpChilds(List<String> lkpChilds) {
		this.lkpChilds = lkpChilds;
	}

	public boolean isCalculation() {
		return calculation;
	}

	public void setCalculation(boolean calculation) {
		this.calculation = calculation;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "FormDetail [guid=" + guid + ", formVersionId=" + formVersionId + ", fieldName=" + fieldName
				+ ", variantType=" + variantType + ", length=" + length + ", labelName=" + labelName + ", controlType="
				+ controlType + ", defaultValue=" + defaultValue + ", orderNo=" + orderNo + ", encrypted=" + encrypted
				+ ", mandatory=" + mandatory + ", readOnly=" + readOnly + ", visible=" + visible + ", inputType="
				+ inputType + ", lkpValue=" + lkpValue + ", value=" + value + ", options=" + options + ", allowedType="
				+ allowedType + ", allowedSize=" + allowedSize + ", lkpParent=" + lkpParent + ", columnLength="
				+ columnLength + ", rowNo=" + rowNo + ", calculation=" + calculation + ", parameter=" + parameter
				+ ", lkpChilds=" + lkpChilds + "]";
	}

}
