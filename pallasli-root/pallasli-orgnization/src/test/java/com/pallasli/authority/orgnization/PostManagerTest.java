package com.pallasli.authority.orgnization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostManagerTest {

	public ClassPathXmlApplicationContext context;

	@Before
	public void getContext() {
		context = new ClassPathXmlApplicationContext("orgnization-beans.xml");

	}

	@Test
	public void testPost() {
		AbstractUser user = (AbstractUser) context.getBean("userBean");
		Assert.assertEquals("", user.getF_caption());

	}
}
