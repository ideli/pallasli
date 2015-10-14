package com.pallasli.job;

import org.junit.Test;

public class JobOwnerTest {
	@Test
	public void doJob() {
		int hourOfDay = 0, minute = 0, second = 0;
		new JobOwner(hourOfDay, minute, second).doJob();
	}
}
