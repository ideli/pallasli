package com.pallasli.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.pallasli.jdbc.column.DoubleColumn;
import com.pallasli.jdbc.column.IntColumn;
import com.pallasli.jdbc.exception.CallDbException;
import com.pallasli.jdbc.exception.UnsupportedTypeException;

public class BasicOperation {

	public BasicOperation(DataSource ds, int databaseType)
			throws CallDbException {
		if (ds == null) {
			throw new CallDbException(
					"BasicOperation: �������������Դ����ΪNULL!");
		}
		this.ds = ds;
		this.databaseType = databaseType;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public void disconnect() throws CallDbException {
		clearParameters();
		try {
			if (cnt != null && !cnt.isClosed()) {
				cnt.close();
				cnt = null;
			}
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	public void closestmt() throws CallDbException {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (this.pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (this.cstmt != null) {
				cstmt.close();
				cstmt = null;
			}
		} catch (Exception e) {
			throw new CallDbException(e.getMessage());
		}
	}

	/**
	 * ��������Ϣ
	 */
	public void clearParameters() throws CallDbException {
		try {
			if (cnt != null && !cnt.isClosed()) {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (cstmt != null) {
					cstmt.close();
					cstmt = null;
				}
			}
			this.sqlParameterExt = null;
		} catch (Exception err) {
			throw new CallDbException("�ر���ݼ�����ʧ�ܣ�" + err.getMessage());
		}
	}

	public boolean connect() throws CallDbException {
		try {
			if (cnt == null || cnt.isClosed()) {
				cnt = ds.getConnection();
			}
		} catch (Exception err) {
			throw new CallDbException("������ݿ����ʧ�ܣ�" + err.getMessage());
		}
		return true;
	}

	public void setSqlParameterExt(SqlParameterExt sqlParameterExt) {
		this.sqlParameterExt = sqlParameterExt;
	}

	/**
	 * ��ѯ
	 * 
	 * @param sqlStatement
	 *            ��ѯ���
	 * @param scroll
	 *            ����
	 * @param rw
	 *            ��д
	 * @return ���
	 * @throws CallDbException
	 */
	public ResultSet query(String sqlStatement, int scroll, int rw)
			throws CallDbException {
		ResultSet rs = null;
		xlog.info("SQL=[" + sqlStatement + "]");
		try {
			this.connect();
			if (sqlParameterExt != null && sqlParameterExt.getSize() > 0) {
				// ʹ��PreparedStatement���������е�sqlParameter
				pstmt = cnt.prepareStatement(sqlStatement, scroll, rw);
				pstmt.clearParameters();
				sqlParameterExt.setBoParameter(pstmt, databaseType);
				xlog.debug("before query");
				rs = pstmt.executeQuery();
				xlog.debug("after query");
			} else {
				// ʹ�ù淶��Statement
				stmt = cnt.createStatement(scroll, rw);
				xlog.debug("before query");
				rs = stmt.executeQuery(sqlStatement);
				xlog.debug("after query");
			}
			// �������ж�����б���
			return rs;
		} catch (Exception err) {
			throw new CallDbException("��ѯ��ݿ����ʧ�ܣ�" + err.getMessage());
		}
	}

	/**
	 * 
	 * @param sqlStatement
	 * @param scroll
	 * @param rw
	 * @param pagesize
	 *            ÿҳ��¼��
	 * @param pagenum
	 *            ���ѯ�ڼ�ҳ
	 * @return
	 * @throws CallDbException
	 */
	public ResultSet query(String sqlStatement, int scroll, int rw,
			int pagesize, int pagenum) throws CallDbException {
		if (pagesize != 0 && pagenum > 0) {
			int minrn; // ���ѯҳ��һ���������ѯ��¼�����Ӧ������
			int maxrn; // ���ѯҳ������������ѯ��¼�����Ӧ������
			try {
				minrn = pagesize * (pagenum - 1);
				maxrn = pagesize * pagenum;

				switch (this.databaseType) {
				case BasicOperation.DB_TYPE_ORACLE:
					sqlStatement = "select * from (select rownum rn,a.* from (select * from ("
							+ sqlStatement
							+ ") where rownum<="
							+ maxrn
							+ ") a) b where b.rn>" + minrn;
					break;
				case BasicOperation.DB_TYPE_INFORMIX:
					throw new CallDbException("��֧�ֵ���ݿ�����!");

				case BasicOperation.DB_TYPE_MSSQLSERVER:
					throw new CallDbException("��֧�ֵ���ݿ�����!");

				case BasicOperation.DB_TYPE_DB2:
					// as400
					if (((GjjmxDataSource) ds).getDriverClassName().indexOf(
							"as400") > 0) {
						sqlStatement = "select * from (" + sqlStatement
								+ ") a where rrn(a)>" + minrn + " and rrn(a)<="
								+ maxrn;
					} else// db2udb
					{
						sqlStatement = "select * from (select rownumber() over()  rn,a.* from ("
								+ sqlStatement
								+ ") a) b "
								+ "where b.rn >"
								+ minrn + " and b.rn<=" + maxrn;
					}
					break;
				case BasicOperation.DB_TYPE_SYBASE:
					sqlStatement = "select rn=identity(12), a.* into #tmp_table from ("
							+ sqlStatement
							+ ") a "
							+ "select * from #tmp_table where rn >"
							+ minrn
							+ " and rn<=" + maxrn;
					break;
				default:
					throw new CallDbException("δ�������ݿ�����!");
				}
			} catch (Exception e) {
				throw new CallDbException(e.getMessage());
			}
		}

		return query(sqlStatement, scroll, rw);
	}

	private ResultSet getSummary(String sqlStatement, int pagesize, int pagenum)
			throws Exception {
		StringBuffer params = new StringBuffer();
		ResultSet rs = null;
		String paramstr = "";
		if (pagesize != 0 && pagenum > 0) {
			if (!hjparameter.trim().equals("")) {
				String[] hjparams = hjparameter.split(",");
				params.append(",");
				for (int i = 0; i < hjparams.length; i++) {
					String param = "sum(" + hjparams[i] + ") " + hjparams[i];
					params.append(param).append(",");
				}
				paramstr = params.substring(0, params.length() - 1);
			}
			String sqlStatement1 = sqlStatement;
			if (sqlStatement.indexOf("order") != -1) {
				sqlStatement1 = sqlStatement1.substring(0,
						sqlStatement1.indexOf("order"));
			}
			rs = query("select count(*) cnt" + paramstr + " from ("
					+ sqlStatement1 + ")", ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			if (!rs.next()) {
				throw new CallDbException("��ѯ�ܼ�¼�����!");
			}
		}
		return rs;
	}

	public List<Row> queryToList(String sqlStatement, int pagesize, int pagenum)
			throws CallDbException {
		xlog.debug("Enter BasicOperation.queryToList()");
		ResultSet rs = query(sqlStatement, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY, pagesize, pagenum);
		try {
			List<Row> list = toList(rs);
			int count = 0;
			ResultSet rs1 = getSummary(sqlStatement, pagesize, pagenum);
			if (rs1 != null) {
				count = rs1.getInt("cnt"); // �ܼ�¼
				int pages = count / pagesize; // ��ҳ��
				if (count % pagesize != 0) {
					pages += 1;
				}
				if (pagenum > pages) {
					pagenum = pages;
				}
				xlog.debug("�ܼ�¼��" + count + "����ҳ��" + pages);
			}
			if (list.size() > 0) {
				Row row = list.get(0);
				row.addColumns(new Column[] { new IntColumn("count", count) },
						new int[] { Types.INTEGER });
				if (!hjparameter.trim().equals("")) {
					String[] hjparams = hjparameter.split(",");
					for (int i = 0; i < hjparams.length; i++) {
						String param = hjparams[i];
						double paramvalue = 0;
						if (rs1 != null) {
							paramvalue = rs1.getDouble(param);
						}
						row.addColumns(new Column[] { new DoubleColumn(param
								+ "hj", paramvalue) },
								new int[] { Types.DOUBLE });
					}
				}
			}
			return list;
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	/**
	 * ��ѯ�Ƿ��м�¼
	 * 
	 * @param sqlStatement
	 * @return true-�н��
	 * @throws CallDbException
	 */
	public boolean queryResult(String sqlStatement) throws CallDbException {
		ResultSet rs = query(sqlStatement, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	/**
	 * ��ѯ
	 * 
	 * @param sqlStatement
	 *            ��ѯ���
	 * @param scroll
	 *            ����
	 * @param rw
	 *            ��д
	 * @return ���
	 * @throws CallDbException
	 */
	public ResultSet query(String sqlStatement) throws CallDbException {
		return query(sqlStatement, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
	}

	public ResultSet queryToScroll(String sqlStatement) throws CallDbException {
		return query(sqlStatement, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
	}

	public List<Row> queryToList(String sqlStatement) throws CallDbException {
		xlog.debug("Enter BasicOperation.queryToList()");
		ResultSet rs = query(sqlStatement);
		try {
			return toList(rs);
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	public int update(String sqlStatement) throws CallDbException {
		int noOfRows = 0;

		xlog.info("SQL=[" + sqlStatement + "]");
		try {
			this.connect();
			if (sqlParameterExt != null && sqlParameterExt.getSize() > 0) {
				// Use a PreparedStatement and set all values
				pstmt = cnt.prepareStatement(sqlStatement);
				pstmt.clearParameters();
				sqlParameterExt.setBoParameter(pstmt, databaseType);
				noOfRows = pstmt.executeUpdate();
			} else {
				// Use a regular Statement
				stmt = cnt.createStatement();
				noOfRows = stmt.executeUpdate(sqlStatement);
			}
			return noOfRows;
		} catch (Exception err) {
			throw new CallDbException("������ݿ����ʧ�ܣ�" + err.getMessage());
		}
	}

	public boolean execute(String sqlStatement) throws CallDbException {
		xlog.info("SQL=[" + sqlStatement + "]");
		boolean ret = false;
		try {
			this.connect();
			if (sqlParameterExt != null && sqlParameterExt.getSize() > 0) {
				// Use a PreparedStatement and set all values
				pstmt = cnt.prepareStatement(sqlStatement);
				pstmt.clearParameters();
				sqlParameterExt.setBoParameter(pstmt, databaseType);
				ret = pstmt.execute();
			} else {
				// Use a regular Statement
				stmt = cnt.createStatement();
				ret = stmt.execute(sqlStatement);
			}
			return ret;
		} catch (Exception err) {
			throw new CallDbException("������ݿ����ʧ�ܣ�" + err.getMessage());
		}
	}

	/**
	 * ����в�����ͬʱ����setSqlParameter��setSqlParameterType
	 * 
	 * @throws SQLException
	 */
	public void executeProcedure(String sqlStatement) throws CallDbException {
		exeProcedure(sqlStatement);
		try {
			if (sqlParameterExt != null) {
				sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
			}
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ�洢����ʧ�ܣ�" + err.getMessage());
		}
	}

	public List<Row> queryProcedureToList(String sqlStatement, String tableName)
			throws CallDbException {
		return queryProcedureToList(sqlStatement, tableName, null);
	}

	public List<Row> queryProcedureToList(String sqlStatement,
			String tableName, String queryStatement) throws CallDbException {
		ResultSet rs = queryProcedure(sqlStatement, tableName, queryStatement);
		try {
			List<Row> ret = toList(rs);
			sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
			switch (this.databaseType) {
			case BasicOperation.DB_TYPE_ORACLE:
				if (tableName != null && !tableName.trim().equals("")) {
					this.sqlParameterExt = null;
					execute("truncate table " + tableName); // ɾ����ʱ��
				} else {
					throw new CallDbException("δ�������ݿ����!");
				}
				break;
			case BasicOperation.DB_TYPE_INFORMIX:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_MSSQLSERVER:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_DB2:
				break;
			case BasicOperation.DB_TYPE_SYBASE:
				break;
			default:
				throw new CallDbException("δ�������ݿ�����!");
			}
			return ret;
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	public List<Row> queryProcedureToList(String sqlStatement,
			String tableName, String queryStatement, int pagesize, int pagenum)
			throws CallDbException {
		List<Row> ret = null;
		try {
			ret = queryProcedure(sqlStatement, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY, tableName, queryStatement,
					pagesize, pagenum);
			sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
			switch (this.databaseType) {
			case BasicOperation.DB_TYPE_ORACLE:
				if (tableName != null && !tableName.trim().equals("")) {
					this.sqlParameterExt = null;
					execute("truncate table " + tableName); // ɾ����ʱ��
				} else {
					throw new CallDbException("δ�������ݿ����!");
				}
				break;
			case BasicOperation.DB_TYPE_INFORMIX:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_MSSQLSERVER:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_DB2:
				break;
			case BasicOperation.DB_TYPE_SYBASE:
				break;
			default:
				throw new CallDbException("δ�������ݿ�����!");
			}
			return ret;
		} catch (Exception err) {
			throw new CallDbException(err.getMessage());
		}
	}

	public List<List<Row>> queryToMoreList(String[] sqlStatementArray)
			throws CallDbException {
		List<List<Row>> ret = new ArrayList<List<Row>>();
		try {
			for (int i = 0; i < sqlStatementArray.length; i++) {
				ret.add(queryToList(sqlStatementArray[i]));
			}
			return ret;
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ��ѯ����ʧ�ܣ�" + err.getMessage());
		}
	}

	public List<List<Row>> queryProcedureToMoreList(String sqlStatement,
			String[] tableNameArray) throws CallDbException {
		return queryProcedureToMoreList(sqlStatement, tableNameArray, null);
	}

	public List<List<Row>> queryProcedureToMoreList(String sqlStatement,
			String[] tableNameArray, String[] queryStatementArray)
			throws CallDbException {
		List<List<Row>> ret = new ArrayList<List<Row>>();
		ResultSet rs = null;
		String s = "";
		try {
			switch (this.databaseType) {
			case BasicOperation.DB_TYPE_ORACLE:
				if (tableNameArray != null && tableNameArray.length > 0) {
					executeProcedure(sqlStatement);
					this.sqlParameterExt = null;
					for (int i = 0; i < tableNameArray.length; i++) {
						if (queryStatementArray != null
								&& queryStatementArray.length > 0) {
							s = queryStatementArray[i];
							ret.add(queryToList(s));
						} else {
							s = "select * from " + tableNameArray[i];
							ret.add(queryToList(s));
						}
						execute("truncate table " + tableNameArray[i]); // ɾ����ʱ��
					}
					return ret;
				} else {
					throw new CallDbException("δ�������ݿ����!");
				}
			case BasicOperation.DB_TYPE_INFORMIX:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_MSSQLSERVER:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_DB2:
			case BasicOperation.DB_TYPE_SYBASE:

				// rs = queryProcedure(sqlStatement,
				// ResultSet.TYPE_FORWARD_ONLY,
				// ResultSet.CONCUR_READ_ONLY);
				// ret.add(toList(rs));
				// while (cstmt.getMoreResults()) {
				// rs = cstmt.getResultSet();
				// ret.add(toList(rs));
				// }
				exeProcedure(sqlStatement);
				int rowCount = -1;
				boolean b = false;
				while (true) {
					rowCount = cstmt.getUpdateCount();
					if (!b && rowCount >= 0) { // ���Ǹ��¼���
						b = cstmt.getMoreResults();
						continue;
					}
					rs = cstmt.getResultSet();
					if (rs != null) {
						ret.add(toList(rs));
						b = cstmt.getMoreResults();
						continue;
					}
					break; // û��������
				}
				break;
			default:
				throw new CallDbException("δ�������ݿ�����!");
			}
			sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
			return ret;
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ�洢����ʧ�ܣ�" + err.getMessage());
		}
	}

	/**
	 * �ӽ����ȡ��������ݷ���һ���ж�����б�
	 * 
	 * @param rs
	 *            the ResultSet
	 * @return a List of Row objects
	 * @exception SQLException
	 *                , thrown by the JDBC API calls
	 * @exception UnsupportedTypeException
	 *                , if the returned value doesn't match any Value subclass
	 */
	public List<Row> toList(ResultSet rs) throws SQLException,
			UnsupportedTypeException {
		xlog.debug("Enter BasicOperation.toList()");
		List<Row> rows = new ArrayList<Row>();
		while (rs != null && rs.next()) {
			Row row = new Row(rs);
			rows.add(row);
		}
		// rs.close();
		xlog.debug("BasicOperation.toList return rows=[" + rows.size() + "]");
		return rows;
	}

	/** ��ݿ����� */
	public static final int DB_TYPE_ORACLE = 0;
	public static final int DB_TYPE_INFORMIX = 1;
	public static final int DB_TYPE_MSSQLSERVER = 2;
	public static final int DB_TYPE_DB2 = 3;
	public static final int DB_TYPE_SYBASE = 4;

	// --------------------------------------------------------------------------
	/** JDBC�������Դ */
	private DataSource ds = null;
	/** JDBC���� */
	private Connection cnt = null;
	/** ��ݿ����� */
	protected int databaseType = DB_TYPE_SYBASE;
	/** SQL���� */
	private SqlParameterExt sqlParameterExt = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private String hjparameter = "";
	private static org.apache.log4j.Logger xlog = org.apache.log4j.Logger
			.getLogger(ShareData.LOG_NAME);

	/**
	 * ����в�����ͬʱ����setSqlParameter��setSqlParameterType
	 * 
	 * @throws SQLException
	 */
	private void exeProcedure(String sqlStatement) throws CallDbException {
		String s = "";
		try {
			this.connect();
			s = "{call " + clearParamName(sqlStatement.trim()) + "}";
			cstmt = cnt.prepareCall(s);
			xlog.info("SQL=[" + s + "]");
			if (sqlParameterExt != null && sqlParameterExt.getSize() > 0) {
				cstmt.clearParameters();
				sqlParameterExt.setBoParameterAndType(cstmt, databaseType);
			}
			cstmt.execute();
			// cstmt.close();
			// this.disconnect();
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ�洢����ʧ�ܣ�" + err.getMessage());
		}
	}

	private String clearParamName(String sqlStatement) {
		String tmp = sqlStatement.replaceAll("[pP][0-9]*,", ",");
		tmp = tmp.replaceAll("[pP][0-9]*[)]", ")");
		return tmp;
	}

	private ResultSet queryProcedure(String sqlStatement, int scroll, int rw)
			throws CallDbException {
		ResultSet rs = null;
		String s = "";
		try {
			this.connect();
			s = "{call " + clearParamName(sqlStatement.trim()) + "}";
			cstmt = cnt.prepareCall(s, scroll, rw);
			xlog.info("SQL=[" + s + "]");
			if (sqlParameterExt != null && sqlParameterExt.getSize() > 0) {
				cstmt.clearParameters();
				sqlParameterExt.setBoParameterAndType(cstmt, databaseType);
			}
			rs = cstmt.executeQuery();
			return rs;
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ�洢����ʧ�ܣ�" + err.getMessage());
		}
	}

	private ResultSet queryProcedure(String sqlStatement, int scroll, int rw,
			String tableName, String queryStatement) throws CallDbException {
		String s = "";
		switch (this.databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			if ((tableName != null && !tableName.trim().equals(""))
					|| (queryStatement != null && !queryStatement.trim()
							.equals(""))) {
				executeProcedure(sqlStatement);
				if (queryStatement != null && !queryStatement.trim().equals("")) {
					s = queryStatement;
				} else {
					s = "select * from " + tableName;
				}
				SqlParameterExt tmpSpx = this.sqlParameterExt; // �������
				this.sqlParameterExt = null;
				ResultSet rs = query(s, scroll, rw);
				this.sqlParameterExt = tmpSpx;
				return rs;
			} else {
				throw new CallDbException("δ�������ݿ����!");
			}
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("δʵ�ֵ���ݿ�����!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("δʵ�ֵ���ݿ�����!");
		case BasicOperation.DB_TYPE_DB2:
		case BasicOperation.DB_TYPE_SYBASE:
			return queryProcedure(sqlStatement, scroll, rw);
		default:
			throw new CallDbException("δ�������ݿ�����!");
		}
	}

	private List<Row> queryProcedure(String sqlStatement, int scroll, int rw,
			String tableName, String queryStatement, int pagesize, int pagenum)
			throws CallDbException {
		String s = "";
		List<Row> ret = null;
		try {
			switch (this.databaseType) {
			case BasicOperation.DB_TYPE_ORACLE:
				if ((tableName != null && !tableName.trim().equals(""))
						|| (queryStatement != null && !queryStatement.trim()
								.equals(""))) {
					executeProcedure(sqlStatement);
					if (queryStatement != null
							&& !queryStatement.trim().equals("")) {
						s = queryStatement;
					} else {
						s = "select * from " + tableName;
					}
					SqlParameterExt tmpSpx = this.sqlParameterExt; // �������
					this.sqlParameterExt = null;
					ResultSet rs = null;
					ResultSet rs1 = null;
					int count = 0;
					if (pagesize != 0 && pagenum > 0) {
						rs = query(s, scroll, rw, pagesize, pagenum);
						rs1 = getSummary(queryStatement, pagesize, pagenum);
						if (rs1 != null) {
							count = rs1.getInt("cnt"); // �ܼ�¼
							int pages = count / pagesize; // ��ҳ��
							if (count % pagesize != 0) {
								pages += 1;
							}
							xlog.debug("�ܼ�¼��" + count + "����ҳ��" + pages);
						}
					} else {
						rs = query(s, scroll, rw);
					}
					ret = toList(rs);
					if (ret.size() > 0) {
						Row row = ret.get(0);
						row.addColumns(new Column[] { new IntColumn("count",
								count) }, new int[] { Types.INTEGER });
						if (!hjparameter.trim().equals("")) {
							String[] hjparams = hjparameter.split(",");
							for (int i = 0; i < hjparams.length; i++) {
								String param = hjparams[i];
								double paramvalue = 0;
								if (rs1 != null) {
									paramvalue = rs1.getDouble(param);
								}
								row.addColumns(new Column[] { new DoubleColumn(
										param + "hj", paramvalue) },
										new int[] { Types.DOUBLE });
							}
						}
					}
					this.sqlParameterExt = tmpSpx;
					return ret;
				} else {
					throw new CallDbException("δ�������ݿ����!");
				}
			case BasicOperation.DB_TYPE_INFORMIX:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_MSSQLSERVER:
				throw new CallDbException("δʵ�ֵ���ݿ�����!");
			case BasicOperation.DB_TYPE_DB2:
			case BasicOperation.DB_TYPE_SYBASE:
			default:
				throw new CallDbException("δ�������ݿ�����!");
			}
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ�洢����ʧ�ܣ�" + err.getMessage());
		}
	}

	private ResultSet queryProcedure(String sqlStatement, String tableName,
			String queryStatement) throws CallDbException {
		return queryProcedure(sqlStatement, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY, tableName, queryStatement);
	}

	@SuppressWarnings("unused")
	private ResultSet queryProcedureToScroll(String sqlStatement,
			String tableName, String queryStatement) throws CallDbException {
		return queryProcedure(sqlStatement, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY, tableName, queryStatement);
	}

	@SuppressWarnings("unused")
	private List<List<Row>> queryProcedureToMoreList(String sqlStatement)
			throws CallDbException {
		ResultSet rs = null;
		String s = "";
		try {
			this.connect();
			s = "{call " + clearParamName(sqlStatement.trim()) + "}";
			cstmt = cnt.prepareCall(s);
			xlog.info("SQL=[" + s + "]");
			if (sqlParameterExt != null && sqlParameterExt.getSize() > 0) {
				cstmt.clearParameters();
				sqlParameterExt.setBoParameterAndType(cstmt, databaseType);
			}
			rs = cstmt.executeQuery();
			List<List<Row>> ret = new ArrayList<List<Row>>();
			ret.add(toList(rs));
			while (cstmt.getMoreResults()) {
				rs = cstmt.getResultSet();
				ret.add(toList(rs));
			}
			sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
			return ret;
		} catch (Exception err) {
			throw new CallDbException("ִ����ݿ�洢����ʧ�ܣ�" + err.getMessage());
		}
	}

	public void setHjParameter(String parameter) throws CallDbException {
		xlog.info("SQL=[" + parameter + "]");
		hjparameter = parameter;
	}

}
