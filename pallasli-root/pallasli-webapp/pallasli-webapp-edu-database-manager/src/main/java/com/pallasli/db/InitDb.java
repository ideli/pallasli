package com.pallasli.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "serial", "unused" })
public class InitDb extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(InitDb.class);
	private static final String DB_PRE = "pallasli_test_";
	private static final String MYSQL_URL = "jdbc:mysql://localhost/";
	private static final String MANAGER_DB = "mysql";
	private static final String USER = "root";
	private static final String PWD = "root";

	private InitDb() {

	}

	private void initProperties(String dbName) {

		String rootPath = this.getClass().getResource("/").getPath();
		// 数据库配置文件，微博 @刘海了RUC
		String filePath = rootPath + "database.properties";
		Properties p = new Properties();
		File pFile = new File(filePath);
		FileInputStream pInStream = null;
		try {
			pInStream = new FileInputStream(pFile);
			p.load(pInStream);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：创建数据库
	 * 
	 */
	private void createTables(String dbName) {
		log.debug("创建数据库{}", dbName);
		try {
			String url = MYSQL_URL + MANAGER_DB;
			Connection connection = DriverManager.getConnection(url, USER, PWD);
			Statement statement = connection.createStatement();
			String dbCreateSQL = "CREATE DATABASE " + dbName
					+ " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
			statement.executeUpdate(dbCreateSQL);
		} catch (Exception e) {
			log.debug("数据库{}初始化失败：{}", dbName, e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 功能：创建数据库
	 * 
	 */
	public void createDatabase(String dbName) {
		log.debug("创建数据库{}", dbName);
		Connection connection = null;
		try {
			String url = MYSQL_URL + MANAGER_DB;
			connection = DriverManager.getConnection(url, USER, PWD);
			Statement statement = connection.createStatement();
			String dbCreateSQL = "CREATE DATABASE " + dbName
					+ " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
			statement.executeUpdate(dbCreateSQL);
		} catch (Exception e) {
			log.debug("数据库{}初始化失败：{}", dbName, e.getMessage());
			e.printStackTrace();
		}
	}

	public List<String> loadSql(String path, String dbName) {
		// 从SQL文件中读取SQL语句，每行一条，末尾没有分号 @刘海龙RUC
		List<String> sqlList = new ArrayList<String>();

		try {
			String filePath = path + "/create";
			File dir = new File(filePath);
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.getName().startsWith(dbName + ".mysql.create.")) {

					FileInputStream in = new FileInputStream(file);
					// 指定读取文件时以UTF-8的格式读取,微博 @刘海龙RUC
					BufferedReader br = new BufferedReader(
							new InputStreamReader(in, "UTF-8"));
					String instring;
					String temp = "";
					while ((instring = br.readLine()) != null) {
						if (0 != instring.length()) {
							String line = instring.trim();
							if (!line.startsWith("#")) {
								temp += " " + line;
								if (line.endsWith(";")) {
									sqlList.add(temp.replace(";$", ""));
									temp = "";
								}
							}
						}
					}
					br.close();

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlList;
	}

	/**
	 * 功能：初始化单个数据库
	 * 
	 * 逻辑：建立临时jdbc链接，连接（若不存在则先创建） 读取初始化脚本，按行读取非#开头，换行替换为‘ ’，遇分号而止
	 */
	private void initDatabase(String dbName, String path) {
		log.debug("连接数据库{}", dbName);
		String url = MYSQL_URL + DB_PRE + dbName;

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, USER, PWD);
			// Statement statement = conn.createStatement();
			// String dbCreateSQL = "DROP DATABASE " + DB_PRE + dbName;
			// statement.executeUpdate(dbCreateSQL);
			// createDatabase(DB_PRE + dbName);
			// conn = DriverManager.getConnection(url, USER, PWD);
		} catch (Exception e) {
			log.debug("数据库{}连接失败，重新创建：{}", dbName, e.getMessage());
			createDatabase(DB_PRE + dbName);
			initDatabase(dbName, path);
			return;
		}

		// 从SQL文件中读取SQL语句，每行一条，末尾没有分号 @刘海龙RUC
		List<String> sqlList = loadSql(path, dbName);

		try (Connection conn2 = DriverManager.getConnection(url, USER, PWD);
				Statement st = conn2.createStatement();) {

			for (String sql : sqlList) {
				// st.executeUpdate(sql);
				System.out.println(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：循环初始化数据库（包括自身，框架，各应用数据库 ）
	 * 
	 * 逻辑：按文件夹区分数据库，初始化相应数据库
	 * 
	 */
	public void initDatabases() {

		String rootPath = this.getClass().getResource("/").getPath();
		String dirPath = "/Users/lyt1987/快盘/"
				+ rootPath.substring(rootPath.indexOf("gitRepo")) + "db";
		System.out.println(dirPath);
		File dir = new File(dirPath);
		log.info("加载数据库文件目录：{}\n{}", dirPath, dir.listFiles());
		String[] dbNames = dir.list();
		for (String dbName : dbNames) {
			initDatabase(dbName, dirPath + "/" + dbName);
		}

	}

	public static void main(String[] args) throws Exception {
		// InitDb.instance().initDatabases();
	}

	public static InitDb instance() {
		return new InitDb();
	}

}
