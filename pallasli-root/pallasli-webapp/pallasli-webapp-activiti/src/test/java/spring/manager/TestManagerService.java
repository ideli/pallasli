package spring.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/activiti.cfg.xml" })
public class TestManagerService {
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;
	@Autowired
	ManagementService managementService;
	@Autowired
	IdentityService identityService;
	@Autowired
	FormService formService;

	@Test
	public void startProcess() {
		// 发布
		repositoryService.createDeployment()
				.addClasspathResource("standalone/MyProcess.bpmn").deploy();
		long deploycount = repositoryService.createDeploymentQuery().count();
		assertEquals(1, deploycount);

		// 启动
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("myProcess");
		assertNotNull(processInstance);
		Task curTask = taskService.createTaskQuery().taskAssignee("activiti")
				.singleResult();
		assertNotNull(curTask);
		assertEquals("审核", curTask.getName());
		// 签收
		taskService.claim(curTask.getId(), "activiti");
		List<Deployment> deployes = repositoryService.createDeploymentQuery()
				.orderByDeploymenTime().desc().list();
		Deployment deploy = deployes.remove(0);
		repositoryService.deleteDeployment(deploy.getId(), false);
		deploycount = repositoryService.createDeploymentQuery().count();
		assertEquals(0, deploycount);

	}
}
