package com.pallasli.component;

import com.google.gson.JsonObject;

public abstract class IFieldEditor {
	public JsonObject getEditor(Field field);
}
