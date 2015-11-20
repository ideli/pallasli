package com.pallasli.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.pallasli.constant.SystemConstant;

public class FileUtils {

	/**
	 * 读取文件路径对应的文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static String readFileToString(String filepath) {
		File file = null;
		if (filepath != null && !filepath.equals("")) {
			try {
				file = new File(filepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String content = readFileToString(file);
		return content;
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
					content.append(new String(buf));
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

	private String data_dir = SystemConstant.DATA_PATH;

	private static long row = 0;
	private static int handleCount = 0;

	public String getFilePath(String fileName) {
		return data_dir + SystemConstant.FILE_SEP + fileName;
	}

	public String getDataDir() {
		return data_dir;
	}

	public String getDir() {
		String dir = data_dir + SystemConstant.FILE_SEP
				+ formatDate(new Date(), "yyyyMMdd");
		if (createDir(dir)) {
			return dir;
		} else {
			return ".";
		}
	}

	public long getFileSize(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			return f.length();
		} else {
			return 0;
		}
	}

	public long getFileRow(String filePath) {
		int i_line = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), SystemConstant.CHARSET));
			String Line = br.readLine();
			br.close();
			while (Line != null) {
				i_line++;
				Line = br.readLine();
			}
		} catch (Exception e) {
		}
		return i_line;
	}

	public void ListFile(String dir) {
		File[] fs = getDirFile(dir);
		for (int i = 0; i < fs.length; i++) {
			try {
				if (fs[i].isDirectory()) {
					ListFile(fs[i].getAbsolutePath());
				} else if (fs[i].isFile()) {
					FileHandle(fs[i]);
				}
			} catch (Exception e) {
			}
		}
	}

	public String getHandleResult() {
		return "row = " + row + ", handleCount = " + handleCount;
	}

	private void FileHandle(File file) {
		if (file.getName().lastIndexOf(".java") != -1) {
			row += getFileRow(file.getAbsolutePath());
			handleCount++;
		}
	}

	public void createFile(String path, String filename) {
		try {
			File f = new File(path, filename);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (Exception e) {
		}
	}

	public void createFile(String filePath) {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (Exception e) {
		}
	}

	public void deleteFile(String filename) {
		deleteFile(this.data_dir, filename);
	}

	public void deleteFile(String path, String filename) {
		try {
			File f = new File(path, filename);
			if (f.exists()) {
				f.delete();
			}
		} catch (Exception e) {
		}
	}

	public static boolean createDir(String dir) {
		try {
			File f = new File(dir);
			if (!f.exists()) {
				return f.mkdirs();
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static void deleteDir(String dir) {
		try {
			File f = new File(dir);
			if (f.exists()) {
				if (!f.delete()) {
					File[] files = getDirFile(dir);
					for (int i = 0; i < files.length; i++) {
						if (files[i].isDirectory()) {
							deleteDir(files[i].getAbsolutePath());
						} else {
							files[i].delete();
						}
					}
					if (!f.delete()) {
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static File[] getDirFile(String path) {
		File d = new File(path);
		return d.listFiles();
	}

	public static String readFile(String path, String filename) {
		return readFile(path + SystemConstant.FILE_SEP + filename);
	}

	public static String readFile(String filePathName) {
		StringBuffer sb = new StringBuffer();
		// int i_line = 0;
		try {
			// FileReader fr = new FileReader(filePahtName);
			// BufferedReader br = new BufferedReader(fr);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePathName), SystemConstant.CHARSET));
			String Line = br.readLine();

			while (Line != null) {
				// i_line++;
				sb.append(Line);// .append("\n");
				Line = br.readLine();
			}
			br.close();
			// fr.close();
		} catch (Exception e) {
		}
		return sb.toString().replaceAll("'", "\\\\\'");
	}

	public static boolean writeFile(String str, String filePath) {
		try {
			/*
			 * FileWriter fw = new FileWriter(filePath); fw.write(str);
			 * fw.close();
			 */
			FileOutputStream fos = new FileOutputStream(filePath, true);
			fos.write(str.getBytes(SystemConstant.CHARSET));
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean writeFile(String str, String path, String filename) {
		String filePath = path + SystemConstant.FILE_SEP + filename;
		return writeFile(str, filePath);
	}

	public static boolean appendFile(String str, String path, String filename) {
		String filePath = path + SystemConstant.FILE_SEP + filename;
		return appendFile(str, filePath);
	}

	public static boolean appendFile(String str, String filePath) {
		try {
			/*
			 * FileWriter fw = new FileWriter(filePath, true); fw.write(str);
			 * fw.close();
			 */
			FileOutputStream fos = new FileOutputStream(filePath, true);
			fos.write(str.getBytes(SystemConstant.CHARSET));
			fos.close();
			return true;

			/*
			 * RandomAccessFile rf = new RandomAccessFile(path +
			 * SystemConstants.FILE_SEP + filename, "rw"); rf.seek(rf.length());
			 * rf.writeChars(str); rf.close(); return true;
			 */
		} catch (Exception e) {
			return false;
		}
	}

	public boolean writeByteFile(byte[] b, String path, String filename) {
		try {
			File file = new File(path + SystemConstant.FILE_SEP + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write(b);
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public boolean moveFile(String filename, String dir) {
		File file = new File(filename);
		File newFile = new File(dir + File.separator + file.getName());
		System.out.println("newFile path:" + newFile.getAbsoluteFile());
		return file.renameTo(newFile);
	}

	public String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}

	public String getFileName(int cmd) {
		String str = String.valueOf(cmd);
		str = StringUtils.repeat("0", (3 - str.length())) + str;
		return data_dir + File.separator + str
				+ DateUtils.formatDate(new Date(), "_yyyyMMdd_HHmmss_SS")
				+ ".txt";
	}

	public String getRandFileaName(int cmd) {
		String str = String.valueOf(cmd);
		str = StringUtils.repeat("0", (3 - str.length())) + str;
		return data_dir + File.separator + str + "_"
				+ (new RandomGUID()).toString() + ".txt";
	}

	public String getFileName(String prefix, String postfix, String ext,
			String pattern) {
		return getFileName(prefix, postfix, ext, pattern, new Date());
	}

	public String getFileName(String prefix, String postfix, String ext,
			String pattern, Date dt) {
		return data_dir + File.separator + prefix
				+ DateUtils.formatDate(dt, pattern) + postfix + "." + ext;
	}

}
