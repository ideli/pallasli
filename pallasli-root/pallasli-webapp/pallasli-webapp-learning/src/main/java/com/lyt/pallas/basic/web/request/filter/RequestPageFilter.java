package com.lyt.pallas.basic.web.request.filter;

import java.io.IOException;
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
import com.lyt.pallas.web.physics.control.AbstractTabAction;


public class RequestPageFilter implements Filter {

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

		Enumeration<String> emun = request.getParameterNames();
		Map parameter = new HashMap();
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
			// Method method = iTabAction.getClass().getDeclaredMethod(
			// methodName,
			// new Class<?>[] { Map.class, HttpServletRequest.class,
			// HttpServletResponse.class });
			Method[] methods = iTabAction.getClass().getDeclaredMethods();
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {
					Class[] paramTypes = m.getParameterTypes();
					Object[] obj = new Object[paramTypes.length];
					for (int i = 0; i < paramTypes.length; i++) {
						if (!paramTypes[i].isInterface()) {
							obj[i] = paramTypes[i].newInstance();
						}
					}
					if (m.getGenericReturnType().equals(String.class)) {
						String data = (String) m.invoke(iTabAction, obj);
						request.setAttribute("data", data);
					} else {
						m.invoke(iTabAction, obj);
					}
				}
			}
			// Annotation[][] ps = method.getParameterAnnotations();
			// for (Annotation[] pss : ps) {
			// Annotation[] s = pss;
			// for (Annotation psss : pss) {
			// Annotation ss = psss;
			// }
			// }

			StringBuffer path = new StringBuffer(request.getRequestURI());
			if (path.length() > 0) {
				path.deleteCharAt(0);
			}
			int index = path.indexOf("/");
			if (index > 0) {
				path.delete(0, index);
			}
			index = path.lastIndexOf(".");
			int length = path.length();
			if (index > 0 && length > index) {
				path.replace(index + 1, length, "jsp");
			}
			request.getRequestDispatcher(path.toString()).forward(request,
					response);
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