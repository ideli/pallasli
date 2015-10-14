package com.pallas.design.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Config;
import com.pallas.design.bean.Fieldset;
import com.pallas.design.bean.FieldsetField;

public interface FieldsetService {

	public List<FieldsetField> getFieldSetFields(Fieldset fieldset);

	public JsonObject saveFieldSet(Fieldset params);

	public List<Fieldset> loadFieldsets();

	public List<Config> getFieldSetFieldPropetyValues(
			FieldsetField fieldsetField);

	public List<Config> getFieldSetPropetyValues(Fieldset fieldset);

	public void saveFieldSetField(FieldsetField fieldsetField);

}
