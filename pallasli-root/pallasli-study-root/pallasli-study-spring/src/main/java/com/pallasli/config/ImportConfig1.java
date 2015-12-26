package com.pallasli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pallasli.scanclass.ImportTestBean;
import com.pallasli.scanclass.ImportWithParamTestBean;

@Configuration
public class ImportConfig1 {
	@Bean(name = "importTestBean")
	public ImportTestBean importTestBean() {
		return new ImportTestBean();
	}

	@Bean
	@Autowired
	public ImportWithParamTestBean importWithParamTestBean() {
		return new ImportWithParamTestBean();
	}
}
