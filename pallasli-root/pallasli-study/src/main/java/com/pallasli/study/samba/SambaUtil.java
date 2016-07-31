package com.pallasli.study.samba;

import java.net.MalformedURLException;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class SambaUtil {

	public static void delete(String filepath, String username, String pwd) {
		SmbFile f = null;
		try {
			f = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
			try {
				if (f.exists()) {
					f.delete();
				}
			} catch (SmbException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static boolean exists(String filepath, String username, String pwd) throws Exception {
		SmbFile file = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
		try {
			return file.exists();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean filerename(String filepath, String newFilename, String username, String pwd) {
		try {
			SmbFile f = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
			if (f.isFile()) {
				String str = filepath.substring(0, filepath.lastIndexOf("/"));
				str = "smb://" + username + ":" + pwd + "@" + str + "/" + newFilename;
				f.renameTo(new SmbFile(str));
			} else if (f.isDirectory()) {
				String str = filepath.substring(0, filepath.length() - 1);
				str = filepath.substring(0, str.lastIndexOf("/"));
				str = "smb://" + username + ":" + pwd + "@" + str + "/" + newFilename;
				f.renameTo(new SmbFile(str));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void mkdir(String dir, String username, String pwd) {
		try {
			SmbFile f = new SmbFile("smb://" + username + ":" + pwd + "@" + dir);
			if (!f.exists()) {
				f.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mkfile(String filepath, String username, String pwd) {
		try {
			SmbFile f = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mkfile(String filepath, String username, String pwd, String content) {
		try {
			SmbFile f = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
			if (!f.exists())
				f.createNewFile();
			writefile(filepath, content, username, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readfile(String filepath, String username, String pwd) {
		StringBuffer sb = new StringBuffer("");
		try {
			SmbFile f = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
			if (f.exists() && f.isFile()) {
				int length = f.getContentLength();// 得到文件的大小
				byte buffer[] = new byte[length];

				SmbFileInputStream in = new SmbFileInputStream(f);
				while ((in.read(buffer)) != -1) {
					sb.append(new String(buffer));
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static boolean isdir(String filepath, String username, String pwd) throws Exception {
		String dir = "smb://" + username + ":" + pwd + "@" + filepath;
		SmbFile f = new SmbFile(dir);
		return f.isDirectory();
	}

	public static void writefile(String filepath, String content, String username, String pwd) {
		try {
			SmbFile to = new SmbFile("smb://" + username + ":" + pwd + "@" + filepath);
			SmbFileOutputStream out = new SmbFileOutputStream(to);
			out.write(content.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@org.junit.Test
	public void testCreateDir() {
		String dir = "www/sillycat/com/share/test1";
		SambaUtil.mkdir(dir, "guest", "guest");
		SambaUtil.mkfile(dir + "/test2.txt", "guest", "guest", "<html></html>中文hello world!");
		String tmp = SambaUtil.readfile(dir + "/test2.txt", "guest", "guest");
		System.out.println(tmp);
		SambaUtil.delete(dir + "/test2.txt", "guest", "guest");
	}
}