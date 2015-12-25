package com.lyt.pallas.web.physics.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pallasli.utils.StringUtils;
import com.lyt.pallas.basic.web.action.IAction;

public class BasicFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestPath = request.getRequestURI();
		String actionName = "physics.app.control."
				+ StringUtils.alterFirstCharToUpper(requestPath.substring(
						requestPath.lastIndexOf("/") + 1,
						requestPath.lastIndexOf("."))) + "Action";

		Class<IAction> actionClass = null;
		try {
			actionClass = (Class<IAction>) Class.forName(actionName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			IAction iAction = actionClass.newInstance();
			iAction.doAction(request, response);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
