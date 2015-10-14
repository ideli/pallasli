package com.pallas.sys.sso.action;

public class User {
	private long id;
	private String name;
	private String password;
	private String fingerCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFingerCode() {
		return fingerCode;
	}

	public void setFingerCode(String fingerCode) {
		this.fingerCode = fingerCode;
	}

}
