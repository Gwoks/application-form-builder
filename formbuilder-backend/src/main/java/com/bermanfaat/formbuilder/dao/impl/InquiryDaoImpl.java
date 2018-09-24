package com.bermanfaat.formbuilder.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.InquiryDao;
import com.bermanfaat.formbuilder.mapper.InquiryMapper;
import com.bermanfaat.formbuilder.model.Inquiry;
import com.bermanfaat.formbuilder.util.CustomLogger;
import com.bermanfaat.formbuilder.util.SQLGenerator;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class InquiryDaoImpl implements InquiryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Inquiry> getInquiryById(String inquiryId) {
		String sql = "SELECT * FROM formconfig.inqr WHERE inquiry_id= ?";
		List<Inquiry> listInquiry = jdbcTemplate.query(sql, new Object[] { inquiryId }, new InquiryMapper());

		CustomLogger.logSQL(sql + inquiryId);
		return listInquiry;
	}

	@Override
	public List getInquiryValue(List<String> inquiryListFieldName) {
		// "SELECT " + selectColumn + " FROM " + tableList.get(0) + " " + innerJoin;
		String sql = SQLGenerator.inquiryJoinSql(inquiryListFieldName);

		List results = jdbcTemplate.queryForList(sql);
		CustomLogger.logSQL(sql);

		return results;
	}

	@Override
	public List getInquiryJoinSearch(String idInqr, Map<String, String> additionString,
			List<String> inquiryListFieldName) {
		String sqlJoin = SQLGenerator.inquiryJoinSql(inquiryListFieldName);

		String sql = SQLGenerator.inquiryJoinSqlSearch(sqlJoin, additionString);

		List res = jdbcTemplate.queryForList(sql);
		CustomLogger.logSQL(sql);

		return res;
	}
}
