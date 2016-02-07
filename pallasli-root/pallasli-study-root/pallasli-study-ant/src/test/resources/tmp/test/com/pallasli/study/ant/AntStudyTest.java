package com.pallasli.study.ant;

import org.apache.tools.ant.Project;
import org.junit.Test;

public class AntStudyTest {

	@Test
	public void buildProject() {

		//
		// 编译
		// 打包（jar/war）
		// 部署
		// javadoc

		runProjectTask("build-compile.xml", null);
		runProjectTask("build-jar.xml", null);
		runProjectTask("build-deploy.xml", null);
		runProjectTask("build-javadoc.xml", null);
	}

	// @Test
	public void buildWebProject() {
		//
		// 编译
		// 打包（jar/war）
		// 部署
		runProjectTask("build-compile.xml", "compile-web");
		runProjectTask("build-war.xml", null);
		runProjectTask("build-deploy.xml", "deploy-web");
	}

	@Test
	public void operateFile() {
		runProjectTask("build-fileOperation.xml", "mkdir");
		runProjectTask("build-fileOperation.xml", "copydir");
		runProjectTask("build-fileOperation.xml", "deletedir");

	}

	// @Test
	public void operateFtp() {
		runProjectTask("build-ftp.xml", null);

	}

	// @Test
	public void operateEmail() {
		runProjectTask("build-email.xml", null);

	}

	@Test
	public void other() {
		runProjectTask("build-info.xml", null);
		runProjectTask("build-useTaskdef.xml", null);
		runProjectTask("build-foreach.xml", null);
		runProjectTask("build-condition.xml", null);
		runProjectTask("build-variable.xml", null);
		runProjectTask("build-antfile.xml", null);

	}

	private void runProjectTask(String path, String taskName) {
		Project p = AntStudy.initAntProject(".//src/test/resources/ant-study/"
				+ path);
		p.executeTarget(taskName == null ? p.getDefaultTarget() : taskName);
		AntSample.finishBuild(p);
	}
}
