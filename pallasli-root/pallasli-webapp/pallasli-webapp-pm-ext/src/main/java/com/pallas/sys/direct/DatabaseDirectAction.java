package com.pallas.sys.direct;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.sys.bean.Menu;
import com.pallas.sys.direct.action.DatabaseAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class DatabaseDirectAction {
	public DatabaseAction databaseAction = new DatabaseAction();

	@DirectMethod(method = "loadTables")
	public JsonArray loadTables(String key, boolean isRoot, int childType,
			String menuTableName) {

		JsonArray array = new JsonArray();

		List<Menu> list = databaseAction.loadTables();
		for (Menu menu : list) {

			JsonObject jsonC = new JsonObject();
			jsonC.addProperty("menuId", menu.getId());
			jsonC.addProperty("text", menu.getMenuCaption());
			jsonC.addProperty("urlPath", menu.getUrlPath());
			jsonC.addProperty("menuKey", menu.getMenuKey());
			jsonC.addProperty("menuCaption", menu.getMenuCaption());
			jsonC.addProperty("menuTypeCode", menu.getMenuTypeCode());
			jsonC.addProperty("menuTableName", menu.getMenuTableName());
			jsonC.addProperty("menuWhereSql", menu.getMenuWhereSql());
			jsonC.addProperty("expanded", false);
			jsonC.addProperty("leaf", true);
			array.add(jsonC);
		}

		// } else if (childType == 0) {
		// }
		return array;
	}

	@DirectMethod(method = "loadColumns")
	public JsonObject loadColumns(String tableName) {

		JsonObject array = databaseAction.loadColumns(tableName);

		return array;
	}

	@DirectMethod(method = "selectDataFromTable")
	public JsonObject selectDataFromTable(String tableName, String columns) {

		JsonArray array = databaseAction
				.selectDataFromTable(tableName, columns);
		JsonObject ret = new JsonObject();
		ret.add("data", array);
		return ret;
	}
}
