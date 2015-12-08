package com.lyt.pallas.quartz.trigger;

import com.lyt.pallas.quartz.job.SimpleQuartzJob;

public class SimpleTriggerRunner {

	public static void task() throws SchedulerException {

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();

		Scheduler scheduler = schedulerFactory.getScheduler();

		long ctime = System.currentTimeMillis();

		JobDetail jobDetail = new JobDetail("jobDetail-s1",
				"jobDetailGroup-s1", SimpleQuartzJob.class);

		SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger",
				"triggerGroup-s1");

		simpleTrigger.setRepeatInterval(1000);

		simpleTrigger.setRepeatCount(5);

		scheduler.scheduleJob(jobDetail, simpleTrigger);

		scheduler.start();
	}

	public static void main(String args[]) {
		try {
			task();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
