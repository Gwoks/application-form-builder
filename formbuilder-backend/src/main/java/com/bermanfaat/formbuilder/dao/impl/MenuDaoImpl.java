package com.bermanfaat.formbuilder.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bermanfaat.formbuilder.dao.MenuDao;
import com.bermanfaat.formbuilder.mapper.MenuMapper;
import com.bermanfaat.formbuilder.model.Menu;
import com.bermanfaat.formbuilder.util.CustomLogger;

@Transactional
@Repository
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Menu> getAllMenus() {
//		String sql = "SELECT * FROM onboard.mnu ORDER BY head DESC";
		String sql = "SELECT * FROM onboard.mnu ORDER BY order_no ASC";

		List<Menu> menus = jdbcTemplate.query(sql, new MenuMapper());

		CustomLogger.logSQL(sql);

		return menus;
	}

}
