package com.scheduler;
import java.text.SimpleDateFormat;

import java.util.Date;

public class AlarmClock {

	private final Scheduler scheduler = new Scheduler();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd MMM yyyy HH:mm:ss.SSS");
	
	private final int hourOfDay, minute, second;

	public AlarmClock(int hourOfDay, int minute, int second) {
		this.hourOfDay = hourOfDay;
		this.minute = minute;
		this.second = second;
	}
	
	public void start() {
		scheduler.schedule(new SchedulerTask() {
			public void run() {
				soundAlarm();
			}

			private void soundAlarm() {
				System.out.println("Wake up! " + "It's "
						+ dateFormat.format(new Date()));
				// Start a new thread to sound an alarm
			}
		}, new DailyIterator(hourOfDay, minute, second));
	}
	
//	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	
//	private final int year, month, day, hour, minute, second;
//	
//	public AlarmClock(int year, int month, int day,int hour, int minute, int second) {
//		this.year = year;
//		this.month = month;
//		this.day = day;
//		this.hour = hour;
//		this.minute = minute;
//		this.second = second;
//	}
//	
//	public void start() {
//		scheduler.schedule(new SchedulerTask() {
//			public void run() {
//				soundAlarm();
//			}
//
//			private void soundAlarm() {
//				System.out.println("Wake up! " + "It's "
//						+ dateFormat.format(new Date()));
//				// Start a new thread to sound an alarm
//			}
//		}, new DailyIterator(hourOfDay, minute, second));
//	}

	public static void main(String[] args) {
		AlarmClock alarmClock = new AlarmClock(13, 10, 50);
		alarmClock.start();
	}
}
