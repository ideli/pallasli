package com.pallas.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring为ApplicationContext提供的三种方式 ClassPathXmlApplicationContext
 * FileSystemXmlApplicationContext XmlWebApplicationContext 其中
 * XmlWebApplicationContext是专为Web工程定制的。使用举例如下： //加载多个文件 String[]
 * Local={"classpath:applicationContext.xml"}; //这是放在src下，所以用classpath: 当然你也可以不用
 * //第一种方式 ApplicationContext context=new
 * FileSystemXmlApplicationContext(Local); //第二种方式 context= new
 * ClassPathXmlApplicationContext(Local);
 * 这两种方式一般用于在Action中或者Manager中获得其他的Manager 第三种方式： 在web.xml中配置 配置监听器： <listener>
 * <listener-class>org.springframework.web.context.ContextLoaderListener
 * </listener-class> </listener> 配置监听器监听的xml <context-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>classpath:applicationContext.xml</param-value> </context-param>
 * Jsp中处理： <% //第三种方式 ServletContext servletContext =
 * request.getSession().getServletContext(); ApplicationContext
 * ctx=WebApplicationContextUtils.getWebApplicationContext(servletContext);
 * out.println(ctx);//测试
 * 
 * %> 这种方式一般用于在jsp中取Manager,当然其他的你能get到的 都可以获得 jsp 中导入的包 <%@ page import=
 * "org.springframework.web.context.support.WebApplicationContextUtils,org.springframework.context.ApplicationContext"
 * %>
 * 
 * @author Administrator
 * 
 */
public class BaseAction {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 读取Customer配置文件
		// Configuration config = new Configuration().configure(
		// "/hibernate/designer.cfg.xml").setProperty(
		// Environment.FORMAT_SQL, "true");
		//
		// // 通过配置文件建表
		// SchemaUpdate schemaUpdate = new SchemaUpdate(config);
		// schemaUpdate.execute(true, true);
		//
		// // 读取Customer配置文件
		// Configuration config2 = new Configuration().configure(
		// "/hibernate/databaseAll.cfg.xml").setProperty(
		// Environment.FORMAT_SQL, "true");
		//
		// // 通过配置文件建表
		// SchemaUpdate schemaUpdate2 = new SchemaUpdate(config2);
		// schemaUpdate2.execute(true, true);
	}

	// private static ApplicationContext ctx = null;

	private static ClassPathXmlApplicationContext context = null;

	public ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"application-context.xml");
			// this.ctx = new FileSystemXmlApplicationContext("/"
			// + SystemConstant.WEB_ROOT
			// + "WEB-INF/classes/application-context.xml");
		}

		return context;
	}
}
