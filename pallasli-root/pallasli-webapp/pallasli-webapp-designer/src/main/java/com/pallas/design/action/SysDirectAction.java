package com.pallas.design.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.JsonObject;
import com.pallas.design.bean.CompConfig;
import com.pallas.design.bean.CompType;
import com.pallas.design.service.SysService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class SysDirectAction {
	private final SysService sysService;

	public SysService getWordService() {
		return sysService;
	}

	public SysDirectAction() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		sysService = (SysService) ctx.getBean("sysService");
	}

	@DirectMethod
	public List<CompType> getCompTypes() {
		return sysService.getCompTypes();
	}

	@DirectMethod
	public JsonObject saveCompType(CompConfig compType) {
		return sysService.saveCompType(compType);
	}
}
