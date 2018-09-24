package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.FormDetail;

public class FormDetailMapper implements RowMapper<FormDetail> {

	@Override
	public FormDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		FormDetail formDetail = new FormDetail();

		formDetail.setGuid(rs.getString("id"));
		formDetail.setFormVersionId(rs.getString("frmv_id"));
		formDetail.setFieldName(rs.getString("field_name"));
		formDetail.setVariantType(rs.getString("variant_type"));
		formDetail.setLength(rs.getInt("length"));
		formDetail.setLabelName(rs.getString("label_name"));
		formDetail.setControlType(rs.getString("control_type"));
		formDetail.setDefaultValue(rs.getString("default_value"));
		formDetail.setOrderNo(rs.getInt("order_no"));
		formDetail.setEncrypted(rs.getBoolean("encrypted"));
		formDetail.setMandatory(rs.getBoolean("mandatory"));
		formDetail.setReadOnly(rs.getBoolean("read_only"));
		formDetail.setVisible(rs.getBoolean("visible"));
		formDetail.setInputType(rs.getString("input_type"));
		formDetail.setLkpValue(rs.getString("lkp_value"));
		formDetail.setAllowedType(rs.getString("allowed_type"));
		formDetail.setAllowedSize(rs.getInt("allowed_size"));
		formDetail.setLkpParent(rs.getString("lkp_parent"));
		formDetail.setColumnLength(rs.getInt("column_length"));
		formDetail.setRowNo(rs.getInt("row_no"));
		formDetail.setParameter(rs.getString("parameter"));
		formDetail.setCalculation(rs.getBoolean("calculation"));

		String stringChilds = rs.getString("lkp_childs");
		List<String> lkpChilds = new ArrayList<String>();
		if (stringChilds != null) {
			lkpChilds = new ArrayList<String>(Arrays.asList(stringChilds.split("\\s*;\\s*")));
		}

		formDetail.setLkpChilds(lkpChilds);

		return formDetail;
	}

}
