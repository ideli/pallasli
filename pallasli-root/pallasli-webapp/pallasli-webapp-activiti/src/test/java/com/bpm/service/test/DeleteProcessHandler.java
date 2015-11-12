package com.bpm.service.test;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.jobexecutor.JobHandler;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.JobEntity;

public class DeleteProcessHandler implements JobHandler {

	public static final String TYPE = "delete-process";

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	public void execute(JobEntity job, String configuration,
			ExecutionEntity execution, CommandContext commandContext) {
		execution.deleteCascade("");

	}

}
