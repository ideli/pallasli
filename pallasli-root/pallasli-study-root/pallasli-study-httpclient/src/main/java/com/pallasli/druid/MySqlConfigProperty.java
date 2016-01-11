package com.pallasli.druid;

import java.util.Properties;

public class MySqlConfigProperty {
	static MySqlConfigProperty mySqlConfigProperty = null;

	public static MySqlConfigProperty getInstance() {
		if (mySqlConfigProperty == null)
			mySqlConfigProperty = new MySqlConfigProperty();
		return mySqlConfigProperty;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		p.put("url", "jdbc:mysql://localhost:3306/dragoon_v25_masterdb");
		p.put("driverClassName", "com.mysql.jdbc.Driver");
		p.put("username", "root");
		p.put("password", "aaaaaaaa");
		return p;
	}

	public Properties getProperties2() {
		// TODO Auto-generated method stub
		return null;
	}

}
