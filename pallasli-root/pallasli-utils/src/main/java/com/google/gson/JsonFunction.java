package com.google.gson;

import java.io.IOException;
import java.io.StringWriter;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonFunctionWriter;

public class JsonFunction extends JsonElement {
	JsonPrimitive el;

	public JsonElement deepCopy() {
		return el;
	}

	public JsonFunction(String func) {
		this.el = new JsonPrimitive(func);

	}

	public JsonPrimitive getAsJsonPrimitive() {
		return el;
	}

	public boolean isJsonPrimitive() {
		return true;
	}

	@Override
	public String toString() {
		try {
			StringWriter stringWriter = new StringWriter();
			JsonFunctionWriter jsonWriter = new JsonFunctionWriter(stringWriter);
			jsonWriter.setLenient(true);

			Streams.write(this, jsonWriter);
			System.out.println();
			return "dddddddd";
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
}