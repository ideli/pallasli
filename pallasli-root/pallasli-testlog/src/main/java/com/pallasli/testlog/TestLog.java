package com.pallasli.testlog;

import java.util.Map;

public class TestLog {
	private Map<String, String> header;
	private Map<String, Object> params;

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
