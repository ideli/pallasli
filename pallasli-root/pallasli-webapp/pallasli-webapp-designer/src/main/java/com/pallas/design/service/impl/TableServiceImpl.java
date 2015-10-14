package com.pallas.design.service.impl;

import java.sql.PreparedStatement;
import java.util.List;

import lyt.designer.dao.BaseConn;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Config;
import com.pallas.design.bean.Table;
import com.pallas.design.bean.TableField;
import com.pallas.design.dao.ConfigDAO;
import com.pallas.design.dao.TableDAO;
import com.pallas.design.dao.TableFieldDAO;
import com.pallas.design.service.TableService;

public class TableServiceImpl implements TableService {
	private TableDAO tableDao;
	private TableFieldDAO tableFieldDao;
	private ConfigDAO configDao;

	public TableDAO getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDAO tableDao) {
		this.tableDao = tableDao;
	}

	public TableFieldDAO getTableFieldDao() {
		return tableFieldDao;
	}

	public void setTableFieldDao(TableFieldDAO tableFieldDao) {
		this.tableFieldDao = tableFieldDao;
	}

	public ConfigDAO getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDAO configDao) {
		this.configDao = configDao;
	}

	public List<Table> getTablesByPrefixion(Table params) {
		List<Table> list = tableDao.selectTablesByPrefixion(params);
		return list;
	}

	public JsonObject getTablesF(Table params) {
		JsonObject json = new JsonObject();
		JsonObject data = new JsonObject();
		String sql = "select t.table_name name,b.comments caption,b.comments comments from user_tables t "
				+ "left join user_tab_comments b on t.table_name=b.TABLE_NAME "
				+ "where t.table_name like '" + "%'";// + params.getPrefixion()+
														// "%'";
		// try {
		// PreparedStatement ps = BaseConn.appCon.prepareStatement(sql);
		// ResultSet rs = ps.executeQuery();
		// JsonArray array = RowUtils.toJsonArray(rs);
		// data.add("data", array);
		// json.addProperty("total", array.size());
		// json.add("results", array);
		// json.addProperty("success", true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return json;
	}

	public JsonObject getOracleTableColumns(TableField params) {
		JsonObject json = new JsonObject();
		String sql = "select c.TABLE_NAME,c.COLUMN_NAME,c.DATA_TYPE,c.DATA_LENGTH,c.DATA_PRECISION,"
				+ " c.NULLABLE,c.DATA_DEFAULT,c.COLUMN_ID,cc.comments COLUMN_CAPTION from user_tab_columns c"
				+ " left join user_col_comments cc on cc.TABLE_NAME=c.table_name  and cc.column_name=c.COLUMN_NAME "
				+ " where c.TABLE_NAME='"
				+ params.getTableName()
				+ "' order by c.COLUMN_NAME ";
		// try {
		// PreparedStatement ps = BaseConn.appCon.prepareStatement(sql);
		// ResultSet rs = ps.executeQuery();
		// json.add("results", RowUtils.toJsonArray(rs));
		// json.addProperty("success", true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return json;
	}

	public JsonObject saveTableFields(JsonObject json) {
		String sql = "insert into ";
		try {
			PreparedStatement ps = BaseConn.appCon.prepareStatement(sql);
			boolean success = ps.execute();
			json.addProperty("success", success);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public List<Table> loadTables() {
		List<Table> list = tableDao.selectAll();
		return list;
	}

	@Override
	public List<TableField> getTableFields(Table table) {
		List<TableField> list = tableFieldDao.selectByTableName(table
				.getTableName());
		return list;
	}

	@Override
	public List<Config> getTableFieldPropetyValues(TableField tablefield) {
		// List<TableFieldConfig>
		// fieldCfgList=tableFieldConfigDao.getByTableFieldId(tablefield.getId());
		List<Config> list = configDao.selectAll();
		return list;
	}

	@Override
	public Table load(Table t) {
		// TODO Auto-generated method stub
		return tableDao.load(t);
	}

	@Override
	public Table loadByTableName(String tableName) {
		// TODO Auto-generated method stub
		return tableDao.loadByTableName(tableName);
	}

	@Override
	public TableField loadTableFieldByTableNameAndFieldName(String tableName,
			String fieldName) {
		// TODO Auto-generated method stub
		return tableFieldDao.loadTableFieldByTableNameAndFieldName(tableName,
				fieldName);
	}

	@Override
	public Table loadByProjectTableName(String projectName, String tableName) {
		return tableDao.loadByProjectTableName(projectName, tableName);
	}

	@Override
	public TableField loadTableFieldByProjectTableFieldName(String projectName,
			String tableName, String fieldName) {
		return tableFieldDao.loadByProjectTableFieldName(projectName,
				tableName, fieldName);
	}
}
