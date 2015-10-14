package com.pallas.webmvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.pallasli.authority.orgnization.manager.UserManager;
import com.pallasli.bean.User;

public class UserStateFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		System.out.println("Protocol: " + request.getProtocol());
		System.out.println("Scheme: " + request.getScheme());
		System.out.println("Server Name: " + request.getServerName()); // 获得服务器的名字
		System.out.println("Server Port: " + request.getServerPort()); // 获得服务器的端口号
		System.out.println("rotocol: " + request.getProtocol());
		System.out.println("Remote Addr: " + request.getRemoteAddr()); // 获得客户端的ip地址
		System.out.println("Remote Host: " + request.getRemoteHost()); // 获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
		System.out.println("Character Encoding: "
				+ request.getCharacterEncoding());
		System.out.println("Content Length: " + request.getContentLength());
		System.out.println("Content Type: " + request.getContentType());
		System.out.println("Auth Type: " + request.getAuthType());
		System.out.println("HTTP Method: " + request.getMethod()); // 获得客户端向服务器端传送数据的方法有get、post、put等类型
		System.out.println("ath Info: " + request.getPathInfo());
		System.out.println("ath Trans: " + request.getPathTranslated());
		System.out.println("Query String: " + request.getQueryString());
		System.out.println("Remote User: " + request.getRemoteUser());
		System.out.println("Session Id: " + request.getRequestedSessionId());
		System.out.println("Request URI: " + request.getRequestURI());// 获得发出请求字符串的客户端地址
		System.out.println("Servlet Path: " + request.getServletPath()); // 获得客户端所请求的脚本文件的文件路径
		System.out.println(request.getHeaderNames()); // 返回所有request
														// header的名字，结果集是一个enumeration（枚举）类的实例
		System.out.println("Accept: " + request.getHeader("Accept"));
		System.out.println("Host: " + request.getHeader("Host"));
		System.out.println("Referer : " + request.getHeader("Referer"));
		System.out.println("Accept-Language : "
				+ request.getHeader("Accept-Language"));
		System.out.println("Accept-Encoding : "
				+ request.getHeader("Accept-Encoding"));
		System.out.println("User-Agent : " + request.getHeader("User-Agent")); // 返回客户端浏览器的版本号、类型
		System.out.println("Connection : " + request.getHeader("Connection"));
		System.out.println("Cookie : " + request.getHeader("Cookie"));
		Object userName = request.getSession().getAttribute("userName");
		String method = request.getHeader("method");
		System.out.println("method------------->" + method);
		if (userName != null) {
			User user = UserManager.instance().getUserByName(
					userName.toString());
			String ip = getRemoteIpAddr(request);
			if (user.getF_state().equals("01")
					&& !user.getF_latest_ip().equals(ip)) {
				request.getSession().setAttribute("userId", null);
				request.setAttribute("msg", "用户已在" + user.getF_latest_ip()
						+ "登录,登录时间：" + user.getF_latest_time());
				request.getRequestDispatcher("/login.do");
			}
		} else {
			System.out.println("method------------->" + method);
		}
		System.out.println("------------->checkState");
		chain.doFilter(request, response);

	}

	private String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
