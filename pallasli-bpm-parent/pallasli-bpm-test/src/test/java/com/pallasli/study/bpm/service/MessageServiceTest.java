package com.pallasli.study.bpm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MessageServiceTest extends BaseTest {

	@Test
	public void insertSms() {
		messageService.insertSms("", "", "", "");
	}

	@Test
	public void searchTelNumberEmail() {
		messageService.searchTelNumberEmail("");
	}
}
