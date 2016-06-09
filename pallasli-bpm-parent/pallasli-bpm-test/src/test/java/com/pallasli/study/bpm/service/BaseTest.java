package com.pallasli.study.bpm.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class BaseTest {
	@Autowired
	protected OrgnizationService orgnizationService;
	@Autowired
	protected AuditService auditService;
	@Autowired
	protected InstanceService instanceService;
	@Autowired
	protected ManagerService managerService;
	@Autowired
	protected MessageService messageService;
	@Autowired
	protected MoniterService moniterService;
	@Autowired
	protected NodeService nodeService;
	@Autowired
	protected ProcessDefinitionService processDefinitionService;
	@Autowired
	protected StatisticsService statisticsService;
	@Autowired
	protected TaskInfoService taskInfoService;

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

	@Autowired
	protected ApplicationContext ctx;

	@Autowired
	protected ProcessEngine processEngine;

	static Deployment deployment;

	@Before
	public void deploy() {

		FlowElement startElement = createStartEvent();
		FlowElement endElement = createEndEvent();
		FlowElement task1 = createUserTask("task1", "申请", "fred");
		FlowElement task2 = createUserTask("task2", "Second task", "john");
		FlowElement task3 = createUserTask("task3", "third task", "james");
		FlowElement sequenceFlow1 = createSequenceFlow("sequenceFlow1",
				"start", "task1");
		FlowElement sequenceFlow2 = createSequenceFlow("sequenceFlow2",
				"task1", "task2");
		FlowElement sequenceFlow3 = createSequenceFlow("sequenceFlow3",
				"task1", "task3");
		FlowElement sequenceFlow4 = createSequenceFlow("sequenceFlow4",
				"task2", "end");
		FlowElement sequenceFlow5 = createSequenceFlow("sequenceFlow5",
				"task3", "end");
		BpmnModel model = new BpmnModel();
		model.setTargetNamespace("catlog");
		Process process = new Process();
		process.setId("my-process");
		process.setName("我的流程");
		process.setDocumentation("描述");
		process.addFlowElement(startElement);
		process.addFlowElement(task1);
		process.addFlowElement(task2);
		process.addFlowElement(task3);
		process.addFlowElement(endElement);

		process.addFlowElement(sequenceFlow1);
		process.addFlowElement(sequenceFlow2);
		process.addFlowElement(sequenceFlow3);
		process.addFlowElement(sequenceFlow4);
		process.addFlowElement(sequenceFlow5);
		model.addProcess(process);
		GraphicInfo graphicInfo = new GraphicInfo();
		graphicInfo.setElement(startElement);
		graphicInfo.setHeight(100);
		graphicInfo.setWidth(100);
		graphicInfo.setX(100);
		graphicInfo.setY(100);
		model.addGraphicInfo("start", graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setElement(task1);
		graphicInfo.setHeight(100);
		graphicInfo.setWidth(100);
		graphicInfo.setX(300);
		graphicInfo.setY(100);
		model.addGraphicInfo("task1", graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setElement(task2);
		graphicInfo.setHeight(100);
		graphicInfo.setWidth(100);
		graphicInfo.setX(500);
		graphicInfo.setY(0);
		model.addGraphicInfo("task2", graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setElement(task3);
		graphicInfo.setHeight(100);
		graphicInfo.setWidth(100);
		graphicInfo.setX(500);
		graphicInfo.setY(200);
		model.addGraphicInfo("task3", graphicInfo);
		List<GraphicInfo> graphicInfolist = new ArrayList<GraphicInfo>();
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(500);
		graphicInfo.setY(100);
		graphicInfolist.add(graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setElement(endElement);
		graphicInfo.setHeight(100);
		graphicInfo.setWidth(100);
		graphicInfo.setX(700);
		graphicInfo.setY(100);
		model.addGraphicInfo("end", graphicInfo);

		graphicInfo = new GraphicInfo();
		graphicInfo.setX(200);
		graphicInfo.setY(150);
		graphicInfolist.add(graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(300);
		graphicInfo.setY(150);
		graphicInfolist.add(graphicInfo);
		model.addFlowGraphicInfoList("sequenceFlow1", graphicInfolist);
		graphicInfolist = new ArrayList<GraphicInfo>();
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(400);
		graphicInfo.setY(150);
		graphicInfolist.add(graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(500);
		graphicInfo.setY(50);
		graphicInfolist.add(graphicInfo);
		model.addFlowGraphicInfoList("sequenceFlow2", graphicInfolist);
		graphicInfolist = new ArrayList<GraphicInfo>();
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(400);
		graphicInfo.setY(150);
		graphicInfolist.add(graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(500);
		graphicInfo.setY(250);
		graphicInfolist.add(graphicInfo);
		model.addFlowGraphicInfoList("sequenceFlow3", graphicInfolist);
		graphicInfolist = new ArrayList<GraphicInfo>();
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(600);
		graphicInfo.setY(50);
		graphicInfolist.add(graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(700);
		graphicInfo.setY(150);
		graphicInfolist.add(graphicInfo);
		model.addFlowGraphicInfoList("sequenceFlow4", graphicInfolist);
		graphicInfolist = new ArrayList<GraphicInfo>();
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(800);
		graphicInfo.setY(250);
		graphicInfolist.add(graphicInfo);
		graphicInfo = new GraphicInfo();
		graphicInfo.setX(700);
		graphicInfo.setY(150);
		graphicInfolist.add(graphicInfo);
		model.addFlowGraphicInfoList("sequenceFlow5", graphicInfolist);

		byte[] b = new BpmnXMLConverter().convertToXML(model);
		// System.out.println(new String(b));
		String processName = model.getProcessById("my-process").getName();
		deployment = repositoryService.createDeployment()
				.addBpmnModel(processName + ".bpmn", model)
				.category(model.getTargetNamespace()).name(processName)
				.deploy();

		ProcessDefinition procdef = repositoryService
				.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();
		String diagramResourceName = procdef.getDiagramResourceName();
		System.out.println(diagramResourceName);
		String modelId = managerService.createProcessModel(
				deployment.getName(), "", deployment.getCategory(),
				procdef.getKey());
		Model m = repositoryService.getModel(modelId);
		System.out.println(processEngine.getProcessEngineConfiguration()
				.getActivityFontName());
		System.out.println(processEngine.getProcessEngineConfiguration()
				.getLabelFontName());
		InputStream is1 = processEngine
				.getProcessEngineConfiguration()
				.getProcessDiagramGenerator()
				.generateDiagram(
						model,
						"png",
						processEngine.getProcessEngineConfiguration()
								.getActivityFontName(),
						processEngine.getProcessEngineConfiguration()
								.getLabelFontName(),
						processEngine.getProcessEngineConfiguration()
								.getClassLoader(), 1.0);
		// ProcessDefinition pd =
		// repositoryService.createProcessDefinitionQuery()
		// .processDefinitionKey(procdef.getKey()).latestVersion().singleResult();
		BpmnModel bm = repositoryService.getBpmnModel(procdef.getId());
		InputStream is2 = new DefaultProcessDiagramGenerator().generateDiagram(
				bm, "png", processEngine.getProcessEngineConfiguration()
						.getActivityFontName(), processEngine
						.getProcessEngineConfiguration().getLabelFontName(),
				processEngine.getProcessEngineConfiguration().getClassLoader(),
				1.0); // 生成图片，获取图片的输入流</span></strong>
		Map<String, List<GraphicInfo>> map = bm.getFlowLocationMap();
		System.out.println(map.size());
		Set<String> keyset = map.keySet();
		Iterator<String> it = keyset.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key);
			List<GraphicInfo> l = map.get(key);
			for (GraphicInfo g : l) {
				if (g.getElement() != null)
					System.out.println(g.getElement().getName());
				System.out.println(g.getX());
			}
		}
		Map<String, GraphicInfo> map2 = bm.getLocationMap();
		System.out.println(map2.size());
		Set<String> keyset2 = map2.keySet();
		Iterator<String> it2 = keyset2.iterator();
		while (it2.hasNext()) {
			String key = it2.next();
			System.out.println(key);
			GraphicInfo g = map2.get(key);
			if (g.getElement() != null)
				System.out.println(g.getElement().getName());
			System.out.println(g.getX());
		}

		try {
			FileUtils.copyInputStreamToFile(is1, new File("target/1.png"));
			FileUtils.copyInputStreamToFile(is2, new File("target/2.png"));
		} catch (IOException e) {
		}
		m.setDeploymentId(deployment.getId());
		repositoryService.saveModel(m);
		System.out.println(repositoryService.createModelQuery().list().size());
		InputStream processBpmn = managerService
				.exportProcessModelInfoByDeploymentId(deployment.getId());
		try {
			FileUtils.copyInputStreamToFile(processBpmn, new File("target/"
					+ deployment.getName() + ".bpmn20.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected UserTask createUserTask(String id, String name, String assignee) {
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setAssignee(assignee);
		return userTask;
	}

	protected SequenceFlow createSequenceFlow(String id, String from, String to) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		flow.setId(id);
		return flow;
	}

	protected StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("start");
		startEvent.setName("开始");
		startEvent.setDocumentation("开始");
		return startEvent;
	}

	protected EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("end");
		endEvent.setName("end");
		endEvent.setDocumentation("end");
		return endEvent;
	}

}
