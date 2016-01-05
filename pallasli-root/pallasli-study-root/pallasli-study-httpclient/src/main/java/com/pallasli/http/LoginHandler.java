package com.pallasli.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class LoginHandler extends AbstractHandler {
	final String _greeting;

	final String _body;

	public LoginHandler() {
		_greeting = "Hello World";
		_body = null;
	}

	public LoginHandler(String greeting) {
		_greeting = greeting;
		_body = null;
	}

	public LoginHandler(String greeting, String body) {
		_greeting = greeting;
		_body = body;
	}

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println(request.getCookies());
		Cookie cookie = new Cookie("JSESSIONID",
				"8D465FEED5C5D3A71F44765B02E2160B.jvm1");
		response.addCookie(cookie);
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>" + _greeting + "</h1>");
		if (_body != null)
			response.getWriter().println(_body);

	}
}
