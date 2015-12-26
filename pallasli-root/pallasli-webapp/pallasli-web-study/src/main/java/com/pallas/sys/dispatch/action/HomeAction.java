package com.pallas.sys.dispatch.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class HomeAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		ModelAndView view = getLoginView();
		return view;
	}

	private ModelAndView getLoginView() {
		String portalLoginPath = "";
		if (portalLoginPath == null || "".equals(portalLoginPath)) {
			portalLoginPath = "/home/home";
		}
		System.out.println("***********");
		ModelAndView view = new ModelAndView(portalLoginPath);

		/**
		 * 1. tbar
		 * 
		 * 
		 * 
		 * 
		 */
		JsonArray tbars = new JsonArray();
		JsonObject tbar = new JsonObject();
		tbar.addProperty("text", "字段设置");
		tbars.add(tbar);
		view.addObject("tbars", tbars);
		return view;
	}
}