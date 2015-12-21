package com.pallasli.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	public static Properties loadProperties(String path) {
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
			if (path.endsWith("properties")) {
				p.load(in);
			} else if (path.endsWith("xml")) {
				p.loadFromXML(in);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
}
