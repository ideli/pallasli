package com.pallasli.core.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import com.pallasli.core.WsAppContext;

public class JettyServer {

	private static Logger log = LogManager.getLogger(JettyServer.class);

	/**
	 * 监听端口, 缺省为16999。
	 */
	private int port = 10010;

	/**
	 * 应用上下文, 缺省为/(无上下文)
	 */
	private String webContext = "/";

	/**
	 * 构造JettyServer实例
	 */
	public JettyServer() {

	}

	/**
	 * 构造JettyServer实例,配置上下文和监听端口
	 * 
	 * @param webContext
	 * @param port
	 */
	public JettyServer(String webContext, int port) {
		this.webContext = webContext;
		this.port = port;
	}

	public void start() throws Exception {
		WsAppContext.getApplicationContext();
		final String webRoot = System.getProperty("user.dir")
				+ "/src/main/webapp";
		Server server = new Server();
		SelectChannelConnector connector0 = new SelectChannelConnector();
		connector0.setUseDirectBuffers(false);
		connector0.setPort(port);
		server.setConnectors(new Connector[] { connector0 });
		WebAppContext context = new WebAppContext();
		context.setDescriptor(webRoot + "/WEB-INF/web.xml");
		context.setResourceBase(webRoot);
		context.setContextPath(webContext);
		context.setParentLoaderPriority(true);
		server.setHandler(context);
		server.start();

		String msg = "启动成功";
		webContext = webContext.equals("/") ? "" : webContext;
		msg = msg + " >> localhost:" + port + webContext
				+ " | 技术支持 >> www.pallasli.com";
		log.info(msg);
		server.join();
	}

	public int getPort() {
		return port;
	}

	public String getWebContext() {
		return webContext;
	}

}
