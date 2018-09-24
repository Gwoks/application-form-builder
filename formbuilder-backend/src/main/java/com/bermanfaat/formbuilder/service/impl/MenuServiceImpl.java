package com.bermanfaat.formbuilder.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bermanfaat.formbuilder.dao.MenuDao;
import com.bermanfaat.formbuilder.model.Menu;
import com.bermanfaat.formbuilder.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> getAllMenus() {
//		 List<Menu> menus = menuDao.getAllMenus();
		List<Menu> result = startLooping(menuDao.getAllMenus());

//		result.sort((o1, o2) -> o1.getId_mnu().compareTo(o2.getId_mnu()));
		result.sort((o1, o2) -> o1.getOrderNo().compareTo(o2.getOrderNo()));
		return result;
	}

	private List<Menu> startLooping(List<Menu> listMenu) {

		List<Menu> resultList = new ArrayList<Menu>();

		for (Menu menu : listMenu) {

			// kalo headnya null, berati dia si "root"
			if (menu.getHead() == null) {

				// create new menu
				Menu currentHead = new Menu();

				// insert value
				currentHead = menu;

				// mulai rekursif
				currentHead.setSubmenus(startRecursive(listMenu, currentHead));

				resultList.add(currentHead);

			}

		}
		return resultList;
	}

	private List<Menu> startRecursive(List<Menu> listMenu, Menu currentHead) {

		List<Menu> addMenu = new ArrayList<Menu>();

		for (int i = 0; i < listMenu.size(); i++) {

			// untuk menghindari error null
			if (listMenu.get(i).getHead() == null) {
//				System.out.println(listMenu.get(i).getHead());
			}
			// kalo currentMenu setara dengan currentHead
			// berati kita harus menambahkan nya ke currentHead submenu
			else if (listMenu.get(i).getHead().equals(currentHead.getId_mnu())) {

				// init menu
				Menu currentMenu = new Menu();

				// insert value to currentMenu
				currentMenu = listMenu.get(i);

				// cari si child
				currentMenu.setSubmenus(startRecursive(listMenu, currentMenu));

				// then add it to
				addMenu.add(currentMenu);

				currentHead.setSubmenus(addMenu);
			}

		}

		return addMenu;

	}

}
