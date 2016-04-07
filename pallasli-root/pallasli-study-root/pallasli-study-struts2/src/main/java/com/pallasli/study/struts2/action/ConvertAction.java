package com.pallasli.study.struts2.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author lyt
 * 
 */

// 增加校验需要继承ActionSupport
public class ConvertAction extends ActionSupport {
	private String message;
	private Date birthday;
	private User user;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
		System.out.println(birthday);
		return "toHello";
	}

	public void validateTestActionMethod() {
		System.out.println("某一个方法校验");
		this.addFieldError("fieldName", "errorMessage");
		// 出错时跳转到input
	}

	@Override
	public void validate() {
		System.out.println("全局校验");
		this.addFieldError("fieldName", "errorMessage");
		// 出错时跳转到input
	}

	public String testActionMethod2() {
		return "toHello2";
	}
}
