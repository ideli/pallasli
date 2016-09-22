package com.pallas.system;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

import com.pallas.sys.dispatch.action.ContextHolder;

@SuppressWarnings("serial")
public class LoadOnStart extends HttpServlet implements ApplicationContextAware {
	private WebApplicationContext context;

	@Override
	public void init() throws ServletException {
		// 初始化系统上下文环境
		// WebApplicationContext context =
		// WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		ContextHolder.instance().setApplicationContext(context);
		// DataServicePool.rebuildDataServicePool();
		// if (context.containsBean("mixky-thread-manager")) {
		// MixkyThreadManager threadMgr =
		// (MixkyThreadManager)context.getBean("mixky-thread-manager");
		// threadMgr.activate();
		// }
		/*
		 * // 装载应用相关初始数据 ApplicationDataLoader.instance().load();
		 * 
		 * // 根据运行模式，生成相应文件 String appcode =
		 * ApplicationParameters.instance().getApplicationCode();
		 * JsCodeBuilder.instance().buildJsMenus(appcode);
		 * JsCodeBuilder.instance().buildJsModules(appcode);
		 * JsCodeBuilder.instance().buildJsViews(appcode);
		 * JsCodeBuilder.instance().buildJsDocuments(appcode);
		 * JsCodeBuilder.instance().buildJsDictionarys(appcode);
		 */
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = (WebApplicationContext) context;
	}
}