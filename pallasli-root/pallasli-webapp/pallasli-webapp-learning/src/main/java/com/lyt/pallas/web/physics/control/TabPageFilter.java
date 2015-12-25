package com.lyt.pallas.web.physics.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pallasli.utils.StringUtils;

public class TabPageFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	@SuppressWarnings("unchecked")
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

		Enumeration<String> emun = request.getParameterNames();
		Map<String, String> parameter = new HashMap<String, String>();
		while (emun.hasMoreElements()) {
			String name = emun.nextElement();
			parameter.put(name, request.getParameter(name));
		}
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

			response.setContentType("application/text; charset=utf-8");
			Method method = iTabAction.getClass().getDeclaredMethod(
					methodName,
					new Class<?>[] { Map.class, HttpServletRequest.class,
							HttpServletResponse.class });
			if (method.getGenericReturnType().equals(String.class)) {
				String data = (String) method.invoke(iTabAction, new Object[] {
						parameter, request, response });
				PrintWriter out = response.getWriter();
				if (data != null) {
					out.print(data.toString());
				}
				if (out != null) {
					out.flush();
					out.close();
				}
			} else {
				method.invoke(iTabAction, new Object[] { parameter, request,
						response });
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
