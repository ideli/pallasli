package com.pallas.design.service.impl;

import java.util.List;

import lyt.portal.config.CompBean;
import lyt.portal.config.Config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pallas.design.bean.Comp;
import com.pallas.design.bean.CompConfig;
import com.pallas.design.bean.CompType;
import com.pallas.design.dao.CompConfigDAO;
import com.pallas.design.dao.CompDAO;
import com.pallas.design.dao.CompTypeDAO;
import com.pallas.design.service.CompService;

public class CompServiceImpl implements CompService {
	private CompDAO compDao;
	private CompTypeDAO compTypeDao;
	private CompConfigDAO compConfigDao;

	public CompDAO getCompDao() {
		return compDao;
	}

	public void setCompDao(CompDAO compDao) {
		this.compDao = compDao;
	}

	public CompTypeDAO getCompTypeDao() {
		return compTypeDao;
	}

	public void setCompTypeDao(CompTypeDAO compTypeDao) {
		this.compTypeDao = compTypeDao;
	}

	public CompConfigDAO getCompConfigDao() {
		return compConfigDao;
	}

	public void setCompConfigDao(CompConfigDAO compConfigDao) {
		this.compConfigDao = compConfigDao;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	Gson gson = new Gson();

	public JsonObject getCompFromConfig() {
		JsonObject result = new JsonObject();
		JsonArray records = new JsonArray();
		String home = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		home = home.substring(1, home.indexOf("WEB-INF/classes/"));
		List<CompBean> compList;
		try {
			compList = Config.load(home + "data/container.xml");
			for (CompBean comp : compList) {

				String json = gson.toJson(comp);
				records.add(new JsonParser().parse(json));
				// CompBean c=gson.fromJson(json, CompBean.class);
				// System.out.println(c.getCaption());
			}
			result.add("results", records);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Comp> loadCompsByType(CompType compType) {
		List<Comp> list = compDao.selectCompsByTypeId(compType.getId());
		return list;
	}

	@Override
	public List<Comp> loadComps() {
		List<Comp> list = compDao.selectAll();
		return list;
	}

	@Override
	public List<CompType> loadCompTypes() {
		List<CompType> list = compTypeDao.selectAll();
		return list;
	}

	@Override
	public List<CompConfig> loadCompConfigs() {
		List<CompConfig> list = compConfigDao.selectAll();
		return list;
	}

	@Override
	public CompType saveCompType(CompType compType) {
		compType = compTypeDao.insert(compType);
		return compType;
	}

	@Override
	public Comp saveComp(Comp comp) {
		comp = compDao.insert(comp);
		return comp;
	}

	@Override
	public CompConfig saveCompConfig(CompConfig compConfig) {
		compConfig = compConfigDao.insert(compConfig);
		return compConfig;
	}

}
