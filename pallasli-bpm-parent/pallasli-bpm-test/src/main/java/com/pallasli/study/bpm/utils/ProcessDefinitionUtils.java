package com.pallasli.study.bpm.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ProcessDefinitionUtils {

	private static final Logger log = LoggerFactory
			.getLogger(ProcessDefinitionUtils.class);

	private static int SEQUNCE_NUMBER = 0;

	public static ActivityImpl cloneActivity(
			ProcessDefinitionEntity processDefinition,
			ActivityImpl prototypeActivity, String newActivityId,
			String... fieldNames) {
		ActivityImpl clone = processDefinition.createActivity(newActivityId);

		copyFields(prototypeActivity, clone, fieldNames);

		return clone;
	}

	public static TaskDefinition cloneTaskDefinition(
			TaskDefinition taskDefinition) {
		TaskDefinition newTaskDefinition = new TaskDefinition(
				taskDefinition.getTaskFormHandler());
		BeanUtils.copyProperties(taskDefinition, newTaskDefinition);
		return newTaskDefinition;
	}

	public static ActivityImpl createActivity(ProcessEngine processEngine,
			ProcessDefinitionEntity processDefinition,
			ActivityImpl prototypeActivity, String cloneActivityId,
			List<String> assignee) {

		ActivityImpl clone = cloneActivity(processDefinition,
				prototypeActivity, cloneActivityId, "executionListeners",
				"properties");

		UserTaskActivityBehavior activityBehavior = (UserTaskActivityBehavior) (prototypeActivity
				.getActivityBehavior());

		TaskDefinition taskDefinition = cloneTaskDefinition(activityBehavior
				.getTaskDefinition());
		taskDefinition.setKey(cloneActivityId);

		if (assignee != null) {
			taskDefinition.getCandidateUserIdExpressions().add(
					new FixedValue(assignee));
		}

		UserTaskActivityBehavior cloneActivityBehavior = ((ProcessEngineConfigurationImpl) processEngine
				.getProcessEngineConfiguration()).getActivityBehaviorFactory()
				.createUserTaskActivityBehavior(null, taskDefinition);
		clone.setActivityBehavior(cloneActivityBehavior);

		return clone;
	}

	// public static ActivityImpl createActivity(ProcessEngine processEngine,
	// ProcessDefinitionEntity processDefinition,
	// String prototypeActivityId, String cloneActivityId,
	// List<String> assignee) {
	// ActivityImpl prototypeActivity = ProcessDefinitionUtil.getActivity(
	// processEngine.getRepositoryService(),
	// processDefinition.getId(), prototypeActivityId);
	//
	// return createActivity(processEngine, processDefinition,
	// prototypeActivity, cloneActivityId, assignee);
	// }

	// protected void createActivityChain(List<ActivityImpl> activities,
	// ActivityImpl nextActivity) {
	// for (int i = 0; i < activities.size(); i++) {
	// // 设置各活动的下线
	// activities.get(i).getOutgoingTransitions().clear();
	// activities.get(i).createOutgoingTransition("flow" + (i + 1))
	// .setDestination(i == activities.size() - 1 ? nextActivity :
	// activities.get(i + 1));
	// }
	// }

	public static String createUniqueActivityId(String processInstanceId,
			String prototypeActivityId) {
		return processInstanceId + ":" + prototypeActivityId + ":"
				+ System.currentTimeMillis() + "-" + (SEQUNCE_NUMBER++);
	}

	public static void copyFields(Object source, Object target,
			String... fieldNames) {
		for (String fieldName : fieldNames) {
			try {
				Field field = FieldUtils.getField(source.getClass(), fieldName,
						true);
				field.setAccessible(true);
				field.set(target, field.get(source));
			} catch (Exception e) {
				log.warn(e.getMessage());
			}
		}
	}

	public static Expression stringToExpression(
			ProcessEngineConfigurationImpl conf, String expr) {
		return conf.getExpressionManager().createExpression(expr);
	}

	public static Expression stringToExpression(String expr) {
		return new FixedValue(expr);
	}

	public static Set<Expression> stringToExpressionSet(String exprs) {
		Set<Expression> set = new LinkedHashSet<Expression>();
		for (String expr : exprs.split(";")) {
			set.add(stringToExpression(expr));
		}

		return set;
	}

	// public static ActivityImpl getActivity(RepositoryService repoService,
	// String processDefId, String activityId) {
	// ProcessDefinitionEntity pde = ProcessUtil.getProcessDefinition(
	// repoService, processDefId);
	// return pde.findActivity(activityId);
	// }

	public static TaskDefinition getTaskDefinition(ActivityImpl activityImpl) {

		if (!isUserTask(activityImpl)) {
			return null;
		}
		ActivityBehavior behavior = activityImpl.getActivityBehavior();
		if (behavior instanceof SequentialMultiInstanceBehavior) {
			behavior = ((SequentialMultiInstanceBehavior) behavior)
					.getInnerActivityBehavior();
		}
		if (behavior instanceof ParallelMultiInstanceBehavior) {
			behavior = ((ParallelMultiInstanceBehavior) behavior)
					.getInnerActivityBehavior();
		}
		TaskDefinition taskDefinition = null;

		if (behavior instanceof UserTaskActivityBehavior) {
			UserTaskActivityBehavior extBehavior = (UserTaskActivityBehavior) behavior;
			taskDefinition = extBehavior.getTaskDefinition();
		}

		return taskDefinition;

	}

	// public static ActivityImpl getFirstUserActivity(
	// RepositoryService repoService, String processDefId) {
	//
	// List<ActivityImpl> activityImpls = ProcessUtil
	// .getProcessDefinitionAllActivities(repoService, processDefId);
	//
	// List<ActivityImpl> activityList = new ArrayList<ActivityImpl>();
	//
	// for (ActivityImpl activityImpl : activityImpls) {
	// log.debug("getFirstUserActivity. -act.id:{},type:{}",
	// activityImpl.getId(), activityImpl.getProperty("type"));
	// if (ProcessDefinitionUtil.isStartEvent(activityImpl)) {
	// log.debug("getFirstUserActivity. startEvent.id:{}",
	// activityImpl.getId());
	// List<PvmTransition> transitions =
	// getActOutgoingTransitions(activityImpl);
	//
	// iterFindNextUserActivity(transitions, activityList, true, 0);
	//
	// break;
	//
	// }
	// }
	// log.debug("getFirstUserActivity. activityList.size():{}",
	// activityList.size());
	// if (activityList.size() > 0) {
	// ActivityImpl act = activityList.get(0);
	// log.debug("getFirstUserActivity. find act:{}", act);
	// return act;
	// }
	//
	// return null;
	// }
	//
	// /**
	// *
	// 通过getIncomingTransitions并且递归，找到currentActivityId（与activityImpl.getId()相对比
	// * ）结点之前的userTask或callActivity结点
	// *
	// * @param repoService
	// * @param processDefId
	// * @param currentActivityId
	// * @return
	// */
	// private static List<ActivityImpl> findBackUserActivity(
	// RepositoryService repoService, String processDefId,
	// String currentActivityId) {
	//
	// List<ActivityImpl> activityImpls = ProcessUtil
	// .getProcessDefinitionAllActivities(repoService, processDefId);
	//
	// List<ActivityImpl> activityList = new ArrayList<ActivityImpl>();
	// boolean findAct = false;
	// for (ActivityImpl activityImpl : activityImpls) {
	//
	// if (currentActivityId.equalsIgnoreCase(activityImpl.getId())) {
	// findAct = true;
	//
	// List<PvmTransition> transitions = activityImpl
	// .getIncomingTransitions();
	//
	// iterFindBackUserActivity(transitions, activityList, true, 0);
	//
	// break;
	//
	// }
	// }
	// if (findAct == false) {
	// // 没找到当前节点，有可能当前节点是在嵌入式subprocess子流程中，此时把嵌入式子流程中的节点也加到所有结点的list中
	// // 担心直接用那个List会改库，所以还是复制出另一个list吧。
	// List<ActivityImpl> activityImplsCopy = new ArrayList<ActivityImpl>();
	// activityImplsCopy.addAll(activityImpls);
	// int size = activityImplsCopy.size();
	// for (int i = 0; i < size; i++) {
	// ActivityImpl eact = activityImplsCopy.get(i);
	// if (!eact.getActivities().isEmpty()) {
	// // 加到list的最后。顺序不重要，因为下面的查找是靠连线来迭代查找的
	// activityImplsCopy.addAll(size, eact.getActivities());
	// size = activityImplsCopy.size();
	// }
	//
	// for (ActivityImpl activityImpl : activityImplsCopy) {
	// if (currentActivityId
	// .equalsIgnoreCase(activityImpl.getId())) {
	// List<PvmTransition> transitions = activityImpl
	// .getIncomingTransitions();
	// iterFindBackUserActivity(transitions, activityList,
	// true, 0);
	// break;
	//
	// }
	// }
	// }
	//
	// }
	//
	// return activityList;
	// }

	/**
	 * 通过getIncomingTransitions并且递归，找到currentActivityId（与activityImpl.getId()相对比
	 * ）结点之前的userTask或callActivity结点，并去重
	 * 
	 * @param repoService
	 * @param processDefId
	 * @param currentActivityId
	 * @return
	 */
	// public static Map<String, ActivityImpl> findBackUserActivityId(
	// RepositoryService repoService, String processDefId,
	// String currentActivityId) {
	//
	// Map<String, ActivityImpl> result = new HashMap<String, ActivityImpl>();
	//
	// List<ActivityImpl> userActivityList = findBackUserActivity(repoService,
	// processDefId, currentActivityId);
	//
	// for (ActivityImpl impl : userActivityList) {
	// String activityId = impl.getId();
	// if (!result.containsKey(activityId)) {
	// result.put(activityId, impl);
	// }
	// }
	//
	// return result;
	// }

	private static void iterFindBackUserActivity(
			List<PvmTransition> transitions, List<ActivityImpl> activityList,
			boolean isCont, int level) {

		if (level > 10)
			return;

		for (PvmTransition transition : transitions) {

			PvmActivity pvmActivity = transition.getSource();

			if (isUserTask(pvmActivity)) {
				activityList.add((ActivityImpl) pvmActivity);
				// weigq0724 永涛提出不能回退到子流程，那也不要回退到子流程以前的结点了。
			} else if (isCallActivity(pvmActivity) || isSubProcess(pvmActivity)) {
				return;
				// } else if (isCallActivity(pvmActivity)) {
				// 调用式子流程也按用户任务一样添加进来，到转化为nodeinfo的时候再处理
				// weigq
				// 子流程到主流程后，子流程实际已结束，无法退回到子流程中的最后一个节点，所以不加子流程中的节点了。要跳过此节点。跳过的做法就是把这个if分支注释掉就好了。

				// activityList.add((ActivityImpl) pvmActivity);

				// } else if (isSubProcess(pvmActivity)) {
				// 嵌入式子流程，就把子流程中的节点全加进来好了
				// weigq
				// 子流程到主流程后，子流程实际已结束，无法退回到子流程中的最后一个节点，所以不加子流程中的节点了。要跳过此节点。跳过的做法就是把这个if分支注释掉就好了。
				// List subProcActs = pvmActivity.getActivities();
				// for(Object sa:subProcActs){
				// ActivityImpl saAct = (ActivityImpl)sa;
				// activityList.add(saAct);
				// }

			}
			if (isCont) {
				transitions = pvmActivity.getIncomingTransitions();
				// 在嵌入式子流程中，进入的连线找不到时，就到主流程中找。如果不在就不管了
				if (transitions == null || transitions.isEmpty()) {
					ActivityImpl act = (ActivityImpl) pvmActivity;
					ActivityImpl actParent = act.getParentActivity();
					if (actParent != null) {
						transitions = actParent.getIncomingTransitions();
					}
				}
				// 嵌入式子流程结束
				iterFindBackUserActivity(transitions, activityList, true,
						level + 1);
			}
		}
	}

	private static void iterFindNextUserActivity(
			List<PvmTransition> transitions, List<ActivityImpl> activityList,
			boolean isCont, int level) {
		log.debug("iterFindNextUserActivity. transitions.size:{}",
				transitions.size());
		if (level > 10)
			return;

		for (PvmTransition transition : transitions) {
			PvmActivity pvmActivity = transition.getDestination();
			if (isUserTask(pvmActivity) || isCallActivity(pvmActivity)
					|| isSubProcess(pvmActivity)) {
				log.debug("iterFindNextUserActivity. ut. act:{}", pvmActivity);
				activityList.add((ActivityImpl) pvmActivity);
				// } else if (isGateway(pvmActivity) && isOneInTran(pvmActivity)
				// && isCont) {
			} else if (isOneInTran(pvmActivity) && isCont) {
				log.debug("iterFindNextUserActivity. Gateway");
				iterFindNextUserActivity(
						getActOutgoingTransitions(pvmActivity), activityList,
						false, level + 1);
			}
		}
	}

	/**
	 * 是否只有一个进来的连线。并行网关和包含网关有汇聚功能，不应该被直接跳过
	 * 
	 * @param pvmActivity
	 * @return
	 */
	public static boolean isOneInTran(PvmActivity pvmActivity) {
		if (pvmActivity.getIncomingTransitions() != null
				&& pvmActivity.getIncomingTransitions().size() == 1) {
			return true;
		}
		return false;
	}

	public static String getActivityType(PvmActivity activityImpl) {
		String activityType = (String) activityImpl.getProperty("type");
		return activityType;
	}

	public static String getActivityName(PvmActivity activityImpl) {
		String activityName = (String) activityImpl.getProperty("name");
		return activityName;
	}

	public static boolean isStartEvent(PvmActivity activityImpl) {
		if ("startEvent".equalsIgnoreCase(getActivityType(activityImpl))
				|| "startTimerEvent"
						.equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}
		return false;
	}

	public static boolean isEndEvent(PvmActivity activityImpl) {
		if ("endEvent".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}

		return false;
	}

	public static boolean isUserTask(PvmActivity activityImpl) {
		if ("userTask".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}

		return false;
	}

	/**
	 * 排他网关
	 * 
	 * @param activityImpl
	 * @return
	 */
	public static boolean isExclusiveGateway(PvmActivity activityImpl) {

		if ("exclusiveGateway".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}
		return false;
	}

	/**
	 * 并行网关
	 * 
	 * @param activityImpl
	 * @return
	 */
	public static boolean isParallelGateway(PvmActivity activityImpl) {

		if ("parallelGateway".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}
		return false;
	}

	/**
	 * 包含网关
	 * 
	 * @param activityImpl
	 * @return
	 */
	public static boolean isInclusiveGateway(PvmActivity activityImpl) {

		if ("inclusiveGateway".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}
		return false;
	}

	public static boolean isGateway(PvmActivity activityImpl) {

		if (isInclusiveGateway(activityImpl)) {
			return true;
		}

		if (isParallelGateway(activityImpl)) {
			return true;
		}

		if (isExclusiveGateway(activityImpl)) {
			return true;
		}

		return false;
	}

	public static boolean isCallActivity(PvmActivity activityImpl) {
		if ("callActivity".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}
		return false;
	}

	public static boolean isSubProcess(PvmActivity activityImpl) {
		if ("subProcess".equalsIgnoreCase(getActivityType(activityImpl))) {
			return true;
		}
		return false;
	}

	public static ActivityImpl getFirstUsertaskFromSubProcess(
			ActivityImpl actSubProcess) {
		log.debug("getFirstUsertaskFromSubProcess. actSubProcess:{}",
				actSubProcess);
		if (isSubProcess(actSubProcess)) {
			List<ActivityImpl> activityList = new ArrayList<ActivityImpl>();
			List<ActivityImpl> actList = actSubProcess.getActivities();
			log.debug("activityImpl.getActivities() size:{}", actList.size());
			ActivityImpl startEvent = null;
			for (ActivityImpl act : actList) {
				log.debug("-name:{}", act.getProperty("name"));
				if (isStartEvent(act)) {
					startEvent = act;
					log.debug("getFirstUsertaskFromSubProcess. startEvent:{}",
							startEvent);
					break;
				}
			}
			if (startEvent != null) {
				List<PvmTransition> outs = getActOutgoingTransitions(startEvent);
				if (!outs.isEmpty()) {
					iterFindNextUserActivity(outs, activityList, true, 0);
				}
			}
			log.debug("activityList.size:{}", activityList.size());
			if (!activityList.isEmpty()) {
				log.debug("find utnode(userTask,callact,subproc).");
				return activityList.get(0);
			}
		}
		return null;
	}

	// public static ActivityImpl getActivity(RepositoryService repoService,
	// String taskId, TaskService taskService) {
	//
	// Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	//
	// return getActivity(repoService, task);
	// }

	// public static ActivityImpl getActivity(RepositoryService repoService,
	// Task task) {
	//
	// String activityId = task.getTaskDefinitionKey();
	//
	// return getTargetActivity(repoService, task, activityId);
	//
	// }
	//
	// public static ActivityImpl getTargetActivity(RepositoryService
	// repoService,
	// String taskId, TaskService taskService, String activityId) {
	// Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	// return getTargetActivity(repoService, task, activityId);
	// }
	//
	// public static ActivityImpl getTargetActivity(RepositoryService
	// repoService,
	// Task task, String activityId) {
	//
	// if (task == null) {
	// return null;
	// }
	//
	// String processDefId = task.getProcessDefinitionId();
	//
	// return getActivity(repoService, processDefId, activityId);
	//
	// }

	public static void grantPermission(ActivityImpl activity,
			String assigneeExpression, String candidateGroupIdExpressions,
			String candidateUserIdExpressions) throws Exception {
		TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activity
				.getActivityBehavior()).getTaskDefinition();
		taskDefinition.setAssigneeExpression(assigneeExpression == null ? null
				: new FixedValue(assigneeExpression));
		FieldUtils.writeField(taskDefinition, "candidateUserIdExpressions",
				stringToExpressionSet(candidateUserIdExpressions), true);
		FieldUtils.writeField(taskDefinition, "candidateGroupIdExpressions",
				stringToExpressionSet(candidateGroupIdExpressions), true);
	}

	// public static List<PvmTransition>
	// clearActivityOutgoingTransition(ActivityImpl activity) {
	// List<PvmTransition> result = new ArrayList<PvmTransition>();
	//
	// List<PvmTransition> pvmTransList = activity.getOutgoingTransitions();
	//
	// if (pvmTransList != null && pvmTransList.size() > 0) {
	// for (PvmTransition tran : pvmTransList) {
	// result.add(tran);
	// }
	// pvmTransList.clear();
	// }
	//
	// return result;
	// }

	public static void restoreActivityOutgoingTransition(ActivityImpl activity,
			List<PvmTransition> originPvmTransitionList) {
		List<PvmTransition> pvmTransList = getActOutgoingTransitions(activity);
		if (pvmTransList != null && pvmTransList.size() > 0) {
			pvmTransList.clear();
		}

		if (originPvmTransitionList != null
				&& originPvmTransitionList.size() > 0) {
			for (PvmTransition tran : originPvmTransitionList) {
				pvmTransList.add(tran);
			}
		}

	}

	// public static String getEndEventId(RepositoryService repoService,
	// String processDefId) {
	// ActivityImpl endEvent = getEndEvent(repoService, processDefId);
	// if (endEvent != null) {
	// return endEvent.getId();
	// }
	// return null;
	//
	// }

	// public static ActivityImpl getEndEvent(RepositoryService repoService,
	// String processDefId) {
	// List<ActivityImpl> activityImpls = ProcessUtil
	// .getProcessDefinitionAllActivities(repoService, processDefId);
	// for (ActivityImpl activityImpl : activityImpls) {
	// if (isEndEvent(activityImpl)) {
	// return activityImpl;
	// }
	// }
	// return null;
	//
	// }

	public static String getCalledElementFromCallActivity(
			ActivityImpl callActivity) {
		if (isCallActivity(callActivity)) {
			ActivityBehavior behavior = callActivity.getActivityBehavior();
			org.activiti.engine.impl.bpmn.behavior.CallActivityBehavior cabh = (org.activiti.engine.impl.bpmn.behavior.CallActivityBehavior) behavior;
			String subProcDefinitionKey = cabh.getProcessDefinitonKey();
			log.debug("subProcDifineKey:{}", subProcDefinitionKey);
			return subProcDefinitionKey;
		} else {
			return null;
		}
	}

	/**
	 * 是否在嵌入式子流程中的结点上，如果是找到此子流程的subProcess元素。如果不是就返回null
	 * 
	 * @param currentNodeId
	 * @param processDefId
	 * @return
	 */
	// public static ActivityImpl getParentSubProcessAct(
	// RepositoryService repoService, String currentNodeId,
	// String processDefId) {
	// log.debug("getParentSubProcessAct. currentNodeId:{},processDefId:{}",
	// currentNodeId, processDefId);
	// ProcessDefinitionEntity processDefEntity = ProcessUtil
	// .getProcessDefinition(repoService, processDefId);
	// ActivityImpl currentAct = processDefEntity.findActivity(currentNodeId);
	// if (currentAct != null) {
	// ActivityImpl parentAct = currentAct.getParentActivity();
	// log.debug("getParentSubProcessAct.   parentAct:{}", parentAct);
	// return parentAct;
	// }
	// return null;
	// }

	/**
	 * 向上递归找到最上层的嵌入式子流程的那个SubProcess节点对应的act
	 * 
	 * @param act
	 * @return
	 */
	public static ActivityImpl getTopSubProcessAct(ActivityImpl act) {
		ActivityImpl parent = act.getParentActivity();
		while (parent != null) {
			act = parent;
			parent = act.getParentActivity();
		}
		return act;
	}

	//
	// public static boolean isFirstNode(RepositoryService repoService,
	// String currentNodeId, String processDefId) {
	// boolean isFirst = false;
	// ActivityImpl subProc = ProcessDefinitionUtil.getParentSubProcessAct(
	// repoService, currentNodeId, processDefId);
	// if (subProc != null) {
	// isFirst = ProcessDefinitionUtil.isFirstNodeItrSubProc(
	// currentNodeId, subProc, 0);
	// if (isFirst) {
	// subProc = ProcessDefinitionUtil.getTopSubProcessAct(subProc);
	// isFirst = ProcessDefinitionUtil.isFirstUserTaskNode(
	// repoService, subProc.getId(), processDefId);
	// }
	// } else {
	// isFirst = ProcessDefinitionUtil.isFirstUserTaskNode(repoService,
	// currentNodeId, processDefId);
	// }
	// return isFirst;
	// }
	//
	// public static boolean isLastNode(RepositoryService repoService,
	// String currentNodeId, String processDefId) {
	// log.debug(
	// "ProcessDefinitionUtil.isLastNode.  currentNodeId:{},processDefId:{}",
	// currentNodeId, processDefId);
	// boolean isEnd = false;
	// ActivityImpl subProc = ProcessDefinitionUtil.getParentSubProcessAct(
	// repoService, currentNodeId, processDefId);
	// log.debug("ProcessDefinitionUtil.isLastNode.  subProc:{}", subProc);
	// if (subProc != null) {// 嵌入式子流程
	// isEnd = isLastNodeInSubProc(currentNodeId, subProc);
	// // //weigq 递归到最外层的写法
	// // isEnd = ProcessDefinitionUtil.isLastNodeItrSubProc(currentNodeId,
	// // subProc, 0);
	// // if (isEnd) {
	// // subProc = ProcessDefinitionUtil.getTopSubProcessAct(subProc);
	// // isEnd = ProcessDefinitionUtil.isLastUserTaskNode(repoService,
	// // subProc.getId(), processDefId);
	// // }
	// } else {
	// isEnd = ProcessDefinitionUtil.isLastUserTaskNode(repoService,
	// currentNodeId, processDefId);
	// }
	// return isEnd;
	// }

	/**
	 * 递归所有嵌入式子流程，看此结点是不是第一个
	 * 
	 * @param currentNodeId
	 * @param subProc
	 * @return
	 */
	private static boolean isFirstNodeItrSubProc(String currentNodeId,
			ActivityImpl subProc, int level) {
		while (isFirstNodeInSubProc(currentNodeId, subProc)) {
			level++;
			currentNodeId = subProc.getId();
			subProc = subProc.getParentActivity();
			if (subProc == null) {
				return true;
			}
			if (level > 20) {
				return false;
			}
		}
		return false;
	}

	/**
	 * 在此嵌入式子流程中，当前结点是不是第一个
	 * 
	 * @param currentNodeId
	 * @param subProc
	 * @return
	 */
	private static boolean isFirstNodeInSubProc(String currentNodeId,
			ActivityImpl subProc) {
		List<ActivityImpl> activityImpls = subProc.getActivities();
		for (ActivityImpl act : activityImpls) {
			if (currentNodeId.equalsIgnoreCase(act.getId())) {
				List<PvmTransition> transitions = act.getIncomingTransitions();
				for (PvmTransition transition : transitions) {
					PvmActivity pvmActivity = transition.getSource();
					if (!ProcessDefinitionUtils.isStartEvent(pvmActivity)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 嵌入式子流程判断是否是最后一节点
	 * 
	 * @param currentNodeId
	 * @param subProc
	 * @return
	 */
	private static boolean isLastNodeInSubProc(String currentNodeId,
			ActivityImpl subProc) {
		log.debug(
				"ProcessDefinitionUtil.isLastNodeInSubProc-.  subProc:{},currentNodeId:{}",
				subProc, currentNodeId);
		List<ActivityImpl> activityImpls = subProc.getActivities();
		for (ActivityImpl act : activityImpls) {
			if (currentNodeId.equalsIgnoreCase(act.getId())) {
				List<PvmTransition> transitions = getActOutgoingTransitions(act);
				for (PvmTransition transition : transitions) {
					PvmActivity pvmActivity = transition.getDestination();
					if (!ProcessDefinitionUtils.isEndEvent(pvmActivity)) {
						// 此结点后面还有需要用户参与的结点
						log.debug(
								"ProcessDefinitionUtil.isLastNodeInSubProc-. UserTask... act:{}",
								act);
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	// private static boolean isFirstUserTaskNode(RepositoryService repoService,
	// String activityId, String processDefId) {
	//
	// List<ActivityImpl> activityImpls = ProcessUtil
	// .getProcessDefinitionAllActivities(repoService, processDefId);
	//
	// for (ActivityImpl activityImpl : activityImpls) {
	//
	// // if (ProcessDefinitionUtil.isStartEvent(activityImpl)) {
	// //
	// // List<PvmTransition> transitions = activityImpl
	// // .getOutgoingTransitions();
	// //
	// // for (PvmTransition transition : transitions) {
	// //
	// // PvmActivity pvmActivity = transition.getDestination();
	// //
	// // // if (ProcessDefinitionUtil.isUserTask(pvmActivity)) {
	// //
	// // if (activityId.equalsIgnoreCase(pvmActivity.getId())) {
	// // return true;
	// // }
	// // // }
	// // }
	// //
	// // } else if (ProcessDefinitionUtil.isUserTask(activityImpl)) {
	// if (activityId.equalsIgnoreCase(activityImpl.getId())) {
	//
	// // for (;;) {
	// List<PvmTransition> transitions = activityImpl
	// .getIncomingTransitions();
	// // if (transitions == null || transitions.isEmpty()) {
	// // break;
	// // }
	// for (PvmTransition transition : transitions) {
	//
	// PvmActivity pvmActivity = transition.getSource();
	//
	// // if (ProcessDefinitionUtil.isStartEvent(pvmActivity)) {
	// // return true;
	// // } else
	// if (!ProcessDefinitionUtil.isStartEvent(pvmActivity)) {
	// return false;
	// }
	// }
	// return true;
	//
	// // activityImpl = (ActivityImpl) transitions.get(0)
	// // .getSource();
	//
	// // }
	//
	// }
	// // }
	// }
	//
	// return false;
	//
	// }
	//
	// private static boolean isLastUserTaskNode(RepositoryService repoService,
	// String activityId, String processDefId) {
	// log.debug("activityId:{},processDefId:{}", activityId, processDefId);
	// List<ActivityImpl> activityImpls = ProcessUtil
	// .getProcessDefinitionAllActivities(repoService, processDefId);
	// if (ThreadLock.getInst().readLock(activityId) == false) {
	// throw new BPMException("当前系统繁忙，请稍候再试." + activityId);
	// }
	// try {
	// for (ActivityImpl activityImpl : activityImpls) {
	// // if (ProcessDefinitionUtil.isUserTask(activityImpl)) {
	// if (activityId.equalsIgnoreCase(activityImpl.getId())) {
	// log.debug("activityId:{},activityImpl find", activityId);
	// List<PvmTransition> transitions =
	// getActOutgoingTransitions(activityImpl);
	// for (PvmTransition transition : transitions) {
	// PvmActivity pvmActivity = transition.getDestination();
	// if (log.isDebugEnabled()) {
	// log.debug("-destination:{}", pvmActivity);
	// log.debug("-destination.type:{}",
	// pvmActivity.getProperty("type"));
	// log.debug("-destination.id:{}", pvmActivity.getId());
	// }
	// if (!ProcessDefinitionUtil.isEndEvent(pvmActivity)) {
	// log.debug("return false");
	// return false;
	// }
	// }
	// return true;
	// }
	// // }
	// }
	// } catch (Exception e) {
	// log.error("", e);
	// } finally {
	// ThreadLock.getInst().readUnlock(activityId);
	// }
	//
	// return false;
	//
	// }

	public static boolean getFirtNodeItrSubprocHasCallActivity(
			RepositoryService repoService, ActivityImpl curAct, int deep) {
		if (deep > 20) {
			return false;
		}
		if (isSubProcess(curAct)) {
			ActivityImpl subProcFirstAct = getFirstUsertaskFromSubProcess(curAct);
			return getFirtNodeItrSubprocHasCallActivity(repoService,
					subProcFirstAct, deep + 1);
		} else if (isCallActivity(curAct)) {
			return true;
		} else {
			return false;
		}
	}

	// public static ActivityImpl getFirtNodeItrSubproc(
	// RepositoryService repoService, ActivityImpl curAct, int deep) {
	// if (deep > 20) {
	// return curAct;
	// }
	// if (isSubProcess(curAct)) {
	// ActivityImpl subProcFirstAct = getFirstUsertaskFromSubProcess(curAct);
	// return getFirtNodeItrSubproc(repoService, subProcFirstAct, deep + 1);
	// } else if (isCallActivity(curAct)) {
	// String subProcDefinitionKey = getCalledElementFromCallActivity(curAct);
	// String subProcessDefId = repoService.createProcessDefinitionQuery()
	// .processDefinitionKey(subProcDefinitionKey).latestVersion()
	// .singleResult().getId();
	// ActivityImpl subProcFirstAct = ProcessDefinitionUtil
	// .getFirstUserActivity(repoService, subProcessDefId);
	// return getFirtNodeItrSubproc(repoService, subProcFirstAct, deep + 1);
	//
	// } else {
	// return curAct;
	// }
	// }

	public static ActivityImpl getEndEventInSubProc(ActivityImpl subProcAct) {
		if (isSubProcess(subProcAct)) {
			List<ActivityImpl> taskBrothers = subProcAct.getActivities();
			ActivityImpl endEvent = null;
			for (ActivityImpl e : taskBrothers) {
				if (ProcessDefinitionUtils.isEndEvent(e)) {
					endEvent = e;
					break;
				}
			}
			return endEvent;
		}
		return null;
	}

	public static List<PvmTransition> getActOutgoingTransitions(PvmActivity act) {
		String actProcDefId = act.getProcessDefinition().getId();
		String actId = act.getId();
		List<PvmTransition> transitions = null;
		transitions = getOutgoingTransitionsFrmCache(actProcDefId, actId);
		if (transitions == null) {
			transitions = act.getOutgoingTransitions();
			putOutgoingTransitionsFrmCache(actProcDefId, actId, transitions);
		}
		return transitions;
	}

	private static Map<String, Map<String, List<PvmTransition>>> cacheActOutgoingTransitions = new HashMap<String, Map<String, List<PvmTransition>>>();

	private static List<PvmTransition> getOutgoingTransitionsFrmCache(
			String actProcDefId, String actId) {
		Map<String, List<PvmTransition>> cacheProc = cacheActOutgoingTransitions
				.get(actProcDefId);
		if (cacheProc == null) {
			cacheProc = new HashMap<String, List<PvmTransition>>();
			cacheActOutgoingTransitions.put(actProcDefId, cacheProc);
		}
		List<PvmTransition> trans = cacheProc.get(actId);
		return trans;
	}

	private static void putOutgoingTransitionsFrmCache(String actProcDefId,
			String actId, List<PvmTransition> trans) {
		if (trans == null) {
			trans = new ArrayList<PvmTransition>();
		}
		Map<String, List<PvmTransition>> cacheProc = cacheActOutgoingTransitions
				.get(actProcDefId);
		if (cacheProc == null) {
			cacheProc = new HashMap<String, List<PvmTransition>>();
			cacheActOutgoingTransitions.put(actProcDefId, cacheProc);
		}

		cacheProc.put(actId, trans);
	}

}
