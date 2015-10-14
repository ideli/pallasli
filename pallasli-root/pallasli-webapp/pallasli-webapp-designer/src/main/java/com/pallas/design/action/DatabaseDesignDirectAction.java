package com.pallas.design.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lyt.designer.dao.BaseConn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pallas.design.bean.Database;
import com.pallas.design.bean.Table;
import com.pallas.design.bean.TableField;
import com.pallas.design.service.DatabaseService;
import com.pallas.design.service.TableService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class DatabaseDesignDirectAction {
	private final DatabaseService databaseService;
	private final TableService tableService;

	public DatabaseService getWordService() {
		return databaseService;
	}

	public DatabaseDesignDirectAction() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		databaseService = (DatabaseService) ctx.getBean("databaseService");
		tableService = (TableService) ctx.getBean("tableService");
	}

	public List<Table> getTablesFromDatabase(Database database) {

		List<Table> l = new ArrayList<Table>();
		String sql = "select t.table_name tableName,t.table_name tableKey,b.comments tableCaption,b.comments tableComments from user_tables t "
				+ "left join user_tab_comments b on t.table_name=b.TABLE_NAME ";
		try {
			if (Integer.parseInt(database.getDatabaseType()) == BaseConn.TYPE_MYSQL) {
				sql = "show tables from " + database.getDatabaseSchema();
			}
			BaseConn.getAppCon(new Gson().toJsonTree(database)
					.getAsJsonObject());
			PreparedStatement ps = BaseConn.appCon.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (Integer.parseInt(database.getDatabaseType()) == BaseConn.TYPE_MYSQL) {
				while ((rs != null) && (rs.next())) {
					String tableName = rs.getString(1);
					Table t = new Table();
					t.setTableName(tableName);
					t.setTableCaption(tableName);
					t.setTableComments(tableName);
					l.add(t);
				}
			} else {
				while ((rs != null) && (rs.next())) {
					// Row row = new Row(rs);
					// String jsonData = RowUtils.toJsonObject(row).toString();
					// Table t = UnMarshalUtil.unMarshal(jsonData, Table.class);
					// l.add(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@DirectMethod(method = "getTables")
	public List<Table> getTables(Database database) {

		List<Table> l = getTablesFromDatabase(database);
		List<Table> ret = new ArrayList<Table>();
		for (Table t : l) {
			// Table tmp = tableService.loadByTableName(t.getTableName());
			Table tmp = tableService.loadByProjectTableName(
					database.getProjectName(), t.getTableName());
			if (tmp != null) {
				// ret.add(tmp);
			} else {
				t.setProjectName(database.getProjectName());
				ret.add(t);
			}
		}
		return ret;
	}

	@DirectMethod(method = "saveTables")
	public JsonObject saveTables(List<Table> tableList) {
		JsonObject result = new JsonObject();
		List<Table> list = new ArrayList<Table>();
		for (Table t : tableList) {

			// Table t = new Table();
			// if (tMap.containsKey("id") && tMap.get("id") != null
			// && !tMap.get("id").toString().trim().equals("")) {
			// t.setId(Long.parseLong(tMap.get("id").toString().trim()));
			// }
			// t.setProjectName(tMap.get("projectName").toString());
			// t.setTableCaption(tMap.get("tableCaption").toString());
			// t.setTableName(tMap.get("tableName").toString());
			// t.setTableComments(tMap.get("tableComments").toString());
			list.add(t);
		}

		databaseService.saveDesignTables(list);
		result.addProperty("success", true);
		return result;
	}

	public List<TableField> getTableFieldsFromDbTable(Table table,
			Database database) {
		System.out.println("getTablesFromApp");
		List<TableField> l = new ArrayList<TableField>();
		String sql = "select c.TABLE_NAME tableName,c.COLUMN_NAME fieldName,c.DATA_TYPE fieldType,c.DATA_LENGTH fieldLength,"
				+ "c.DATA_PRECISION fieldPrecision,c.NULLABLE fieldAllowblank,c.DATA_DEFAULT fieldDefault,"
				+ "c.COLUMN_ID ,cc.comments fieldCaption,cc.comments fieldComments from user_tab_columns c"
				+ " left join user_col_comments cc on cc.TABLE_NAME=c.table_name  and cc.column_name=c.COLUMN_NAME "
				+ " where c.table_name='" + table.getTableName() + "'";
		try {

			if (Integer.parseInt(database.getDatabaseType()) == BaseConn.TYPE_MYSQL) {
				sql = "show columns from " + table.getTableName() + " from "
						+ database.getDatabaseSchema();
			}
			BaseConn.getAppCon(new Gson().toJsonTree(database)
					.getAsJsonObject());
			PreparedStatement ps = BaseConn.appCon.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (Integer.parseInt(database.getDatabaseType()) == BaseConn.TYPE_MYSQL) {
				while ((rs != null) && (rs.next())) {
					// Field | Type | Null | Key | Default| Extra
					String fieldName = rs.getString(1);
					TableField t = new TableField();
					t.setTableName(table.getTableName());
					t.setFieldName(fieldName);
					t.setFieldCaption(fieldName);
					String allowblank = rs.getString(3);
					if ("NO".equals(allowblank)) {
						t.setFieldAllowblank("0");
					}
					if ("YES".equals(allowblank)) {
						t.setFieldAllowblank("1");
					}
					t.setDataTypeName(rs.getString(2));
					t.setFieldDefault(rs.getString(5));
					l.add(t);
				}
			} else {
				while ((rs != null) && (rs.next())) {
					// Row row = new Row(rs);
					// String jsonData = RowUtils.toJsonObject(row).toString();
					// TableField t = UnMarshalUtil.unMarshal(jsonData,
					// TableField.class);
					// l.add(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@DirectMethod(method = "getTableFields")
	public List<TableField> getTableFields(Table table, Database database) {
		List<TableField> l = getTableFieldsFromDbTable(table, database);
		List<TableField> ret = new ArrayList<TableField>();
		for (TableField t : l) {
			// TableField tmp = tableService
			// .loadTableFieldByTableNameAndFieldName(t.getTableName(),
			// t.getFieldName());
			TableField tmp = tableService
					.loadTableFieldByProjectTableFieldName(
							database.getProjectName(), t.getTableName(),
							t.getFieldName());
			if (tmp != null) {
				// ret.add(tmp);
			} else {
				t.setProjectName(database.getProjectName());
				ret.add(t);
			}
		}
		return ret;
	}

	@DirectMethod(method = "saveTableFields")
	public JsonObject saveTableFields(List<TableField> l) {
		JsonObject result = new JsonObject();
		List<TableField> list = new ArrayList<TableField>();
		for (TableField t : l) {

			// TableField t = new TableField();
			// if (tMap.containsKey("id") && tMap.get("id") != null
			// && !tMap.get("id").toString().trim().equals("")) {
			// t.setId(Long.parseLong(tMap.get("id").toString().trim()));
			// }
			// t.setFieldCaption(tMap.get("fieldCaption").toString());
			// t.setFieldName(tMap.get("fieldName").toString());
			// t.setTableName(tMap.get("tableName").toString());
			// t.setProjectName(tMap.get("projectName").toString());
			// String fieldAllowblank = tMap.get("fieldAllowblank").toString();
			// if (tMap.get("fieldLength") != null
			// && !tMap.get("fieldLength").toString().trim().equals("")) {
			//
			// int fieldLength = Integer.parseInt(tMap.get("fieldLength")
			// .toString());
			// t.setFieldLength(fieldLength);
			// }
			// if (tMap.get("fieldPrecision") != null
			// && !tMap.get("fieldPrecision").toString().trim().equals("")) {
			//
			// int fieldPrecision = Integer.parseInt(tMap
			// .get("fieldPrecision").toString());
			// t.setFieldPrecision(fieldPrecision);
			// }
			// String fieldType = tMap.get("fieldType").toString();
			// t.setFieldAllowblank(fieldAllowblank);
			// t.setDataTypeName(fieldType);
			list.add(t);
		}

		databaseService.saveDesignTableFields(list);
		result.addProperty("success", true);
		return result;
	}

	@DirectMethod(method = "saveDatabase")
	public boolean saveDatabase(Database database) {

		databaseService.saveDatabase(database);
		return true;
	}

	@DirectMethod(method = "getDatabases")
	public List<Database> getDatabases() {

		List<Database> l = databaseService.getDatabases();
		return l;
	}
}
