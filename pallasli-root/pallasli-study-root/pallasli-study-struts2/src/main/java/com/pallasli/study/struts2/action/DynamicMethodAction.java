package com.pallasli.study.struts2.action;

import java.util.Date;

//import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author lyt
 * 
 */

public class DynamicMethodAction {
	private String message;
	private Date date;
	private User user;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String testActionMethod() {
		System.out.println(user.getName());
		System.out.println(date);
		return "toHello";
	}

	public String testActionMethod2() {
		return "toHello2";
	}
}
