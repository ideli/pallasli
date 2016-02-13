package com.pallasli.webroot.strut2.action.annotationaction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Namespace("/User")
@ResultPath(value = "/")
@Result(name = "success", location = "/login.jsp")
public class HomeAction extends ActionSupport {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	@Action(value = "Welcome", results = { @Result(name = "success", location = "welcome_user.jsp") })
	public String execute() {

		return SUCCESS;

	}
}