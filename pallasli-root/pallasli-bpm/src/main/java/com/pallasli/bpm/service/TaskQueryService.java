package com.pallasli.bpm.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface TaskQueryService {

	public List<Task> find(String user, String proInstId);

	public List<Task> find(String proInstId);
}
