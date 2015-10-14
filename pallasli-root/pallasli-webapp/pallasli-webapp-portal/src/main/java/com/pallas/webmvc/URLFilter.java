package com.pallas.webmvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URLFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) rep;
		String uri = request.getRequestURI();
		String basPath = request.getContextPath();
		if (uri.endsWith(basPath + "/login.do")) {

		} else if (uri.endsWith(basPath + "/auth.do")) {
			// 登录验证

		} else if (uri.endsWith(basPath + "/home.do")) {

		} else if (uri.endsWith(basPath + "/home1.do")) {

		} else if (uri.endsWith(basPath + "/home2.do")) {

		} else {
			System.out.println(uri);
			// response.sendRedirect("login.do");
			return;
		}
		chain.doFilter(req, rep);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
