package com.bermanfaat.formbuilder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.bermanfaat.formbuilder.model.Menu;

public class MenuMapper implements RowMapper<Menu> {

	@Override
	public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.setId_mnu(rs.getString("id_mnu"));
		menu.setShortname(rs.getString("shortname"));
		menu.setDescription(rs.getString("description"));
		menu.setLinkaction(rs.getString("linkaction"));
		menu.setUserform(rs.getBoolean("useform"));
		menu.setHead(rs.getString("head"));
		menu.setOrderNo(rs.getInt("order_no"));
		return menu;
	}

}
