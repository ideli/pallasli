package com.pallasli.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

public class LangAction extends ActionSupport {
	private String welcome;

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String run() {
		String params[] = { "张三" };

		welcome = getText("login.title", params);
		return "success";
	}
}
