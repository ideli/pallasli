package com.pallasli.db;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "serial", "unused" })
public class InitDbServlet extends HttpServlet {
	private static final Logger log = LoggerFactory
			.getLogger(InitDbServlet.class);

	@Override
	public void init() throws ServletException {
		InitDb.instance().initDatabases();
	}

}
