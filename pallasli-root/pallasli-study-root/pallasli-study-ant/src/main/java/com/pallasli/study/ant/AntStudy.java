package com.pallasli.study.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AntStudy {
	public static Project initAntProject(String buildFilePath) {

		File buildFile = new File(buildFilePath);

		// 创建一个ANT项目

		Project p = new Project();

		// 创建一个默认的监听器,监听项目构建过程中的日志操作

		DefaultLogger consoleLogger = new DefaultLogger();

		consoleLogger.setErrorPrintStream(System.err);

		consoleLogger.setOutputPrintStream(System.out);

		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);

		p.addBuildListener(consoleLogger);

		try {

			p.fireBuildStarted();

			// 初始化该项目

			p.init();

			ProjectHelper helper = ProjectHelper.getProjectHelper();

			// 解析项目的构建文件

			helper.parse(p, buildFile);

		} catch (BuildException be) {
			be.printStackTrace();
		}
		return p;
	}

	public static void finishBuild(Project p) {
		try {

			p.fireBuildFinished(null);

		} catch (BuildException be) {

			p.fireBuildFinished(be);

		}
	}
}
