/*
 * ������ݿ�Ψһ��
 */
package com.pallasli.jdbc;

import javax.sql.DataSource;

import lyt.soft.calldb.CallDbException;
import lyt.soft.calldb.ShareData;

/**
 * <p>
 * Title: ������ݿ�Ψһ��
 * </p>
 * <p>
 * Description: ������ݿ�Ψһ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: wasoft
 * </p>
 * 
 * @version 1.0
 */

public class DbSingleton {
	
	public String toString() {
		return getClass().getName();
	}

	public static DbSingleton makeDbSingleton() {
		if (dbSingleton == null) {
			dbSingleton = new DbSingleton();
		}
		return dbSingleton;
	}

	/**
	 * ��ʼ����־����
	 */
	public static void setInitLog() throws CallDbException {
		try {
			if (initCount == 0) {
				org.apache.log4j.PropertyConfigurator
						.configure(ShareData.LOG_CFG_FILENAME);
				xlog = org.apache.log4j.Logger.getLogger(ShareData.LOG_NAME);
				initCount++;
			}
		} catch (Exception err) {
			throw new CallDbException("ϵͳ��ʼ��ʧ��[DbSingleton.setInitLog()]\n"
					+ err.getMessage());
		}
	}

	/** �õ��������ݿ����� */
	public lyt.soft.calldb.BasicOperation getGjjDb() throws CallDbException {
		if (this.ds == null) {
			throw new CallDbException("�������ݿ������������Ϣδ����!");
		} else {
			return new lyt.soft.calldb.BasicOperation(ds, databaseType);
		}
	}

	/** �õ��������ݿ����� */
	public lyt.soft.calldb.BasicOperation getTempDb(DataSource ds,
			int databaseType) throws CallDbException {
		return new lyt.soft.calldb.BasicOperation(ds, databaseType);
	}

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public DataSource getDataSource() {
		return this.ds;
	}

	public int getDatabaseType() {
		return this.databaseType;
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	public DbFactory getDbFactory() {
		if (dbFactory == null) {
			dbFactory = new DbFactory();
		}
		return dbFactory;
	}

	// --------------------------------------------------------------------------
	private DbSingleton() {
	}

	private static DbSingleton dbSingleton = null;

	/** ��ݿ⹤�� */
	private DbFactory dbFactory = null;

	/** ���Դ */
	private DataSource ds = null;
	/** ��ݿ����� */
	private int databaseType = lyt.soft.calldb.BasicOperation.DB_TYPE_SYBASE;
	/** ��ʱ���Դ */
	private final DataSource tempDs = null;
	private final int tempDbType = lyt.soft.calldb.BasicOperation.DB_TYPE_SYBASE;

	private static org.apache.log4j.Logger xlog = null;
	private static int initCount = 0;

	// --------------------------------------------------------------------------
	public static void main(String[] args) {
		DbSingleton dbSingleton1 = DbSingleton.makeDbSingleton();
		dbSingleton1.getDataSource();
	}

}
