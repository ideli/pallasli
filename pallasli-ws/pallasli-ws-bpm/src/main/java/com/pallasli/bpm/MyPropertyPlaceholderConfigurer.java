package com.pallasli.bpm;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	@Override
	public void convertProperties(Properties props) {

		props.setProperty("datasource.url", props.getProperty("datasource.url"));
		super.convertProperties(props);
	}
}
