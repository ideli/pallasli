package com.pallasli.ws.bpm.activiti.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pallasli.bpm.entity.InstanceInfo;
import com.pallasli.bpm.entity.TaskInfo;

public interface TaskServiceDao {

	public List<TaskInfo> findTaskInfos(String user, int state, String keyword, Date s, Date e, int firstNumber,
			int pageSize);

	public InstanceInfo completeTask(String user, String taskId, Map<String, Object> variables);

}
