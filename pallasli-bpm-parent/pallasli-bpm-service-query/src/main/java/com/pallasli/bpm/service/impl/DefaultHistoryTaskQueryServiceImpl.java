package com.pallasli.bpm.service.impl;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.query.HistoryTaskQueryService;

public class DefaultHistoryTaskQueryServiceImpl implements HistoryTaskQueryService {

	@Autowired
	HistoryService historyService;

	@Override
	public List<HistoricTaskInstance> listTaskByCandidateUser(String user) {
		List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery()
				.taskCandidateUser(user).list();
		return hisTaskList;
	}

	@Override
	public List<HistoricTaskInstance> listTaskByAuditUser(String user) {
		List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery().taskAssignee(user)
				.list();
		return hisTaskList;

	}

}
