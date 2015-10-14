package com.pallas.design.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Page;
import com.pallas.design.service.PageService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class PageDirectAction {
	private final PageService pageService;

	public PageService getWordService() {
		return pageService;
	}

	public PageDirectAction() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		pageService = (PageService) ctx.getBean("pageService");
	}

	@DirectMethod(method = "getPages")
	public List<Page> getPages(Page params) {
		return pageService.getPages(params);
	}

	@DirectMethod(method = "getPageFields")
	public JsonObject getPageFields(Page params) {
		return pageService.getPageFields(params);
	}

	@DirectMethod(method = "savePage")
	public JsonObject savePage(Page params) {
		return pageService.savePage(params);
	}

}
