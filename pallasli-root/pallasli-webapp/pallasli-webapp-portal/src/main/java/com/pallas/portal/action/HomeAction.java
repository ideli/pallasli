package com.pallas.portal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HomeAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView view = getHomeView();
		return view;
	}

	private ModelAndView getHomeView() {
		String portalLoginPath = "";
		if (portalLoginPath == null || "".equals(portalLoginPath)) {
			portalLoginPath = "home/home";
		}
		ModelAndView view = new ModelAndView(portalLoginPath);

		return view;
	}
}