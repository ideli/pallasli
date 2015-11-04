package com.pallas.bpm.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pallas.bpm.service.ManagerService;

/**
 * 
 * 流程管理测试类：创建流程，保存流程，删除流程，发布流程，取消发布，导出流程（全部，单一，多个），导入流程（全部，单一，多个），
 * 
 * 
 * @author lyt1987
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "" })
public class TestManagerService {
	@Autowired
	ManagerService managerService;

	@Test
	public void createProcess() {}
	@Test
	public void deployProcess() {}
	@Test
	public void unDeployProcess() {}
	@Test
	public void importZipOrBar() {}
	@Test
	public void exportZipOrBar() {}
	@Test
	public void importBpmn() {}
	@Test
	public void exportBpmn() {}
	@Test
	public void stopProcessInstance() {}
	@Test
	public void suspendProcessInstance() {}
	@Test
	public void activeProcessInstance() {}
	@Test
	public void stopProcess() {}
	@Test
	public void suspendProcess() {}
	@Test
	public void activeProcess() {}

}
