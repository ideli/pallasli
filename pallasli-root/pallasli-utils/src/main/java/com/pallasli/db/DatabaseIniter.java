package com.pallasli.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.pallasli.db.bean.Component;
import com.pallasli.designer.sys.ConvertUtilsExtend;
import com.pallasli.designer.sys.FileUtils;

public class DatabaseIniter {
	// 根据SQL脚本初始化数据库。
	@Test
	public void initDatabase() {
		ConvertUtilsExtend.init();
		List<String> sqlList = FileUtils.loadSqlFile("init.sql");
		Connection c = DbOper.getConn();

		try {
			Statement st = c.createStatement();
			for (String sql : sqlList) {
				st.addBatch(sql);
				st.executeUpdate(sql);
				System.out.println(sql);
			}
			// st.executeBatch();
			st.close();

			DbOper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void select() {
		ConvertUtilsExtend.init();
		Connection c = DbOper.getConn();

		try {
			Statement st = c.createStatement();

			ResultSet rs = st.executeQuery("select * from t_component");
			ResultSetMetaData rsmd = rs.getMetaData();
			List<String> tableColumnNames = new ArrayList<String>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				tableColumnNames.add(rsmd.getColumnName(i + 1));
			}
			Class<?> clazz = Component.class;
			List<Component> datas = new ArrayList<Component>();

			if (rs != null) {
				while (rs.next()) {
					Object object = clazz.newInstance();
					for (String columnName : tableColumnNames) {
						BeanUtils.setProperty(object, columnName.toLowerCase(),
								rs.getObject(columnName));
					}
					datas.add((Component) object);
				}
			}
			for (Component ct : datas) {
				System.out.println(ct.getId() + "  " + ct.getCaption());
			}
			st.close();
			DbOper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
