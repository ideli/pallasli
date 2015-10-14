package com.pallas.designer.design.bean;

import java.util.List;

import com.google.gson.JsonObject;

public class AuthPage {
	public static final int TYPE_SIMPGRIG = 1;
	public static final int TYPE_SIMPFORM = 2;
	public static final int TYPE_MUTIGRID = 3;
	public static final int TYPE_MUTIFORM = 4;
	public static final int TYPE_TABVIEW = 5;
	public static final int TYPE_COMPLEX = 9;

	private List<AuthComp> authCompList;
	private List<AuthComp> authPageList;
	private String title;
	private String pageKey;
	private JsonObject configs;
	private String customScripts;

	public String getCustomScripts() {
		return customScripts;
	}

	public void setCustomScripts(String customScripts) {
		this.customScripts = customScripts;
	}

	public List<AuthComp> getAuthCompList() {
		return authCompList;
	}

	public void setAuthCompList(List<AuthComp> authCompList) {
		this.authCompList = authCompList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public JsonObject getConfigs() {
		return configs;
	}

	public void setConfigs(JsonObject configs) {
		this.configs = configs;
	}

	public List<AuthComp> getAuthPageList() {
		return authPageList;
	}

	public void setAuthPageList(List<AuthComp> authPageList) {
		this.authPageList = authPageList;
	}

}
