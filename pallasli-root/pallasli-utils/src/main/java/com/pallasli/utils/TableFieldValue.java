package com.pallasli.utils;

public class TableFieldValue {
	private String fieldName;
	private Class fieldType;
	private Object value;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Class getFieldType() {
		return fieldType;
	}

	public void setFieldType(Class fieldType) {
		this.fieldType = fieldType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
