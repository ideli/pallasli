package com.pallas.webmvc.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

public class UserLogoutAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/**
		 * User user = null; if (user != null) { }
		 **/
		return new ModelAndView(new RedirectView("login.do"));
	}

	@SuppressWarnings("unused")
	private void notifyUserLogout(String username) {
	}
}