package com.pallas.activiti.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activitiContext.xml")
public class BpmServiceTest {

	@Autowired
	private BpmService bpmService;

	@Test
	public void createProcessDefinition() {
		// String id = bpmService.createProcessDefinition("key", "name",
		// "category", "description");
		// Assert.assertEquals("1062501", id);
	}

	@Test
	public void saveProcessDefinition() {
		String source0 = bpmService.getEditorSource("1062501");
		System.out.println(source0);
		Assert.assertTrue(source0.trim().endsWith("{}"));

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");

		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);
		String id = bpmService.saveProcessDefinition("1062501", "key",
				"new_name", "category", "description", editorNode);

		String source = bpmService.getEditorSource("1062501");
		System.out.println(source);
		Assert.assertTrue(source.length() > 10);

	}

	@Test
	public void deleteProcessDefinition() {

	}

	@Test
	public void deployProcess() {

	}

	@Test
	public void importProcess() {

	}

	@Test
	public void exportProcess() {

	}

	@Test
	public void unDeployProcess() {

	}

	@Test
	public void startProcessInstance() {

	}

	@Test
	public void stopEngine() {

	}

	@Test
	public void completeTask() {

	}

	@Test
	public void findTaskInfos() {

	}

}
