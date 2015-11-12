package com.bpm.service.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class SingleTaskTest extends PluggableActivitiTestCase {

	@Override
	protected void setUp() throws Exception {

		super.setUp();

	}

	public void testProcess() {

		InputStream jsonStream = this.getClass().getClassLoader()
				.getResourceAsStream("singleTask.json");

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"bpm\">"
				+ "<process id=\"lyttest\" name=\"bpm_test\" isExecutable=\"true\">"
				+ "<startEvent id=\"sid-357C230B-CF0D-4B22-BE00-6157A102E07E\" activiti:formKey=\"bpmTest.docTjsq\"></startEvent>"
				+ "<userTask id=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\"  activiti:formKey=\"bpmTest.docTjsq\" activiti:candidateUsers=\"000000001196,000000001348,000000002074,000000001495\" activiti:atwa_iscustomformkey=\"false\" activiti:atwa_appkey=\" \"></userTask>"
				+ "<endEvent id=\"sid-9AE82916-AF6A-49B0-846C-A7F21493FCF2\"></endEvent>"
				+ "<sequenceFlow id=\"sid-1F0D1E5A-F775-4CC7-92D1-F874667493D1\" sourceRef=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\" targetRef=\"sid-9AE82916-AF6A-49B0-846C-A7F21493FCF2\"></sequenceFlow>"
				+ "<sequenceFlow id=\"sid-5DE59400-75F2-4733-A81E-306384E359F9\" sourceRef=\"sid-357C230B-CF0D-4B22-BE00-6157A102E07E\" targetRef=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\"></sequenceFlow>"
				+ "</process>" + "</definitions>";

		Deployment deployment = repositoryService.createDeployment()
				.addString("lyttest.bpmn", xml).deploy();
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> assigneeList = new ArrayList<String>();
		assigneeList.add("kermit");
		map.put("assigneeList", assigneeList);
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("lyttest", map);
		List<Task> taskList = taskService.createTaskQuery().list();
		for (Task t : taskList) {
			t.setName("new Name" + i++);
			taskService.saveTask(t);
			taskService.complete(t.getId());
		}
		processInstance = runtimeService.startProcessInstanceByKey("lyttest",
				map);
		taskList = taskService.createTaskQuery().list();
		for (Task t : taskList) {
			t.setName("new Name" + i++);
			taskService.saveTask(t);
			taskService.complete(t.getId());
		}
		processInstance = runtimeService.startProcessInstanceByKey("lyttest",
				map);
		taskList = taskService.createTaskQuery().list();
		for (Task t : taskList) {
			t.setName("new Name" + i++);
			taskService.saveTask(t);
			taskService.complete(t.getId());
		}
		processInstance = runtimeService.startProcessInstanceByKey("lyttest",
				map);
		taskList = taskService.createTaskQuery().list();
		for (Task t : taskList) {
			t.setName("new Name" + i++);
			taskService.saveTask(t);
			taskService.complete(t.getId());
		}
		processInstance = runtimeService.startProcessInstanceByKey("lyttest",
				map);
		taskList = taskService.createTaskQuery().list();
		for (Task t : taskList) {
			t.setName("new Name" + i++);
			taskService.saveTask(t);
			taskService.complete(t.getId());
		}
		processInstance = runtimeService.startProcessInstanceByKey("lyttest",
				map);
		taskList = taskService.createTaskQuery().list();
		for (Task t : taskList) {
			t.setName("new Name" + i++);
			t.setFormKey("formkey" + i);
			taskService.saveTask(t);
			taskService.complete(t.getId());
		}
		List<HistoricTaskInstance> list = historyService
				.createHistoricTaskInstanceQuery().list();
		for (HistoricTaskInstance t : list) {
			System.out.println(t.getStartTime() + "   " + t.getName());
			System.out.println(t.getStartTime() + "   " + t.getFormKey());
		}

		historyService.deleteHistoricProcessInstance(processInstance.getId());
		repositoryService.deleteDeployment(deployment.getId());
	}

}
