package com.pallasli.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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

	/**
	 * @param zipFilePath
	 *            打包文件存放路径
	 * @param inputFolderName
	 *            需要打包的文件夹
	 * @throws Exception
	 */
	public static void zip(String zipFileName, File inputFolder)
			throws Exception {
		FileOutputStream fileOut = new FileOutputStream(zipFileName);
		ZipOutputStream out = new ZipOutputStream(fileOut);
		zip(out, inputFolder, "");
		out.flush();
		fileOut.flush();
		out.close();
		fileOut.close();
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

	/**
	 * 解压缩zip包
	 * 
	 * @param zipFilePath
	 *            zip文件路径
	 * @param targetPath
	 *            解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
	 * @throws IOException
	 */
	public void unzip(String zipFilePath, String targetPath) throws IOException {
		byte[] buf = new byte[20480];
		int readSize = -1;
		ZipInputStream zis = null;
		FileOutputStream fos = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath);
			zipFile.close();
			// 判断目标目录是否存在，不存在则创建
			File newdir = new File(targetPath);
			if (!newdir.exists()) {
				newdir.mkdirs();
			}
			zis = new ZipInputStream(new FileInputStream(new File(zipFilePath)));

			ZipEntry zipEntry = zis.getNextEntry();
			while (null != zipEntry) {
				String zipEntryName = zipEntry.getName().replace('\\', '/');

				// 判断zipEntry是否为目录，如果是，则创建
				if (!zipEntryName.trim().equals("")) {
					if (zipEntry.isDirectory()) {
						int indexNumber = zipEntryName.lastIndexOf('/');
						File entryDirs = new File(targetPath
								+ zipEntryName.substring(0, indexNumber));
						entryDirs.mkdirs();
						entryDirs = null;
					} else {
						try {
							fos = new FileOutputStream(targetPath
									+ zipEntryName);
							while ((readSize = zis.read(buf, 0, 20480)) != -1) {
								fos.write(buf, 0, readSize);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								if (null != fos) {
									fos.close();
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				zipEntry = zis.getNextEntry();
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != zis) {
					zis.close();
				}
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
