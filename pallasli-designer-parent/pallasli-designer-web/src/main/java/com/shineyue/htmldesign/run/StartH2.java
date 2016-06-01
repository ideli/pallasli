package com.shineyue.htmldesign.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class StartH2 {
	public static void main(String[] args) {
		try {
			Class.forName("org.h2.Driver");

			// connect to h2
			Connection conn = DriverManager.getConnection(
					"jdbc:h2:database/h2db", "sa", "sa");
			Server.startWebServer(conn);
			Server.openBrowser("http://localhost:8082");
			System.in.read();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
