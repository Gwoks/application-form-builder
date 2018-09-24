package com.bermanfaat.formbuilder.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.DataDao;
import com.bermanfaat.formbuilder.model.KeyValue;
import com.bermanfaat.formbuilder.model.Response;
import com.bermanfaat.formbuilder.util.CommonUtils;
import com.bermanfaat.formbuilder.util.CustomLogger;
import com.bermanfaat.formbuilder.util.KeyValueUtil;
import com.bermanfaat.formbuilder.util.SQLGenerator;
import com.bermanfaat.formbuilder.wrapper.FormDataWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Repository
public class DataDaoImpl implements DataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private KeyValueUtil keyValueUtil;

	@Override
	public Response updateData(String data) {
		Response res = new Response();
		int UpdatedRow = 0;

		// Convert data string to data list
		List<FormDataWrapper> convertedData = CommonUtils.convertJSONtoListRest(data);

		String idForm = null;
		String tableName = null;
		String idRow = null;

		// Loop trough converted data
		for (FormDataWrapper formDataWrapper : convertedData) {
			// Map keyValueMap = formDataWrapper.getData();

			// looping child dari form-detail
			if (formDataWrapper.getType().equals("form-detail")) {

				for (Object detail : formDataWrapper.getData()) {
					ObjectMapper mapper = new ObjectMapper();

					FormDataWrapper formDetailDataWrapper = new FormDataWrapper();
					formDetailDataWrapper = mapper.convertValue(detail, FormDataWrapper.class);

					idForm = formDetailDataWrapper.getIdform();
					tableName = getTableName(idForm);
					idRow = formDetailDataWrapper.getIdrow();

					if (!tableName.equals("no-table")) {
						List<String> typeFileUploads = getControlTypeFileUpload(idForm);
						List<String> encryptedFields = getEncryptedFields(idForm);

						// loop setiap data
						for (Object listKeyValue : formDetailDataWrapper.getData()) {
							Map map = (Map) listKeyValue;
							Map keyValueMap = new HashMap<>();

							// loop setiap key-value
							for (Object keyValueRow : map.keySet()) {
								keyValueMap.put(keyValueRow, map.get(keyValueRow));
							}

							String sql = SQLGenerator.update(keyValueMap, tableName, idRow, encryptedFields,
									typeFileUploads, idForm, "id");

							int row = jdbcTemplate.update(sql);
							UpdatedRow += row;
							CustomLogger.logSQL(sql);

						}
					}

				}
			} else {
				Map keyValueMap = new HashMap<>();

				for (Object listKeyValue : formDataWrapper.getData()) {
					Map map = (Map) listKeyValue;
					for (Object keyValueRow : map.keySet()) {
						keyValueMap.put(keyValueRow, map.get(keyValueRow));
					}
				}

				idForm = formDataWrapper.getIdform();
				tableName = getTableName(idForm);
				idRow = formDataWrapper.getIdrow();

				if (!tableName.equals("no-table")) {
					List<String> typeFileUploads = getControlTypeFileUpload(idForm);
					List<String> encryptedFields = getEncryptedFields(idForm);

					// Generate SQL
					String sql = SQLGenerator.update(keyValueMap, tableName, idRow, encryptedFields, typeFileUploads,
							idForm, "id");

					// Update action sql
					int row = jdbcTemplate.update(sql);
					UpdatedRow += row;
					CustomLogger.logSQL(sql);
				}

			}

		}
		res.setInserted(UpdatedRow);
		res.setRow(idRow);
		return res;
	}

	@Override
	public Response addData(String data) {
		Response res = new Response();

		int insertedRow = 0;

		// Convert data string to data list
		List<FormDataWrapper> convertedData = CommonUtils.convertJSONtoListRest(data);

		// Generate ID using timemilis
		String idGenerate = String.valueOf(System.currentTimeMillis());

		// Loop trough converted data
		for (FormDataWrapper formDataWrapper : convertedData) {

			// cek kalo type nya form-detail
			if (formDataWrapper.getType().equals("form-detail")) {

				String idRow = String.valueOf(System.currentTimeMillis());

				// looping setiap form dari "data" dari form-detail
				for (Object detail : formDataWrapper.getData()) {
					ObjectMapper mapper = new ObjectMapper();

					FormDataWrapper formDetailDataWrapper = new FormDataWrapper();

					formDetailDataWrapper = mapper.convertValue(detail, FormDataWrapper.class);

					String idForm = formDetailDataWrapper.getIdform();
					String tableName = getTableName(idForm);

					if (!tableName.equals("no-table")) {

						List<String> encryptedFields = getEncryptedFields(idForm);
						List<String> controlTypeFileUpload = getControlTypeFileUpload(idForm);

						// loop setiap data
						for (Object listKeyValue : formDetailDataWrapper.getData()) {
							Map map = (Map) listKeyValue;
							Map keyValueMap = new HashMap<>();

							// llop setiap "key-value"
							for (Object keyValueRow : map.keySet()) {
								keyValueMap.put(keyValueRow, map.get(keyValueRow));
							}

							keyValueMap.put("id_parent", idGenerate);
							keyValueMap.put("id", idRow);

							// Generate SQL
							String sql = SQLGenerator.insert(keyValueMap, tableName, encryptedFields,
									controlTypeFileUpload, idForm);

							// Insert action sql
							int row = jdbcTemplate.update(sql);
							insertedRow += row;
							CustomLogger.logSQL(sql);

						}
					}

				}

				// cek kalo type nya selain form-detail
			} else {
				// Map keyValueMap = formDataWrapper.getData();
				Map keyValueMap = new HashMap<>();

				for (Object listKeyValue : formDataWrapper.getData()) {
					Map map = (Map) listKeyValue;
					for (Object keyValueRow : map.keySet()) {
						keyValueMap.put(keyValueRow, map.get(keyValueRow));
					}
				}

				String idForm = formDataWrapper.getIdform();
				String tableName = getTableName(idForm);

				if (!tableName.equals("no-table")) {
					List<String> encryptedFields = getEncryptedFields(idForm);
					List<String> controlTypeFileUpload = getControlTypeFileUpload(idForm);

					// formDataWrapper.getData().put("id", idGenerate);
					keyValueMap.put("id", idGenerate);
					// Generate SQL
					String sql = SQLGenerator.insert(keyValueMap, tableName, encryptedFields, controlTypeFileUpload,
							idForm);

					// Insert action sql
					int row = jdbcTemplate.update(sql);
					insertedRow += row;
					CustomLogger.logSQL(sql);
				}
			}

		}

		res.setInserted(insertedRow);
		res.setRow(idGenerate);
		return res;
	}

	@Override
	public List<String> getEncryptedFields(String idForm) {

		String sql = "SELECT formconfig.frmd.field_name FROM formconfig.frmd "
				+ "INNER JOIN formconfig.frmv ON (formconfig.frmd.frmv_id = formconfig.frmv.id) "
				+ "INNER JOIN formconfig.frmh ON (formconfig.frmv.frmh_id = formconfig.frmh.id) "
				+ "WHERE formconfig.frmh.id = ? AND formconfig.frmd.encrypted = 'true' ";

		List<String> listFormDetail = jdbcTemplate.queryForList(sql, new Object[] { idForm }, String.class);
		CustomLogger.logSQL(sql + idForm);
		return listFormDetail;
	}

	@Override
	public List<KeyValue> getValueFormDetail(String idForm, String idData) {
		List<KeyValue> restList = new ArrayList<>();

		String tableName = getTableName(idForm);

		if (!tableName.equals("no-table")) {
			String sql = "SELECT * FROM " + getTableName(idForm) + " WHERE id = ?";

			List results = jdbcTemplate.queryForList(sql, new Object[] { idData });

			// looping setiap hasil(result) ke model Rest, column sebagai key dan isinya
			// sebagai value
			restList = keyValueUtil.generateKeyValue(results);
		}

		return restList;
	}

	@Override
	public List<List<KeyValue>> getValueFormDetailByIdParent(String idForm, String idData) {
		List<List<KeyValue>> result = new ArrayList<>();

		String tableName = getTableName(idForm);

		if (!tableName.equals("no-table")) {
			String sql = "SELECT * FROM " + getTableName(idForm) + " WHERE id_parent = ?";
			// String sql = "SELECT * FROM master.insurance where id_parent =
			// '1531452947066'";

			List results = jdbcTemplate.queryForList(sql, new Object[] { idData });
			// List results = jdbcTemplate.queryForList(sql);

			for (Object keyValue : results) {
				Map map = (Map) keyValue;
				List<KeyValue> keyvalues = new ArrayList<>();
				for (Object key : map.keySet()) {
					KeyValue rest = new KeyValue();

					rest.setKey(key.toString());

					if ((map.get(key) != null)) {
						rest.setValue(map.get(key).toString());
					}

					keyvalues.add(rest);
				}
				result.add(keyvalues);
			}
			// restList = keyValueUtil.generateNestedListKeyValue(results);
		}

		return result;
	}

	@Override
	public List getAllTableContent(String idForm) {
		// query select buat nampilin all data dari tabel yang didapat dari method
		// getTableName(...)
		String sql = "SELECT * FROM " + getTableName(idForm);

		// execute query pada variabel sql diatas
		List results = jdbcTemplate.queryForList(sql);
		CustomLogger.logSQL(sql + idForm);
		return results;
	}

	@Override
	public List<String> getControlTypeFileUpload(String idForm) {
		String sql = "SELECT formconfig.frmd.field_name FROM formconfig.frmd "
				+ "INNER JOIN formconfig.frmv ON (formconfig.frmd.frmv_id = formconfig.frmv.id) "
				+ "INNER JOIN  formconfig.frmh ON (formconfig.frmv.frmh_id = formconfig.frmh.id) "
				+ "WHERE formconfig.frmh.id = ? AND formconfig.frmd.control_type = 'fileupload'";

		List<String> listControlTypeFileUpload = jdbcTemplate.queryForList(sql, new Object[] { idForm }, String.class);

		CustomLogger.logSQL(sql + idForm);

		return listControlTypeFileUpload;
	}

	private String getTableName(String idForm) {
		String sql = "SELECT table_name FROM formconfig.frmh WHERE id = ?";
		CustomLogger.logSQL(sql + idForm);
		return jdbcTemplate.queryForObject(sql, new Object[] { idForm }, String.class);
	}

	@Override
	public List<String> getListIdByIdForm(String idForm, String idParent) {
		List<String> listIdByIdForm = new ArrayList<String>();
		String tableName = getTableName(idForm);

		if (!tableName.equals("no-table")) {
			String sql = "SELECT id FROM " + tableName + " WHERE id_parent = ?";
			listIdByIdForm = jdbcTemplate.queryForList(sql, new Object[] { idParent }, String.class);
			CustomLogger.logSQL(sql + idForm);
		}
		return listIdByIdForm;
	}

	@Override
	public List<String> getListTableName(List<String> listFormDetail) {
		String sql = "SELECT table_name FROM formconfig.frmh where ";

		String listId = "";
		for (int i = 0; i < listFormDetail.size(); i++) {
			if (i == listFormDetail.size() - 1) {
				listId = listId + "id = '" + listFormDetail.get(i) + "';";
			} else {
				listId = listId + "id = '" + listFormDetail.get(i) + "' or ";
			}
		}

		sql = sql + listId;
		List<String> res = jdbcTemplate.queryForList(sql, new Object[] {}, String.class);
		CustomLogger.logSQL(sql);
		return res;
	}

	@Override
	public int deleteListFormTableName(List<String> listFormTableName, String rowid) {
		String sql = "";
		for (int i = 0; i < listFormTableName.size(); i++) {
			if (!listFormTableName.get(i).equals("no-table")) {
				sql = sql + "DELETE FROM " + listFormTableName.get(i) + " WHERE id = '" + rowid + "'; ";
			}
		}

		int res = jdbcTemplate.update(sql);
		CustomLogger.logSQL(sql);
		return res;
	}

	@Override
	public int deleListFormDetailTableName(List<String> listFormDetailTableName, String rowid) {
		String sql = "";
		for (int i = 0; i < listFormDetailTableName.size(); i++) {
			if (!listFormDetailTableName.get(i).equals("no-table")) {
				sql = sql + "DELETE FROM " + listFormDetailTableName.get(i) + " WHERE id_parent = '" + rowid + "'; ";
			}
		}

		int res = jdbcTemplate.update(sql);
		CustomLogger.logSQL(sql);
		return res;
	}

}
