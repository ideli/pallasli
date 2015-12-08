package com.mixky.designer.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DesignerJsFileBuilder {
	private static DesignerJsFileBuilder singleton;
	private Log logger;
	private String charset;

	private DesignerJsFileBuilder() {
		logger = LogFactory.getLog(this.getClass());
		charset = "utf-8";
	}

	public static DesignerJsFileBuilder instance() {
		if (singleton == null) {
			singleton = new DesignerJsFileBuilder();
		}
		return singleton;
	}

	/**
	 * 生成管理工具LIB文件
	 */
	public void buildJsDesignerLib() {
		folderFilesMerge("/resources/js/designer/lib",
				"/resources/output/designer/mixky.awsoft.lib.js", "js");
	}

	/**
	 * 生成管理工具FRAMEWORK文件
	 */
	public void buildJsDesignerFramework() {
		folderFilesMerge("/resources/js/designer/framework",
				"/resources/output/designer/mixky.awsoft.framework.js", "js");
	}

	/**
	 * 生成定制工具类文件
	 */
	public void buildJsDesignerClass() {
		folderFilesMerge("/resources/js/designer/class",
				"/resources/output/designer/mixky.awsoft.lib.classes.js", "js");
	}

	/**
	 * 合并某个目录下的所有文件到某个文件中
	 * 
	 * @param foldername
	 * @param filename
	 */
	private void folderFilesMerge(String foldername, String filename,
			String extname) {
		// foldername = ContextHolder.instance().getRealPath(foldername);
		// filename = ContextHolder.instance().getRealPath(filename);
		int current;
		char[] buffer = new char[1024];
		FileInputStream fis;

		try {
			File filefolder = new File(foldername);
			File[] fileList = filefolder.listFiles();
			FileOutputStream fos = new FileOutputStream(filename);
			OutputStreamWriter osw = new OutputStreamWriter(fos, charset);

			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					continue;
				}
				if (extname != null && !"".equals(extname)) {
					if (!fileList[i].getName().endsWith("." + extname)) {
						continue;
					}
				}
				fis = new FileInputStream(fileList[i]);
				InputStreamReader isReader = new InputStreamReader(fis, charset);
				// 输出注释头
				StringBuffer strbuffer = new StringBuffer();
				strbuffer.append("\r\n");
				strbuffer
						.append("//=================================================================\r\n");
				strbuffer.append("//\t文件名：" + fileList[i].getName() + "\r\n");
				strbuffer
						.append("//=================================================================\r\n");
				PrintStream ps = new PrintStream(fos);
				ps.print(strbuffer.toString());
				// 输出注释头 结束
				while ((current = isReader.read(buffer, 0, 1024)) != -1) {
					osw.write(buffer, 0, current);
					osw.flush();
				}
			}
			osw.close();
		} catch (Exception e) {
			logger.error("生成目录[" + foldername + "]下合并[" + filename + "]文件时发生错误");
			e.printStackTrace();
		}
	}
}
