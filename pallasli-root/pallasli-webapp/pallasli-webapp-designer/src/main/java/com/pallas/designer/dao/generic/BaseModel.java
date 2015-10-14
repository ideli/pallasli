package com.pallas.designer.dao.generic;

public class BaseModel {
	private Long id;
	private String t_table_name;
	private String t_table_idcolumn;

	public String getT_table_name() {
		return t_table_name;
	}

	public void setT_table_name(String tTableName) {
		t_table_name = tTableName;
	}

	public String getT_table_idcolumn() {
		return t_table_idcolumn;
	}

	public void setT_table_idcolumn(String tTableIdcolumn) {
		t_table_idcolumn = tTableIdcolumn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
