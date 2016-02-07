package com.bpm.service.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.TimerEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.junit.Ignore;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Ignore
public class ActivitiTest extends PluggableActivitiTestCase {

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		/**
		 * identityService.saveUser(identityService.newUser(\"kermit\"));
		 * identityService.saveUser(identityService.newUser(\"fozzie\"));
		 * identityService.saveUser(identityService.newUser(\"mispiggy\"));
		 * 
		 * identityService.createMembership(\"kermit\", \"muppets\");
		 * identityService.createMembership(\"fozzie\", \"muppets\");
		 * identityService.createMembership(\"mispiggy\", \"muppets\");
		 * 
		 * identityService.createMembership(\"kermit\", \"frogs\");
		 * 
		 * identityService.createMembership(\"fozzie\", \"mammals\");
		 * identityService.createMembership(\"mispiggy\", \"mammals\");
		 * 
		 * identityService.createMembership(\"kermit\", \"admin\");
		 **/

	}

	public void testProcess() {

		InputStream jsonStream = this.getClass().getClassLoader().getResourceAsStream("test.json");

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"bpm\">"
				+ "<process id=\"lyttest\" name=\"bpm_test\" isExecutable=\"true\">"
				+ "<startEvent id=\"sid-357C230B-CF0D-4B22-BE00-6157A102E07E\" activiti:formKey=\"bpmTest.docTjsq\"></startEvent>"
				+ "<userTask id=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\"  activiti:formKey=\"bpmTest.docTjsq\" activiti:candidateUsers=\"000000001196,000000001348,000000002074,000000001495\" activiti:atwa_iscustomformkey=\"false\" activiti:atwa_appkey=\" \"></userTask>"
				+ "<endEvent id=\"sid-9AE82916-AF6A-49B0-846C-A7F21493FCF2\"></endEvent>"
				+ "<sequenceFlow id=\"sid-1F0D1E5A-F775-4CC7-92D1-F874667493D1\" sourceRef=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\" targetRef=\"sid-9AE82916-AF6A-49B0-846C-A7F21493FCF2\"></sequenceFlow>"
				+ "<sequenceFlow id=\"sid-5DE59400-75F2-4733-A81E-306384E359F9\" sourceRef=\"sid-357C230B-CF0D-4B22-BE00-6157A102E07E\" targetRef=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\"></sequenceFlow>"
				+ "</process>" + "</definitions>";

		BpmnJsonConverter jsonConverter = new BpmnJsonConverter();

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = null;
		BpmnModel bpmnModel = null;
		try {
			jsonNode = objectMapper.readTree(jsonStream);

			if (jsonNode != null) {
				try {
					bpmnModel = jsonConverter.convertToBpmnModel(jsonNode);
				} catch (Exception e) {
					log.error("ת��ʱ����:", e);
				}
			}

		} catch (IOException e) {
			log.error("����JSON����:", e);
		}
		if (bpmnModel != null) {

			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();

			byte[] xmlData = xmlConverter.convertToXML(bpmnModel, "utf-8");

			System.out.println(new String(xmlData));
			Deployment deployment = repositoryService.createDeployment().addString("lyttest.bpmn", new String(xmlData))
					.deploy();
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> assigneeList = new ArrayList<String>();
			assigneeList.add("kermit");
			// assigneeList.add("fozzie");
			assigneeList.add("gonzo");
			map.put("assigneeList", assigneeList);
			identityService.setAuthenticatedUserId("gonzo");
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("lyttest", map);
			// runtimeService.updateBusinessKey(processInstanceId, businessKey);
			List<Task> taskList = taskService.createTaskQuery().taskInvolvedUser("fozzie").list();
			System.out.println("taskListsize" + taskList.size());
			try {
				for (Task t : taskList) {

					taskService.unclaim(t.getId());
					taskService.claim(t.getId(), "fozzie");
					System.out.println("getOwner:" + t.getOwner());
					System.out.println("getAssignee:" + t.getAssignee());
					System.out.println("linksize:" + taskService.getIdentityLinksForTask(t.getId()).size());
					taskService.addUserIdentityLink(t.getId(), "gonzo", IdentityLinkType.CANDIDATE);
					System.out.println("linksize:" + taskService.getIdentityLinksForTask(t.getId()).size());
					taskService.unclaim(t.getId());
					taskService.claim(t.getId(), "gonzo");
					System.out.println("gonzo claim " + t.getName());
					taskService.complete(t.getId());

					System.out.println("gonzo complete " + t.getId() + "  " + t.getName());
					System.out.println("end");
				}
			} catch (Exception e) {
				System.out.println("error");
			}
			taskList.clear();
			taskList = taskService.createTaskQuery().taskInvolvedUser("fozzie").list();
			for (Task t : taskList) {
				System.out.println(t.getId() + ":" + t.getName());
				t.setName("new Name");
				taskService.saveTask(t);
			}
			taskList = taskService.createTaskQuery().list();
			for (Task t : taskList) {
				System.out.println(t.getId() + ":" + t.getName());
				System.out.println("linksize:" + taskService.getIdentityLinksForTask(t.getId()).size());
				taskService.addUserIdentityLink(t.getId(), "fozzie", IdentityLinkType.CANDIDATE);
				System.out.println("linksize:" + taskService.getIdentityLinksForTask(t.getId()).size());
				taskService.unclaim(t.getId());
				taskService.claim(t.getId(), "fozzie");
				System.out.println("fozzie claim " + t.getName());
				taskService.complete(t.getId());
				System.out.println("fozzie complete " + t.getName());
				System.out.println("end");
			}
			taskList.clear();
			taskList = taskService.createTaskQuery().list();
			for (Task t : taskList) {
				taskService.unclaim(t.getId());
				taskService.claim(t.getId(), "kermit");
				System.out.println("kermit claim " + t.getName());
				taskService.complete(t.getId());
				System.out.println("kermit complete " + t.getName());
				System.out.println("end");
			}
			taskList.clear();
			taskList = taskService.createTaskQuery().list();
			for (Task t : taskList) {
				taskService.unclaim(t.getId());
				taskService.claim(t.getId(), "kermit");
				System.out.println("kermit claim " + t.getName());
				taskService.complete(t.getId());
				System.out.println("kermit complete " + t.getName());
			}
			taskList.clear();
			taskList = taskService.createTaskQuery().list();

			// 时间计算
			Date now = new Date();
			// delay为相较当前时间，延时的时间变量
			Date target = new Date(now.getTime() + 2 * 60 * 1000);
			// 时间事件声明
			TimerEntity timer = new TimerEntity();
			timer.setDuedate(target);
			timer.setExclusive(true);
			timer.setJobHandlerConfiguration("customProcessKey");// 这里存入需要启动的流程key
			// timer.setJobHandlerType(TimerStartEventJobHandler.TYPE);
			timer.setJobHandlerType(DeleteProcessHandler.TYPE);
			Context.setProcessEngineConfiguration(processEngineConfiguration);
			// managementService.executeCommand(new Jobe());
			// 保存作业事件
			Context.getCommandContext().getJobEntityManager().schedule(timer);

			for (Task t : taskList) {
				taskService.unclaim(t.getId());
				taskService.claim(t.getId(), "kermit");
				System.out.println("kermit claim " + t.getName());
				taskService.complete(t.getId());
				System.out.println("kermit complete " + t.getName());
			}
			List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
					.involvedUser("gonzo").list();
			for (HistoricProcessInstance hi : list) {
				System.out.println(hi.getProcessDefinitionId());
			}
			List<HistoricTaskInstance> tlist = historyService.createHistoricTaskInstanceQuery()
					.taskInvolvedUser("gonzo").list();
			for (HistoricTaskInstance ti : tlist) {
				System.out.println(ti.getId());
			}

			historyService.deleteHistoricProcessInstance(processInstance.getId());
			// runtimeService.deleteProcessInstance(processInstance.getId(),
			// "clean");
			repositoryService.deleteDeployment(deployment.getId());
		}

	}

}
