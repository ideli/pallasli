package com.pallasli.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DownloadAction extends ActionSupport {

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	@Override
	public String execute() throws Exception {
		fileInputStream = new FileInputStream(new File(
				"C:\\file-for-download.txt"));
		return SUCCESS;
	}
}
