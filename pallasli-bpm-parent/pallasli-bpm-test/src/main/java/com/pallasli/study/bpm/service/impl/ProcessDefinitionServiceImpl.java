package com.pallasli.study.bpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pallasli.study.bpm.model.NodeInfo;
import com.pallasli.study.bpm.service.ProcessDefinitionService;
import com.pallasli.study.bpm.utils.ProcessDefinitionUtils;

@Transactional
@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected FormService formService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected ManagementService managementService;
	@Autowired
	protected IdentityService identityService;

	@Override
	public List<NodeInfo> getFirstNodes(String processDefId) {
		ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity) repositoryService
				.createProcessDefinitionQuery().singleResult();
		List<ActivityImpl> activityImpls = processDefEntity.getActivities();
		ActivityImpl activityImpl = null;
		for (ActivityImpl activity : activityImpls) {
			if (ProcessDefinitionUtils.isStartEvent(activity)) {
				activityImpl = activity;
				break;
			}
		}
		List<NodeInfo> nodeInfos = new ArrayList<NodeInfo>();
		List<PvmTransition> transitions = ProcessDefinitionUtils
				.getActOutgoingTransitions(activityImpl);
		for (PvmTransition transition : transitions) {
			PvmActivity pvmActivity = transition.getDestination();
			NodeInfo nodeInfo = new NodeInfo();
			// log.debug("transition.getDestination():{}", pvmActivity);
			// 对3种路由的支持
			if (ProcessDefinitionUtils.isGateway(pvmActivity)) {
				// getNextNodeListGateway( );
			} else {
				// infoList.getInfos().add(
				// getNodeInfoBy((ActivityImpl) pvmActivity,
				// processInstance, NodeInfo.NODE_TYPE_CHECKBOX,
				// false, true, user, processKey, variables,
				// goOutSubProc));
			}
			nodeInfos.add(nodeInfo);
		}
		return nodeInfos;
	}

	@Override
	public List<NodeInfo> getPreNodes(String processDefId, String curNodeId) {
		return null;
	}

	@Override
	public List<NodeInfo> getNextNodes(String processDefId, String curNodeId) {
		return null;
	}

	@Override
	public NodeInfo getNodeInfo(String processDefId, String curNodeId) {
		return null;
	}

	/**
	 * 根据实例编号查找下一个任务节点
	 * 
	 * @param String
	 *            procInstId ：实例编号
	 * @return
	 */
	public TaskDefinition nextTaskDefinition(String procInstId) {
		// 流程标示
		String processDefinitionId = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(procInstId).singleResult()
				.getProcessDefinitionId();

		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		// 执行实例
		ExecutionEntity execution = (ExecutionEntity) runtimeService
				.createProcessInstanceQuery().processInstanceId(procInstId)
				.singleResult();
		// 当前实例的执行到哪个节点
		String activitiId = execution.getActivityId();
		// 获得当前任务的所有节点
		List<ActivityImpl> activitiList = def.getActivities();
		String id = null;
		for (ActivityImpl activityImpl : activitiList) {
			id = activityImpl.getId();
			if (activitiId.equals(id)) {
				System.out.println("当前任务：" + activityImpl.getProperty("name"));
				return nextTaskDefinition(activityImpl, activityImpl.getId(),
						"${iscorrect==1}");
				// System.out.println(taskDefinition.getCandidateGroupIdExpressions().toArray()[0]);
				// return taskDefinition;
			}
		}
		return null;
	}

	/**
	 * 下一个任务节点
	 * 
	 * @param activityImpl
	 * @param activityId
	 * @param elString
	 * @return
	 */
	private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl,
			String activityId, String elString) {
		if ("userTask".equals(activityImpl.getProperty("type"))
				&& !activityId.equals(activityImpl.getId())) {
			TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl
					.getActivityBehavior()).getTaskDefinition();
			// taskDefinition.getCandidateGroupIdExpressions().toArray();
			return taskDefinition;
		} else {
			List<PvmTransition> outTransitions = activityImpl
					.getOutgoingTransitions();
			List<PvmTransition> outTransitionsTemp = null;
			for (PvmTransition tr : outTransitions) {
				PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
				if ("exclusiveGateway".equals(ac.getProperty("type"))) {
					outTransitionsTemp = ac.getOutgoingTransitions();
					if (outTransitionsTemp.size() == 1) {
						return nextTaskDefinition(
								(ActivityImpl) outTransitionsTemp.get(0)
										.getDestination(), activityId, elString);
					} else if (outTransitionsTemp.size() > 1) {
						for (PvmTransition tr1 : outTransitionsTemp) {
							Object s = tr1.getProperty("conditionText");
							if (elString.equals(StringUtils.trim(s.toString()))) {
								return nextTaskDefinition(
										(ActivityImpl) tr1.getDestination(),
										activityId, elString);
							}
						}
					}
				} else if ("userTask".equals(ac.getProperty("type"))) {
					return ((UserTaskActivityBehavior) ((ActivityImpl) ac)
							.getActivityBehavior()).getTaskDefinition();
				} else {
					System.out.println(ac.getProperty("type"));
				}
			}
			return null;
		}
	}

	@Override
	public void getProcessDiagram(String processDefId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getNodeLocation(String processDefId) {
		// TODO Auto-generated method stub

	}
}
