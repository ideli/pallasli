package com.scheduler.quartz;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExample {

	private static Logger log = LoggerFactory.getLogger(SimpleExample.class);

	public void run() throws Exception {
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler sched = sf.getScheduler();

		System.out.println(new Date());
		Date runTime = DateBuilder.evenSecondDate(new Date());
		runTime = DateBuilder.evenSecondDate(runTime);
		runTime = DateBuilder.evenSecondDate(runTime);
		runTime = DateBuilder.evenSecondDate(runTime);
		System.out.println(runTime);
		// 通过过JobDetail封装HelloJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job1", "group1").build();

		// 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。
		// 接着设置调度的时间规则.当前时间运行
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1").startAt(runTime).build();

		// 注册并进行调度
		sched.scheduleJob(job, trigger);

		// 启动调度器
		sched.start();

		try {
			// 当前线程等待15秒
			Thread.sleep(15L * 1000L);
		} catch (Exception e) {

		}

		// 调度器停止运行
		sched.shutdown(true);

		log.error("结束运行。。。。");

	}

	public static void main(String[] args) throws Exception {
		SimpleExample example = new SimpleExample();
		example.run();

		JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("testJob_1", "group_1").build();

		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger_1", "group_1")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10) // 时间间隔
								.withRepeatCount(5) // 重复次数(将执行6次)
				).build();
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		sched.scheduleJob(jobDetail, trigger);

		sched.start();

	}
}
