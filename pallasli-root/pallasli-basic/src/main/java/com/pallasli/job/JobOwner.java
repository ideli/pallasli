package com.pallasli.job;

import java.text.SimpleDateFormat;

public class JobOwner {
	private final TimerJob timerJob = new TimerJob();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd MMM yyyy HH:mm:ss.SSS");

	private final int hourOfDay, minute, second;

	public JobOwner(int hourOfDay, int minute, int second) {
		this.hourOfDay = hourOfDay;
		this.minute = minute;
		this.second = second;
	}

	public void doJob() {
		System.out.println(timerJob);
		System.out.println(dateFormat);
		System.out.println(hourOfDay + "" + minute + "" + second);
	}

}
