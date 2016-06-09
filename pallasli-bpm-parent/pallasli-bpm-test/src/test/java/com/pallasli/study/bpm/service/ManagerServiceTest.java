package com.pallasli.study.bpm.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManagerServiceTest extends BaseTest {

	@Test
	public void importFile() {

		deployment = repositoryService.createDeployment()
				.addClasspathResource("Dynamic_process_deployment.bpmn20.xml")
				// .addString("sourceName",
				// arg1)不指定sourcename时为deployment对应的name
				// .category("bpm").addInputStream("sourceName", processBpmn)
				.name("Dynamic_process_deployment_new").deploy();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();
		// 缺少category，流程名procdef表的name
		InputStream processBpmn = managerService
				.exportProcessModelInfoByDeploymentId(deployment.getId());

	}

	public void test() {
		// managerService.createProcessModel("流程测试1", "流程描述", "bpm应用",
		// "bpmTest");
		List<Model> list = managerService.listProcessModels("", "", 0, 0);
		for (Model model : list) {
			System.out.println(model.getName());
			System.out.println(model.getKey());
			System.out.println(model.getCategory());
			System.out.println(model.getMetaInfo());
			String editorSource = managerService
					.getProcessModelEditorJson(model.getId());
			System.out.println(editorSource);
			String pro = managerService.exportProcessModelInfo(model.getId());
			System.out.println(pro);
			managerService.importProcessModelInfo(pro);
		}

		System.out.println(managerService.listProcessModels("", "", 0, 0)
				.size());
		InputStream processBpmn = managerService
				.exportProcessModelInfoByDeploymentId(deployment.getId());
		try {
			FileUtils.copyInputStreamToFile(processBpmn, new File("target/"
					+ deployment.getName() + ".bpmn20.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// processBpmn = managerService
		// .exportProcessModelInfoByDeploymentId(deployment.getId());
		// try {
		// FileUtils.copyInputStreamToFile(processBpmn, new File("target/"
		// + deployment.getName() + ".bpmn20.xml"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		managerService.saveProcessModel("", "");
		managerService.deleteProcessModel("");
		managerService.copyProcessModel("", "", "", "");
		managerService.validationProcess(null);
		managerService.unDeployProcessModel("", true, "");
	}

}
