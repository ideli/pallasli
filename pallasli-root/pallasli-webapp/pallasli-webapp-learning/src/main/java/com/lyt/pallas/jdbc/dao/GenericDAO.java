package com.lyt.pallas.jdbc.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lyt.pallas.basic.util.XMLUtil;
import com.pallasli.utils.StringUtils;

public class GenericDAO {
	String driverClassName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.24.78.42:1521:lyt";
	String username = "lyt";
	String password = "lyt";

	public <T extends Object> List<T> findAll(Class<T> cls) {
		List<T> list = new ArrayList<T>();

		String tableName = new XMLUtil().getTableMappingFromXML(cls);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ "sql/sqlmaps/" + tableName + "_SqlMap.xml";
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rt = null;
			rt = pstmt.executeQuery();
			while (rt.next()) {

				T obj = cls.newInstance();

				NodeList nodeList = document.getElementsByTagName("result");
				for (int i = 0; i < nodeList.getLength(); i++) {

					Element selElement = (Element) nodeList.item(i);
					String columnName = selElement.getAttribute("column");
					String propertyName = selElement.getAttribute("property");
					Field f = cls.getDeclaredField(propertyName);
					Class<?> type = f.getType();
					Method m = cls.getDeclaredMethod(
							StringUtils.addPreSet(propertyName),
							new Class[] { type });
					if (type.equals(String.class)) {
						m.invoke(obj, new Object[] { rt.getObject(columnName) });
					} else {
						if (type.equals(Integer.class)) {
							m.invoke(obj,
									new Object[] { rt.getInt(columnName) });
						} else {
							if (type.equals(Double.class)) {
								m.invoke(
										obj,
										new Object[] { rt.getDouble(columnName) });
							} else {
								if (type.equals(Boolean.class)) {
									m.invoke(obj, new Object[] { rt
											.getBoolean(columnName) });
								} else {
									if (type.equals(Long.class)) {
										m.invoke(obj, new Object[] { rt
												.getLong(columnName) });
									} else {
										if (type.equals(BigDecimal.class)) {
											m.invoke(
													obj,
													new Object[] { rt
															.getBigDecimal(columnName) });
										}
									}

								}
							}

						}
					}
				}
				list.add(obj);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public <T extends Object> List<T> findAll(Class<T> cls, int start, int limit) {
		List<T> list = new ArrayList<T>();

		String tableName = new XMLUtil().getTableMappingFromXML(cls);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ "sql/sqlmaps/" + tableName + "_SqlMap.xml";
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "SELECT M.* FROM (SELECT T.*,ROWNUM ROWNM FROM "
					+ tableName
					+ " T ORDER BY T.ID DESC)M WHERE ROWNM BETWEEN ? AND ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start + 1);
			pstmt.setInt(2, start + limit);

			ResultSet rt = null;
			rt = pstmt.executeQuery();
			while (rt.next()) {

				T obj = cls.newInstance();

				NodeList nodeList = document.getElementsByTagName("result");
				for (int i = 0; i < nodeList.getLength(); i++) {

					Element selElement = (Element) nodeList.item(i);
					String columnName = selElement.getAttribute("column");
					String propertyName = selElement.getAttribute("property");
					Field f = cls.getDeclaredField(propertyName);
					Class<?> type = f.getType();
					Method m = cls.getDeclaredMethod(
							StringUtils.addPreSet(propertyName),
							new Class[] { type });
					if (type.equals(String.class)) {
						m.invoke(obj, new Object[] { rt.getObject(columnName) });
					} else {
						if (type.equals(Integer.class)) {
							m.invoke(obj,
									new Object[] { rt.getInt(columnName) });
						} else {
							if (type.equals(Double.class)) {
								m.invoke(
										obj,
										new Object[] { rt.getDouble(columnName) });
							} else {
								if (type.equals(Boolean.class)) {
									m.invoke(obj, new Object[] { rt
											.getBoolean(columnName) });
								} else {
									if (type.equals(Long.class)) {
										m.invoke(obj, new Object[] { rt
												.getLong(columnName) });
									} else {
										if (type.equals(BigDecimal.class)) {
											m.invoke(
													obj,
													new Object[] { rt
															.getBigDecimal(columnName) });
										}
									}

								}
							}

						}
					}
				}
				list.add(obj);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public <T extends Object> Integer count(Class<T> cls) {
		Integer count = 0;
		String tableName = new XMLUtil().getTableMappingFromXML(cls);

		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "SELECT COUNT(T.id) COUNTNUM FROM " + tableName
					+ " T ";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rt = null;
			rt = pstmt.executeQuery();
			while (rt.next()) {
				count = rt.getInt("COUNTNUM");

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return count;
	}

	public <T extends Object> boolean save(T obj) {
		boolean rtn = false;

		String tableName = new XMLUtil().getTableMappingFromXML(obj.getClass());

		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username,
					password);

			Class cls = obj.getClass();
			Field[] fields = cls.getDeclaredFields();
			Method[] methods = cls.getDeclaredMethods();
			String[][] params = new String[fields.length][2];
			Object id = null;
			for (int i = 0, j = 0; i < methods.length; i++) {
				Method m = methods[i];
				if (m.getName().equals("getId")) {
					id = m.invoke(obj, new Object[] {});
					params[j][0] = StringUtils.removePreGetOrSet(m.getName());
					if (id == null) {
						params[j++][1] = "null";
					} else {
						params[j++][1] = id.toString();
					}
				} else {
					if (m.getName().startsWith("get")) {

						Object param = m.invoke(obj, new Object[] {});

						params[j][0] = StringUtils.removePreGetOrSet(m
								.getName());
						if (param == null) {
							params[j++][1] = "null";
						} else {
							params[j++][1] = param.toString();
						}
					}
				}
			}

			String sqlinsert = "insert into " + tableName
					+ "( username,password,chinese_name,id) values (?,?,?,?)";
			String sqlupdate = "update " + tableName
					+ " set username=? , password=?,chinese_name=? where id=?";

			PreparedStatement pstmt = null;
			if (id == null) {

				String sql = "SELECT MAX(T.id) MAXID FROM " + tableName + " T ";
				pstmt = conn.prepareStatement(sql);
				ResultSet rt = null;
				rt = pstmt.executeQuery();
				while (rt.next()) {
					int maxid = rt.getInt("MAXID");
					id = ++maxid;
				}

				pstmt = conn.prepareStatement(sqlinsert);
			} else {

				pstmt = conn.prepareStatement(sqlupdate);
			}
			pstmt.setLong(4, Long.parseLong(id.toString()));
			for (int i = 0; i < params.length; i++) {
				String name = params[i][0];
				if (name != null) {
					if (name.equals("username")) {

						pstmt.setString(1, params[i][1]);
					}
					if (name.equals("password")) {

						pstmt.setString(2, params[i][1]);
					}
					if (name.equals("chineseName")) {

						pstmt.setString(3, params[i][1]);
					}
				}
			}

			rtn = pstmt.execute();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn;
	}

	public <T extends Object> boolean delete(T obj) {
		boolean rtn = false;

		String tableName = new XMLUtil().getTableMappingFromXML(obj.getClass());

		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username,
					password);

			Class cls = obj.getClass();
			Method[] methods = cls.getDeclaredMethods();
			Object id = null;
			for (int i = 0, j = 0; i < methods.length; i++) {
				Method m = methods[i];
				if (m.getName().equals("getId")) {
					id = m.invoke(obj, new Object[] {});
				}
			}

			String sql = "delete from " + tableName + " where id=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			long pkId = Long.parseLong(String.valueOf(id));
			pstmt.setLong(1, pkId);

			rtn = pstmt.execute();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtn;
	}
}
