package com.pallasli.jdbc;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import lyt.soft.Constant;
import lyt.soft.calldb.business.AbstractDbInterface;
import lyt.soft.calldb.business.DbFactory;
import lyt.soft.calldb.business.DbSingleton;
import lyt.soft.calldb.sql.Row;
import lyt.soft.model.xtgl.Dbtrace;
import lyt.soft.model.xtgl.User;
import lyt.soft.util.RandomGUID;

/**
 * <p>
 * Title: �ͻ���ݿ���ýӿ�
 * </p>
 * <p>
 * Description: �ͻ���ݿ���ýӿ�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: wasoft
 * </p>
 * 
 * @version 1.0
 */

public class DbMethod {
	// --------------------------------------------------------------------------

	private static DbMethod dbMethod = null;
	private final DbSingleton dbSingleton;
	private boolean bDbtrace = false;
	/** ���Դ���� */
	private DataSource tempDs = null;
	private int tempDbType = BasicOperation.DB_TYPE_SYBASE;

	private static org.apache.log4j.Logger xlog = org.apache.log4j.Logger
			.getLogger(lyt.soft.calldb.ShareData.LOG_NAME);

	// --------------------------------------------------------------------------
	private DbMethod() {
		this.tempDs = null;
		dbSingleton = DbSingleton.makeDbSingleton();
	}

	/**
	 * ��ʼ������
	 * 
	 * @param ds
	 *            ���Դ
	 * @param databaseType
	 * @param path
	 *            root path
	 */
	public DbMethod(DataSource tempDs, int tempDbType) throws CallDbException {
		this.tempDs = tempDs;
		this.tempDbType = tempDbType;
		dbSingleton = DbSingleton.makeDbSingleton();
		DbSingleton.setInitLog();
	}

	
	public String toString() {
		return getClass().getName();
	}

	public static DbMethod makeDbSingleton() {
		if (dbMethod == null) {
			dbMethod = new DbMethod();
		}
		return dbMethod;
	}

	/**
	 * �������Դ����ݿ�����
	 * 
	 * @param ds
	 *            ���Դ
	 * @param databaseType
	 * @param path
	 *            root path
	 */
	public void setDataSource(DataSource ds, int databaseType)
			throws CallDbException {
		dbSingleton.setDataSource(ds);
		dbSingleton.setDatabaseType(databaseType);
		// dbSingleton.setInitLog();
	}

	/**
	 * ִ����ݿ����
	 * 
	 * @param procedureName
	 *            ���ù��Լ����
	 * @param obj
	 *            ����ֵ����
	 */
	public void Execute(Class<?> objClass, String procedureName, Object obj)
			throws CallDbException {
		this.Execute(objClass, procedureName, obj, null);
	}

	/**
	 * ִ����ݿ����
	 * 
	 * @param procedureName
	 *            ���ù��Լ����
	 * @param obj
	 *            ����ֵ����
	 * @throws CallDbException
	 */
	public void Execute(Class<?> objClass, String procedureName, Object obj,
			User user) throws CallDbException {
		xlog.debug("====================");
		xlog.debug("Entry Execute(" + procedureName + ", "
				+ (obj == null ? "null" : obj.getClass().getName()) + ", "
				+ (user == null ? "null" : user.getUserid()) + ")");
		/***************** begindbtrace ***********************/
		boolean bDbtrace = Constant.bDbtrace;
		Dbtrace dbtrace = new Dbtrace((new RandomGUID()).toString());
		if (user != null && bDbtrace) {
			try {
				dbtrace.setUserid(user.getUserid());
				dbtrace.setIp(user.getLoginip());
				dbtrace.setModule_name(user.getModule_name().trim());
				dbtrace.setWebservice_method(user.getWebservice_method());
				dbtrace.setStart_time(new Date());
				Execute(objClass, "Common_BEGINDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("begin dbtrace err:" + e.getMessage());
			}
		}
		/************************** enddbtrace ********************/
		DbFactory df = dbSingleton.getDbFactory();
		AbstractDbInterface di = df.getDbInterface(objClass, procedureName);
		di.setDatabaseType(dbSingleton.getDatabaseType());
		di.Execute(procedureName, obj, user, tempDs, tempDbType);
		/************************ begindbtrace ************************/
		if (user != null && bDbtrace) {
			try {
				dbtrace.setEnd_time(new Date());
				dbtrace.calDuration();
				dbtrace.setRet_count(0);
				dbtrace.setMethod_name(user.getMethod_name().trim().equals("") ? procedureName
						: user.getMethod_name().trim());
				Execute(objClass, "Common_ENDDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("end dbtrace err:" + e.getMessage());
			}
		}
		/********************* enddbtrace ***********************/
		xlog.debug("Return Execute");
	}

	/**
	 * ִ����ݿ�������н��
	 * 
	 * @param procedureName
	 *            ���ù��Լ����
	 * @param obj
	 *            ����ֵ����
	 * @return �������
	 * @throws CallDbException
	 */
	public List<Row> Open(Class<?> objClass, String procedureName, Object obj)
			throws CallDbException {
		return this.Open(objClass, procedureName, obj, null);
	}

	/**
	 * ִ����ݿ�������н��
	 * 
	 * @param procedureName
	 *            ���ù��Լ����
	 * @param obj
	 *            ����ֵ����
	 * @return �������
	 * @throws CallDbException
	 */
	public List<Row> Open(Class<?> objClass, String procedureName, Object obj,
			User user) throws CallDbException {
		xlog.debug("Entry Open(" + procedureName + ", "
				+ (obj == null ? "null" : obj.getClass().getName()) + ", "
				+ (user == null ? "null" : user.getUserid()) + ")");
		/***************** begindbtrace ***********************/
		boolean bDbtrace = Constant.bDbtrace;
		Dbtrace dbtrace = new Dbtrace((new RandomGUID()).toString());
		if (user != null && bDbtrace) {
			try {
				xlog.debug("===============start==========");
				dbtrace.setUserid(user.getUserid());
				dbtrace.setIp(user.getLoginip());
				dbtrace.setModule_name(user.getModule_name().trim());
				dbtrace.setWebservice_method(user.getWebservice_method());
				dbtrace.setStart_time(new Date());
				Execute(objClass, "Common_BEGINDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("begin dbtrace err:" + e.getMessage());
			}
		}
		/************************** enddbtrace ********************/
		DbFactory df = dbSingleton.getDbFactory();
		AbstractDbInterface di = df.getDbInterface(objClass, procedureName);
		di.setDatabaseType(dbSingleton.getDatabaseType());
		List<Row> ret = di.Open(procedureName, obj, user, tempDs, tempDbType);
		/************************ begindbtrace ************************/
		if (user != null && bDbtrace) {
			try {
				xlog.debug("===============end==========");
				dbtrace.setEnd_time(new Date());
				dbtrace.calDuration();
				dbtrace.setRet_count(ret.size());
				dbtrace.setMethod_name(user.getMethod_name().trim().equals("") ? procedureName
						: user.getMethod_name().trim());
				Execute(objClass, "Common_ENDDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("end dbtrace err:" + e.getMessage());
			}
		}
		/********************* enddbtrace ***********************/

		xlog.debug("Return Open");
		return ret;
	}

	public List<Row> Open(Class<?> objClass, String procedureName, Object obj,
			int pagesize, int pagenum) throws CallDbException {
		return this.Open(objClass, procedureName, obj, null, pagesize, pagenum);
	}

	public List<Row> Open(Class<?> objClass, String procedureName, Object obj,
			User user, int pagesize, int pagenum) throws CallDbException {
		xlog.debug("====================");
		xlog.debug("Entry Open(" + procedureName + ", "
				+ (obj == null ? "null" : obj.getClass().getName()) + ", "
				+ (user == null ? "null" : user.getUserid()) + ", " + pagesize
				+ ", " + pagenum + ")");
		/***************** begindbtrace ***********************/
		boolean bDbtrace = Constant.bDbtrace;
		Dbtrace dbtrace = new Dbtrace((new RandomGUID()).toString());
		if (user != null && bDbtrace) {
			try {
				xlog.debug("===============start==========");
				dbtrace.setUserid(user.getUserid());
				dbtrace.setIp(user.getLoginip());
				dbtrace.setModule_name(user.getModule_name().trim());
				dbtrace.setWebservice_method(user.getWebservice_method());
				dbtrace.setStart_time(new Date());
				Execute(objClass, "Common_BEGINDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("begin dbtrace err:" + e.getMessage());
			}
		}
		/************************** enddbtrace ********************/

		DbFactory df = dbSingleton.getDbFactory();
		AbstractDbInterface di = df.getDbInterface(objClass, procedureName);
		di.setDatabaseType(dbSingleton.getDatabaseType());
		List<Row> ret = di.Open(procedureName, obj, user, tempDs, tempDbType,
				pagesize, pagenum);
		/************************ begindbtrace ************************/
		if (user != null && bDbtrace) {
			try {
				xlog.debug("===============end==========");
				dbtrace.setEnd_time(new Date());
				dbtrace.calDuration();
				dbtrace.setRet_count(ret.size());
				dbtrace.setMethod_name(user.getMethod_name().trim().equals("") ? procedureName
						: user.getMethod_name().trim());
				Execute(objClass, "Common_ENDDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("end dbtrace err:" + e.getMessage());
			}
		}
		/********************* enddbtrace ***********************/
		xlog.debug("Return Open");
		return ret;
	}

	/**
	 * �õ���ݿ�����
	 * 
	 * @return
	 */
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
