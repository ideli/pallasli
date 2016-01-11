package com.pallasli.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <mvc:interceptors>
 * 
 * <mvc:interceptor>
 * 
 * <!-- 匹配的是url路径， 如果不配置或/**,将拦截所有的Controller -->
 * 
 * <mvc:mapping path="/xxx/**" />
 * 
 * <bean class="com.xxx.SecurityTokenInterceptor"></bean>
 * 
 * </mvc:interceptor>
 * 
 * <!-- 当设置多个拦截器时，先按顺序调用preHandle方法，然后逆序调用每个拦截器的postHandle和afterCompletion方法 -->
 * 
 * </mvc:interceptors>
 * 
 * @author lyt1987
 *
 */

/**
 * 防止重复提交过滤器
 *
 */

public class SecurityTokenInterceptor extends HandlerInterceptorAdapter {
	private static final Logger duplicateAvoidLOG = LoggerFactory
			.getLogger(SecurityTokenInterceptor.class);

	public static final String DUPLICATEAVOID_TOKEN = "duplicateAvoid_token";
	public static final String PAGE_DUPLICATEAVOID_TOKEN = "sumbit_token";
	// private static final ConcurrentMap<String, String> tokenMap = new
	// ConcurrentHashMap<String, String>();
	public static final int expressTime = 60 * 60 * 24 * 5;
	public static final String TOKEN_ERROR_CODE = "duplicate_Error";
	public static final ThreadLocal<String> threadtoken = new ThreadLocal<String>();

	/**
	 * 前置处理器中 检查 DuplicateAvoid注解
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		duplicateAvoidLOG.info("DuplicateAvoidSubmitInterceptor start,[client:"
				+ getRemoteHost(request) + ",url:" + request.getServletPath()
				+ "]");
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			SecurityToken annotation = method
					.getAnnotation(SecurityToken.class);
			duplicateAvoidLOG.info("method infos,[method:" + method.getName()
					+ "]");
			if (annotation != null) {
				// 需要验证token
				boolean needValidateToken = annotation.validateToken();
				if (needValidateToken) {
					if (isRepeatSubmit(request)) {
						duplicateAvoidLOG
								.error("please don't repeat submit,[client:"
										+ getRemoteHost(request) + ",url:"
										+ request.getServletPath() + "]");
						request.getSession().setAttribute(TOKEN_ERROR_CODE,
								"please don't repeat submit");
						String fuction = annotation.ajaxFailCallBack();
						String responStr = "{\"duplicate\":\"true\",\"callback\":\""
								+ fuction + "\"}";
						try {
							response.getOutputStream().write(
									responStr.getBytes());

							response.getOutputStream().flush();
							response.getOutputStream().close();
						} catch (IOException e) {
							duplicateAvoidLOG.error("repeat submit ,[client:"
									+ getRemoteHost(request) + ",url:"
									+ request.getServletPath() + "]");
							e.printStackTrace();
						}
						return false;
					}
					request.getSession(false).removeAttribute(
							DUPLICATEAVOID_TOKEN);
				}
				// 需要保存token,先产生token 保存到response的cookie中，服务器端保存在业务方法调用后保存
				boolean needSaveToken = annotation.generateToken();
				if (needSaveToken) {
					String token = UUID.randomUUID().toString();
					Cookie tokenCookie = new Cookie(PAGE_DUPLICATEAVOID_TOKEN,
							token);
					tokenCookie.setMaxAge(expressTime);
					tokenCookie.setPath("/");
					response.addCookie(tokenCookie);
					// response.addHeader(PAGE_DUPLICATEAVOID_TOKEN, token);
					threadtoken.set(token);
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		duplicateAvoidLOG
				.info("DuplicateAvoidSubmitInterceptor postHandle,[client:"
						+ getRemoteHost(request) + ",url:"
						+ request.getServletPath() + "]" + ",token ="
						+ threadtoken.get());
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			SecurityToken annotation = method
					.getAnnotation(SecurityToken.class);
			duplicateAvoidLOG.info("method infos,[method:" + method.getName()
					+ "]");
			if (annotation != null) {
				// 保存token，到服务器的session中
				boolean needSaveToken = annotation.generateToken();
				if (needSaveToken) {
					String token = threadtoken.get();
					threadtoken.set("");
					if (!StringUtils.isEmpty(token)) {
						request.getSession().setAttribute(DUPLICATEAVOID_TOKEN,
								token);
					}
				}
			}
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {

		String serverToken = (String) request.getSession(false).getAttribute(
				DUPLICATEAVOID_TOKEN);
		Cookie cookies[] = request.getCookies();
		Cookie tokenCookie = null;
		String clinetToken = null;
		for (int i = 0; i < cookies.length; i++) {
			tokenCookie = cookies[i];
			if (PAGE_DUPLICATEAVOID_TOKEN.equals(tokenCookie.getName())) {
				clinetToken = tokenCookie.getValue();
			}
		}
		if (StringUtils.isEmpty(clinetToken)) {
			clinetToken = request.getParameter(PAGE_DUPLICATEAVOID_TOKEN);
		}
		duplicateAvoidLOG.info("isRepeatSubmit ,[serverToken:" + serverToken
				+ ",clinetToken:" + clinetToken + "]");
		if (StringUtils.isEmpty(serverToken)
				|| StringUtils.isEmpty(clinetToken)) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}

		return false;
	}

	private String getRemoteHost(HttpServletRequest request) {
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
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}