package com.pallasli.study.lucene3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * 在G盘下建立source文件夹 （G:\\tomcat7.0.47\\logs）
 * 
 * G:\\tomcat7.0.47\\logs文件夹存放待索引的文件，例如，建立两个文件，名称为 test1.txt test2.txt 。
 * 
 * test1.txt文件内容为：欢迎来到绝对秋香的博客。
 * 
 * test2.txt文件内容为：绝对秋香引领你走向潮流。
 * 
 * 在C盘下再建立index文件夹，存放索引文件 （C:\index）
 * 
 * @author lyt
 * 
 */
public class TextFileIndexer {
	public static void main(String[] args) throws Exception {
		/* 指明要索引文件夹的位置,这里是C盘的source文件夹下 */
		File fileDir = new File("G:\\tomcat7.0.47\\logs");

		/* 这里放索引文件的位置 */
		File indexDir = new File("C:\\index");
		Directory dir = FSDirectory.open(indexDir);
		Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35,
				luceneAnalyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		IndexWriter indexWriter = new IndexWriter(dir, iwc);
		File[] textFiles = fileDir.listFiles();
		long startTime = new Date().getTime();

		// 增加document到索引去
		for (int i = 0; i < textFiles.length; i++) {
			if (textFiles[i].isFile()
					&& textFiles[i].getName().endsWith(".log")) {
				System.out.println("File " + textFiles[i].getCanonicalPath()
						+ "正在被索引....");
				String temp = FileReaderAll(textFiles[i].getCanonicalPath(),
						"GBK");
				System.out.println(temp);
				Document document = new Document();
				Field FieldPath = new Field("path", textFiles[i].getPath(),
						Field.Store.YES, Field.Index.NO);
				Field FieldBody = new Field("body", temp, Field.Store.YES,
						Field.Index.ANALYZED,
						Field.TermVector.WITH_POSITIONS_OFFSETS);
				document.add(FieldPath);
				document.add(FieldBody);
				indexWriter.addDocument(document);
			}
		}
		indexWriter.close();

		// 测试一下索引的时间
		long endTime = new Date().getTime();
		System.out.println("这花费了" + (endTime - startTime) + " 毫秒来把文档增加到索引里面去!"
				+ fileDir.getPath());
	}

	public static String FileReaderAll(String FileName, String charset)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(FileName), charset));
		String line = new String();
		String temp = new String();

		while ((line = reader.readLine()) != null) {
			temp += line;
		}
		reader.close();
		return temp;
	}
}