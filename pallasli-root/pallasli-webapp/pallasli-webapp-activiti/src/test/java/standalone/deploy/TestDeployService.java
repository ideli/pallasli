package standalone.deploy;

import static org.junit.Assert.assertNotNull;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class TestDeployService {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule(
			"/standalone/activiti.cfg.xml");

	@Test
	@Deployment(resources = { "standalone/MyProcess.bpmn" })
	public void testDeploy() {

		RuntimeService runtimeService = activitiRule.getRuntimeService();
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("myProcess");
		assertNotNull(processInstance);

	}
}
