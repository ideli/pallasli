package com.lyt.pallas.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleQuartzJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		System.out.println("In SimpleQuartzJob - executing its JOB at "
				+ new Date() + " by " + context.getTrigger().getName());
		// System.out.println(context.getMergedJobDataMap().get("name"));

	}

}
