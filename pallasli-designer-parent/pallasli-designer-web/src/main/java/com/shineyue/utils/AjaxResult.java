package com.shineyue.utils;

import java.util.List;
import java.util.Map;

public class AjaxResult {
	private boolean success;
	private String msg;
	private int totalcount;
	private List<? extends Object> results;
	private List<? extends Object> erros;
	private List<Map<String, String>> vdMapList;
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public List<? extends Object> getResults() {
		return results;
	}

	public void setResults(List<? extends Object> results) {
		this.results = results;
	}

	public List<? extends Object> getErros() {
		return erros;
	}

	public void setErros(List<? extends Object> erros) {
		this.erros = erros;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Map<String, String>> getVdMapList() {
		return vdMapList;
	}

	public void setVdMapList(List<Map<String, String>> vdMapList) {
		this.vdMapList = vdMapList;
	}

}
