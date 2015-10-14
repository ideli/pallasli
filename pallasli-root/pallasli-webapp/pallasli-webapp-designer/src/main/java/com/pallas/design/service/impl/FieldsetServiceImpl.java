package com.pallas.design.service.impl;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Config;
import com.pallas.design.bean.Fieldset;
import com.pallas.design.bean.FieldsetField;
import com.pallas.design.dao.ConfigDAO;
import com.pallas.design.dao.FieldsetDAO;
import com.pallas.design.dao.FieldsetFieldDAO;
import com.pallas.design.service.FieldsetService;

public class FieldsetServiceImpl implements FieldsetService {
	private FieldsetDAO fieldsetDao;
	private FieldsetFieldDAO fieldsetFieldDao;
	private ConfigDAO configDao;

	public FieldsetDAO getFieldsetDao() {
		return fieldsetDao;
	}

	public void setFieldsetDao(FieldsetDAO fieldsetDao) {
		this.fieldsetDao = fieldsetDao;
	}

	public FieldsetFieldDAO getFieldsetFieldDao() {
		return fieldsetFieldDao;
	}

	public void setFieldsetFieldDao(FieldsetFieldDAO fieldsetFieldDao) {
		this.fieldsetFieldDao = fieldsetFieldDao;
	}

	public ConfigDAO getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDAO configDao) {
		this.configDao = configDao;
	}

	public List<FieldsetField> getFieldSetFields(Fieldset fieldset) {
		List<FieldsetField> list = fieldsetFieldDao
				.selectByFieldsetKey(fieldset.getFieldsetName());
		return list;
	}

	public JsonObject saveFieldSet(Fieldset fieldset) {
		JsonObject json = new JsonObject();
		fieldsetDao.insert(fieldset);
		return json;
	}

	@Override
	public List<Fieldset> loadFieldsets() {
		List<Fieldset> list = fieldsetDao.selectAll();
		return list;
	}

	@Override
	public List<Config> getFieldSetFieldPropetyValues(
			FieldsetField fieldsetField) {
		// List<TableFieldConfig>
		// fieldCfgList=tableFieldConfigDao.getByTableFieldId(tablefield.getId());
		List<Config> list = configDao.selectAll();
		return list;
	}

	@Override
	public List<Config> getFieldSetPropetyValues(Fieldset fieldset) {
		// List<TableFieldConfig>
		// fieldCfgList=tableFieldConfigDao.getByTableFieldId(tablefield.getId());
		List<Config> list = configDao.selectAll();
		return list;
	}

	@Override
	public void saveFieldSetField(FieldsetField fieldsetField) {
		fieldsetFieldDao.insert(fieldsetField);

	}

}
