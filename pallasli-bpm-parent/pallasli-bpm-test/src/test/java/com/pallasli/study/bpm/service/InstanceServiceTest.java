package com.pallasli.study.bpm.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pallasli.study.bpm.model.Instance;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class InstanceServiceTest extends BaseTest {

	@Test
	public void startProcessInstance() {

		String user = "";
		String processKey = "my-process";
		Instance instance = auditService.startProcessInstance(user, processKey);

		Instance instance2 = instanceService.getProcessInstance(user,
				processKey, instance.getId());
		System.out.println(instance2.getId());
		instanceService.getHistoryProcessInstanceVar("");

		// 通过流程ID（XML上写的）找到流程
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(processKey).list().get(0);

		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition
				.getId());

		// HistoryProcessInstanceDiagramCmd cmd = new
		// HistoryProcessInstanceDiagramCmd(
		// instance.getId());
		// processEngine.getManagementService().executeCommand(cmd);
		// processEngine
		// .getProcessEngineConfiguration()
		// .getProcessDiagramGenerator()
		// .generateDiagram(
		// bpmnModel,
		// "png",
		// processEngine.getProcessEngineConfiguration()
		// .getActivityFontName(),
		// processEngine.getProcessEngineConfiguration()
		// .getLabelFontName(),
		// processEngine.getProcessEngineConfiguration()
		// .getClassLoader(), 1.0);

		// genericImageByWfKey(processKey);
		String diagramResourceName = processDefinition.getDiagramResourceName();
		// logger.info(diagramResourceName);
		InputStream imageStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), diagramResourceName);
		// 输出
		int len = 0;
		byte[] b = new byte[1024];
		try {
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				System.out.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据流程的key生成图片
	 * 
	 * @param request
	 * @param response
	 * @param wfKey
	 *            流程定义的key
	 */
	// @RequestMapping("/genericImageByWfKey")
	public void genericImageByWfKey(String wfKey) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(wfKey).latestVersion().singleResult();
		BpmnModel bm = repositoryService.getBpmnModel(pd.getId());
		InputStream is = new DefaultProcessDiagramGenerator()
				.generatePngDiagram(bm); // 生成图片，获取图片的输入流</span></strong>
		try {
			FileUtils.copyInputStreamToFile(is, new File(""));
		} catch (IOException e) {
		}
	}
}
