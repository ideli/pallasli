package com.lyt.pallas.basic.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

	public void doAction(HttpServletRequest request,
			HttpServletResponse response);
}
