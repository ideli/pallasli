package lyt.designer.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.google.gson.JsonObject;

public class BaseConn {
	public static Connection appCon;
	public static Connection designCon;
	public static Connection knowledgeCon;
	public static Connection designRootCon;
	public static int TYPE_ORCL = 1;
	public static int TYPE_MYSQL = 2;

	public static void getAppCon(JsonObject database) {
		String user = database.get("databaseUser").getAsString();
		String password = database.get("databasePassword").getAsString();
		String schema = database.get("databaseSchema").getAsString();
		String ip = database.has("databaseIp") ? database.get("databaseIp")
				.getAsString() : "127.0.0.1";
		String port = database.has("databasePort") ? database.get(
				"databasePort").getAsString() : "";
		int type = database.has("databaseType") ? database.get("databaseType")
				.getAsInt() : 1;
		try {
			String driverClassName = "";
			String url = "";
			if (type == TYPE_ORCL) {
				url = "jdbc:oracle:thin:@" + ip + "/" + schema;
				driverClassName = "oracle.jdbc.driver.OracleDriver";
				DriverManager.registerDriver((Driver) Class.forName(
						driverClassName).newInstance());
			} else if (type == TYPE_MYSQL) {
				driverClassName = "com.mysql.jdbc.Driver";
				DriverManager.registerDriver((Driver) Class.forName(
						driverClassName).newInstance());

				url = "jdbc:mysql://" + ip + ":" + port + "/" + schema;

			}

			Properties dbProps = new Properties();

			dbProps.setProperty("driverClassName", driverClassName);
			dbProps.setProperty("url", url);

			dbProps.setProperty("username", user);
			dbProps.setProperty("password", password);
			System.out.println(url);
			System.out.println(driverClassName);
			System.out.println(user);
			System.out.println(password);

			DataSource ds = (BasicDataSourceFactory.createDataSource(dbProps));
			appCon = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("err:" + e.getMessage());
		}
	}

	public static void getAppCon() {
		try {

			String driverClassName = "oracle.jdbc.driver.OracleDriver";
			DriverManager.registerDriver((Driver) Class
					.forName(driverClassName).newInstance());

			Properties dbProps = new Properties();

			dbProps.setProperty("driverClassName", driverClassName);
			dbProps.setProperty("url", "jdbc:oracle:thin:@localhost/orcl");

			dbProps.setProperty("username", "wasys350");
			dbProps.setProperty("password", "wasoft2014");

			DataSource ds = (BasicDataSourceFactory.createDataSource(dbProps));
			appCon = ds.getConnection();
		} catch (Exception e) {
			System.out.println("err:" + e.getMessage());
		}
	}

	public static void getDesignRootCon() {

		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			DriverManager.registerDriver((Driver) Class
					.forName(driverClassName).newInstance());

			Properties dbProps = new Properties();

			dbProps.setProperty("driverClassName", driverClassName);
			dbProps.setProperty("url",
					"jdbc:mysql://127.0.0.1:3306/databaseAll");

			dbProps.setProperty("username", "root");
			dbProps.setProperty("password", "root");

			DataSource ds = (BasicDataSourceFactory.createDataSource(dbProps));
			designRootCon = ds.getConnection();
		} catch (Exception e) {
			System.out.println("err:" + e.getMessage());
		}
	}

	public static void getDesignCon(String designer) {

		try {

			String driverClassName = "com.mysql.jdbc.Driver";
			DriverManager.registerDriver((Driver) Class
					.forName(driverClassName).newInstance());

			Properties dbProps = new Properties();

			dbProps.setProperty("driverClassName", driverClassName);
			dbProps.setProperty("url", "jdbc:mysql://127.0.0.1:3306/"
					+ designer);

			dbProps.setProperty("username", "root");
			dbProps.setProperty("password", "root");

			DataSource ds = (BasicDataSourceFactory.createDataSource(dbProps));
			designCon = ds.getConnection();
		} catch (Exception e) {
			System.out.println("err:" + e.getMessage());
		}
	}

	public static void getKnowledgeCon() {

		try {

			String driverClassName = "com.mysql.jdbc.Driver";
			DriverManager.registerDriver((Driver) Class
					.forName(driverClassName).newInstance());

			Properties dbProps = new Properties();

			dbProps.setProperty("driverClassName", driverClassName);
			dbProps.setProperty("url", "jdbc:mysql://127.0.0.1:3306/knowledge");

			dbProps.setProperty("username", "root");
			dbProps.setProperty("password", "root");

			DataSource ds = (BasicDataSourceFactory.createDataSource(dbProps));
			knowledgeCon = ds.getConnection();
		} catch (Exception e) {
			System.out.println("err:" + e.getMessage());
		}
	}
}
