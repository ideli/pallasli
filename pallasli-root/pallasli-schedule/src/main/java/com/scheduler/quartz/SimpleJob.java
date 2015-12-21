package com.scheduler.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job {

	private static Logger _log = LoggerFactory.getLogger(SimpleJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("dslaljjlafljj");
		_log.info(" 咫尺天涯: " + new Date());

	}
}