package com.pallasli.web.action.annotationaction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Namespace("/User")
@ResultPath(value = "/")
public class ValidateUserAction extends ActionSupport {
	@Override
	@Action(value = "Welcome", results = { @Result(name = "success", location = "pages/welcome_user.jsp") })
	public String execute() {
		return SUCCESS;
	}
}