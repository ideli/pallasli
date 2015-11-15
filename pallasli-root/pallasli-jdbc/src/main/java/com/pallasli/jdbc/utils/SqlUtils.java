package com.pallasli.jdbc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pallasli.jdbc.Row;
import com.pallasli.jdbc.exception.NoSuchColumnException;
import com.pallasli.jdbc.exception.UnsupportedConversionException;

public class SqlUtils {
	public static String like = " like ";
	public static String gaOrEq = " >= ";
	public static String leOrEq = " <= ";
	public static String ga = " > ";
	public static String le = " < ";
	public static String eq = " = ";

	private static final Log log = LogFactory.getLog(SqlUtils.class);

	public static enum ParaClass {
		str, num, date
	}

	/**
	 * k ����where k1=v1 and k2=v2
	 * 
	 * @param sql
	 * @param k
	 * @param v
	 * @param op
	 * @param paraClass
	 */
	public static void addWhereOrAndPara(StringBuffer sql, String k, String v,
			String op, ParaClass paraClass) {
		// log.info("------setup sql paramete addWhereOrAndPara-------- k is "+k+"\tv is "+v)
		// ;
		if (v != null && !v.equals("")) {
			if (sql.toString().indexOf("where") > 0) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			// to_char(s.zcrq,'yyyy-mm-dd')
			if (paraClass == ParaClass.date) {
				sql.append("to_char(");
				sql.append(k);
				sql.append(",'yyyy-mm-dd')");
			} else {
				sql.append(k);
			}

			sql.append(op);

			if (op.trim().equals("like")) {
				sql.append("'%");
				sql.append(v);
				sql.append("%'");
			} else {
				if (paraClass == ParaClass.str)
					sql.append("'" + v + "'");
				if (paraClass == ParaClass.num)
					sql.append(v);
				if (paraClass == ParaClass.date)
					sql.append("'" + v.substring(0, 10) + "'");
			}
		}
		// else{
		// log.info("else "+"k is "+k+"\tv is "+v) ;
		// }

	}

	/**
	 * ����Ϣת��һ�����͵�ʵ����� ʵ������������Ҫ��������ͬ������ִ�Сд
	 * Map<String1,String2> String1
	 * �����Ͷ��������:����ĸ��д��String2��sql�����select a as b ...��as
	 * �������������������������ͬ������ָ��
	 */
	public static Object rowToObj(Row row, Object obj,
			Map<String, String> otherNmae) {
		// log.info("[ SqlToo.rowToObj row.colunm num is "+row.getColumnCount()+" obj class is "+obj.getClass()+"]")
		// ;
		Class c = obj.getClass();
		Method[] ms = c.getMethods();
		HashMap<String, Method> setM = new HashMap<String, Method>();
		for (int i = 0; i < ms.length; i++) {
			String name = ms[i].getName();
			if (name.startsWith("set")) {
				setM.put(name.substring(3), ms[i]);
			}
		}
		Set<Entry<String, Method>> mTs = setM.entrySet();
		Iterator<Entry<String, Method>> its = mTs.iterator();
		Entry<String, Method> te = null;
		try {
			for (int i = 0; its.hasNext(); i++) {
				Entry<String, Method> e = its.next();
				te = e;
				Class pc = e.getValue().getParameterTypes()[0];
				// log.info("++++["+i+"]++++++"+"colunm mane is "+e.getKey()+"\tmethod is "+e.getValue().getName()+"\tparemeter class is "+pc.getName()+"++++++++++")
				// ;
				if (pc.getName().equals(String.class.getName())) {
					try {
						e.getValue().invoke(obj,
								new Object[] { row.getString(e.getKey()) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue().invoke(
									obj,
									new Object[] { row.getString(otherNmae
											.get(e.getKey())) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Double.class.getName())) {

					try {
						e.getValue().invoke(
								obj,
								new Object[] { Double.parseDouble((row
										.getString(e.getKey()))) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue()
									.invoke(obj,
											new Object[] { Double.parseDouble((row
													.getString(otherNmae.get(e
															.getKey())))) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(BigDecimal.class.getName())) {

					try {
						e.getValue().invoke(obj,
								new Object[] { row.getBigDecimal(e.getKey()) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue().invoke(
									obj,
									new Object[] { row.getBigDecimal(otherNmae
											.get(e.getKey())) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Byte.class.getName())) {

					try {
						e.getValue().invoke(
								obj,
								new Object[] { Byte.parseByte((row.getString(e
										.getKey()))) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue()
									.invoke(obj,
											new Object[] { Byte.parseByte((row
													.getString(otherNmae.get(e
															.getKey())))) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Date.class.getName())) {
					try {
						e.getValue().invoke(
								obj,
								new Object[] { row.getDateTimeToString(e
										.getKey()) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue().invoke(
									obj,
									new Object[] { row
											.getDateTimeToString(otherNmae
													.get(e.getKey())) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Boolean.class.getName())) {
					try {
						e.getValue().invoke(obj,
								new Object[] { row.getBoolean(e.getKey()) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue().invoke(
									obj,
									new Object[] { row.getBoolean(otherNmae
											.get(e.getKey())) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Integer.class.getName())) {

					try {
						e.getValue().invoke(
								obj,
								new Object[] { Integer.parseInt(row.getString(e
										.getKey())) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue()
									.invoke(obj,
											new Object[] { Integer.parseInt(row
													.getString(otherNmae.get(e
															.getKey()))) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Long.class.getName())) {

					try {
						e.getValue().invoke(
								obj,
								new Object[] { Long.parseLong((row.getString(e
										.getKey()))) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue()
									.invoke(obj,
											new Object[] { Long.parseLong(row
													.getString(otherNmae.get(e
															.getKey()))) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Short.class.getName())) {
					try {
						e.getValue().invoke(
								obj,
								new Object[] { Short.parseShort((row
										.getString(e.getKey()))) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue()
									.invoke(obj,
											new Object[] { Short.parseShort((row
													.getString(otherNmae.get(e
															.getKey())))) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Time.class.getName())) {
					try {
						e.getValue().invoke(obj,
								new Object[] { row.getTime(e.getKey()) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue().invoke(
									obj,
									new Object[] { row.getTime(otherNmae.get(e
											.getKey())) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
				if (pc.getName().equals(Object.class.getName())) {
					try {
						e.getValue().invoke(obj,
								new Object[] { row.getObject(e.getKey()) });
					} catch (NoSuchColumnException e1) {
						if (otherNmae.get(e.getKey()) != null)
							e.getValue().invoke(
									obj,
									new Object[] { row.getObject(otherNmae
											.get(e.getKey())) });
						else {
							// log.info("not find colunm \t"+e.getKey()) ;
						}
					}
					continue;
				}
			}
		} catch (IllegalArgumentException e) {
			try {
				log.info("\nobj class is " + obj.getClass()
						+ "\nmethod name is " + te.getValue().getName()
						+ "\n paramete is " + row.getString(te.getKey()));
			} catch (NoSuchColumnException e1) {
			}
		} catch (IllegalAccessException e) {
			try {
				log.info("\nobj class is " + obj.getClass()
						+ "\nmethod name is " + te.getValue().getName()
						+ "\n paramete is " + row.getString(te.getKey()));
			} catch (NoSuchColumnException e1) {
			}
		} catch (InvocationTargetException e) {
			try {
				log.info("\nobj class is " + obj.getClass()
						+ "\nmethod name is " + te.getValue().getName()
						+ "\n paramete is " + row.getString(te.getKey()));
			} catch (NoSuchColumnException e1) {
			}
			e.printStackTrace();
		} catch (UnsupportedConversionException e) {
			try {
				log.info("\nobj class is " + obj.getClass()
						+ "\nmethod name is " + te.getValue().getName()
						+ "\n paramete is " + row.getString(te.getKey()));
			} catch (NoSuchColumnException e1) {
			}
		} catch (NoSuchColumnException e) {
			try {
				log.info("\nobj class is " + obj.getClass()
						+ "\nmethod name is " + te.getValue().getName()
						+ "\n paramete is " + row.getString(te.getKey()));
			} catch (NoSuchColumnException e1) {
			}
		}
		return obj;

	}
}
