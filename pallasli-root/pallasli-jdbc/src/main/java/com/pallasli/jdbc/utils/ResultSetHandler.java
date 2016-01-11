package com.pallasli.jdbc.utils;

import java.sql.ResultSet;

public interface ResultSetHandler {
	/**
	 * @Method: handler
	 * @Description: 结果集处理方法
	 * @Anthor:孤傲苍狼
	 *
	 * @param rs
	 *            查询结果集
	 * @return
	 */
	public Object handler(ResultSet rs);
}
