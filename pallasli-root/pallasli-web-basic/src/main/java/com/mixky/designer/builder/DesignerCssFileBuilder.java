package com.mixky.designer.builder;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DesignerCssFileBuilder {

	private static DesignerCssFileBuilder singleton;

	private Log LOG = LogFactory.getLog(this.getClass());

	/**
	 * 基路径.
	 */
	private String basePath;

	/**
	 * CSS名称中路径部分用于定位的基路径.
	 */
	private String cssClassBasePath;

	private String template;

	DesignerCssFileBuilder() {
		URL url = getClass().getResource("cssTemplate.txt");
		if (url == null) {
			throw new RuntimeException("cssTemplate.txt missing!");
		}

		try {
			InputStream is = url.openStream();
			// template = FileTool.readFromStream(is);
		} catch (IOException e) {
			LOG.error("读取CSS模板定义文件时出错!", e);
			throw new RuntimeException(e);
		}

	}

	public static DesignerCssFileBuilder instance() {
		if (singleton == null) {
			singleton = new DesignerCssFileBuilder();
		}
		return singleton;
	}

	public void buildIconCss() {
		// basePath = ContextHolder.instance().getRealPath("/resources/icon/");
		// cssClassBasePath =
		// ContextHolder.instance().getRealPath("/resources/icon/");
		// String dirname =
		// ContextHolder.instance().getRealPath("/resources/icon/");
		// File dir = new File(dirname);
		// String cssFileName =
		// ContextHolder.instance().getRealPath("/resources/css/") +
		// "mixky.awsoft.icon.css";
		// try {
		// buildIconCss(dir, cssFileName);
		// } catch (IOException e) {
		// LOG.error("初始化图片的css文件失败", e);
		// }
	}

	/**
	 * 创建指定目录下图片的css文件
	 * 
	 * @param dir
	 * @param cssFileName
	 * @throws IOException
	 */
	private void buildIconCss(File dir, String cssFileName) throws IOException {
		List<File> files = computeAllImageFiles(dir);

		StringBuffer sb = new StringBuffer(8192);
		for (File file : files) {
			sb.append(computeCssCode(file));
		}
		String text = sb.toString();

		File cssFile = new File(cssFileName);
		// FileTool.writeToFile(text, cssFile);
	}

	/**
	 * 在模板的基础上替换，得到修改后的内容。要替换的变量： %fileName%： %fileNameWithoutExt%：
	 * %relativePath%
	 */
	private String computeCssCode(File file) {
		String result = template;
		result = result.replaceAll("%fileName%", file.getName());
		// result = result.replaceAll("%fileNameWithoutExt%",
		// FileTool.getFileNameWithoutExt(file.getName()));
		// result = result.replaceAll("%relativePath%",
		// FileTool.getSubPath(basePath, file));
		//
		// String relativePathForCSSClass =
		// FileTool.getSubPath(cssClassBasePath, file).replace('/', '-');
		// result = result.replaceAll("%relativeForCSSClass%",
		// relativePathForCSSClass);
		return result;
	}

	/**
	 * @param dir
	 * @return
	 */
	private List<File> computeAllImageFiles(File dir) {
		List<File> files = new ArrayList<File>(32);
		for (File file : dir.listFiles(new SuffixFileFilter(".gif,.png,.jpg"))) {
			if (file.isFile()) {
				files.add(file);
			} else if (file.isDirectory()) {
				files.addAll(computeAllImageFiles(file));
			}
		}
		return files;
	}

}

/**
 * 文件筛选器，按后缀筛选，包含子文件夹. 排除CVS/.svn等特殊目录。
 */
class SuffixFileFilter implements FileFilter {

	private String[] suffixs;
	private static String[] globalIngoreDirNames = { "CVS", ".svn" };

	SuffixFileFilter(String suffix) {
		if (suffix == null) {
			throw new IllegalArgumentException("suffix must not null!");
		}
		suffix = suffix.trim();
		if (suffix.length() == 0) {
			throw new IllegalArgumentException("suffix must not empty!");
		}
		this.suffixs = suffix.toLowerCase().split(",");
	}

	/**
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		// 目录的情况
		if (file.isDirectory()) {
			for (String dirName : globalIngoreDirNames) {
				if (dirName.equals(file.getName())) {
					return false;
				}
			}
			return true;
		}

		// 文件的情况
		for (String suffix : suffixs) {
			if (file.getName().endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

}
