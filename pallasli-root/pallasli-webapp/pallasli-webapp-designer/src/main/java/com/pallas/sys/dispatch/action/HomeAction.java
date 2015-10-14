package com.pallas.sys.dispatch.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

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
			portalLoginPath = "/home";
		}
		ModelAndView view = new ModelAndView(portalLoginPath);

		/**
		 * 1. tbar
		 * 
		 * 
		 * 
		 * 
		 */
		JSONArray tbars = new JSONArray();
		JSONObject tbar = new JSONObject();
		tbar.append("text", "字段设置");
		tbars.put(tbar);
		view.addObject("tbars", tbars);
		return view;
	}
}