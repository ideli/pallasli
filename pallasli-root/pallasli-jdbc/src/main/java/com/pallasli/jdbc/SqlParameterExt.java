package com.pallasli.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import com.pallasli.jdbc.exception.UnsupportedConversionException;
import com.pallasli.jdbc.type.BigDecimalValue;
import com.pallasli.jdbc.type.BooleanValue;
import com.pallasli.jdbc.type.ByteArrayValue;
import com.pallasli.jdbc.type.ByteValue;
import com.pallasli.jdbc.type.DateValue;
import com.pallasli.jdbc.type.DoubleValue;
import com.pallasli.jdbc.type.FloatValue;
import com.pallasli.jdbc.type.IntValue;
import com.pallasli.jdbc.type.LongValue;
import com.pallasli.jdbc.type.ObjectValue;
import com.pallasli.jdbc.type.ShortValue;
import com.pallasli.jdbc.type.StringValue;
import com.pallasli.jdbc.type.TimeValue;
import com.pallasli.jdbc.type.TimestampValue;

public class SqlParameterExt {
	@Deprecated
	public SqlParameterExt(int len) {
		this.sqlParameter = new LinkedList<Parameter>();
	}

	public SqlParameterExt() {
		this.sqlParameter = new LinkedList<Parameter>();
	}

	public String toString() {
		return getClass().getName();
	}

	/**
	 * ��������
	 * 
	 * @param v
	 *            �����б�
	 * @param valueType
	 *            ��������
	 * @throws CallDbException
	 */
	public void add(Value v, int valueType) {
		Parameter p = new Parameter(v, valueType);
		this.sqlParameter.add(p);
	}

	/**
	 * �������ӣ�Ĭ�ϲ�������Ϊ��������
	 * 
	 * @param v
	 *            �����б�
	 * @throws CallDbException
	 */
	public void add(Value v) {
		this.add(v, SQL_INPUT);
	}

	/**
	 * �������
	 * 
	 * @param idx
	 *            ����
	 * @param v
	 *            �����б�
	 * @param valueType
	 *            ��������
	 */
	public void add(int index, Value v, int valueType) {
		Parameter p = new Parameter(v, valueType);
		this.sqlParameter.add(index, p);
	}

	/**
	 * �������
	 * 
	 * @param idx
	 *            ����
	 * @param v
	 *            �����б�
	 */
	public void add(int index, Value v) {
		this.add(index, v, SQL_INPUT);
	}

	/**
	 * ����ɾ��Ĭ�ϲ�������Ϊ��������
	 * 
	 * @param v
	 *            �����б�
	 * @throws CallDbException
	 */
	public void remove(int index) {
		index--;
		// assert (index < this.sqlParameter.size()):"��������Ԥ�賤��!";
		this.sqlParameter.remove(index);
	}

	public void set(int index, Value v, int valueType) {
		index--;
		// assert (index < this.sqlParameter.size()):"��������Ԥ�賤��!";
		Parameter p = new Parameter(v, valueType);
		this.sqlParameter.set(index, p);
	}

	public void set(int index, Value v) {
		this.set(index, v, SQL_INPUT);
	}

	public Value get(int index) {
		index--;
		// assert (index < this.sqlParameter.size()):"��������Ԥ�賤��!";
		return ((Parameter) this.sqlParameter.get(index)).getValue();
	}

	public void setType(int index, int valueType) {
		index--;
		// assert (index < this.sqlParameter.size()):"��������Ԥ�賤��!";
		Parameter p = (Parameter) this.sqlParameter.get(index);
		p.setType(valueType);
		this.sqlParameter.set(index, p);
	}

	public int getType(int index) {
		index--;
		// assert (index < this.sqlParameter.size()):"��������Ԥ�賤��!";
		return ((Parameter) this.sqlParameter.get(index)).getType();
	}

	public int getSize() {
		return this.sqlParameter.size();
	}

	/**
	 * Ϊ������value�б��е�ֵ�������PreparedStatement�ϵ�setXXX()����
	 *
	 * @param pstmt
	 *            the PreparedStatement
	 * @param values
	 *            a List with Value objects
	 * @exception SQLException
	 */
	public void setBoParameter(PreparedStatement pstmt, int databaseType)
			throws SQLException {
		try {
			for (int i = 0; i < this.sqlParameter.size(); i++) {
				Value v = ((Parameter) sqlParameter.get(i)).getValue();
				boolean ret = false;
				if (v instanceof ByteArrayValue) {
					xlog.info("parameter " + String.valueOf(i + 1) + "=["
							+ ((ByteArrayValue) v).getBytesCount() + "Bytes]");
				} else {
					xlog.info("parameter " + String.valueOf(i + 1) + "=["
							+ v.getString() + "]");
				}
				// Set the value using the method corresponding to the type.
				// Set methods are indexed from 1, so add 1 to i
				switch (databaseType) {
				case BasicOperation.DB_TYPE_ORACLE:
					ret = setOracleBoParameter(i, pstmt, v);
					break;
				case BasicOperation.DB_TYPE_INFORMIX:
					throw new SQLException("δʵ�ֵ���ݿ�����!");
				case BasicOperation.DB_TYPE_MSSQLSERVER:
					throw new SQLException("δʵ�ֵ���ݿ�����!");
				case BasicOperation.DB_TYPE_DB2:
					ret = setDB2BoParameter(i, pstmt, v);
					break;
				case BasicOperation.DB_TYPE_SYBASE:
					ret = setSybaseBoParameter(i, pstmt, v);
					break;
				default:
					throw new SQLException("δ�������ݿ�����!");
				}
				if (ret) {
					continue;
				}

				if (v instanceof BigDecimalValue) {
					pstmt.setBigDecimal(i + 1, v.getBigDecimal());
				} else if (v instanceof BooleanValue) {
					pstmt.setBoolean(i + 1, v.getBoolean());
				} else if (v instanceof ByteValue) {
					pstmt.setByte(i + 1, v.getByte());
				} else if (v instanceof ByteArrayValue) {
					pstmt.setBytes(i + 1, v.getBytes());
				} else if (v instanceof DateValue) {
					pstmt.setDate(i + 1, v.getDate());
				} else if (v instanceof DoubleValue) {
					pstmt.setDouble(i + 1, v.getDouble());
				} else if (v instanceof FloatValue) {
					pstmt.setFloat(i + 1, v.getFloat());
				} else if (v instanceof IntValue) {
					pstmt.setInt(i + 1, v.getInt());
				} else if (v instanceof LongValue) {
					pstmt.setLong(i + 1, v.getLong());
				} else if (v instanceof ShortValue) {
					pstmt.setShort(i + 1, v.getShort());
				} else if (v instanceof StringValue) {
					pstmt.setString(i + 1, v.getString());
				} else if (v instanceof TimeValue) {
					pstmt.setTime(i + 1, v.getTime());
				} else if (v instanceof TimestampValue) {
					pstmt.setTimestamp(i + 1, v.getTimestamp());
				} else {
					pstmt.setObject(i + 1, v.getObject());
				}
			}
		} catch (UnsupportedConversionException e) {
			xlog.error("�������ת�����ɹ�!", e);
		}
	}

	public void setBoParameterAndType(CallableStatement pstmt, int databaseType)
			throws SQLException {
		setBoParameter(pstmt, databaseType);
		setBoParameterType(pstmt, databaseType);
	}

	public void setBoParameterOutValue(CallableStatement pstmt, int databaseType)
			throws SQLException {
		for (int i = 0; i < this.sqlParameter.size(); i++) {
			Parameter p = (Parameter) sqlParameter.get(i);
			if (p.getType() == SQL_OUTPUT || p.getType() == SQL_INPUT_OUTPUT) {
				Value v = p.getValue();
				Value ret = null;

				switch (databaseType) {
				case BasicOperation.DB_TYPE_ORACLE:
					ret = setOracleBoParameterOutValue(i, pstmt, v);
					break;
				case BasicOperation.DB_TYPE_INFORMIX:
					throw new SQLException("δʵ�ֵ���ݿ�����!");
				case BasicOperation.DB_TYPE_MSSQLSERVER:
					throw new SQLException("δʵ�ֵ���ݿ�����!");
				case BasicOperation.DB_TYPE_DB2:
					ret = setDB2BoParameterOutValue(i, pstmt, v);
					break;
				case BasicOperation.DB_TYPE_SYBASE:
					ret = setSybaseBoParameterOutValue(i, pstmt, v);
					break;
				default:
					throw new SQLException("δ�������ݿ�����!");
				}
				if (ret == null) {
					if (v instanceof BigDecimalValue) {
						v = new BigDecimalValue(pstmt.getBigDecimal(i + 1));
					} else if (v instanceof BooleanValue) {
						v = new BooleanValue(pstmt.getBoolean(i + 1));
					} else if (v instanceof ByteValue) {
						v = new ByteValue(pstmt.getByte(i + 1));
					} else if (v instanceof ByteArrayValue) {
						v = new ByteArrayValue(pstmt.getBytes(i + 1));
					} else if (v instanceof DateValue) {
						v = new DateValue(pstmt.getDate(i + 1));
					} else if (v instanceof DoubleValue) {
						v = new DoubleValue(pstmt.getDouble(i + 1));
					} else if (v instanceof FloatValue) {
						v = new FloatValue(pstmt.getFloat(i + 1));
					} else if (v instanceof IntValue) {
						v = new IntValue(pstmt.getInt(i + 1));
					} else if (v instanceof LongValue) {
						v = new LongValue(pstmt.getLong(i + 1));
					} else if (v instanceof ShortValue) {
						v = new ShortValue(pstmt.getShort(i + 1));
					} else if (v instanceof StringValue) {
						v = new StringValue(pstmt.getString(i + 1));
					} else if (v instanceof TimeValue) {
						v = new TimeValue(pstmt.getTime(i + 1));
					} else if (v instanceof TimestampValue) {
						v = new TimestampValue(pstmt.getTimestamp(i + 1));
					} else {
						v = new ObjectValue(pstmt.getObject(i + 1));
					}
				} else {
					v = ret;
				}
				p.setValue(v);
				sqlParameter.set(i, p);
				xlog.info("out parameter " + String.valueOf(i + 1) + "=["
						+ v.getString() + "]");
			}
		}
	}

	/** SQL����������Ϊ���� */
	public static final int SQL_INPUT = 0;
	/** SQL����������Ϊ��� */
	public static final int SQL_OUTPUT = 1;
	/** SQL����������Ϊ������� */
	public static final int SQL_INPUT_OUTPUT = 2;

	// --------------------------------------------------------------------------
	/**
	 * ����Sybase��ݿ����
	 * 
	 * @param idx
	 *            �����б�����
	 * @param pstmt
	 *            Ԥ����������
	 * @param v
	 *            �Զ������ֵ
	 * @return true-�Ѵ���
	 * @throws SQLException
	 *             , UnsupportedConversionException
	 */
	private boolean setSybaseBoParameter(int idx, PreparedStatement pstmt,
			Value v) throws SQLException, UnsupportedConversionException {
		return false;
	}

	/**
	 * ����Oracle��ݿ����
	 * 
	 * @param idx
	 *            �����б�����
	 * @param pstmt
	 *            Ԥ����������
	 * @param v
	 *            �Զ������ֵ
	 * @return true-�Ѵ���
	 * @throws SQLException
	 *             , UnsupportedConversionException
	 */
	private boolean setOracleBoParameter(int idx, PreparedStatement pstmt,
			Value v) throws SQLException, UnsupportedConversionException {
		if (v instanceof BooleanValue) {
			java.math.BigDecimal value = v.getBoolean() ? java.math.BigDecimal
					.valueOf(1L) : java.math.BigDecimal.valueOf(0L);
			pstmt.setBigDecimal(idx + 1, value);
			return true;
		} else if (v instanceof IntValue) {
			pstmt.setBigDecimal(idx + 1,
					java.math.BigDecimal.valueOf(v.getInt()));
			return true;
		} else if (v instanceof LongValue) {
			pstmt.setBigDecimal(idx + 1,
					java.math.BigDecimal.valueOf(v.getLong()));
			return true;
		} else if (v instanceof ShortValue) {
			pstmt.setBigDecimal(idx + 1,
					java.math.BigDecimal.valueOf(v.getShort()));
			return true;
		} else if (v instanceof StringValue) {
			String s = v.getString();
			pstmt.setString(idx + 1, (s.equals("") ? " " : s));
			return true;
		}
		// else if (v instanceof DateValue) {
		// pstmt.setTimestamp(idx + 1, new Timestamp(v.getDate().getTime()));
		// return true;
		// }
		return false;
	}

	private boolean setDB2BoParameter(int idx, PreparedStatement pstmt, Value v)
			throws SQLException, UnsupportedConversionException {
		return setOracleBoParameter(idx, pstmt, v);
	}

	private void setBoParameterType(CallableStatement pstmt, int databaseType)
			throws SQLException {
		for (int i = 0; i < this.sqlParameter.size(); i++) {
			Parameter p = (Parameter) sqlParameter.get(i);
			if (p.getType() == SQL_OUTPUT || p.getType() == SQL_INPUT_OUTPUT) {
				Value v = p.getValue();
				boolean ret = false;

				switch (databaseType) {
				case BasicOperation.DB_TYPE_ORACLE:
					ret = setOracleBoParameterType(i, pstmt, v);
					break;
				case BasicOperation.DB_TYPE_INFORMIX:
					throw new SQLException("δʵ�ֵ���ݿ�����!");
				case BasicOperation.DB_TYPE_MSSQLSERVER:
					throw new SQLException("δʵ�ֵ���ݿ�����!");
				case BasicOperation.DB_TYPE_DB2:
					ret = setDB2BoParameterType(i, pstmt, v);
					break;
				case BasicOperation.DB_TYPE_SYBASE:
					ret = setSybaseBoParameterType(i, pstmt, v);
					break;
				default:
					throw new SQLException("δ�������ݿ�����!");
				}
				if (ret) {
					continue;
				}

				if (v instanceof BigDecimalValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.DECIMAL);
				} else if (v instanceof BooleanValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.BIT);
				} else if (v instanceof ByteValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.TINYINT);
				} else if (v instanceof ByteArrayValue) {
					pstmt.registerOutParameter(i + 1,
							java.sql.Types.LONGVARBINARY);
				} else if (v instanceof DateValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.DATE);
				} else if (v instanceof DoubleValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.DOUBLE);
				} else if (v instanceof FloatValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.REAL);
				} else if (v instanceof IntValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.INTEGER);
				} else if (v instanceof LongValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.BIGINT);
				} else if (v instanceof ShortValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.SMALLINT);
				} else if (v instanceof StringValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.CHAR);
				} else if (v instanceof TimeValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.TIME);
				} else if (v instanceof TimestampValue) {
					pstmt.registerOutParameter(i + 1, java.sql.Types.TIMESTAMP);
				} else {
					xlog.error("��Ч���������!", new SQLException(
							"��Ч���������!"));
				}
			}
		}
	}

	/**
	 * ���ò���ֵ����������
	 * 
	 * @param idx
	 *            �����б�����
	 * @param pstmt
	 *            Ԥ����������
	 * @param v
	 *            �Զ������ֵ
	 * @return true-�Ѵ���
	 * @throws SQLException
	 */
	private boolean setOracleBoParameterType(int idx, CallableStatement pstmt,
			Value v) throws SQLException {
		if (v instanceof BooleanValue || v instanceof IntValue
				|| v instanceof LongValue || v instanceof ShortValue) {
			pstmt.registerOutParameter(idx + 1, java.sql.Types.NUMERIC);
			return true;
		}
		return false;
	}

	private boolean setDB2BoParameterType(int idx, CallableStatement pstmt,
			Value v) throws SQLException {
		return setOracleBoParameterType(idx, pstmt, v);
	}

	/**
	 * ���ò���ֵ����������
	 * 
	 * @param idx
	 *            �����б�����
	 * @param pstmt
	 *            Ԥ����������
	 * @param v
	 *            �Զ������ֵ
	 * @return true-�Ѵ���
	 * @throws SQLException
	 */
	private boolean setSybaseBoParameterType(int idx, CallableStatement pstmt,
			Value v) throws SQLException {
		return false;
	}

	/**
	 * �õ��������ֵ
	 * 
	 * @param idx
	 *            �����б�����
	 * @param pstmt
	 *            Ԥ����������
	 * @param v
	 *            �Զ������ֵ
	 * @return not null-�Ѵ���
	 * @throws SQLException
	 */
	private Value setSybaseBoParameterOutValue(int idx,
			CallableStatement pstmt, Value v) throws SQLException {
		return null;
	}

	/**
	 * �õ��������ֵ
	 * 
	 * @param idx
	 *            �����б�����
	 * @param pstmt
	 *            Ԥ����������
	 * @param v
	 *            �Զ������ֵ
	 * @return not null-�Ѵ���
	 * @throws SQLException
	 */
	private Value setOracleBoParameterOutValue(int idx,
			CallableStatement pstmt, Value v) throws SQLException {
		if (v instanceof BooleanValue) {
			boolean value = (pstmt.getBigDecimal(idx + 1).intValue() > 0) ? true
					: false;
			return new BooleanValue(value);
		} else if (v instanceof IntValue) {
			return new IntValue(pstmt.getBigDecimal(idx + 1).intValue());
		} else if (v instanceof LongValue) {
			return new LongValue(pstmt.getBigDecimal(idx + 1).longValue());
		} else if (v instanceof ShortValue) {
			return new ShortValue((short) pstmt.getBigDecimal(idx + 1)
					.intValue());
		}

		return null;
	}

	private Value setDB2BoParameterOutValue(int idx, CallableStatement pstmt,
			Value v) throws SQLException {
		return setOracleBoParameterOutValue(idx, pstmt, v);
	}

	private final class Parameter {
		public Parameter(Value value, int type) {
			this.value = value;
			this.type = type;
		}

		public String toString() {
			return getClass().getName();
		}

		public void setValue(Value value) {
			this.value = value;
		}

		public Value getValue() {
			return this.value;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getType() {
			return this.type;
		}

		private Value value;
		private int type;
	}

	/** SQL�����б� */
	private LinkedList<Parameter> sqlParameter = null;

	private static org.apache.log4j.Logger xlog = org.apache.log4j.Logger
			.getLogger(ShareData.LOG_NAME);

	// --------------------------------------------------------------------------
}
