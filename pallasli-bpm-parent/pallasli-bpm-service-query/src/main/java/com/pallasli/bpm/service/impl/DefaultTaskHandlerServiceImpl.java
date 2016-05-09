package com.pallasli.bpm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.query.TaskHandlerService;

public class DefaultTaskHandlerServiceImpl implements TaskHandlerService {

	@Autowired
	TaskService taskService;

	@Override
	public void complete(String taskId) {

		Map<String, Object> vars = new HashMap<String, Object>();
		Map<String, Object> bpm_node_select = new HashMap<String, Object>();
		bpm_node_select.put("sid-03811B67-A918-492F-B5B4-3E9F366D9519", true);
		vars.put("bpm_node_select", bpm_node_select);
		taskService.complete(taskId, vars);
	}

	@Override
	public void complete(String taskId, String nextUser) {
		Map<String, Object> vars = new HashMap<String, Object>();
		Map<String, Object> bpm_node_select = new HashMap<String, Object>();
		bpm_node_select.put("sid-03811B67-A918-492F-B5B4-3E9F366D9519", true);
		vars.put("bpm_node_select", bpm_node_select);
		taskService.complete(taskId, vars);
		taskService.addCandidateUser(taskId, nextUser);
	}

}
