package com.shineyue.htmldesign.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Builder {
	public static Properties COMPONENT_PROPERTIES;
	static {
		COMPONENT_PROPERTIES = new Properties();
		try {
			COMPONENT_PROPERTIES.load(new FileInputStream(new File(
					"components.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
