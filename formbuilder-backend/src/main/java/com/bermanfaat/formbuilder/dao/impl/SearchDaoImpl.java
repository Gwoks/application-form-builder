package com.bermanfaat.formbuilder.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.SearchDao;
import com.bermanfaat.formbuilder.mapper.SearchMapper;
import com.bermanfaat.formbuilder.model.Search;
import com.bermanfaat.formbuilder.util.CustomLogger;
import com.bermanfaat.formbuilder.util.SQLGenerator;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Search> getSearchById(String searchId) {
		String sql = "SELECT * FROM formconfig.srch WHERE search_id= ?";
		List<Search> listSearch = jdbcTemplate.query(sql, new Object[] { searchId }, new SearchMapper());

		CustomLogger.logSQL(sql + searchId);
		return listSearch;
	}

	@Override
	public List getSearchValue(List<String> searchListFieldName) {
		// "SELECT " + selectColumn + " FROM " + tableList.get(0) + " " + innerJoin;
		String sql = SQLGenerator.searchJoinSql(searchListFieldName);

		List results = jdbcTemplate.queryForList(sql);
		CustomLogger.logSQL(sql);

		return results;
	}

	@Override
	public List getSearchJoinSearch(String idSrch, Map<String, String> additionString,
			List<String> searchListFieldName) {
		String sqlJoin = SQLGenerator.searchJoinSql(searchListFieldName);

		String sql = SQLGenerator.searchJoinSqlSearch(sqlJoin, additionString);

		List res = jdbcTemplate.queryForList(sql);
		CustomLogger.logSQL(sql);

		return res;
	}
}
