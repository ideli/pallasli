package com.pallasli.javastudy;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileUtilsTest {
	public void sizeOfDirectory() {
		long size = FileUtils.sizeOfDirectory(new File("C:/Windows"));
		System.out.println("Size: " + size + " bytes");
	}

	public void visitAllDirsAndFiles(File dir) {
		System.out.println(dir);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFiles(new File(dir, children[i]));
			}
		}
	}

	public void listRoots() {
		File[] roots = File.listRoots();
		System.out.println("Root directories  in your system are:");
		for (int i = 0; i < roots.length; i++) {
			System.out.println(roots[i].toString());
		}
	}
}
