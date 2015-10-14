package com.pallasli.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtils {
	/*
	 * inputFileName 输入一个文件夹 zipFileName 输出一个压缩文件夹
	 */
	public static void zipFolder(String zipFileName, String inputFileName)
			throws Exception {
		zip(zipFileName, new File(inputFileName));
	}

	public static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		System.out.println("zip done");
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(new String((base + "/")
					.getBytes("UTF-8"), "GBK")));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(new String((base).getBytes("UTF-8"),
					"GBK")));

			FileInputStream in = new FileInputStream(f);
			byte[] by = new byte[1024];
			int b;
			while ((b = in.read(by)) != -1) {
				out.write(by, 0, b);
			}
			in.close();
		}
	}

	public static void zipFile(File f) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buf = new byte[1024];
		int len;
		FileOutputStream fos = new FileOutputStream(f.getName() + ".zip");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包
		ZipEntry ze = new ZipEntry(f.getName());// 这是压缩包名里的文件名
		zos.putNextEntry(ze);// 写入新的 ZIP 文件条目并将流定位到条目数据的开始处

		while ((len = bis.read(buf)) != -1) {
			zos.write(buf, 0, len);
			zos.flush();
		}
		bis.close();
		zos.close();
	}
}
