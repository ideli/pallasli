package com.pallasli.designer.rest.pagesetting;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Page;
import com.pallas.design.service.PageService;
import com.pallasli.constant.SystemConstant;

@Controller(value = "pageSetting")
@RequestMapping("/pagesetting")
public class PageSetting {
	private final PageService pageService;

	public PageService getWordService() {
		return pageService;
	}

	public PageSetting() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/"
				+ SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		pageService = (PageService) ctx.getBean("pageService");
	}

	@RequestMapping(value = "/getPages", method = RequestMethod.GET)
	@ResponseBody
	public List<Page> getPages(Page params) {
		return pageService.getPages(params);
	}

	@RequestMapping(value = "/getPageFields", method = RequestMethod.GET)
	@ResponseBody
	public JsonObject getPageFields(Page params) {
		return pageService.getPageFields(params);
	}

	@RequestMapping(value = "/savePage", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject savePage(Page params) {
		return pageService.savePage(params);
	}

}
