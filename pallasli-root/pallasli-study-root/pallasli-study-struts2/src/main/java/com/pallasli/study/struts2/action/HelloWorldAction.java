package com.pallasli.study.struts2.action;

//import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author lyt
 * 
 */

public class HelloWorldAction
// extends ActionSupport
{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String testActionMethod() {
		return "toHello";
	}
}
