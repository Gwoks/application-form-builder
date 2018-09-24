package com.bermanfaat.formbuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermanfaat.formbuilder.model.Menu;
import com.bermanfaat.formbuilder.service.MenuService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/")
	public List<Menu> getAllMenu() {
		return menuService.getAllMenus();
	}

}
