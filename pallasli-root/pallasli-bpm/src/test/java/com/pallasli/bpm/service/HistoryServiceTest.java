package com.pallasli.bpm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti/activiti.cfg.xml")
public class HistoryServiceTest {

	@Autowired
	HistoryProcessInstanceQueryService hisProInstQueryService;
	@Autowired
	HistoryTaskQueryService hisTaskQueryService;

	Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void query() {

		String user = "000000001196";
		log.info("测试我的已办");
		log.info("测试我申请的流程");
		hisProInstQueryService.listProInstByStartUser(user);
		log.info("测试我参与的流程");
		hisProInstQueryService.listProInstByCandidateUser(user);
		log.info("测试我办结的流程");
		hisProInstQueryService.listProInstByTerminateUser(user);
		log.info("测试我办理的任务");
		hisTaskQueryService.listTaskByAuditUser(user);
		log.info("测试我参与的任务");
		hisTaskQueryService.listTaskByCandidateUser(user);
		log.info("测试按时间范围查询");
		log.info("测试按办理结果查询流程（完成（审批通过，审批不通过），取消，进行中，强行终止）");
		log.info("测试按办理结果查询任务（通过，不通过，发回修正）");
		log.info("测试逾期任务");

		log.info("测试带有条件的");
		log.info("");
		log.info("");
	}
}
