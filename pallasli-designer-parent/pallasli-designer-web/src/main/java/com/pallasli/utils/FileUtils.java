package com.pallasli.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class FileUtils {

	/**
	 * 读取文件路径对应的文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static String readFileToString(String filepath) {
		File file = readFile(filepath);
		String content = readFileToString(file);
		return content;
	}

	/**
	 * 根据文件路径获取文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static File readFile(String filepath) {
		File file = null;
		if (filepath != null && !filepath.equals("")) {
			try {
				file = new File(filepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 已指定编码格式字符串形式读取文件
	 * 
	 * @param file
	 * @param codeType
	 * @return
	 */
	public static String readFileToString(File file, String codeType) {
		StringBuffer content = new StringBuffer();
		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isReader = new InputStreamReader(fis,
						codeType);

				while (true) {
					char[] buf = new char[1024];
					int length = isReader.read(buf);
					content.append(new String(buf).trim());
					if (length != buf.length) {
						break;
					}
				}
				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	/**
	 * 已编码格式utf-8字符串形式读取文件
	 * 
	 * @param file
	 * @return
	 */
	public static String readFileToString(File file) {
		return readFileToString(file, "utf-8");
	}

	public static boolean writeFile(String destFile, String content) {
		File f = new File(destFile);
		FileOutputStream ostream;
		try {
			ostream = new FileOutputStream(f);
			ostream.write(content.getBytes());
			ostream.flush();
			ostream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean createFile(String path, Boolean isDirectory) {
		File file = new File(path);
		try {
			if (!file.exists()) {

				if (isDirectory) {
					file.mkdir();
				} else {
					file.createNewFile();
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean copyImageFileFrom(String fromPath, String toPath)
			throws IOException {

		File from = new File(fromPath);
		BufferedImage image = ImageIO.read(from);
		File to = new File(toPath);
		ImageIO.write(image, toPath.substring(toPath.lastIndexOf(".") + 1), to);
		return true;
	}
}
