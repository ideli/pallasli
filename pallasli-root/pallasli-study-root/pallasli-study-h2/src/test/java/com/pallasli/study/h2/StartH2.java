package com.pallasli.study.h2;

import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;
import org.junit.Test;

public class StartH2 {
	@Test
	public void start() {
		try {

			// 默认端口8082
			Server server = Server.createWebServer("-webPort", "8083");
			server.start();
			// Connection conn=DriverManager.getConnection(url);
			// Server.startWebServer(conn);
			Server.openBrowser("http://localhost:8083");
			System.in.read();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
