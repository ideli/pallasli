package com.pallas.designer.design.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonFunction;
import com.google.gson.JsonObject;
import com.pallas.designer.design.bean.AuthComp;
import com.pallas.designer.design.bean.AuthForm;
import com.pallas.designer.design.bean.AuthGrid;
import com.pallas.designer.design.bean.AuthPage;
import com.pallas.designer.design.bean.SimpleFormAuthPage;
import com.pallas.designer.design.bean.SimpleGridAuthPage;
import com.pallasli.utils.FileUtils;
import com.pallasli.utils.JsonUtils;

public class AuthPageAction {
	public static AuthPageAction instance() {
		return new AuthPageAction();
	}

	private AuthForm loadAuthForm(String pageKey, String compKey) {
		// 读取data/designer/pages/pageKey---compKey
		// grid: columns,api,extraparams,buttons
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		String path = basePath.replace("WEB-INF/classes/", "") + "data/design/pages/p_addMenu/components.json";

		String json = FileUtils.readFileToString(path);
		JsonElement page = com.pallasli.utils.JsonUtils.fromStringToJsonElement(json);
		JsonArray fieldArray = page.getAsJsonObject().get("f_fields").getAsJsonArray();

		Iterator<JsonElement> it0 = fieldArray.iterator();
		while (it0.hasNext()) {
			JsonObject tmp = it0.next().getAsJsonObject();
			if (tmp.has("modelName")) {
				String modelName = tmp.get("modelName").getAsString();
				tmp.remove("modelName");
				tmp.add("store", new JsonFunction(
						"Ext.create('Ext.data.Store', {" + "model: '" + modelName + "', autoLoad:true" + "}" + ")"));
			}
		}
		JsonArray buttonArray = page.getAsJsonObject().get("f_buttons").getAsJsonArray();
		Iterator<JsonElement> it = buttonArray.iterator();
		while (it.hasNext()) {
			JsonObject tmp = it.next().getAsJsonObject();
			String handlerFn = tmp.get("handler").getAsString();
			tmp.remove("handler");
			tmp.add("handler", new JsonFunction(handlerFn));
		}
		AuthForm authForm = new AuthForm();
		authForm.setButtons(buttonArray);
		authForm.setFields(fieldArray);
		return authForm;
	}

	private AuthPage loadAuthPage(String pageKey) {
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		String path = basePath.replace("WEB-INF/classes/", "") + "data/design/pages/" + pageKey + "/components.json";
		String json = FileUtils.readFileToString(path);
		System.out.println("***************");
		System.out.println(path);
		System.out.println(json);
		System.out.println("***************");
		JsonElement page = JsonUtils.fromStringToJsonElement(json);
		String title = page.getAsJsonObject().get("f_title").getAsString();
		JsonArray panelArray = page.getAsJsonObject().get("f_panels").getAsJsonArray();
		AuthPage authPage = new AuthPage();
		authPage.setTitle(title);
		List<AuthComp> authPageList = new ArrayList<AuthComp>();
		Iterator<JsonElement> it = panelArray.iterator();
		while (it.hasNext()) {
			JsonObject tmp = it.next().getAsJsonObject();
			System.out.println(tmp.toString());
			JsonObject gridJson = tmp;
			JsonArray columnArray = gridJson.get("f_columns").getAsJsonArray();
			String modelName = gridJson.get("f_modelName").getAsString();

			// JsonArray columnArray = new JsonArray();
			// JsonObject columnJson = new JsonObject();
			// columnJson.addProperty("dataIndex", "menuName");
			// columnJson.addProperty("text", "英文名");
			// columnJson.addProperty("flex", 2);
			// columnJson.addProperty("align", "left");
			// columnArray.add(columnJson);
			JsonObject apiJson = gridJson.get("f_api").getAsJsonObject();
			// JsonObject apiJson = new JsonObject();
			// apiJson.add("read", new JsonFunction("MenuAction.loadMenus"));
			// apiJson.add("create", new JsonFunction("MenuAction.saveMenu"));
			// apiJson.add("update", new JsonFunction("MenuAction.saveMenu"));

			JsonObject extraParamsJson = new JsonObject();
			if (gridJson.has("f_extraParams")) {
				extraParamsJson = gridJson.get("f_extraParams").getAsJsonObject();
			}

			JsonArray buttonArray = gridJson.get("f_buttons").getAsJsonArray();
			Iterator<JsonElement> itButton = buttonArray.iterator();
			while (itButton.hasNext()) {
				JsonObject tmpJson = itButton.next().getAsJsonObject();
				String handlerFn = tmpJson.get("handler").getAsString();
				tmpJson.remove("handler");
				tmpJson.add("handler", new JsonFunction(handlerFn));
			}
			// JsonArray buttonArray = new JsonArray();
			// JsonObject buttonJson = new JsonObject();
			// buttonJson.addProperty("text", "增加");
			// buttonJson.add("handler", new
			// JsonFunction("function(){alert(1)}"));
			// buttonArray.add(buttonJson);
			AuthGrid authPageTmp = new AuthGrid();
			authPageTmp.setF_buttons(buttonArray);
			authPageTmp.setF_columns(columnArray);
			authPageTmp.setF_api(apiJson);
			authPageTmp.setF_extraParams(extraParamsJson);
			authPageTmp.setF_modelName(modelName);

			// AuthGrid authPageTmp = JsonUtils.fromJsonToObject(tmp,
			// AuthGrid.class);

			authPageList.add(authPageTmp);

		}
		authPage.setAuthPageList(authPageList);
		return authPage;
	}

	private AuthGrid loadAuthGrid(String pageKey, String compKey) {
		// 读取data/designer/pages/pageKey---compKey
		// grid: columns,api,extraparams,buttons

		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		String path = basePath.replace("WEB-INF/classes/", "") + "data/design/pages/" + pageKey + "/components.json";
		String json = FileUtils.readFileToString(path);
		JsonElement page = JsonUtils.fromStringToJsonElement(json);
		JsonObject gridJson = page.getAsJsonObject().get("f_panels").getAsJsonArray().get(0).getAsJsonObject();
		JsonArray columnArray = gridJson.get("f_columns").getAsJsonArray();
		String modelName = gridJson.get("f_modelName").getAsString();

		// JsonArray columnArray = new JsonArray();
		// JsonObject columnJson = new JsonObject();
		// columnJson.addProperty("dataIndex", "menuName");
		// columnJson.addProperty("text", "英文名");
		// columnJson.addProperty("flex", 2);
		// columnJson.addProperty("align", "left");
		// columnArray.add(columnJson);
		JsonObject apiJson = gridJson.get("f_api").getAsJsonObject();
		// JsonObject apiJson = new JsonObject();
		// apiJson.add("read", new JsonFunction("MenuAction.loadMenus"));
		// apiJson.add("create", new JsonFunction("MenuAction.saveMenu"));
		// apiJson.add("update", new JsonFunction("MenuAction.saveMenu"));

		JsonObject extraParamsJson = new JsonObject();
		if (gridJson.has("f_extraParams")) {
			extraParamsJson = gridJson.get("f_extraParams").getAsJsonObject();
		}

		JsonArray buttonArray = gridJson.get("f_buttons").getAsJsonArray();
		Iterator<JsonElement> it = buttonArray.iterator();
		while (it.hasNext()) {
			JsonObject tmp = it.next().getAsJsonObject();
			String handlerFn = tmp.get("handler").getAsString();
			tmp.remove("handler");
			tmp.add("handler", new JsonFunction(handlerFn));
		}
		// JsonArray buttonArray = new JsonArray();
		// JsonObject buttonJson = new JsonObject();
		// buttonJson.addProperty("text", "增加");
		// buttonJson.add("handler", new JsonFunction("function(){alert(1)}"));
		// buttonArray.add(buttonJson);
		AuthGrid authGrid = new AuthGrid();
		authGrid.setF_buttons(buttonArray);
		authGrid.setF_columns(columnArray);
		authGrid.setF_api(apiJson);
		authGrid.setF_extraParams(extraParamsJson);
		authGrid.setF_modelName(modelName);

		return authGrid;
	}

	/**
	 * 根据页面key加载页面
	 * 
	 * @param authKey
	 * @return
	 */
	public AuthPage loadAuthPage(String pageKey, int pageType) {
		// Page page = new PageDirect().getPageService().getPageByKey(pageKey);
		String title = "";
		// title=page.getPageCaption();

		// pageType = page.getPageType();
		AuthPage authPage = null;
		if (AuthPage.TYPE_SIMPGRIG == pageType) {
			AuthPage authPageTmp = loadAuthPage(pageKey);
			authPage = new SimpleGridAuthPage();
			// PageFieldsetField field = new PageDirect().getPageService()
			// .getAllField(pageKey);
			String fieldKey = "";
			// fieldKey=field.getFieldKey();
			AuthGrid authGrid = loadAuthGrid(pageKey, fieldKey);
			List<AuthComp> authCompList = new ArrayList<AuthComp>();
			authCompList.add(authGrid);
			authPage.setAuthCompList(authCompList);
			authPage.setTitle(authPageTmp.getTitle());
			((SimpleGridAuthPage) authPage).setAuthGrid(authGrid);
		} else if (AuthPage.TYPE_SIMPFORM == pageType) {
			authPage = new SimpleFormAuthPage();
			// PageFieldsetField field = new PageDirect().getPageService()
			// .getAllField(pageKey);
			String fieldKey = "";
			// fieldKey=field.getFieldKey();
			AuthForm authForm = loadAuthForm(pageKey, fieldKey);
			List<AuthComp> authCompList = new ArrayList<AuthComp>();
			authCompList.add(authForm);
			authPage.setAuthCompList(authCompList);
			authPage.setTitle(title);
			((SimpleFormAuthPage) authPage).setAuthForm(authForm);
		} else if (AuthPage.TYPE_TABVIEW == pageType) {
			authPage = loadAuthPage(pageKey);
		}
		String basePath = this.getClass().getClassLoader().getResource("/").getPath();
		String json = FileUtils.readFileToString(basePath.replace("WEB-INF/classes/", "")
				+ "data/designer/pages/m_g_app.m_guiji.m_grzhgl/components.json");
		JsonElement page = JsonUtils.fromStringToJsonElement(json);
		String customScripts = page.getAsJsonObject().get("f_customscripts").getAsString();
		authPage.setCustomScripts(customScripts);
		return authPage;
	}
}
