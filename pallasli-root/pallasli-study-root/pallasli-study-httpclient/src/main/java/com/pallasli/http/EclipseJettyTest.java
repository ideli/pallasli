package com.pallasli.http;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class EclipseJettyTest {
	public static void main2(String[] args) throws Exception {
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("localhost");
		http.setPort(8080);
		http.setIdleTimeout(30000);
		server.addConnector(http);
		server.setHandler(new HelloHandler());
		server.setHandler(new LoginHandler());
		server.start();
		server.join();
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setWelcomeFiles(new String[] { "index.html" });
		resource_handler.setResourceBase(".");
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler,
				new DefaultHandler(), new HelloHandler(), new LoginHandler() });
		server.setHandler(handlers);

		server.start();
		server.join();
	}
}
