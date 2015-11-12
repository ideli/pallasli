package com.pallasli.jdbc.utils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtils {
	public static double getDefRsDouble(int columnIndex, ResultSet rs,
			double value) throws SQLException {
		BigDecimal ret = rs.getBigDecimal(columnIndex);
		if (rs.wasNull()) {
			return value;
		} else {
			return ret.doubleValue();
		}
	}

	public static double getDefRsDouble(String columnName, ResultSet rs,
			double value) throws SQLException {
		return getDefRsDouble(rs.findColumn(columnName), rs, value);
	}

	public static double getDefRsDouble(int columnIndex, ResultSet rs)
			throws SQLException {
		return getDefRsDouble(columnIndex, rs, 0D);
	}

	public static double getDefRsDouble(String columnName, ResultSet rs)
			throws SQLException {
		return getDefRsDouble(columnName, rs, 0D);
	}

	public static double getRsDouble(int columnIndex, ResultSet rs)
			throws SQLException {
		BigDecimal ret = rs.getBigDecimal(columnIndex);
		if (rs.wasNull()) {
			throw new SQLException("��ݼ��ֶ�ΪNullֵ!");
		} else {
			return ret.doubleValue();
		}
	}

	public static double getRsDouble(String columnName, ResultSet rs)
			throws SQLException {
		return getRsDouble(rs.findColumn(columnName), rs);
	}

	public static String getDefRsString(int columnIndex, ResultSet rs,
			String value) throws SQLException {
		String ret = rs.getString(columnIndex);
		if (rs.wasNull()) {
			return value;
		} else {
			return ret;
		}
	}

	public static String getDefRsString(String columnName, ResultSet rs,
			String value) throws SQLException {
		return getDefRsString(rs.findColumn(columnName), rs, value);
	}

	public static String getDefRsString(int columnIndex, ResultSet rs)
			throws SQLException {
		return getDefRsString(columnIndex, rs, "");
	}

	public static String getDefRsString(String columnName, ResultSet rs)
			throws SQLException {
		return getDefRsString(columnName, rs, "");
	}

	public static String getTrimRsString(int columnIndex, ResultSet rs)
			throws SQLException {
		return getDefRsString(columnIndex, rs, "").trim();
	}

	public static String getTrimRsString(String columnName, ResultSet rs)
			throws SQLException {
		return getDefRsString(columnName, rs, "").trim();
	}

	public static String getRsString(int columnIndex, ResultSet rs)
			throws SQLException {
		String ret = rs.getString(columnIndex);
		if (rs.wasNull()) {
			throw new SQLException("Nullֵ!");
		} else {
			return ret;
		}
	}

	public static String getRsString(String columnName, ResultSet rs)
			throws SQLException {
		return getRsString(rs.findColumn(columnName), rs);
	}

	public static int locate(int columnIndex, ResultSet rs, String value)
			throws SQLException {
		rs.beforeFirst();
		while (rs.next()) {
			if (getDefRsString(1, rs).trim().equals(value.trim())) {
				return rs.getRow();
			}
		}
		return -1;
	}

	public static int locate(String columnName, ResultSet rs, String value)
			throws SQLException {
		return locate(rs.findColumn(columnName), rs, value);
	}

}
