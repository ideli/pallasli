package com.pallas.webmvc.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.pallasli.authority.orgnization.manager.UserManager;
import com.pallasli.bean.User;

public class UserLoginAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String isForce = request.getParameter("isForce");
		if (isForce == null) {
			isForce = "0";
		}
		String ip = getRemoteIpAddr(request);
		ModelAndView view = null;
		// byte[] b;
		// BASE64Decoder decoder = new BASE64Decoder();
		// b = decoder.decodeBuffer(password);
		// password = new String(b);

		User user = UserManager.instance().getUserByName(userName);
		if (user.getId() > 0 && user.getF_password().equals(password)) {
			if (!isForce.equals("1")
					&& (user.getF_state().equals("01") && !user
							.getF_latest_ip().equals(ip))) {

				view = getLoginView();
				view.addObject("msg", "用户已在" + user.getF_latest_ip()
						+ "登录,登录时间：" + user.getF_latest_time());
			} else {
				user.setF_latest_ip(ip);
				user.setF_latest_time(new Date());
				user.setF_state("01");
				boolean flag = UserManager.instance().login(user);
				if (flag) {
					request.getSession().setAttribute("userId", user.getId());
					request.getSession().setAttribute("userName",
							user.getF_name());
					view = getHomeView();
				} else {
					view = getLoginView();
					view.addObject("msg", "服务器错误，用户登录失败");
				}
			}
		} else {
			view = getLoginView();
			view.addObject("msg", "用户名或密码错误");
		}
		return view;
	}

	private ModelAndView getLoginView() {
		String portalLoginPath = "";
		if (portalLoginPath == null || "".equals(portalLoginPath)) {
			portalLoginPath = "/login/login";
		}
		ModelAndView view = new ModelAndView(portalLoginPath);
		return view;
	}

	private ModelAndView getHomeView() {
		String portalLoginPath = "";
		if (portalLoginPath == null || "".equals(portalLoginPath)) {
			portalLoginPath = "/home";
		}
		ModelAndView view = new ModelAndView(portalLoginPath);
		return view;
	}

	// private String computeRedirectUrl(String redirectUrl, String userToken) {
	// String url = redirectUrl;
	// if (url.indexOf("?") > -1) {
	// url = url + "&token=" + userToken + "&redirectUrl=" + redirectUrl;
	// } else {
	// url = url + "?token=" + userToken + "&redirectUrl=" + redirectUrl;
	// }
	// return url;
	// }

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
	//
	// private void notifyUserLogout(String username) {
	// }
}