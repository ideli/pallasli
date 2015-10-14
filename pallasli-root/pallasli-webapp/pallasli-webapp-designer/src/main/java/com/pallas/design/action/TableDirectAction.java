package com.pallas.design.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Config;
import com.pallas.design.bean.Table;
import com.pallas.design.bean.TableField;
import com.pallas.design.service.TableService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class TableDirectAction {
	private final TableService tableService;

	public TableService getWordService() {
		return tableService;
	}

	public TableDirectAction() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		tableService = (TableService) ctx.getBean("tableService");
	}

	@DirectMethod
	public List<Table> getTables(Table params) {
		List<Table> list = tableService.loadTables();
		return list;
	}

	@DirectMethod
	public JsonObject getTablesF(Table params) {
		return tableService.getTablesF(params);
	}

	@DirectMethod(method = "getTableColumns")
	public List<TableField> getTableColumns(Table table) {
		// Table table = new Table();
		// table.setTableName(tableName.get(0).getAsJsonObject().get("tableName")
		// .getAsString());
		System.out.println(table.getTableName());
		List<TableField> list = tableService.getTableFields(table);
		return list;
	}

	@DirectMethod(method = "getTableFieldPropetyValues")
	public List<Config> getTableFieldPropetyValues(TableField tablefield) {
		List<Config> list = tableService.getTableFieldPropetyValues(tablefield);
		return list;
	}

	@DirectMethod(method = "getOracleTableColumns")
	public JsonObject getOracleTableColumns(TableField params) {
		return tableService.getOracleTableColumns(params);
	}

	@DirectMethod(method = "saveTableFields")
	public JsonObject saveTableFields(JsonObject json) {
		return tableService.saveTableFields(json);
	}

}
