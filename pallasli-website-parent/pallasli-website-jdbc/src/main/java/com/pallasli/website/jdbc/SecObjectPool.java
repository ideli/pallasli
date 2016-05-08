package com.pallasli.website.jdbc;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

/*
 * pool 操作类 
 */
public class SecObjectPool implements ObjectPool {
	private static SecObjectFactory factory = new SecObjectFactory();
	private static ObjectPool pool = null;
	private static GenericObjectPool.Config config = null;

	private static boolean initFlag = false;// 是否初始化

	static {
		if (!initFlag) {
			config = new Config();
			config.maxActive = 2; // 这个属性没啥用
			config.maxIdle = 5; // 最大连接数,就这个有用.
			config.minIdle = 3; // 最少连接数 ,这个也没用,哈哈.
			pool = new GenericObjectPool(factory, config);
			initFlag = true;
		}
	}

	@Override
	public Object borrowObject() throws Exception {
		return pool.borrowObject();
	}

	@Override
	public void returnObject(Object arg0) throws Exception {
		pool.returnObject(arg0);
	}

	@Override
	public void invalidateObject(Object arg0) throws Exception {
		pool.invalidateObject(arg0);
	}

	@Override
	public void addObject() throws Exception {
		pool.addObject();
	}

	@Override
	public int getNumIdle() throws UnsupportedOperationException {
		return pool.getNumIdle();
	}

	@Override
	public int getNumActive() throws UnsupportedOperationException {
		return pool.getNumActive();
	}

	@Override
	public void clear() throws Exception, UnsupportedOperationException {
		pool.clear();

	}

	@Override
	public void close() throws Exception {
		pool.close();

	}

	@Override
	public void setFactory(PoolableObjectFactory arg0) throws IllegalStateException, UnsupportedOperationException {
		pool.setFactory(arg0);
	}

}
