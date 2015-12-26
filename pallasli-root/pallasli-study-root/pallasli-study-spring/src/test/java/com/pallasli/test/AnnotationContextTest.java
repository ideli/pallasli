package com.pallasli.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pallasli.bean.TestBean;
import com.pallasli.config.AnnotationConfig;
import com.pallasli.control.UserController;
import com.pallasli.scanclass.ImportTestBean;
import com.pallasli.scanclass.ScanTestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnnotationConfig.class)
public class AnnotationContextTest {
	@Autowired
	private TestBean bean;
	@Autowired
	private ScanTestBean scanbean;
	@Autowired
	private ImportTestBean importTestBean;

	@Autowired
	private UserController userController;

	@Test
	public void cdShouldNotBeNull() {
		assertNotNull(bean);
		assertNotNull(scanbean);
		assertNotNull(scanbean.getImportTestBean());
		assertNotNull(importTestBean);
		assertNotNull(userController);
		userController.execute();
		System.out.println("end");
	}
}