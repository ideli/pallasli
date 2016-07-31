package com.pallasli.study.samba;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class Test {
	public static void main(String[] args) {

		FileInputStream fis = null;
		SmbFileOutputStream sfos = null;
		try {
			fis = new FileInputStream(new File("c:/source.txt"));
			sfos = new SmbFileOutputStream(new SmbFile("smb://smbusr:smbusr@192.168.1.94/smbusr/dest.txt"));

			byte[] buffer = new byte[1024];
			int c = 0;
			while ((c = fis.read(buffer)) != -1) {
				sfos.write(buffer);
			}
		} catch (SmbException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				fis.close();
				sfos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
