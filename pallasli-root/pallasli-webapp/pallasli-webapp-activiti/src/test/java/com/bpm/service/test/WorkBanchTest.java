package com.bpm.service.test;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;

public class WorkBanchTest extends PluggableActivitiTestCase {
	public void findMyApplyedTask() {
		if (true)
			return;
		String userId = "";
		List<HistoricProcessInstance> instanceList = historyService.createHistoricProcessInstanceQuery()
				.startedBy(userId).list();
		List<String> myApplyedProcessIdList = new ArrayList<String>();
		for (HistoricProcessInstance hi : instanceList) {
			myApplyedProcessIdList.add(hi.getId());
			historyService.createHistoricTaskInstanceQuery().processInstanceId(hi.getId());
		}
		// historyService.createHistoricTaskInstanceQuery().processInstanceIdIn(
		// myApplyedProcessIdList);
	}

	public void findMyHandledTask() {
		if (true)
			return;
		String userId = "";
		historyService.createHistoricTaskInstanceQuery().taskAssignee(userId);
	}

	public void findMyReceivedTask() {
		if (true)
			return;
		String userId = "";
		historyService.createHistoricTaskInstanceQuery().taskAssignee(userId);

		historyService.createHistoricTaskInstanceQuery().taskCandidateGroup("").or().taskAssignee("");
		taskService.createNativeTaskQuery().sql("").list();
	}

	public void findMyApplyedProcess() {
		if (true)
			return;
		String userId = "";
		historyService.createHistoricProcessInstanceQuery().startedBy(userId).list();
	}

	public void findMyTerminateProcess() {
	}

	// 与我相关的流程
	public void findMyInvolvedProcess() {
		if (true)
			return;
		historyService.createHistoricProcessInstanceQuery().involvedUser("");
	}
}
