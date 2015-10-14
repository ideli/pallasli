package com.pallas.design.service.impl;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Page;
import com.pallas.design.dao.ConfigDAO;
import com.pallas.design.dao.PageDAO;
import com.pallas.design.dao.PageFieldsetDAO;
import com.pallas.design.dao.PageFieldsetFieldDAO;
import com.pallas.design.service.PageService;

public class PageServiceImpl implements PageService {
	private PageDAO pageDao;
	private PageFieldsetDAO pageFieldsetDao;
	private PageFieldsetFieldDAO pageFieldsetFieldDao;
	private ConfigDAO configDao;

	public PageDAO getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDAO pageDao) {
		this.pageDao = pageDao;
	}

	public PageFieldsetDAO getPageFieldsetDao() {
		return pageFieldsetDao;
	}

	public void setPageFieldsetDao(PageFieldsetDAO pageFieldsetDao) {
		this.pageFieldsetDao = pageFieldsetDao;
	}

	public PageFieldsetFieldDAO getPageFieldsetFieldDao() {
		return pageFieldsetFieldDao;
	}

	public void setPageFieldsetFieldDao(
			PageFieldsetFieldDAO pageFieldsetFieldDao) {
		this.pageFieldsetFieldDao = pageFieldsetFieldDao;
	}

	public ConfigDAO getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDAO configDao) {
		this.configDao = configDao;
	}

	public List<Page> getPages(Page params) {
		return pageDao.selectAll();

	}

	public JsonObject getPageFields(Page params) {
		JsonObject json = new JsonObject();

		return json;
	}

	public JsonObject savePage(Page page) {

		JsonObject json = new JsonObject();
		//
		pageDao.insert(page);
		return json;
	}

	@Override
	public List<Page> loadPages() {
		List<Page> list = pageDao.selectAll();
		return list;
	}

}
