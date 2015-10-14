package com.pallas.design.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Page;

public interface PageService {
	public List<Page> getPages(Page params);

	public JsonObject getPageFields(Page params);

	public JsonObject savePage(Page params);

	public List<Page> loadPages();

}
