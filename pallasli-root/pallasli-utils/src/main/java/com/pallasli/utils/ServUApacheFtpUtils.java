package com.pallasli.utils;


public class ServUApacheFtpUtils {
	// private static Logger log = Logger.getLogger(ServUEdtFtpUtilsTest.class);
	//
	// private static String host = "127.0.0.1";
	// private static int port = 21;
	// private static String user = "pallas";
	// private static String password = "pallas";
	// private static FTPClient ftp = null;
	//
	// public static void connect() throws IOException {
	// if (ftp == null || !ftp.isConnected()) {
	// log.info("Connecting");
	// // set up client
	// ftp = new FTPClient();
	// ftp.connect(host, port);
	//
	// }
	// }
	//
	// public static void login() throws IOException {
	// connect();
	// log.info("Logging in");
	// ftp.login(user, password);
	//
	// }
	//
	// public static void dir() throws IOException {
	// connect();
	// login();
	// String[] files = ftp.listNames(".");
	// for (int i = 0; i < files.length; i++)
	// System.out.println(files[i]);
	// }
	//
	// public static void putFile() throws IOException {
	// connect();
	// File f = new File("test.txt");
	// FileInputStream fis = null;
	// if (f.exists()) {
	// fis = new FileInputStream(f);
	// // 设置上传目录
	// // ftp.changeWorkingDirectory("/admin/pic");
	// ftp.setBufferSize(1024);
	// ftp.setControlEncoding("GBK");
	// // 设置文件类型
	// ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	// ftp.storeFile("/wasy35022.sql", fis);
	// }
	// }
	//
	// public static void getFile(String srcPath, String toPath)
	// throws IOException {
	// connect();
	// FileOutputStream fos = null;
	//
	// fos = new FileOutputStream(toPath);
	// // 设置文件类型
	// ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	// ftp.retrieveFile(srcPath, fos);
	// fos.close();
	//
	// }
	//
	// public static void copyFile() throws IOException {
	// connect();
	// }
	//
	// public static void deleteFile() throws IOException {
	// connect();
	// ftp.deleteFile("test.txt");
	//
	// }
	//
	// public static void quit() throws IOException {
	// if (ftp != null && ftp.isConnected())
	// ftp.quit();
	// }
	//
	// public static void disConnected() throws IOException {
	// if (ftp != null && ftp.isConnected()) {
	// ftp.disconnect();
	// ftp = null;
	// }
	// }

}
