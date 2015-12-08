package com.pallasli.authority.orgnization.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;
import com.pallasli.authority.orgnization.DataEncryptService;
import com.pallasli.bean.User;
import com.pallasli.sql.mybatis.MybatisOperation;

public class UserManager {
	static UserManager singlon = null;

	public static UserManager instance() {
		singlon = new UserManager();
		return singlon;
	}

	public JsonObject saveUserConfig(String key, JsonObject json, User user) {
		return null;
	}

	public JsonObject getUserConfig(String key, User user) {
		return null;
	}

	public JsonObject delUserConfig(String key, User user) {
		return null;
	}

	public User getUserByName(String userName) {
		User user = new User();
		ResultSet ret = MybatisOperation
				.instance()
				.execQuery(
						"select id,f_name,f_password,f_caption,f_latest_ip,f_latest_time,f_state from t_sys_user where f_name='"
								+ userName + "'");
		try {
			while (ret.next()) {
				user.setId(ret.getLong("id"));
				user.setF_caption(ret.getString("f_caption"));
				user.setF_name(ret.getString("f_name"));
				user.setF_password(ret.getString("f_password"));
				user.setF_latest_time(ret.getDate("f_latest_time"));
				user.setF_latest_ip(ret.getString("f_latest_ip"));
				user.setF_state(ret.getString("f_state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		decrypt(user);
		return user;
	}

	public User saveUser(User user) {
		encrypt(user);
		StringBuffer sql = new StringBuffer();
		sql.append("insert into  t_sys_user(f_name,f_password,f_caption)values('");
		sql.append(user.getF_name());
		sql.append("','");
		sql.append(user.getF_password());
		sql.append("','");
		sql.append(user.getF_caption());
		sql.append("')");

		// boolean flag = MybatisOperation.instance().execSql(sql.toString());
		return null;
	}

	public String getSqlDateStr(Date date) {
		String dateStr = "";
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateStr = format1.format(date);
		dateStr = "str_to_date('" + dateStr + "','%m.%d.%Y %h:%i:%s')";
		return dateStr;
	}

	public boolean login(User user) {
		encrypt(user);
		StringBuffer sql = new StringBuffer();
		sql.append("update t_sys_user set ");
		sql.append("f_state='");
		sql.append(user.getF_state());
		sql.append("' and ");
		sql.append("f_latest_ip='");
		sql.append(user.getF_latest_ip());
		sql.append("' and ");
		sql.append("f_latest_time=");
		sql.append(getSqlDateStr(user.getF_latest_time()));
		sql.append("");
		System.out.println(sql.toString());
		boolean flag = MybatisOperation.instance().execSql(
				"com.pallas.sys.User.updateUser", user);
		return flag;
	}

	public void encrypt(User user) {
		String pwd = user.getF_password();
		pwd = DataEncryptService.instance().encryptIt(pwd, null);
		user.setF_password(pwd);
	}

	public void decrypt(User user) {
		String pwd = user.getF_password();
		pwd = DataEncryptService.instance().decryptIt(pwd, null);
		user.setF_password(pwd);
	}

}
