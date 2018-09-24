package com.bermanfaat.formbuilder.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String id_mnu;
	private String shortname;
	private String description;
	private String linkaction;
	private boolean userform;
	private String head;
	private List<Menu> submenus = new ArrayList<Menu>();
	private Integer orderNo;
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(String id_mnu, String shortname, String description, String linkaction, boolean userform, String head, List<Menu> submenus, Integer orderNo) {
		this.id_mnu = id_mnu;
		this.shortname = shortname;
		this.description = description;
		this.linkaction = linkaction;
		this.userform = userform;
		this.head = head;
		this.submenus = submenus;
		this.orderNo = orderNo;
	}

	public String getId_mnu() {
		return id_mnu;
	}

	public void setId_mnu(String id_mnu) {
		this.id_mnu = id_mnu;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLinkaction() {
		return linkaction;
	}

	public void setLinkaction(String linkaction) {
		this.linkaction = linkaction;
	}

	public boolean isUserform() {
		return userform;
	}

	public void setUserform(boolean userform) {
		this.userform = userform;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public List<Menu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menu> submenus) {
		this.submenus = submenus;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}
