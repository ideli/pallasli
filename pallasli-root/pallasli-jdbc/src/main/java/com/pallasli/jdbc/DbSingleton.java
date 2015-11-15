package com.pallasli.jdbc;

import javax.sql.DataSource;

import com.pallasli.jdbc.exception.CallDbException;

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

	public BasicOperation getGjjDb() throws CallDbException {
		if (this.ds == null) {
			throw new CallDbException("�������ݿ������������Ϣδ����!");
		} else {
			return new BasicOperation(ds, databaseType);
		}
	}

	public BasicOperation getTempDb(DataSource ds, int databaseType)
			throws CallDbException {
		return new BasicOperation(ds, databaseType);
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

	private DbFactory dbFactory = null;

	private DataSource ds = null;
	private int databaseType = BasicOperation.DB_TYPE_SYBASE;
	private final DataSource tempDs = null;
	private final int tempDbType = BasicOperation.DB_TYPE_SYBASE;

	private static org.apache.log4j.Logger xlog = null;
	private static int initCount = 0;

	// --------------------------------------------------------------------------
	public static void main(String[] args) {
		DbSingleton dbSingleton1 = DbSingleton.makeDbSingleton();
		dbSingleton1.getDataSource();
	}

}
