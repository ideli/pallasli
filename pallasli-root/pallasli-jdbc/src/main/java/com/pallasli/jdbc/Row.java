package com.pallasli.jdbc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

import com.pallasli.jdbc.column.BigDecimalColumn;
import com.pallasli.jdbc.column.BooleanColumn;
import com.pallasli.jdbc.column.ByteArrayColumn;
import com.pallasli.jdbc.column.DoubleColumn;
import com.pallasli.jdbc.column.FloatColumn;
import com.pallasli.jdbc.column.IntColumn;
import com.pallasli.jdbc.column.LongColumn;
import com.pallasli.jdbc.column.ShortColumn;
import com.pallasli.jdbc.column.StringColumn;
import com.pallasli.jdbc.column.TimestampColumn;
import com.pallasli.jdbc.exception.NoSuchColumnException;
import com.pallasli.jdbc.exception.UnsupportedConversionException;
import com.pallasli.jdbc.exception.UnsupportedTypeException;
import com.pallasli.utils.DateUtils;

public class Row {
	private Column[] columns;
	private int[] columnType;

	// private int databaseType = ShareData.DB_TYPE_SYBASE;

	/**
	 * 
	 * @param rs
	 *            the ResultSet
	 * @exception SQLException
	 *                , if thrown by any JDBC API call
	 */
	public Row(Column[] cols, int[] coltype) {
		this.columns = cols;
		this.columnType = coltype;
	}

	public Row(ResultSet rs) throws SQLException, UnsupportedTypeException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		columns = new Column[cols];
		columnType = new int[cols];

		for (int i = 1; i <= cols; i++) {
			int type = rsmd.getColumnType(i);
			String colname = rsmd.getColumnName(i);

			switch (type) {
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				columns[i - 1] = new TimestampColumn(colname,
						rs.getTimestamp(colname));
				columnType[i - 1] = Types.TIMESTAMP;
				break;
			case Types.REAL:
			case Types.FLOAT:
				columns[i - 1] = new FloatColumn(colname, rs.getFloat(colname));
				columnType[i - 1] = Types.FLOAT;
				break;
			case Types.DOUBLE:
				columns[i - 1] = new DoubleColumn(colname,
						rs.getDouble(colname));
				columnType[i - 1] = Types.DOUBLE;
				break;
			case Types.TINYINT:
			case Types.SMALLINT:
				columns[i - 1] = new ShortColumn(colname, rs.getShort(colname));
				columnType[i - 1] = Types.SMALLINT;
				break;
			case Types.INTEGER:
				columns[i - 1] = new IntColumn(colname, rs.getInt(colname));
				columnType[i - 1] = Types.INTEGER;
				break;
			case Types.BIGINT:
				columns[i - 1] = new LongColumn(colname, rs.getLong(colname));
				columnType[i - 1] = Types.BIGINT;
				break;
			case Types.DECIMAL:
			case Types.NUMERIC:
				columns[i - 1] = new BigDecimalColumn(colname,
						rs.getBigDecimal(colname));// , rsmd.getScale(i)));
				columnType[i - 1] = Types.NUMERIC;
				break;
			case Types.CHAR:
			case Types.LONGVARCHAR:
			case Types.VARCHAR:
			case Types.CLOB:
				columns[i - 1] = new StringColumn(colname,
						rs.getString(colname));
				columnType[i - 1] = Types.CHAR;
				break;
			case Types.BIT:
			case Types.BOOLEAN:
				columns[i - 1] = new BooleanColumn(colname,
						rs.getBoolean(colname));
				columnType[i - 1] = Types.BOOLEAN;
				break;
			case Types.BLOB:
			case Types.LONGVARBINARY:
				columns[i - 1] = new ByteArrayColumn(colname,
						rs.getBytes(colname));
				columnType[i - 1] = Types.BLOB;
				break;
			default:
				throw new UnsupportedTypeException("��֧�ֵ�SQL�������: " + type);
			}
			columns[i - 1].setNull(rs.wasNull());
		}
	}

	/**
	 * ��������
	 */
	public int getColumnCount() {
		return columns.length;
	}

	/**
	 * ���ذ��е�����
	 */
	public Column[] getColumns() {
		return columns;
	}

	public int[] getColumnTypes() {
		return columnType;
	}

	public String getColumnName(int columnIndex) throws NoSuchColumnException {
		if (columnIndex < 0 || columnIndex > columns.length)
			throw new NoSuchColumnException(String.valueOf(columnIndex));

		return columns[columnIndex - 1].getName();
	}

	public int getColumnIndex(String columnName) throws NoSuchColumnException {
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].getName().equalsIgnoreCase(columnName))
				return i + 1;
		}
		return -1;
	}

	public BigDecimal getBigDecimal(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getBigDecimal();
	}

	public BigDecimal getBigDecimal(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getBigDecimal(getIndex(columnName));
	}

	public boolean getBoolean(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getBoolean();
	}

	public boolean getBoolean(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getBoolean(getIndex(columnName));
	}

	public byte getByte(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getByte();
	}

	public byte getByte(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getByte(getIndex(columnName));
	}

	public byte[] getBytes(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getBytes();
	}

	public byte[] getBytes(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getBytes(getIndex(columnName));
	}

	public Date getDate(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getDate();
	}

	public Date getDate(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDate(getIndex(columnName));
	}

	public double getDouble(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getDouble();
	}

	public double getDouble(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDouble(getIndex(columnName));
	}

	public float getFloat(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getFloat();
	}

	public float getFloat(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getFloat(getIndex(columnName));
	}

	public int getInt(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getInt();
	}

	public int getInt(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getInt(getIndex(columnName));
	}

	public long getLong(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getLong();
	}

	public long getLong(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getLong(getIndex(columnName));
	}

	public Object getObject(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getObject();
	}

	public Object getObject(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getObject(getIndex(columnName));
	}

	public short getShort(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getShort();
	}

	public short getShort(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getShort(getIndex(columnName));
	}

	public String getString(int columnIndex) throws NoSuchColumnException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getString();
	}

	public String getString(String columnName) throws NoSuchColumnException {
		return getString(getIndex(columnName));
	}

	public Time getTime(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getTime();
	}

	public Time getTime(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getTime(getIndex(columnName));
	}

	public Timestamp getTimestamp(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.getTimestamp();
	}

	public Timestamp getTimestamp(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getTimestamp(getIndex(columnName));
	}

	public boolean isNull(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return col.isNull();
	}

	public boolean isNull(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return this.isNull(getIndex(columnName));
	}

	/**
	 * ����ָ�����������
	 */
	private int getIndex(String columnName) throws NoSuchColumnException {
		for (int i = 0; i < columns.length; i++) {
			Column col = columns[i];
			if (col.getName().equalsIgnoreCase(columnName)) {
				// Adjust to 1 based indexed
				return i + 1;
			}
		}
		throw new NoSuchColumnException(columnName);
	}

	public int getColumnType(int columnIndex) {
		return columnType[columnIndex - 1];
	}

	public int getColumnType(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return columnType[getIndex(columnName) - 1];
	}

	public double getDefDouble(int columnIndex, double value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		double ret = 0D;
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {
				if (this.columnType[columnIndex - 1] == Types.DOUBLE) {
					ret = col.getDouble();

				} else if (this.columnType[columnIndex - 1] == Types.NUMERIC
						|| this.columnType[columnIndex - 1] == Types.DECIMAL) {
					ret = col.getBigDecimal().doubleValue();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret;
	}

	public double getDefDouble(String columnName, double value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDouble(getIndex(columnName), value);
	}

	public double getDefDouble(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDoubleOrDefault(columnIndex, 0D);
	}

	public double getDefDouble(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefDouble(getIndex(columnName), 0D);
	}

	public String getDefString(int columnIndex, String value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		String ret = "";
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {
				ret = col.getString();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret.replaceAll("\"", "\"\"");
	}

	public String getDefString(String columnName, String value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefString(getIndex(columnName), value);
	}

	public String getDefString(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefString(columnIndex, "");
	}

	public String getDefString(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefString(getIndex(columnName), "");
	}

	public String getTrimString(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefString(columnIndex, "").trim();
	}

	public String getTrimString(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefString(getIndex(columnName), "").trim();
	}

	public long getDefLong(int columnIndex, long value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		long ret = 0;
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {
				if (this.columnType[columnIndex - 1] == Types.NUMERIC) {
					ret = col.getBigDecimal().longValue();
				} else if (this.columnType[columnIndex - 1] == Types.BIGINT) {
					ret = col.getLong();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret;
	}

	public long getDefLong(String columnName, long value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefLong(getIndex(columnName), value);
	}

	public long getDefLong(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefLong(columnIndex, 0);
	}

	public long getDefLong(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefLong(getIndex(columnName), 0);
	}

	public int getDefInt(int columnIndex, int value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		int ret = 0;
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {
				if (this.columnType[columnIndex - 1] == Types.INTEGER) {
					ret = col.getInt();
				} else if (this.columnType[columnIndex - 1] == Types.NUMERIC) {
					ret = col.getBigDecimal().intValue();
				} else if (this.columnType[columnIndex - 1] == Types.SMALLINT) {
					ret = col.getShort();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret;
	}

	public int getDefInt(String columnName, int value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefInt(getIndex(columnName), value);
	}

	public int getDefInt(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefInt(columnIndex, 0);
	}

	public int getDefInt(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefInt(getIndex(columnName), 0);
	}

	public short getDefShort(int columnIndex, short value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		short ret = (short) 0;
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {
				if (this.columnType[columnIndex - 1] == Types.SMALLINT) {
					ret = col.getShort();
				} else if (this.columnType[columnIndex - 1] == Types.NUMERIC) {
					ret = (short) col.getBigDecimal().intValue();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret;
	}

	public short getDefShort(String columnName, short value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefShort(getIndex(columnName), value);
	}

	public short getDefShort(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefShort(columnIndex, (short) 0);
	}

	public short getDefShort(String columnName) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefShort(getIndex(columnName), (short) 0);
	}

	public java.util.Date getDefDate(int columnIndex, java.util.Date value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		java.util.Date ret = null;
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {
				if (this.columnType[columnIndex - 1] == Types.TIMESTAMP) {
					ret = new java.util.Date(col.getTimestamp().getTime());
				} else if (this.columnType[columnIndex - 1] == Types.DATE) {
					ret = new java.util.Date(col.getDate().getTime());
				} else if (this.columnType[columnIndex - 1] == Types.TIME) {
					ret = new java.util.Date(col.getTime().getTime());
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret;
	}

	public java.util.Date getDefDate(String columnName, java.util.Date value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDate(getIndex(columnName), value);
	}

	public java.util.Date getDefDate(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDate(columnIndex, new java.util.Date());
	}

	public java.util.Date getDefDate(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDate(getIndex(columnName), new java.util.Date());
	}

	public java.util.Date getDefNullDate(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDate(columnIndex, null);
	}

	public java.util.Date getDefNullDate(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDate(getIndex(columnName), null);
	}

	/**
	 * �������ΪNULL������""
	 * 
	 * @param columnIndex
	 * @return
	 * @throws NoSuchColumnException
	 * @throws UnsupportedConversionException
	 */
	public String getDateToString(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		java.util.Date ret = getDefDate(columnIndex, null);
		if (ret == null) {
			return "";
		} else {
			return DateUtils.formatDate(ret, "yyyy-MM-dd");
		}
	}

	public String getDateToString(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDateToString(getIndex(columnName));
	}

	/**
	 * �������ΪNULL������""
	 * 
	 * @param columnIndex
	 * @return
	 * @throws NoSuchColumnException
	 * @throws UnsupportedConversionException
	 */
	public String getDateTimeToString(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		java.util.Date ret = getDefDate(columnIndex, null);
		if (ret == null) {
			return "";
		} else {
			return DateUtils.formatDate(ret, "yyyy-MM-dd HH:mm:ss");
		}
	}

	public String getDateTimeToString(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDateTimeToString(getIndex(columnName));
	}

	/**
	 * �������ΪNULL�����ص�ǰ����
	 * 
	 * @param columnIndex
	 * @return
	 * @throws NoSuchColumnException
	 * @throws UnsupportedConversionException
	 */
	public String getDefDateToString(int columnIndex)
			throws NoSuchColumnException, UnsupportedConversionException {
		return DateUtils.formatDate(getDefDate(columnIndex), "yyyy-MM-dd");
	}

	public String getDefDateToString(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDateToString(getIndex(columnName));
	}

	public boolean getDefBoolean(int columnIndex, boolean value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		boolean ret = false;
		try {
			col = columns[columnIndex - 1];
			if (col.isNull()) {
				ret = value;
			} else {

				if (this.columnType[columnIndex - 1] == Types.BIT
						|| this.columnType[columnIndex - 1] == Types.BOOLEAN) {
					ret = col.getBoolean();
				} else if (this.columnType[columnIndex - 1] == Types.NUMERIC
						|| this.columnType[columnIndex - 1] == Types.DECIMAL) {
					ret = (col.getBigDecimal().intValue() > 0) ? true : false;
				} else if (this.columnType[columnIndex - 1] == Types.SMALLINT) {
					ret = col.getShort() > 0 ? true : false;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
		return ret;
	}

	public boolean getDefBoolean(String columnName, boolean value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefBoolean(getIndex(columnName), value);
	}

	public boolean getDefBoolean(int columnIndex) throws NoSuchColumnException,
			UnsupportedConversionException {
		return getDefBoolean(columnIndex, false);
	}

	public boolean getDefBoolean(String columnName)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefBoolean(getIndex(columnName), false);
	}

	/**
	 * @deprecated ֻΪ����ԭ�ӿ�ʹ�ã�����ʹ�����з���
	 * @see getDefDouble(int columnIndex, double value)
	 * */
	@Deprecated
	public double getDoubleOrDefault(int columnIndex, double value)
			throws NoSuchColumnException, UnsupportedConversionException {
		return getDefDouble(columnIndex, value);
	}

	public void setDouble(int columnIndex, double value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
			if (this.columnType[columnIndex - 1] == Types.DOUBLE) {
				col.setDouble(value);
			} else if (this.columnType[columnIndex - 1] == Types.NUMERIC
					|| this.columnType[columnIndex - 1] == Types.DECIMAL) {
				col.setBigDecimal(new BigDecimal(value));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
	}

	public void setDouble(String columnName, double value)
			throws NoSuchColumnException, UnsupportedConversionException {
		setDouble(getIndex(columnName), value);
	}

	public void setBigDecimal(int columnIndex, BigDecimal value)
			throws NoSuchColumnException, UnsupportedConversionException {
		Column col = null;
		try {
			col = columns[columnIndex - 1];
			col.setBigDecimal(value);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchColumnException(String.valueOf(columnIndex));
		}
	}

	public void setBigDecimal(String columnName, BigDecimal value)
			throws NoSuchColumnException, UnsupportedConversionException {
		setBigDecimal(getIndex(columnName), value);
	}

	// --------
	// ��������
	public int getColNum() {
		return this.columns.length;
	}

	public void addColumns(Column[] cols, int[] colsType) {
		int columnsSize = columns.length;
		int colsSize = cols.length;
		// log.debug("columnsSize = " + columnsSize + ", colsSize=" + colsSize);

		Column[] newColumns = new Column[columnsSize + colsSize];
		int[] newColumnType = new int[columnsSize + colsSize];
		int i = 0;
		for (i = 0; i < columnsSize; i++) {
			newColumns[i] = columns[i];
			newColumnType[i] = columnType[i];
		}
		// log.debug("i = " + i);
		for (int j = 0; j < colsSize; j++) {
			newColumns[i + j] = cols[j];
			newColumnType[i + j] = colsType[j];
		}
		this.columns = newColumns;
		this.columnType = newColumnType;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Column col : columns) {
			sb.append(col.getString()).append(",");
		}
		sb.setCharAt(sb.length() - 1, ' ');
		return sb.toString();
	}
}
