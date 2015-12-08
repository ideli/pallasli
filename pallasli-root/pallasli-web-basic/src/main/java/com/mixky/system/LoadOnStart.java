package com.mixky.system;

import javax.servlet.ServletException;

@SuppressWarnings("serial")
public class LoadOnStart {
	public void init() throws ServletException {
		// 初始化系统上下文环境
		// WebApplicationContext context = WebApplicationContextUtils
		// .getWebApplicationContext(this.getServletContext());
		// ContextHolder.instance().setApplicationContext(context);
		// if (!ApplicationDataLoader.instance().isApplicationLoaded()) {
		// try {
		// ApplicationDataLoader.instance().load();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
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
