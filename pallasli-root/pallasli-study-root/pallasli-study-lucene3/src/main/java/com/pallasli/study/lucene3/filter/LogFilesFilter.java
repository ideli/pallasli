package com.pallasli.study.lucene3.filter;

import java.io.File;
import java.io.FileFilter;

public class LogFilesFilter implements FileFilter {
	@Override
	public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith(".log");
	}
}
