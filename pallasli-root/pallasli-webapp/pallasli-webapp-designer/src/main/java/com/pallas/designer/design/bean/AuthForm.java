package com.pallas.designer.design.bean;

import com.google.gson.JsonArray;

public class AuthForm extends AuthComp {
	private JsonArray buttons;
	private JsonArray fields;

	public JsonArray getButtons() {
		return buttons;
	}

	public void setButtons(JsonArray buttons) {
		this.buttons = buttons;
	}

	public JsonArray getFields() {
		return fields;
	}

	public void setFields(JsonArray fields) {
		this.fields = fields;
	}

}
