package com.pallasli.study.samba;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class Download {
	Logger logger = Logger.getLogger(getClass());
	String tempDir = "./tmp";

	public void downloadViaShare(final String ip, final String user, final String password, final String dir) {
		logger.debug("Share(SMB) download!");

		String newDir = dir;
		String url = "";
		SmbFile[] fileList = null;
		FileOutputStream fos = null;
		SmbFileInputStream smbIs = null;
		byte[] buffer = new byte[8192];
		int readBytes = 0;
		int totalBytes = 0;

		if (!dir.endsWith("/")) // directory must end with "/"
			newDir = dir + "/";
		url = "smb://" + user + ":" + password + "@" + ip + "/" + newDir;

		long startTime = System.currentTimeMillis();
		try {
			SmbFile shareDir = new SmbFile(url);
			if (shareDir.isDirectory()) {
				fileList = shareDir.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					if (fileList[i].isFile()) {
						smbIs = new SmbFileInputStream(fileList[i]);
						fos = new FileOutputStream(new File(tempDir + File.separator + fileList[i].getName()));
						while ((readBytes = smbIs.read(buffer)) > 0) {
							fos.write(buffer, 0, readBytes);
							totalBytes += readBytes;
						}
						smbIs.close();
						fos.close();
						logger.debug(fileList[i].getName() + " is downloaded!");
						try {
							fileList[i].delete();
						} catch (SmbAuthException smbae) {
							logger.debug(fileList[i].getName() + " can not be deleted！");
						}
					}
				}
				long endTime = System.currentTimeMillis();
				long timeTaken = endTime - startTime;
				logger.debug(totalBytes + "bytes downloaded in " + timeTaken / 1000 + " seconds at "
						+ ((totalBytes / 1000) / Math.max(1, (timeTaken / 1000))) + "Kb/sec");
			}
		} catch (MalformedURLException urle) {
			logger.debug("Incorrect URL format!");
		} catch (SmbException smbe) {
			smbe.printStackTrace();
			logger.debug(this.getClass().getName() + "||" + smbe.getMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.debug(this.getClass().getName() + "||" + ioe.getMessage());
		} finally {
			try {
				smbIs.close();
				fos.close();
			} catch (Exception smbe) {
				logger.debug(this.getClass().getName() + "||" + smbe.getMessage());
			}
		}

	}
}
