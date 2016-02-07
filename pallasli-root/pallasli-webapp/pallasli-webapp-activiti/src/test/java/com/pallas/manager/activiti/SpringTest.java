package com.pallas.manager.activiti;

import static org.junit.Assert.assertEquals;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:activiti/activiti-context.cfg.xml")

@Ignore
public class SpringTest {

	public void getEngine() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"activiti/activiti-context.cfg.xml");

		RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
		String deploymentId = repositoryService.createDeployment()
				.addClasspathResource("activiti/bpmn20/hello.bpmn20.xml").deploy().getId();
		UserBean userBean = (UserBean) applicationContext.getBean("userBean");
		userBean.hello();
	}

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	@Ignore
	public void simpleProcessTest() {
		if (true)
			return;
		getEngine();
		runtimeService.startProcessInstanceByKey("helloProcess");
		Task task = taskService.createTaskQuery().singleResult();
		// assertEquals("My Task", task.getName());
		// taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	}
}
