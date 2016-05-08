package com.pallasli.website.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 * @author keyboardsun mail: keyboardsun@163.com
 * @site http://www.chinacsharp.net
 * @首先我们要实现一个工厂类，用于处理验证，创建对象等方法
 */
public class SecObjectFactory implements PoolableObjectFactory {

	// private ConnectionGetter connectionGetter = new ConnectionGetter();
	// private String CONNECTION_NAME="Sec";
	/**
	 * 这里，我们在使用对象的时候，需要首先激活这个对象，但是在多线程情况下， 这个对象已经被别的线程借去用了，那我们就要再建立一个对象。
	 */
	@Override
	public void activateObject(Object arg0) throws Exception {
		System.out.println("doit");
	}

	/**
	 * 这里我们销毁这个对象。不用这个对象了，这个方法得在业务使用过程中主动调用。 在调用完这个方法过后，对象已经被销毁，但是在Object
	 * pool里面还是存在这个对象的 只是这个对象是null而已。所以在调用完destroyObject过后，要记得把pool清空一下。
	 */
	@Override
	public void destroyObject(Object arg) throws Exception {
		if (((Connection) arg).isClosed() != true) {
			((Connection) arg).close();
		}
	}

	/**
	 * 这里，在业务中第一次借对象的时候，这里要初始化一个，如果初始的都被借了，那就要继续初始 这里创建的对象将被存到pool里面
	 */
	@Override
	public Object makeObject() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.30.150:1521/orcl", "wasys3_cs",
				"wasoft2010");
		conn.setAutoCommit(false);

		return conn;
	}

	/**
	 * 这里，在我们使用完每个对象过后，要做的就是归还，在归还后就要调用这个方法。 简单的说，在还款凭证上面签个大名
	 */
	@Override
	public void passivateObject(Object arg0) throws Exception {
	}

	/**
	 * 这个方法是验证对象，这里我们不做处理，这里在借对象和还对象的时候都要验证下的。
	 */
	@Override
	public boolean validateObject(Object arg) {
		try {
			if (((Connection) arg).isClosed() == true) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
