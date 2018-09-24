package com.bermanfaat.formbuilder.model;

public class APIs {
	private String apisId;
	private String url;
	private String method;
	private Boolean authentication;
	private String username;
	private String password;

	public APIs(String apisId, String url, String method, Boolean authentication, String username, String password) {
		super();
		this.apisId = apisId;
		this.url = url;
		this.method = method;
		this.authentication = authentication;
		this.username = username;
		this.password = password;
	}

	public APIs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getApisId() {
		return apisId;
	}

	public void setApisId(String apisId) {
		this.apisId = apisId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Boolean getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Boolean authentication) {
		this.authentication = authentication;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "APIs [apisId=" + apisId + ", url=" + url + ", method=" + method + ", authentication=" + authentication
				+ ", username=" + username + ", password=" + password + "]";
	}

}
