package com.mixky.designer.builder;

import com.google.gson.JsonObject;
import com.mixky.system.ApplicationDataLoader;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class BuilderDirect {

	@DirectMethod
	public JsonObject syncToPortal(){
		JsonObject result = new JsonObject();
		
		// 从中心服务器同步数据
		ApplicationDataLoader.instance().loadApplicationDataFromServer();
		
		result.addProperty("success", true);
		return result;
	}
}
