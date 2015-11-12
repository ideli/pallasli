package com.bpm.service.test;

import org.activiti.engine.impl.test.ResourceActivitiTestCase;

public class RuleTest extends ResourceActivitiTestCase {

	public RuleTest() {
		super("activiti.cfg.xml");
	}

	@SuppressWarnings("unchecked")
	@org.activiti.engine.test.Deployment(resources = {
			"rulesDeploymentTestProcess.bpmn20.xml", "simpleRule1.drl" })
	public void testRulesDeployment() {

		// org.activiti.engine.impl.rules.RulesDeployer deployer = new
		// org.activiti.engine.impl.rules.RulesDeployer();
		// org.activiti.engine.impl.rules.RulesHelper helper;
		//
		// Deployment deployment = repositoryService.createDeployment()
		// .addClasspathResource("rulesDeploymentTestProcess.bpmn20.xml")
		// .addClasspathResource("simpleRule1.drl").deploy();
		//
		// deployer.deploy((DeploymentEntity) deployment, null);
		//
		// Map<String, Object> variableMap = new HashMap<String, Object>();
		// BeanForRule order1 = new BeanForRule();
		// order1.setCount(2);
		// variableMap.put("otherOrder", order1);
		//
		// ProcessInstance processInstance = runtimeService
		// .startProcessInstanceByKey("rulesDeployment", variableMap);
		// assertNotNull(processInstance);
		// assertTrue(processInstance.getProcessDefinitionId().startsWith(
		// "rulesDeployment:1"));
		//
		// order1 = (BeanForRule) runtimeService.getVariable(
		// processInstance.getId(), "otherOrder");
		// System.out.println(order1.isValid());
		// assertTrue(order1.isValid());
		//
		// Collection<Object> ruleOutputList = (Collection<Object>)
		// runtimeService
		// .getVariable(processInstance.getId(), "rulesOutput");
		// assertNotNull(ruleOutputList);
		// assertEquals(1, ruleOutputList.size());
		// order1 = (BeanForRule) ruleOutputList.iterator().next();
		// assertTrue(order1.isValid());
		//
		// List<Task> taskList = taskService.createTaskQuery().list();
		// System.out.println("taskListsize" + taskList.size());
	}
}
