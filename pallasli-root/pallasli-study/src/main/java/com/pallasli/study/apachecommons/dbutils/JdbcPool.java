package com.pallasli.study.apachecommons.dbutils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * 
 * 
 * 数据库连接池在初始化时将创建一定数量的数据库连接放到连接池中,
 * 这些数据库连接的数量是由最小数据库连接数来设定的.无论这些数据库连接是否被使用,连接池都将一直保证至少拥有这么多的连接数量.
 * 连接池的最大数据库连接数量限定了这个连接池能占有的最大连接数,当应用程序向连接池请求的连接数超过最大连接数量时,这些请求将被加入到等待队列中.
 * 
 * 数据库连接池的最小连接数和最大连接数的设置要考虑到以下几个因素:
 * 
 * 最小连接数:是连接池一直保持的数据库连接,所以如果应用程序对数据库连接的使用量不大,将会有大量的数据库连接资源被浪费.
 * 最大连接数:是连接池能申请的最大连接数,如果数据库连接请求超过次数,后面的数据库连接请求将被加入到等待队列中,这会影响以后的数据库操作
 * 如果最小连接数与最大连接数相差很大:那么最先连接请求将会获利,之后超过最小连接数量的连接请求等价于建立一个新的数据库连接.不过,
 * 这些大于最小连接数的数据库连接在使用完不会马上被释放,他将被放到连接池中等待重复使用或是空间超时后被释放. 2.2、编写数据库连接池
 * 
 * 编写连接池需实现java.sql.DataSource接口。DataSource接口中定义了两个重载的getConnection方法：
 * 
 * Connection getConnection() Connection getConnection(String username, String
 * password) 实现DataSource接口，并实现连接池功能的步骤：
 * 
 * 在DataSource构造函数中批量创建与数据库的连接，并把创建的连接加入LinkedList对象中。
 * 实现getConnection方法，让getConnection方法每次调用时，从LinkedList中取一个Connection返回给用户。
 * 当用户使用完Connection，调用Connection.close()方法时，Collection对象应保证将自己返回到LinkedList中,
 * 而不要把conn还给数据库。Collection保证将自己返回到LinkedList中是此处编程的难点。
 * 
 * @ClassName: JdbcPool
 * @Description:编写数据库连接池
 * @author: 孤傲苍狼
 * @date: 2014-9-30 下午11:07:23
 *
 */
public class JdbcPool implements DataSource {

	/**
	 * @Field: listConnections 使用LinkedList集合来存放数据库链接，
	 *         由于要频繁读写List集合，所以这里使用LinkedList存储数据库连接比较合适
	 */
	private static LinkedList<Connection> listConnections = new LinkedList<Connection>();

	static {
		// 在静态代码块中加载db.properties数据库配置文件
		InputStream in = JdbcPool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			// 数据库连接池的初始化连接数大小
			int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
			// 加载数据库驱动
			Class.forName(driver);
			for (int i = 0; i < jdbcPoolInitSize; i++) {
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("获取到了链接" + conn);
				// 将获取到的数据库连接加入到listConnections集合中，listConnections集合此时就是一个存放了数据库连接的连接池
				listConnections.add(conn);
			}

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * 获取数据库连接
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		// 如果数据库连接池中的连接对象的个数大于0
		if (listConnections.size() > 0) {
			// 从listConnections集合中获取一个数据库连接
			final Connection conn = listConnections.removeFirst();
			System.out.println("listConnections数据库连接池大小是" + listConnections.size());
			// 返回Connection对象的代理对象
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if (!method.getName().equals("close")) {
								return method.invoke(conn, args);
							} else {
								// 如果调用的是Connection对象的close方法，就把conn还给数据库连接池
								listConnections.add(conn);
								System.out.println(conn + "被还给listConnections数据库连接池了！！");
								System.out.println("listConnections数据库连接池大小为" + listConnections.size());
								return null;
							}
						}
					});
		} else {
			throw new RuntimeException("对不起，数据库忙");
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {

		return null;
	}
}