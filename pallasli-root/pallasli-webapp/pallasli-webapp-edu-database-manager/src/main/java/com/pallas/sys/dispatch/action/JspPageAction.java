package com.pallas.sys.dispatch.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JspPageAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws Exception {
		ModelAndView view = null;

		String path = httpRequest.getParameter("url");
		if (path != null && !path.equals("")) {
			path = ContextVariablesContainer.instance()
					.replaceContextVariables(path);
			view = new ModelAndView(path);
			@SuppressWarnings("unchecked")
			Map<String, String[]> params = httpRequest.getParameterMap();
			Iterator<String> keys = params.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (params.get(key) != null) {
					if (params.get(key).length == 1) {
						view.addObject(key, params.get(key)[0]);
					} else {
						view.addObject(key, params.get(key));
					}
				}
			}
		}
		return view;
	}

}
