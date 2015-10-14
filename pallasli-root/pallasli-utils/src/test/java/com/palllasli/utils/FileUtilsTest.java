package com.palllasli.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.pallasli.utils.FileUtils;

@Ignore
public class FileUtilsTest {
	@Test
	public void createFolder() {

	}

	@Test
	public void deleteFile() {

	}

	@Test
	public void deleteFolder() {

	}

	@Test
	public void readFileAsByteArray() {

	}

	@Test
	public void readFileAsString() {
		String filepath = "/Users/lyt1987/mysqlStart.sh";
		File file = FileUtils.readFile(filepath);
		String content = FileUtils.readFileToString(file);
		assertTrue(content != null && content.length() > 0);
		String codeType = "utf-8";
		content = FileUtils.readFileToString(file, codeType);
		assertTrue(content != null && content.length() > 0);
	}

	@Test
	public void readFile() {
		String filepath = "/Users/lyt1987/mysqlStart.sh";
		File file = FileUtils.readFile(filepath);
		assertTrue(file != null && file.exists());
	}

	@Test
	public void writeFile() {
		String destFile = "/Users/lyt1987/Desktop/tmp/tmp.txt";
		String content = "test";
		boolean success = FileUtils.writeFile(destFile, content);
		assertTrue(success);
	}

	@Test
	public void createFile() {
		String path = "/Users/lyt1987/Desktop/tmp/tmp1.txt";
		boolean isDirectory = true;
		boolean success = FileUtils.createFile(path, isDirectory);
		assertTrue(success);
		path = "/Users/lyt1987/Desktop/tmp/tmp1.txt";
		isDirectory = true;
		success = FileUtils.createFile(path, isDirectory);
		assertTrue(success);
		path = "/Users/lyt1987/Desktop/tmp/tmp2.txt";
		isDirectory = false;
		success = FileUtils.createFile(path, isDirectory);
		assertTrue(success);
		path = "/Users/lyt1987/Desktop/tmp/tmp2";
		isDirectory = true;
		success = FileUtils.createFile(path, isDirectory);
		assertTrue(success);
	}

	// @Test
	public void copyImageFileFrom() throws IOException {
		String fromPath = "/Users/lyt1987/mysqlStart.sh";
		String toPath = "/Users/lyt1987/Desktop/tmp/tmp.sh";

		boolean success = FileUtils.copyImageFileFrom(fromPath, toPath);
		assertTrue(success);
	}
}
