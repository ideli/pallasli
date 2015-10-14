package com.pallas.manager.activiti;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.annotations.EnableActiviti;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:activiti/activiti-context.cfg.xml")
public class ConfigCls {
	Logger logger = LoggerFactory.getLogger(ConfigCls.class);

	// @Configuration
	// @EnableActiviti
	// public static class SimplestConfiguration {
	// }

	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private FormService formService;

	@Configuration
	@EnableActiviti
	public static class Config {
		@Bean
		public DataSource dataSource() {
			assertEquals("sss", "");
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUsername("sa2");
			basicDataSource.setUrl("jdbc:h2:mem:anotherDatabase");
			basicDataSource.setDefaultAutoCommit(false);
			basicDataSource.setDriverClassName(org.h2.Driver.class.getName());
			basicDataSource.setPassword("");
			return basicDataSource;
		}
	}

	@Test
	@Deployment
	public void simpleProcessTest() {
		runtimeService.startProcessInstanceByKey("helloProcess");
		Task task = taskService.createTaskQuery().singleResult();
		// assertEquals("My Task", task.getName());
		// taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	}

	/**
	 * ͨ��zip/bar��������
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	@Deployment
	public void zipInputStreamTest() throws FileNotFoundException {
		// String barFileName = "path/to/process-one.bar";
		// ZipInputStream inputStream = new ZipInputStream(new FileInputStream(
		// barFileName));
		// repositoryService.createDeployment().name("process-one.bar")
		// .addZipInputStream(inputStream).deploy();
	}

	/**
	 * ͨ��png���̶���ͼƬ��������
	 */
	@Test
	@Deployment
	public void processImgTest() {
		repositoryService.createDeployment().name("Helpdesk-process.bar")
		// .addClasspathResource("activiti/bpmn20/Helpdesk.bpmn20.xml")
				.addClasspathResource("activiti/bpmn20/Helpdesk.png").deploy();

		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey("escalationExample").singleResult();
		String diagramResourceName = processDefinition.getDiagramResourceName();
		InputStream imageStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), diagramResourceName);

		// formService.submitStartFormData(processDefinitionId, properties);
		// formService.submitStartFormData(processDefinitionId, businessKey,
		// properties);
		// formService.submitTaskFormData(taskId, properties);
	}
}