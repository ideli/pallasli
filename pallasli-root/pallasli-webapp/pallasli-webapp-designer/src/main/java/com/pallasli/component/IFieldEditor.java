package com.pallasli.component;

import com.google.gson.JsonObject;

public abstract class IFieldEditor {
	public abstract JsonObject getEditor(Field field);

	public static String getApplicationKey() {
		return null;
	}
}
