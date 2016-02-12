package com.pallasli.study.scheduler.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("dslaljjlafljj");
		System.out.println(" 咫尺天涯: " + new Date());

	}
}