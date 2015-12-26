package com.pallas.portal;

import javax.servlet.ServletContextEvent;

import lyt.designer.dao.BaseConn;

import org.springframework.web.context.ContextLoaderListener;

public class LoadOnStart extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		BaseConn.getDesignCon("designer");
		BaseConn.getKnowledgeCon();
		BaseConn.getAppCon();

	}
}
