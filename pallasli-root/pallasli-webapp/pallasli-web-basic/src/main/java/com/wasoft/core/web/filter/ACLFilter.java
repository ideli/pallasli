/**
 * 河北省神玥软件科技有限公司 版权所有
 *
 * @file 文件:com.wasoft.core.web.filter.ACLFliter.java
 *
 * @date 创建时间:2014-6-16
 *
 * @author 创建人:吕绪文
 *
 * @Description TODO
 */
package com.wasoft.core.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用于禁止相应的远程客户端访问
 * @author 吕绪文
 * @created 吕绪文 2014-6-16
 * @version 1
 */
public class ACLFilter implements Filter {

	private List<String> domainList = new ArrayList<String>();
	private List<String> ipList = new ArrayList<String>();
	private static final String SPLIT_PATTERN = "\\s+|,\\s*|;\\s*|\r\n\\s*";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
			doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (inDomainList(request)) {
			printACLMessage(response);
			return;
		}

		if (inIPList(request)) {
			printACLMessage(response);
			return;
		}

		chain.doFilter(request, response);
	}

	private void printACLMessage(HttpServletResponse response) {
		String message = "<h1>NO ACCESS<h1>";
		try {
			response.getOutputStream().print(message);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean inDomainList(HttpServletRequest request) {
		String refer = getHeader(request, "Referer");

		if (refer == null || refer.equals(""))
			return false;

		for (String domain : domainList) {
			// System.out.println("domain:-----"+domain);
			if (domain.contains(refer)) {
				// System.out.println("domain catch:"+domain+";refer:"+refer);
				return true;
			}
			if (refer.contains(domain)) {
				// System.out.println("domain catch:"+domain+";refer:"+refer);
				return true;
			}

		}

		return false;

	}

	private boolean inIPList(HttpServletRequest request) {
		String ip = getHeader(request, "x-forwarded-for");

		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = getHeader(request, "Proxy-Client-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = getHeader(request, "WL-Proxy-Client-IP");
		}
		if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		for (String tip : ipList) {
			System.out.println(tip);
			if (ip.contains(tip)) {
				// System.out.println("tip catch:"+tip+";ip:"+ip);
				return true;
			}
		}

		return false;

	}

	private String getHeader(HttpServletRequest request, String headerName) {

		Enumeration<?> enumeration = request.getHeaderNames();

		while (enumeration.hasMoreElements()) {
			String paraName = (String) enumeration.nextElement();
			if (headerName.equalsIgnoreCase(paraName)) {
				return request.getHeader(paraName);
			}
		}

		return null;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String domainListStr = config.getInitParameter("domainList");
		String ipListStr = config.getInitParameter("ipList");

		if (domainListStr != null) {
			domainListStr = domainListStr.trim();
			if (domainListStr.length() > 0) {
				String[] domainListArr = domainListStr.split(SPLIT_PATTERN);
				for (String domain : domainListArr) {
					// System.out.println("domain:"+domain);
					domainList.add(domain.trim());
				}
			}

		}

		if (ipListStr != null) {
			ipListStr = ipListStr.trim();
			if (ipListStr.length() > 0) {
				String[] ipListArr = ipListStr.split(SPLIT_PATTERN);
				for (String ip : ipListArr) {
					// System.out.println("ip:"+ip);
					ipList.add(ip.trim());
				}
			}
		}

	}

}
