package com.lyt.pallas.basic.web.request.filter;

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
import com.lyt.pallas.web.physics.control.AbstractTabAction;


public class RequestDataFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestPath = request.getRequestURI();
		if (requestPath.indexOf('?') > 0) {
			requestPath = requestPath.substring(0, requestPath.indexOf('?'));
		}
		StringBuffer pack = new StringBuffer(requestPath);
		if (pack.length() > 0) {
			pack.deleteCharAt(0);
			int index = pack.indexOf("/");
			if (index > 0) {
				pack.delete(0, index);
				index = pack.lastIndexOf("/");
				if (index > 0) {
					pack.delete(index, pack.length()).append(".");
				}
			}
		}
		String actionName = "physics.app.control.tab"
				+ pack.toString().replaceAll("/", ".").toLowerCase()
				+ StringUtils.alterFirstCharToUpper(requestPath.substring(
						requestPath.lastIndexOf("/") + 1, requestPath
								.lastIndexOf("."))) + "Action";
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
			Object[] objs = null;
			Object rtn = new Object();
			int totalCount = 0;
			boolean flag = true;
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {

					if (m.getName().equals("save")
							|| m.getName().equals("delete")) {
						if (m.getReturnType().equals(boolean.class)) {
							flag = (Boolean.parseBoolean(m.invoke(
									iTabAction,
									new Object[] { request
											.getParameter("jsonData") })
									.toString()));

						}

					} else {
						Class[] ps = m.getParameterTypes();
						objs = new Object[ps.length];
						for (int i = 0; i < ps.length; i++) {
							if (ps[i].equals(String.class)) {
							} else {
								if (ps[i].equals(Integer.class)) {
									String start = request
											.getParameter("start");
									String limit = request
											.getParameter("limit");
									if (i == 0) {

										objs[i] = Integer.parseInt(start);
									}
									if (i == 1) {

										objs[i] = Integer.parseInt(limit);
									}
								} else {
									if (ps[i].equals(int.class)) {
										String start = request
												.getParameter("start");
										String limit = request
												.getParameter("limit");
										if (i == 0) {

											objs[i] = Integer.parseInt(start);
										}
										if (i == 1) {

											objs[i] = Integer.parseInt(limit);
										}
									}
								}
							}
						}
						rtn = m.invoke(iTabAction, objs);
					}

				} else if (m.getName().equals("count")) {
					if (m.getReturnType().equals(int.class)) {
						totalCount = (Integer.parseInt(m.invoke(iTabAction,
								new Object[] {}).toString()));
					}

				}
			}
			response.setContentType("application/text; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.print("{ 'data':" + rtn.toString() + ",'totalCount':"
					+ totalCount + ",'success':" + flag + "}");

			if (out != null) {
				out.flush();
				out.close();
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

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}