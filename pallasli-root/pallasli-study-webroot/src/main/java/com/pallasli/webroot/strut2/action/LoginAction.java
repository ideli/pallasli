package com.pallasli.webroot.strut2.action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	private String username;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// business logic
	@Override
	public String execute() {
		return "SUCCESS";
	}

	// simple validation
	@Override
	public void validate() {
		if ("".equals(getUsername())) {
			addFieldError("username", getText("username.required"));
		}
		if ("".equals(getPassword())) {
			addFieldError("password", getText("password.required"));
		}
		if ("yiibai.com".equals(getUsername())) {
			addActionMessage("You are valid user!");
		} else {
			addActionError("I don't know you, dont try to hack me!");
		}
	}
}
