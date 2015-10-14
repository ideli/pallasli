package com.pallas.design.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Config;
import com.pallas.design.bean.Table;
import com.pallas.design.bean.TableField;

public interface TableService {

	public JsonObject getTablesF(Table params);

	public JsonObject getOracleTableColumns(TableField params);

	public JsonObject saveTableFields(JsonObject json);

	public List<Table> loadTables();

	public List<TableField> getTableFields(Table table);

	public List<Config> getTableFieldPropetyValues(TableField tablefield);

	public Table load(Table t);

	public Table loadByTableName(String tableName);

	public TableField loadTableFieldByTableNameAndFieldName(String tableName,
			String fieldName);

	public Table loadByProjectTableName(String projectName, String tableName);

	public TableField loadTableFieldByProjectTableFieldName(String projectName,
			String tableName, String fieldName);
}
