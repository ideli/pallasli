package com.bpm.service.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.junit.Ignore;
import org.restlet.data.ChallengeScheme;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.atwasoft.bpm.image.HistoryProcessInstanceDiagramCmd;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessTest extends PluggableActivitiTestCase {

	public Model m;
	public Map<String, Object> variables = new HashMap<String, Object>();

	public String businessKey = "1000000";
	public String childBusinessKey = "200000";
	public String childBusinessKey2 = "300000";

	@Ignore
	public void tables(String url) {

		// User user = identityService.newUser("kermit");
		// user.setFirstName("Kermit");
		// user.setLastName("the Frog");
		// user.setPassword("kermit");
		// identityService.saveUser(user);
		//
		// Group group = identityService.newGroup("admin");
		// group.setName("Administrators");
		// identityService.saveGroup(group);
		//
		// identityService.createMembership(user.getId(), group.getId());

		ClientResource client = new ClientResource(url);
		client.setChallengeResponse(ChallengeScheme.HTTP_BASIC, "jbarrez",
				"password");
		Representation resp = client.get();
		ObjectMapper om = new ObjectMapper();
		JsonNode node;
		try {
			node = om.readTree(resp.getStream());
			System.out.println(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void test() {

		tables("http://localhost:8080/identity/users");
		tables("http://localhost:8080/management/tables");
		tables("http://localhost:8080/management/tables/ACT_ID_USER");
		tables("http://localhost:8080/management/tables/ACT_ID_USER/data");

		// 流程的一切属性都应与流程图保持一致，不应另备数据表保存，已避免后期审计问题（统一流程版本的条件发生过变化不被察觉，导致有猫腻）
		deployTest("bpmn/bpm-main.bpmn20.xml", "main1.bpmn");
		deployTest("bpmn/bpm-sub1.bpmn20.xml", "sub1.bpmn");
		deployTest("bpmn/bpm-sub2.bpmn20.xml", "sub2.bpmn");

		startProcessTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		back();
		getDiagram();
		getCustomDiagram();
		getDiagramWithLog();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		handlerTaskTest();
		queryDoingTaskTest();
		queryProcessTest();
		queryHisTaskTest();

		getNextNode("main");
		getNextNode("main", "sid-03811B67-A918-492F-B5B4-3E9F366D9519");
		getNextNode("main", "sid-6E12EA41-87AE-4B5E-B677-6AF1720F9737");
		// getNextNode("sub2", "sid-6C536749-0B5A-4E5C-9E8D-0FBF040C48E4");
		// getNextNode("main", "sid-4B8B1EF3-F28E-48BE-912C-8BF0DE3EBF16");
		clean();
	}

	@Ignore
	void getDiagramWithLog() {

		HistoricProcessInstance i = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey("main").list().get(0);
		String processDefinitionId = i.getProcessDefinitionId();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> list = def.getActivities();
		List<Map<String, Object>> infos = new ArrayList<Map<String, Object>>();
		for (ActivityImpl a : list) {
			ActivityBehavior be = a.getActivityBehavior();
			if (be instanceof CallActivityBehavior) {
			}
			Map<String, Object> info = new HashMap<String, Object>();
			info.put("height:", a.getHeight());
			info.put("width:", a.getWidth());
			info.put("x:", a.getX());
			info.put("y:", a.getY());
			info.put("id:", a.getId());
			info.put("type:", a.getProperty("type"));
			infos.add(info);
		}
		log.info("infos:{}", infos);
	}

	@Ignore
	void getCustomDiagram() {
		HistoricProcessInstance i = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey("main").list().get(0);
		String processDefinitionId = i.getProcessDefinitionId();
		String processInstanceId = i.getId();
		RepositoryServiceImpl repoServiceImpl = (RepositoryServiceImpl) repositoryService;

		List<String> highLightedActivities = new ArrayList<String>();
		List<String> highLightedFlows = new ArrayList<String>();

		String activityFontName = processEngine.getProcessEngineConfiguration()
				.getActivityFontName();
		String labelFontName = processEngine.getProcessEngineConfiguration()
				.getLabelFontName();
		processEngine.getProcessEngineConfiguration().setActivityFontName("宋体");
		processEngine.getProcessEngineConfiguration().setLabelFontName("宋体");
		Command<InputStream> cmd = null;
		cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);

		InputStream inputStream = managementService.executeCommand(cmd);
		BufferedInputStream bufferedStream = new BufferedInputStream(
				inputStream);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		int length = 1024;

		byte[] b = new byte[length];

		try {
			while (bufferedStream.read(b) > 0) {
				outStream.write(b);
			}
		} catch (IOException e) {
			log.error("生成流程图片出错", e);
		} finally {

			try {

				bufferedStream.close();
				outStream.close();

			} catch (IOException e) {
				log.warn("生成流程图片时，关闭流出错", e);
			}
		}

		try {

			File dir = new File("e://tmp");
			if (!dir.exists()) {
				dir.mkdir();
			}
			long time = new Date().getTime();
			File f = new File("e://tmp/" + time + ".png");
			java.io.OutputStream fos = new FileOutputStream(f);
			fos.write(outStream.toByteArray());
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 图片与流程定义中的位置坐标不一致，无法做扩展
	@Ignore
	void getDiagram() {
		HistoricProcessInstance i = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey("main").list().get(0);
		String processDefinitionId = i.getProcessDefinitionId();

		String activityFontName = processEngine.getProcessEngineConfiguration()
				.getActivityFontName();
		String labelFontName = processEngine.getProcessEngineConfiguration()
				.getLabelFontName();
		BpmnModel m = repositoryService.getBpmnModel(processDefinitionId);
		InputStream inputStream = new DefaultProcessDiagramGenerator()
				.generateDiagram(m, "png", runtimeService
						.getActiveActivityIds(i.getId()),
						new ArrayList<String>(), "宋体", "宋体", processEngine
								.getProcessEngineConfiguration()
								.getClassLoader(), 1.0);

		BufferedInputStream bufferedStream = new BufferedInputStream(
				inputStream);

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		int length = 1024;

		byte[] b = new byte[length];

		try {
			while (bufferedStream.read(b) > 0) {
				outStream.write(b);
			}
		} catch (IOException e) {
			log.error("生成流程图片出错", e);
		} finally {

			try {

				bufferedStream.close();
				outStream.close();

			} catch (IOException e) {
				log.warn("生成流程图片时，关闭流出错", e);
			}
		}

		try {

			File dir = new File("e://tmp");
			if (!dir.exists()) {
				dir.mkdir();
			}
			long time = new Date().getTime();
			File f = new File("e://tmp/" + time + ".png");
			java.io.OutputStream fos = new FileOutputStream(f);
			fos.write(outStream.toByteArray());
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("imgsb:{}", outStream.toByteArray());
	}

	@Ignore
	private void getNextNode(String processInstanceId, String activitiId) {

		if (activitiId == null) {
			getNextNode(processInstanceId);
			return;
		}
		String processDefinitionId = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processInstanceId).list().get(0)
				.getProcessDefinitionId();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		// 获得当前任务的所有节点
		List<ActivityImpl> activitiList = def.getActivities();
		getNextNode(processInstanceId, activitiList, activitiId);
	}

	private void getNextNode(String processInstanceId, PvmActivity activiti) {
		List<? extends PvmActivity> activitiList = activiti.getActivities();
		for (PvmActivity activityImpl : activitiList) {
			if ("startEvent".equals(activityImpl.getProperty("type"))) {
				getNextNode(processInstanceId, activiti, activityImpl.getId());
				break;
			}
		}
	}

	private void getNextNode(String processInstanceId) {
		String processDefinitionId = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processInstanceId).list().get(0)
				.getProcessDefinitionId();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);

		// 获得当前任务的所有节点
		List<ActivityImpl> activitiList = def.getActivities();
		for (PvmActivity activityImpl : activitiList) {
			if ("startEvent".equals(activityImpl.getProperty("type"))) {
				getNextNode(processInstanceId, activityImpl.getId());
				break;
			}
		}
	}

	// 获取嵌入子流程开始环节
	private void getNextNode(String processInstanceId,
			List<? extends PvmActivity> activitiList, String activitiId) {
		String id = null;
		for (PvmActivity activityImpl : activitiList) {
			id = activityImpl.getId();
			if (activitiId != null && activitiId.equals(id)) {

				List<PvmTransition> outTransitions = activityImpl
						.getOutgoingTransitions();
				for (PvmTransition tr : outTransitions) {
					PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
					if ("parallelGateway".equals(ac.getProperty("type"))) {
						getNextNode(processInstanceId, ac.getId());
					} else if ("exclusiveGateway"
							.equals(ac.getProperty("type"))) {
						getNextNode(processInstanceId, ac.getId());
					} else {
						ActivityImpl acimp = (ActivityImpl) ac;
						log.info("下一环节：{} ", acimp.getProperties());
						log.info("nextNode-{}:{}  {}  ",
								ac.getProperty("type"), ac.getProperty("name"),
								ac.getId());

						if ("subProcess".equals(ac.getProperty("type"))) {
							getNextNode(processInstanceId, ac);
						}
						if ("callActivity".equals(ac.getProperty("type"))) {
							CallActivityBehavior be = (CallActivityBehavior) acimp
									.getActivityBehavior();
							getNextNode(be.getProcessDefinitonKey());
						}
					}
				}
			}
		}
	}

	// 获取嵌入子流程开始环节
	private void getNextNode(String processInstanceId, PvmActivity activiti,
			String activitiId) {
		if (activitiId == null) {
			getNextNode(processInstanceId, activiti);
			return;
		}
		// 获得当前任务的所有节点
		List<? extends PvmActivity> activitiList = activiti.getActivities();
		getNextNode(processInstanceId, activitiList, activitiId);
	}

	@Ignore
	public void setNode(String nodeId, String atrributeKey,
			String atrributeValue) {
		List<ProcessDefinition> deplist = repositoryService
				.createProcessDefinitionQuery().list();
		for (ProcessDefinition d : deplist) {
		}
	}

	@Ignore
	public void setFlow() {

	}

	@Ignore
	public void setGateWay() {

	}

	@Ignore
	public void setEvent() {

	}

	@Ignore
	public void back() {

		List<HistoricProcessInstance> l = historyService
				.createHistoricProcessInstanceQuery().list();

	}

	@Ignore
	public void clean() {
		List<Model> mlist = repositoryService.createModelQuery().list();
		for (Model m : mlist) {
			repositoryService.deleteModel(m.getId());
		}

		List<Deployment> deplist = repositoryService.createDeploymentQuery()
				.list();
		for (Deployment d : deplist) {
			repositoryService.deleteDeployment(d.getId(), true);
		}

		List<HistoricProcessInstance> l = historyService
				.createHistoricProcessInstanceQuery().list();
		List<HistoricTaskInstance> l2 = historyService
				.createHistoricTaskInstanceQuery().list();
		for (HistoricProcessInstance h : l) {
			historyService.deleteHistoricProcessInstance(h.getId());
		}
		for (HistoricTaskInstance h : l2) {
			historyService.deleteHistoricTaskInstance(h.getId());
		}
	}

	@Ignore
	public void deployTest(String jsonFilePath, String bpmnFileName) {

		try {
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jsonFilePath);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "");
			}
			repositoryService.createDeployment()
					.addString(bpmnFileName, sb.toString()).deploy();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Ignore
	public List<Model> queryProcessModelTest() {
		List<Model> list = repositoryService.createModelQuery().list();
		log.info("ProcessModelList:{}", list);
		List<Deployment> deplist = repositoryService.createDeploymentQuery()
				.list();
		log.info("DeploymentList:{}", deplist);

		return list;
	}

	@Ignore
	public void startProcessTest() {
		identityService.setAuthenticatedUserId("lyt");
		runtimeService
				.startProcessInstanceByKey("main", businessKey, variables);
		// variables.put("WF_CS1", "流程2");
		// runtimeService.startProcessInstanceByKey("sub1", childBusinessKey,
		// variables);
		// runtimeService.startProcessInstanceByKey("sub2", childBusinessKey2,
		// variables);
		log.info(" 流程启动成功");

	}

	public boolean RULE_OPTION = false;
	public Map<String, Boolean> bpm_node_select = new HashMap<String, Boolean>();

	@Ignore
	public void handlerTaskTest() {
		bpm_node_select.put("sid-03811B67-A918-492F-B5B4-3E9F366D9519",
				RULE_OPTION);
		bpm_node_select.put("sid-0697EA61-7188-4C5B-90B7-0FAF85DFB099",
				!RULE_OPTION);
		variables.put("bpm_node_select", bpm_node_select);
		if (!RULE_OPTION)
			RULE_OPTION = true;
		runtimeService.createExecutionQuery().list();
		List<Task> l = taskService.createTaskQuery().list();
		List<String> nodeList = new ArrayList<String>();
		nodeList.add("sid-8C5F20F0-9298-47A8-BA49-8DF4362FFDE0");
		nodeList.add("sid-9731F060-2F71-461C-9DA3-DEF71E14C2EC");
		nodeList.add("sid-03811B67-A918-492F-B5B4-3E9F366D9519");
		for (Task t : l) {
			if (!t.getProcessInstanceId().equals(t.getExecutionId())) {
			}

			List<HistoricProcessInstance> l2 = historyService
					.createHistoricProcessInstanceQuery().list();
			for (HistoricProcessInstance h : l2) {
				if (t.getProcessInstanceId().equals(h.getId())
						&& h.getSuperProcessInstanceId() != null) {
					HistoricProcessInstance h2 = historyService
							.createHistoricProcessInstanceQuery()
							.processInstanceId(h.getSuperProcessInstanceId())
							.singleResult();
					if (h2 != null && h2.getSuperProcessInstanceId() != null) {
						runtimeService.updateBusinessKey(
								t.getProcessInstanceId(), childBusinessKey2);
					} else {
						runtimeService.updateBusinessKey(
								t.getProcessInstanceId(), childBusinessKey);
					}
				}
			}
			taskService.setAssignee(t.getId(), "lyt");
			taskService.complete(t.getId(), variables);
		}

		l = taskService.createTaskQuery().list();

		for (Task t : l) {

			if (nodeList.contains(t.getTaskDefinitionKey())) {

				// taskService.setAssignee(t.getId(), "lyt");
				// taskService.setOwner(taskId, userId);
				// taskService.addCandidateGroup(taskId, groupId);
				taskService.getIdentityLinksForTask(t.getId()).clear();
				taskService.addCandidateUser(t.getId(), "lyt");
				// taskService.addGroupIdentityLink(taskId, groupId,
				// IdentityLinkType.CANDIDATE);
				// taskService.addUserIdentityLink(taskId, userId,
				// IdentityLinkType.ASSIGNEE);
				// taskService.addUserIdentityLink(taskId, userId,
				// IdentityLinkType.OWNER);
				// taskService.addUserIdentityLink(taskId, userId,
				// IdentityLinkType.PARTICIPANT);
				// taskService.addUserIdentityLink(taskId, userId,
				// IdentityLinkType.STARTER);

				log.info("设置待办人：   {} - {} ", t.getName(), taskService
						.getIdentityLinksForTask(t.getId()).get(0).getUserId());
			}

		}
		String handlerType = "";
		switch (handlerType) {
		case "办理":
			break;
		case "转交":
			break;
		case "回退":
			break;
		case "传阅":
			break;
		default:
			break;
		}

	}

	@Ignore
	public void queryHisTaskTest() {
		// List<HistoricTaskInstance> l = historyService
		// .createHistoricTaskInstanceQuery()
		// .orderByHistoricTaskInstanceEndTime().desc().list();
		// for (HistoricTaskInstance h : l) {
		// log.info("             processKey: {} -  name: {}  -handler: {}",
		// h.getProcessDefinitionId(), h.getName(), h.getAssignee());
		// }

		log.info("历史活动");
		List<HistoricActivityInstance> l2 = historyService
				.createHistoricActivityInstanceQuery()
				.orderByHistoricActivityInstanceStartTime().asc().list();

		for (HistoricActivityInstance t : l2) {
			String type = t.getActivityType();
			if ("userTask".equals(type) || "callActivity".equals(type)
					|| "subProcess".equals(type)) {
				log.info("       name:{}  -handler: {}  -  {}  ",
						t.getActivityName(), t.getAssignee(),
						t.getActivityType());
			}
		}
	}

	@Ignore
	public void queryProcessTest() {

		List<HistoricProcessInstance> l = historyService
				.createHistoricProcessInstanceQuery().list();
		log.info("历史流程");
		for (HistoricProcessInstance h : l) {
			log.info(
					"           id: {} - processKey: {} - parentId: {} - businessKey: {} - startUser: {}",
					h.getId(), h.getProcessDefinitionId(),
					h.getSuperProcessInstanceId(), h.getBusinessKey(),
					h.getStartUserId());
		}
	}

	@Ignore
	public void queryDoingTaskTest() {
		List<Task> l = taskService.createTaskQuery().list();

		log.info("任务列表：{} 条待办", l.size());
		for (Task t : l) {
			List<IdentityLink> userList = taskService.getIdentityLinksForTask(t
					.getId());
			String canUser = "";
			if (userList.size() > 0) {
				canUser = userList.get(0).getUserId();
			}
			log.info("         {}  - {}  -  {}", t.getName(), canUser,
					t.getParentTaskId());
		}

	}
}
