package com.pallasli.jdbc.utils;

import java.sql.Types;
import java.util.List;

import com.pallasli.constant.SystemConstant;
import com.pallasli.jdbc.Row;
import com.pallasli.utils.FileUtils;
import com.pallasli.utils.StringUtils;

public class SqlFileUtils {

	private String data_dir = SystemConstant.DATA_PATH;

	public void produceFile(List<Row> lst, String filename) throws Exception {
		produceFile(lst, filename, "|", true);
	}

	public void produceFile(List<Row> lst, String filename, String separation)
			throws Exception {
		produceFile(lst, filename, separation, true);
	}

	public void produceFile(List<Row> lst, String filename, String separation,
			boolean Upper) throws Exception {
		produceFile(lst, filename, separation, Upper, null, null);
	}

	public void produceFile(List<Row> lst, String filename, String separation,
			boolean Upper, int[] width, String[] mapNames) throws Exception {
		if (Upper)
			filename = filename.toUpperCase();

		if (lst == null) {
			FileUtils.appendFile("", data_dir, filename);
		} else {
			StringBuffer sb = new StringBuffer();

			for (int k = 0, i = 0; i < lst.size(); i++, k++) {
				Row row = lst.get(i);
				if (width != null && (width.length != row.getColumnCount())
						&& mapNames == null) {
					throw new Exception(" ");
				}
				int colCount = mapNames != null ? mapNames.length : row
						.getColumnCount();

				if (k != 0)
					sb.append("\r\n");
				for (int j = 1; j <= colCount; j++) {
					if (width != null) {
						int tmp = j;
						if (mapNames != null) {
							j = row.getColumnIndex(mapNames[j - 1]);
						}
						if (row.getColumnType(j) == Types.DATE
								|| row.getColumnType(j) == Types.TIME
								|| row.getColumnType(j) == Types.TIMESTAMP) {
							sb.append(StringUtils.FillWithChar(
									row.getDateToString(j), width[tmp - 1],
									' ', false)
									+ (j == row.getColumnCount() ? ""
											: separation));
						} else if (row.getColumnType(j) == Types.REAL
								|| row.getColumnType(j) == Types.FLOAT
								|| row.getColumnType(j) == Types.DOUBLE
								|| row.getColumnType(j) == Types.DECIMAL
								|| row.getColumnType(j) == Types.NUMERIC) {
							String value = row.getDefString(j);
							if (value.lastIndexOf(".") == -1) {
								// value += ".00";
							} else if (value.indexOf(".") == value.length() - 2) {
								value += "0";
							}
							sb.append(StringUtils.FillWithChar(value,
									width[tmp - 1], '0', true)
									+ (j == row.getColumnCount() ? ""
											: separation));
						} else if (row.getColumnType(j) == Types.TINYINT
								|| row.getColumnType(j) == Types.SMALLINT
								|| row.getColumnType(j) == Types.INTEGER
								|| row.getColumnType(j) == Types.BIGINT) {
							sb.append(StringUtils.FillWithChar(
									row.getDefString(j), width[tmp - 1], '0',
									true)
									+ (j == row.getColumnCount() ? ""
											: separation));
						} else {
							sb.append(StringUtils.FillWithChar(
									row.getDefString(j), width[tmp - 1], ' ',
									false)
									+ (j == row.getColumnCount() ? ""
											: separation));
						}
						// sb.append("["+ row.getColumnType(j) +
						// ","+width[tmp-1]+"]");
						j = tmp;
					} else {
						sb.append(row.getDefString(j)
								+ (j == row.getColumnCount() ? "" : separation));
					}
				}
				// sb.append("\r\n");
				if (k >= 5000) {
					FileUtils.appendFile(sb.toString(), data_dir, filename);
					sb = new StringBuffer();
					k = 0;
				}
			}
			FileUtils.appendFile(sb.append("\r\n").toString(), data_dir,
					filename);
		}
	}

}
