package com.pallasli.bpm.service.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorTest {

	Logger log = LoggerFactory.getLogger(getClass());

	public void test() {

		log.info("测试运行中的流程");
		log.info("测试已办结的流程");
		log.info("测试运行中的任务");
		log.info("测试已办理的任务");
		log.info("测试已逾期的任务");
		log.info("测试定时job");
		log.info("测试流程变量");
		log.info("测试任务强转");
		log.info("测试实例强删");
		log.info("");
	}
}
