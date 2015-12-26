package com.pallasli.run.mac;

import org.junit.Test;

public class CommandTest {
	@Test
	public void find() {
		new Command().find();
		new Command().start("/Users/lyt1987/nexusStart.sh");
	}
}
