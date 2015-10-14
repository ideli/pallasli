package com.pallas.app.build;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.app.build.bean.Action;
import com.pallas.app.build.bean.Column;
import com.pallas.app.build.bean.View;

public class BuildAction {
	static Gson gson = new Gson();

	public View buildView(String viewKey) {
		JsonObject viewJson = JsonLoader.loadViewJson(viewKey);
		View view = JsBuilder.buildView(viewJson);
		return view;
	}

	public static JsonArray getGridStoreFields(String viewKey) {
		JsonObject viewJson = JsonLoader.loadViewJson(viewKey);
		View view = JsBuilder.buildView(viewJson);
		Column[] columns = view.getF_columns();
		JsonArray result = new JsonArray();
		for (Column c : columns) {

			JsonObject json = new JsonObject();
			json.addProperty("name", c.getF_key());
			json.addProperty("mapping", c.getF_key());
			json.addProperty("type", "string");
			result.add(json);
		}
		return result;
	}

	public static JsonArray getGridActions(String viewKey) {
		JsonObject viewJson = JsonLoader.loadViewJson(viewKey);
		View view = JsBuilder.buildView(viewJson);
		Action[] actions = view.getF_actions();
		JsonArray result = new JsonArray();
		for (Action a : actions) {
			JsonObject json = new JsonObject();
			json.addProperty("name", a.getF_key());
			json.addProperty("text", a.getF_caption());
			json.add("handler", a.getF_handler());
			result.add(json);
		}
		return result;
	}

	public static JsonArray getGridColumns(String viewKey) {
		JsonObject viewJson = JsonLoader.loadViewJson(viewKey);
		View view = JsBuilder.buildView(viewJson);
		Column[] columns = view.getF_columns();
		JsonArray result = new JsonArray();
		for (Column c : columns) {

			JsonObject json = new JsonObject();
			json.addProperty("dataIndex", c.getF_key());
			json.addProperty("flex", c.getF_flex());
			json.addProperty("text", c.getF_caption());
			// json.addProperty("align", );
			// json.addProperty("renderer", "string");
			result.add(json);
		}
		return result;
	}

	public static void main(String[] a) {
		System.out.println(BuildAction.getGridStoreFields(""));
		System.out.println(BuildAction.getGridActions(""));
		System.out.println(BuildAction.getGridColumns(""));
	}
}
