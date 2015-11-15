package com.pallasli.designer.sys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlPropUtils {
	public File loadFile() {
		return null;
	}

	public void writeFile() {

	}

	public void saveFile() {

	}

	public static Properties getProperties(String path) {

		String rootPath = "/" + SqlPropUtils.class.getResource("/").getPath();
		// 数据库配置文件
		String filePath = rootPath + path;// + "database.properties";
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
		return p;
	}

	public static List<String> loadSqlFile(String path) {

		String rootPath = SqlPropUtils.class.getResource("/").getPath();
		String filePath = rootPath + path;// "init.sql";
		// 从SQL文件中读取SQL语句，每行一条，末尾没有分号
		List<String> sqlList = new ArrayList<String>();

		try {
			File file = new File(filePath);
			FileInputStream in = new FileInputStream(file);
			// 指定读取文件时以UTF-8的格式读取
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String instring;

			String sql = "";
			while ((instring = br.readLine()) != null) {
				if (0 != instring.length()) {
					String line = instring.trim();
					if (line.startsWith("--")) {
						sqlList.add(line);
					} else if (line.endsWith(";")) {
						sql += line + " ";
						sqlList.add(sql);
						sql = "";
					} else {
						sql += line + " ";
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sqlList;
	}
}
