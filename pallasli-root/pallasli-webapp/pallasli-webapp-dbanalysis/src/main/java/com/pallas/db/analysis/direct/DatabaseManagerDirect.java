package com.pallas.db.analysis.direct;

import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class DatabaseManagerDirect {
	@DirectMethod
	public JsonObject getTables(String url) {

		JsonObject result = new JsonObject();

		return result;
	}
}
