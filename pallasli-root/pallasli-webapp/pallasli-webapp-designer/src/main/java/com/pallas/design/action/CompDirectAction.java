package com.pallas.design.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.design.bean.Comp;
import com.pallas.design.bean.CompConfig;
import com.pallas.design.bean.CompType;
import com.pallas.design.service.CompService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class CompDirectAction {
	private final CompService compService;

	public CompService getWordService() {
		return compService;
	}

	public CompDirectAction() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		compService = (CompService) ctx.getBean("compService");
	}

	@DirectMethod(method = "loadCompsByType")
	public List<Comp> loadCompsByType(JsonArray data) {
		// JsonObject addNode = data.get(0).getAsJsonObject();
		CompType compType = new CompType();
		List<Comp> list = compService.loadCompsByType(compType);
		return list;
	}

	@DirectMethod(method = "loadComps")
	public List<Comp> loadComps(JsonArray data) {
		// JsonObject addNode = data.get(0).getAsJsonObject();
		List<Comp> list = compService.loadComps();
		return list;
	}

	@DirectMethod(method = "loadCompTypes")
	public List<CompType> loadCompTypes() {
		List<CompType> list = compService.loadCompTypes();
		return list;
	}

	@DirectMethod(method = "loadCompConfigs")
	public List<CompConfig> loadCompConfigs() {
		List<CompConfig> list = compService.loadCompConfigs();
		return list;
	}

	@DirectMethod(method = "saveCompType")
	public JsonObject saveCompType(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String compTypeCode = addNode.has("compTypeCode") ? addNode.get(
				"compTypeCode").getAsString() : " ";
		String compTypeCaption = addNode.has("compTypeCaption") ? addNode.get(
				"compTypeCaption").getAsString() : " ";
		CompType compType = new CompType();
		compType.setCompTypeCode(compTypeCode);
		compType.setCompTypeCaption(compTypeCaption);
		compType = compService.saveCompType(compType);

		return new JsonObject();

	}

	@DirectMethod(method = "saveComp")
	public JsonObject saveComp(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String compCaption = addNode.has("compCaption") ? addNode.get(
				"compCaption").getAsString() : " ";
		String compName = addNode.has("compName") ? addNode.get("compName")
				.getAsString() : " ";
		String compClass = addNode.has("compClass") ? addNode.get("compClass")
				.getAsString() : " ";
		String compTypeCode = addNode.has("compTypeCode") ? addNode.get(
				"compTypeCode").getAsString() : " ";
		Comp comp = new Comp();
		comp.setCompCaption(compCaption);
		comp.setCompClass(compClass);
		comp.setCompName(compName);
		comp.setCompTypeCode(compTypeCode);
		comp = compService.saveComp(comp);

		return new JsonObject();

	}

	@DirectMethod(method = "saveCompConfigs")
	public JsonObject saveCompConfigs(JsonArray data) {
		JsonArray dataArr = data.get(0).getAsJsonArray();
		int i = 0;
		while (data.get(i) != null) {

			JsonObject addNode = dataArr.get(i).getAsJsonObject();
			// String menuCaption = addNode.has("menuCaption") ? addNode.get(
			// "menuCaption").getAsString() : " ";
			// String menuName = addNode.has("menuName") ?
			// addNode.get("menuName")
			// .getAsString() : " ";
			String compName = addNode.has("compName") ? addNode.get("compName")
					.getAsString() : " ";
			String configName = addNode.has("configName") ? addNode.get(
					"configName").getAsString() : " ";

			CompConfig compConfig = new CompConfig();
			compConfig.setCompName(compName);
			compConfig.setConfigName(configName);
			compConfig = compService.saveCompConfig(compConfig);
			i++;
		}
		return new JsonObject();

	}

}
