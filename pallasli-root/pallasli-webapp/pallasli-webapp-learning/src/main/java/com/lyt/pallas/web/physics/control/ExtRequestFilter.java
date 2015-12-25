package com.lyt.pallas.web.physics.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pallasli.utils.StringUtils;

public class ExtRequestFilter implements Filter {

	@Override
	public void destroy() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestPath = request.getRequestURI();
		if (requestPath.indexOf('?') > 0) {
			requestPath = requestPath.substring(0, requestPath.indexOf('?'));
		}
		String actionName = "physics.app.control.tab."
				+ StringUtils.alterFirstCharToUpper(requestPath.substring(
						requestPath.lastIndexOf("/") + 1,
						requestPath.lastIndexOf("."))) + "Action";
		//
		// Enumeration<String> emun = request.getParameterNames();
		// int length=emun.;
		// Object parameter = new Object[]{};
		// while (emun.hasMoreElements()) {
		// String name = emun.nextElement();
		// parameter.put(name, request.getParameter(name));
		// }
		String methodName = request.getParameter("method");
		if (methodName == null || "".equals(methodName)) {
			methodName = "doTabAction";
		}
		Class<AbstractTabAction> actionClass = null;
		try {
			actionClass = (Class<AbstractTabAction>) Class.forName(actionName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			AbstractTabAction iTabAction = actionClass.newInstance();
			Method[] methods = iTabAction.getClass().getDeclaredMethods();
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {

					response.setContentType("application/text; charset=utf-8");
					Object rtn = m.invoke(iTabAction, new Object[] {});
					PrintWriter out = response.getWriter();
					out.print(rtn.toString());
					if (out != null) {
						out.flush();
						out.close();
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
