package com.pallasli.bpm.service.query;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti/activiti.cfg.xml")
public class ServiceTest {

	@Autowired
	ProcessInstanceHandlerService proInstHandlerService;
	@Autowired
	ProcessInstanceQueryService proInstQueryService;
	@Autowired
	ProcessModelQueryService proModelQueryService;
	@Autowired
	ProcessModelHandlerService proModelHandlerService;
	@Autowired
	TaskQueryService taskQueryService;
	@Autowired
	TaskHandlerService taskHandlerService;
	@Autowired
	ProcessDiagramService proDiagramService;
	Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {

		InputStream jsonStream = this.getClass().getClassLoader().getResourceAsStream("test.json");

		log.info("测试发布流程");

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"bpm\">"
				+ "<process id=\"lyttest\" name=\"bpm_test\" isExecutable=\"true\">"
				+ "<startEvent id=\"sid-357C230B-CF0D-4B22-BE00-6157A102E07E\" activiti:formKey=\"bpmTest.docTjsq\"></startEvent>"
				+ "<userTask id=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\"  activiti:formKey=\"bpmTest.docTjsq\" activiti:candidateUsers=\"000000001196,000000001348,000000002074,000000001495\" activiti:atwa_iscustomformkey=\"false\" activiti:atwa_appkey=\" \"></userTask>"
				+ "<endEvent id=\"sid-9AE82916-AF6A-49B0-846C-A7F21493FCF2\"></endEvent>"
				+ "<sequenceFlow id=\"sid-1F0D1E5A-F775-4CC7-92D1-F874667493D1\" sourceRef=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\" targetRef=\"sid-9AE82916-AF6A-49B0-846C-A7F21493FCF2\"></sequenceFlow>"
				+ "<sequenceFlow id=\"sid-5DE59400-75F2-4733-A81E-306384E359F9\" sourceRef=\"sid-357C230B-CF0D-4B22-BE00-6157A102E07E\" targetRef=\"sid-78998E93-DCDD-4D54-AC45-720639E6E1E6\"></sequenceFlow>"
				+ "</process>" + "</definitions>";
		Deployment deployment = null;
		try {

			xml = FileUtils.readFileToString(new File("src/test/resources/bpm-sub1.bpmn20.xml"));
			deployment = deployProcess(xml.trim());
			Assert.assertNotNull(deployment);
			Assert.assertNotNull(deployment.getId());
			xml = FileUtils.readFileToString(new File("src/test/resources/bpm-sub2.bpmn20.xml"));
			deployment = deployProcess(xml.trim());
			Assert.assertNotNull(deployment);
			Assert.assertNotNull(deployment.getId());
			xml = FileUtils.readFileToString(new File("src/test/resources/bpm-main.bpmn20.xml"));
			deployment = deployProcess(xml.trim());
			Assert.assertNotNull(deployment);
			Assert.assertNotNull(deployment.getId());
			log.info("测试查看流程图");
			proDiagramService.getDiagram(deployment.getId());
			proDiagramService.getDiagramWithCoords(deployment.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("测试启动流程");
		String proKey = "main";
		ProcessInstance proInst = startProcess(proKey);
		Assert.assertNotNull(proInst);
		Assert.assertNotNull(proInst.getId());
		String proInstId = proInst.getId();
		Task t = findFirstTask(proInstId);
		String user = "000000001196";
		log.info("测试待办查询");
		t = findTask(user, proInstId);
		log.info("测试审批流程");
		auditProcess(t);
		log.info("测试取消流程");
		cancelProcess();
		log.info("强行测试终止流程");
		forceEndProcess();
		log.info("排他网关测试自主选择分支");
		//
		//
		//

		log.info("排他网关测试条件选择分支");
		//
		//
		//
		log.info("多选网关测试自主选择分支");
		//
		//
		//
		log.info("多选网关测试条件选择分支");
		//
		//
		//
		log.info("并行网关测试");
		//
		//
		//
		log.info("子流程");
		//
		//
		//
		log.info("");
		log.info("测试取消发布");
		//
		//
		//
		log.info("测试暂停流程");
		//
		//
		//
		log.info("测试激活流程");
		//
		//
		//
		log.info("测试定时发布定时取消");
		//
		//
		//
		log.info("测试定时暂停定时激活");
		//
		//
		//
		log.info("测试同版本流程允许动态更换节点候选人");
		//
		//
		//

		File bpmn20file = null;
		log.info("测试导出流程");
		// 导出单个流程
		String prodefid = null;
		List<String> prodefIdList = null;
		exportProcess(prodefid, bpmn20file);
		// 导出所选多个流程
		exportProcess(prodefIdList, bpmn20file);
		// 导出某类流程
		String prokey = null;
		prodefIdList = listProDefIdByProKey(prokey);
		exportProcess(prodefIdList, bpmn20file);
		prodefIdList = listProDefIdByPyGroup(true);
		exportProcess(prodefIdList, bpmn20file);
		// 导出全部流程
		prodefIdList = listAllProDefId(true);
		exportProcess(prodefIdList, bpmn20file);
		log.info("测试导入流程");
		// 导入zip不覆盖
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_ZIP, false, false);
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_ZIP, false, true);
		// 导入单个xml不覆盖
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_XML, false, false);
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_XML, false, true);
		// 导入单个json不覆盖
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_JSON, false, false);
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_JSON, false, true);
		// 导入zip覆盖
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_ZIP, true, false);
		// 导入单个xml覆盖
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_XML, true, false);
		// 导入单个json覆盖
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_JSON, true, false);
		// 导入zip覆盖并发布
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_ZIP, true, true);
		// 导入单个xml覆盖并发布
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_XML, true, true);
		// 导入单个json覆盖并发布
		importProcess(bpmn20file, ProcessModelHandlerService.FILE_TYPE_JSON, true, true);
		log.info("");
		log.info("");
		log.info("");
		log.info("");

	}

	private List<String> listAllProDefId(boolean containsAllVersion) {

		return null;
	}

	private List<String> listProDefIdByPyGroup(boolean containsAllVersion) {

		return null;
	}

	private List<String> listProDefIdByProKey(String prokey) {

		return null;
	}

	private void exportProcess(List<String> prodefIdList, File bpmn20file) {
	}

	private void exportProcess(String prodefid, File bpmn20file) {
		exportProcess(prodefid, ProcessModelHandlerService.FILE_TYPE_ZIP, bpmn20file);
	}

	private void exportProcess(String prodefid, int fileTypeZip, File bpmn20file) {
	}

	private void importProcess(File bpmn20file, int fileType, boolean override, boolean deploy) {

	}

	private void auditProcess(Task t, String nextUser) {

		taskHandlerService.complete(t.getId(), nextUser);
	}

	private Task findFirstTask(String proInstId) {

		List<Task> taskList = taskQueryService.find(proInstId);
		Task t = taskList.get(0);
		return t;
	}

	private Deployment deployProcess(String xml) {
		return proModelHandlerService.deploy(xml);
	}

	private ProcessInstance startProcess(String proKey) {

		ProcessInstance proInst = proInstHandlerService.startByProKey(proKey);
		return proInst;
	}

	private Task findTask(String user, String proInstId) {
		List<Task> taskList = taskQueryService.find(proInstId);
		Task t = taskList.get(0);
		return t;
	}

	private void auditProcess(Task t) {

		taskHandlerService.complete(t.getId());
	}

	private void cancelProcess() {

	}

	private void forceEndProcess() {

	}

}
