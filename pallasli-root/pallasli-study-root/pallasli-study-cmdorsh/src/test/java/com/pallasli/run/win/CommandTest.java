package com.pallasli.run.win;

import java.io.IOException;

import org.junit.Test;

public class CommandTest {
	@Test
	public void find() {
		try {
			Runtime.getRuntime().exec(
					new String[] { "rundll32", "url.dll,FileProtocolHandler",
							"http://www.baidu.com" });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
