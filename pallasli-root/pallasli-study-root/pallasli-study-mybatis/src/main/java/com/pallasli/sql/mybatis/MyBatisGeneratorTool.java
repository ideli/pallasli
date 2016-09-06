package com.pallasli.sql.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 动态生成配置文件和类
 * 
 * @author Administrator
 * 
 */
public class MyBatisGeneratorTool {

	public static void run() throws Exception {
		MybatisOperation.instance().createTable();
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String genCfg = "/generator.xml"; // src的一级目录下
		File configFile = new File(MyBatisGeneratorTool.class.getResource(genCfg).getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = null;
		config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = null;
		myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
