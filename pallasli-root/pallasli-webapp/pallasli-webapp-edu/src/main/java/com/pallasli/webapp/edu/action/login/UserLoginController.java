package com.pallasli.webapp.edu.action.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pallasli.webapp.edu.model.UserLoginLog;
import com.pallasli.webapp.edu.model.UserRegister;
import com.pallasli.webapp.edu.service.LoginService;
import com.pallasli.webapp.edu.service.UserService;

@Controller
@RequestMapping("/")
public class UserLoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public String login(UserRegister userRegister, HttpServletRequest request) {
		System.out.println("login");
		String loginName = userRegister.getUserName();
		String ip = "";
		String proxyIp = "";

		String userName = userService.findUserNameByLoginName(loginName);
		if (userName != null) {
			userRegister.setUserName(userName);
		}
		userRegister = loginService.login(userRegister);

		if (userRegister != null) {
			UserLoginLog userLoginLog = new UserLoginLog();
			userLoginLog.setLoginDate(new Date());
			userLoginLog.setLoginId(userRegister.getId());
			userLoginLog.setLoginIp(ip);
			userLoginLog.setProxyIp(proxyIp);
			userLoginLog.setLoginName(loginName);
			loginService.addLoginLog(userLoginLog);
			return "/pages/home/home.html";
		} else {
			return "/index.html";
		}
	}

	@RequestMapping("/register")
	@ResponseBody
	public String register(UserRegister userRegister) {
		boolean flag = loginService.register(userRegister);
		System.out.println(flag);
		System.out.println("register");
		return "login in";
	}
}
