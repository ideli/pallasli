package com.pallasli.study.https;

import org.junit.Test;

public class ExportCertFormKeystoreTest {
	static String os = System.getProperty("os.name");

	@Test
	public void genkeyTest() {
		if (os.toLowerCase().startsWith("windows")) {

		} else {

		}
		// 生成密钥测试
		new ExportCertFormKeystore().genkey();
	}

	@Test
	public void exportTest() {
		// 导出证书文件测试
		new ExportCertFormKeystore().export();
	}

}