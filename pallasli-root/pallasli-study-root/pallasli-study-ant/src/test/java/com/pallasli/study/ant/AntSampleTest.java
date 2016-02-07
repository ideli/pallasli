package com.pallasli.study.ant;

import org.apache.tools.ant.Project;
import org.junit.Test;

public class AntSampleTest {

	String path = ".//src/test/resources/sample.xml";

	@Test
	public void initAntProject() {
		Project p = AntSample.initAntProject(path);
		// Assert.assertEquals("myproject", p.getName());
	}

	@Test
	public void buildJar() {
		AntSample.buildJar(path);
	}

	@Test
	public void buildWar() {
		AntSample.buildWar(path);

	}

	@Test
	public void generateJavaDoc() {
		AntSample.generateJavaDoc(path);

	}

	@Test
	public void deploy() {
		AntSample.deploy(path);

	}

	@Test
	public void start() {
		AntSample.start(path);

	}

	@Test
	public void compile() {
		AntSample.compile(path);

	}

	@Test
	public void uploadFtp() {
		AntSample.uploadFtp(path);

	}

	@Test
	public void sendMail() {
		AntSample.sendMail(path);

	}
}
