package com.pallasli.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Echo;

public class AntSample {

	public static void main(String[] args) {

		File buildFile = new File(".//samplebuild.xml");

		// 创建一个ANT项目

		Project p = new Project();

		// 创建一个默认的监听器,监听项目构建过程中的日志操作

		DefaultLogger consoleLogger = new DefaultLogger();

		consoleLogger.setErrorPrintStream(System.err);

		consoleLogger.setOutputPrintStream(System.out);

		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);

		p.addBuildListener(consoleLogger);

		Echo t = (Echo) p.createTask("echo");
		t.setMessage("消息");
		t.addText("文本");
		t.setDescription("描述");
		// t.execute();
		Target target = new Target();
		target.setName("defaultTarget");
		p.addTarget(target);
		target.addTask(t);
		p.setDefault(target.getName());
		p.executeTarget(target.getName());
		try {

			p.fireBuildStarted();

			// 初始化该项目

			p.init();

			ProjectHelper helper = ProjectHelper.getProjectHelper();

			// 解析项目的构建文件

			helper.parse(p, buildFile);

			// 执行项目的某一个目标

			p.executeTarget(p.getDefaultTarget());

			p.fireBuildFinished(null);

		} catch (BuildException be) {

			p.fireBuildFinished(be);

		}

	}

	public static Project initAntProject() {

		File buildFile = new File(".//src/test/resources/sample.xml");

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

			// 执行项目的某一个目标

			p.executeTarget(p.getDefaultTarget());

			p.fireBuildFinished(null);

		} catch (BuildException be) {

			p.fireBuildFinished(be);

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

	public static void buildJar() {
		Project p = initAntProject();
		p.executeTarget("build-jar");
		finishBuild(p);

	}

	public static void buildWar() {
		Project p = initAntProject();
		p.executeTarget("build-war");
		finishBuild(p);

	}

	public static void compile() {

		Project p = initAntProject();
		p.executeTarget("compile");

		finishBuild(p);
	}

	public static void start() {

		Project p = initAntProject();
		p.executeTarget("start");

		finishBuild(p);
	}

	public static void deploy() {

		Project p = initAntProject();
		p.executeTarget("deploy");

		finishBuild(p);
	}

	public static void generateJavaDoc() {

		Project p = initAntProject();
		p.executeTarget("generate-javadoc");

		finishBuild(p);
	}

	public static void uploadFtp() {

		Project p = initAntProject();
		p.executeTarget("upload-ftp");

		finishBuild(p);
	}

	public static void sendMail() {

		Project p = initAntProject();
		p.executeTarget("send-mail");

		finishBuild(p);
	}

}