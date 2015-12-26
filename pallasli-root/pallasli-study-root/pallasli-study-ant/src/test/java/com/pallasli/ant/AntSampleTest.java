package com.pallasli.ant;

import org.apache.tools.ant.Project;
import org.junit.Assert;
import org.junit.Test;

public class AntSampleTest {

	@Test
	public void initAntProject() {
		Project p = AntSample.initAntProject();
		Assert.assertEquals("myproject", p.getName());
	}

	@Test
	public void buildJar() {
		AntSample.buildJar();
	}

	@Test
	public void buildWar() {
		AntSample.buildWar();

	}

	@Test
	public void generateJavaDoc() {
		AntSample.generateJavaDoc();

	}

	@Test
	public void deploy() {
		AntSample.deploy();

	}

	@Test
	public void start() {
		AntSample.start();

	}

	@Test
	public void compile() {
		AntSample.compile();

	}

	@Test
	public void uploadFtp() {
		AntSample.uploadFtp();

	}

	@Test
	public void sendMail() {
		AntSample.sendMail();

	}
}
