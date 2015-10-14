package com.pallas.webmvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class Dispatch extends DispatcherServlet {

	protected void doOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		initCrossDomainHeaders(request, response);
		super.doOptions(request, response);
	}

	protected void doService(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		initCrossDomainHeaders(arg0, arg1);
		super.doService(arg0, arg1);
	}

	private void initCrossDomainHeaders(HttpServletRequest request,
			HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin",
				request.getHeader("Origin"));
		response.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.addHeader("Access-Control-Allow-Credentials", "true");
	}
}
