package com.pallasli.study.bpm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OrgnizationServiceTest extends BaseTest {

	@Test
	public void getUserInfoList() {

		((OrgnizationService) ctx.getBean("orgnizationService"))
				.getUserInfoList("", 0, 0);
		orgnizationService.getUserInfoList("", 0, 0);
	}

	@Test
	public void getGroupInfoList() {
		orgnizationService.getGroupInfoList("", 0, 0);
	}

	@Test
	public void getDepartmentInfoList() {
		orgnizationService.getDepartmentInfoList("", 0, 0);
	}

	@Test
	public void getPositionInfoList() {
		orgnizationService.getPositionInfoList("", 0, 0);
	}

}
