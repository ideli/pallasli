package com.pallasli.study.lucene.filter;

import java.io.File;
import java.io.FileFilter;

public class TextFilesFilter implements FileFilter {
	public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith(".txt");
	}
}
