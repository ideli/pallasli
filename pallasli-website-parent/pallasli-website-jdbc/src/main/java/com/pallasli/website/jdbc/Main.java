package com.pallasli.website.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		SecObjectPool p = new SecObjectPool();
		Connection connection = null;
		try {
			connection = (Connection) p.borrowObject();
			System.out.println("================");
			System.out.println("使用中几个：" + p.getNumActive());
			System.out.println("队列中还有：" + p.getNumIdle());
			p.addObject();
			p.addObject();
			p.addObject();
			p.addObject();
			p.addObject();
			p.addObject();
			System.out.println("================");
			System.out.println("使用中几个：" + p.getNumActive());
			System.out.println("队列中还有：" + p.getNumIdle());
			connection = (Connection) p.borrowObject();
			connection = (Connection) p.borrowObject();
			connection = (Connection) p.borrowObject();

			System.out.println("=====clear===========");
			p.clear();
			System.out.println("=====close===========");
			p.close();

		} catch (Throwable e) {
			e.printStackTrace();
			connection.close();
		}

	}

}
