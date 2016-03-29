package com.pallasli.bpm.service.impl;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.TaskQueryService;

public class DefaultTaskQueryServiceImpl implements TaskQueryService {

	@Autowired
	TaskService taskService;

	@Override
	public List<Task> find(String user, String proInstId) {
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(user).processInstanceId(proInstId)
				.active().list();
		return taskList;
	}

	@Override
	public List<Task> find(String proInstId) {

		List<Task> taskList = taskService.createTaskQuery().processInstanceId(proInstId).active().list();
		return taskList;
	}

}
