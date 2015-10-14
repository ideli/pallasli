package com.pallasli.utils;


public class ServUEdtFtpUtils {
	// private static Logger log = Logger.getLogger(ServUEdtFtpUtilsTest.class);
	//
	// private static String host = "127.0.0.1";
	// private static int port = 21;
	// private static String user = "pallas";
	// private static String password = "pallas";
	// private static FTPClient ftp = null;
	// private static FTPMessageCollector listener = new FTPMessageCollector();
	//
	// public static void connect() throws IOException, FTPException {
	// if (ftp == null || !ftp.connected()) {
	// log.info("Connecting");
	// // set up client
	// ftp = new FTPClient();
	// ftp.setRemoteHost(host);
	// ftp.setRemotePort(port);
	// setting();
	// ftp.connect();
	// }
	// }
	//
	// public static void login() throws IOException, FTPException {
	// connect();
	// log.info("Logging in");
	// ftp.login(user, password);
	//
	// }
	//
	// public static void setting() throws IOException, FTPException {
	// connect();
	// log.debug("Setting up passive, ASCII transfers");
	// ftp.setConnectMode(FTPConnectMode.PASV);
	// ftp.setType(FTPTransferType.ASCII);
	// // ftp.setAutoPassiveIPSubstitution(true);
	// }
	//
	// public static void dir() throws IOException, FTPException {
	// connect();
	// login();
	// String[] files = ftp.dir(".", true);
	// for (int i = 0; i < files.length; i++)
	// System.out.println(files[i]);
	// }
	//
	// public static void putFile() throws IOException, FTPException {
	// connect();
	// File f = new File("test.txt");
	// if (f.exists())
	// ftp.put("test.txt", "test.txt");
	// }
	//
	// public static void getFile() throws IOException, FTPException {
	// connect();
	// if (ftp.exists("test.txt"))
	// ftp.get("test.txt" + ".copy", "test.txt");
	//
	// }
	//
	// public static void copyFile() throws IOException, FTPException {
	// connect();
	// }
	//
	// public static void deleteFile() throws IOException, FTPException {
	// connect();
	// ftp.delete("test.txt");
	//
	// }
	//
	// public static void quit() throws IOException, FTPException {
	// if (ftp != null && ftp.connected())
	// ftp.quit();
	// }
	//
	// public static void getMessage() throws IOException, FTPException {
	// connect();
	// String messages = listener.getLog();
	// log.info(messages);
	// }

}
