package com.pallasli.webapp.edu.action.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pallasli.webapp.edu.model.UserLoginLog;
import com.pallasli.webapp.edu.service.LogService;

@Controller
@RequestMapping("/log/")
public class LogController {
	@Autowired
	private LogService logService;

	@RequestMapping("/listLoginLog")
	@ResponseBody
	public String listLoginLog(String userId, HttpServletRequest request) {
		List<UserLoginLog> list = logService.listLoginLog(userId);

		System.out.println("login");
		return "login in";
	}

}
