package com.pallasli.http;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.xml.XmlConfiguration;
import org.xml.sax.SAXException;

public class MortbayJettyTest {
	public static void main(String[] args) {
		org.mortbay.jetty.Server server = new Server(8080);
		server.setHandler(new DefaultHandler());
		XmlConfiguration configuration = null;
		try {
			configuration = new XmlConfiguration(new FileInputStream(
					MortbayJettyTest.class.getResource("/").getPath()
							+ "/jetty.xml"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// configuration.configure(server);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
