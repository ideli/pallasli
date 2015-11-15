package com.pallasli.jdbc;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pallasli.utils.FileUtils;

public class InitEnv {
	private static final Log log = LogFactory.getLog(InitEnv.class);

	public static boolean initServer() {
		return (initApp());
	}

	private static boolean initApp() {
		try {
			if (!FileUtils.createDir(Constant.DATA_PATH)) {
				log.error("������־Ŀ¼���ɹ���");
				return false;
			}
			if (System.getProperty("app.config") != null) {
				Constant.CONGFIG_FILE = Constant.CONFIG_PATH
						+ System.getProperty("app.config");
			}
			if (System.getProperty("app.container") != null) {
				Constant.CONTAINER_FILE = Constant.CONFIG_PATH
						+ System.getProperty("app.container");
			}
			log.debug("���������ļ�");

			Properties prop = com.pallasli.designer.sys.SqlPropUtils
					.getProperties(Constant.CONGFIG_FILE);
			System.out.println("Constant.CONGFIG_FILE=" + Constant.CONGFIG_FILE
					+ "dbtye=" + Integer.parseInt(prop.getProperty("dbtype")));
			Constant.POOL_SIZE = Integer
					.parseInt(prop.getProperty("pool-size"));
			Constant.LogCounter = Integer.parseInt(prop
					.getProperty("log-counter"));
			Constant.WebServicePort = Integer.parseInt(prop
					.getProperty("webservice-port"));
			Constant.bDbtrace = Boolean.parseBoolean(prop
					.getProperty("dblog-trace"));
			log.debug("��ʼ�����Դ...");

			int dbtype = Integer.parseInt(prop.getProperty("dbtype"));

			String driver = prop.getProperty("jdbc-driver");
			String dburl = prop.getProperty("jdbc-url");
			String dbuser = prop.getProperty("jdbc-user");
			String dbpassword = prop.getProperty("jdbc-password");

			DataSource ds = new GjjmxDataSource(driver, dburl, dbuser,
					dbpassword, prop.getProperty("datasource-initialsize"),
					prop.getProperty("datasource-maxactive"),
					prop.getProperty("datasource-maxidle"),
					prop.getProperty("datasource-minidle"),
					prop.getProperty("datasource-maxwait"),
					prop.getProperty("datasource-removeabandoned"),
					prop.getProperty("datasource-removeabandonedtimeout"),
					prop.getProperty("datasource-logabandoned"),
					prop.getProperty("datasource-testonborrow"));

			DbMethod dm = DbMethod.makeDbSingleton();
			dm.setDataSource(ds, dbtype);

			log.debug("��ʼ�����Դ���");

			return true;
		} catch (Exception e) {
			log.error("��ʼ��Ӧ�û�������: " + e.getMessage());
			return false;
		}
	}

}
