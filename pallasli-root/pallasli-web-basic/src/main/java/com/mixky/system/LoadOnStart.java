package com.mixky.system;

import java.io.FileNotFoundException;

import javax.servlet.ServletException;

import org.springframework.web.context.ContextLoaderServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class LoadOnStart extends ContextLoaderServlet {
	@Override
	public void init() throws ServletException {
		// 初始化系统上下文环境
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ContextHolder.instance().setApplicationContext(context);
		if (!ApplicationDataLoader.instance().isApplicationLoaded()) {
			try {
				ApplicationDataLoader.instance().load();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 装载设计元素
		// try {
		// ApplicationDataLoader.instance().load();
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }

		// 根据运行模式，生成相应文件
		// 根据运行模式，生成相应文件
		// DesignerJsFileBuilder.instance().buildJsDesignerClass();
		// DesignerJsFileBuilder.instance().buildJsDesignerLib();
		// DesignerJsFileBuilder.instance().buildJsDesignerFramework();
		// 生成类文件
		// DesignerCssFileBuilder.instance().buildIconCss();
	}
}
