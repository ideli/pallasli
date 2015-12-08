package com.pallasli.jdbc;

import java.util.List;

import javax.sql.DataSource;

import com.pallasli.jdbc.exception.CallDbException;

public class DbMethod {
	// --------------------------------------------------------------------------

	private static DbMethod dbMethod = null;
	private final DbSingleton dbSingleton;
	private boolean bDbtrace = false;
	private DataSource tempDs = null;
	private int tempDbType = BasicOperation.DB_TYPE_SYBASE;

	private static org.apache.log4j.Logger xlog = org.apache.log4j.Logger
			.getLogger(ShareData.LOG_NAME);

	// --------------------------------------------------------------------------
	private DbMethod() {
		this.tempDs = null;
		dbSingleton = DbSingleton.makeDbSingleton();
	}

	public DbMethod(DataSource tempDs, int tempDbType) throws CallDbException {
		this.tempDs = tempDs;
		this.tempDbType = tempDbType;
		dbSingleton = DbSingleton.makeDbSingleton();
		DbSingleton.setInitLog();
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public static DbMethod makeDbSingleton() {
		if (dbMethod == null) {
			dbMethod = new DbMethod();
		}
		return dbMethod;
	}

	public void setDataSource(DataSource ds, int databaseType)
			throws CallDbException {
		dbSingleton.setDataSource(ds);
		dbSingleton.setDatabaseType(databaseType);
		// dbSingleton.setInitLog();
	}

	public void Execute(Class<?> objClass, String procedureName, Object obj)
			throws CallDbException {
		xlog.debug("====================");
		xlog.debug("Entry Execute(" + procedureName + ", "
				+ (obj == null ? "null" : obj.getClass().getName()) + ", "
				+ ")");
		/***************** begindbtrace ***********************/
		boolean bDbtrace = Constant.bDbtrace;

		/************************** enddbtrace ********************/
		DbFactory df = dbSingleton.getDbFactory();
		AbstractDbInterface di = df.getDbInterface(objClass, procedureName);
		di.setDatabaseType(dbSingleton.getDatabaseType());
		di.Execute(procedureName, obj, tempDs, tempDbType);
		/************************ begindbtrace ************************/

		/********************* enddbtrace ***********************/
		xlog.debug("Return Execute");
	}

	public List<Row> Open(Class<?> objClass, String procedureName, Object obj)
			throws CallDbException {
		xlog.debug("Entry Open(" + procedureName + ", "
				+ (obj == null ? "null" : obj.getClass().getName()) + ", "
				+ ")");
		/***************** begindbtrace ***********************/

		/************************** enddbtrace ********************/
		DbFactory df = dbSingleton.getDbFactory();
		AbstractDbInterface di = df.getDbInterface(objClass, procedureName);
		di.setDatabaseType(dbSingleton.getDatabaseType());
		List<Row> ret = di.Open(procedureName, obj, tempDs, tempDbType);
		/************************ begindbtrace ************************/

		/********************* enddbtrace ***********************/

		xlog.debug("Return Open");
		return ret;
	}

	public List<Row> Open(Class<?> objClass, String procedureName, Object obj,
			int pagesize, int pagenum) throws CallDbException {
		xlog.debug("====================");
		xlog.debug("Entry Open(" + procedureName + ", "
				+ (obj == null ? "null" : obj.getClass().getName()) + ", "
				+ ", " + pagesize + ", " + pagenum + ")");
		/***************** begindbtrace ***********************/

		/************************** enddbtrace ********************/

		DbFactory df = dbSingleton.getDbFactory();
		AbstractDbInterface di = df.getDbInterface(objClass, procedureName);
		di.setDatabaseType(dbSingleton.getDatabaseType());
		List<Row> ret = di.Open(procedureName, obj, tempDs, tempDbType,
				pagesize, pagenum);
		/************************ begindbtrace ************************/

		/********************* enddbtrace ***********************/
		xlog.debug("Return Open");
		return ret;
	}

	public int getDatabaseType() {
		return dbSingleton.getDatabaseType();
	}

	public DataSource getDataSource() {
		return dbSingleton.getDataSource();
	}

	public boolean isBDbtrace() {
		return bDbtrace;
	}

	public void setBDbtrace(boolean dbtrace) {
		bDbtrace = dbtrace;
	}

	class DataSourceInfo {
		String id;
		DataSource ds;
		int DbType;
	}
}
