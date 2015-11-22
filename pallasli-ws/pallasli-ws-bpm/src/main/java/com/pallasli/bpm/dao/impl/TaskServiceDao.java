package com.pallasli.bpm.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pallasli.bpm.bean.InstanceInfo;
import com.pallasli.bpm.bean.TaskInfo;

public class TaskServiceDao {

	public List<TaskInfo> findTaskInfos(String user, int state, String keyword,
			Date s, Date e, int firstNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public InstanceInfo completeTask(String user, String taskId,
			Map<String, Object> variables) {
		// TODO Auto-generated method stub
		return null;
	}

}
