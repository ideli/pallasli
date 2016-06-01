package com.pallasli.study.lucene4;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.pallasli.study.lucene.PallasliLucene;

public class PallasliLucene4 {

	private static final Version LUCENE_VERSION = Version.LUCENE_45;
	Directory dir;
	IndexWriter iw;
	QueryParser parser;
	IndexSearcher is;
	IndexReader reader;

	public void openIndexWriter() throws IOException {
		// 打开保存索引目录
		Directory dir = FSDirectory.open(new File(PallasliLucene.indexDir));
		IndexWriterConfig iwc = new IndexWriterConfig(LUCENE_VERSION,
				new StandardAnalyzer(LUCENE_VERSION));
		iwc.setOpenMode(OpenMode.CREATE);
		iw = new IndexWriter(dir, iwc);
	}

	public void closeIndexWriter() throws IOException {
		iw.close();
	}

	public void createIndex(String dataDir, FileFilter filter) throws Exception {
		createFileIndex(dataDir, filter);
	}

	public void createIndex(File f) throws Exception {
		createFileIndex(f);
	}

	private Document getDocument(File f) {
		Document doc = new Document();
		try {
			// doc.add(new Field("content", new FileReader(f)));
			doc.add(new TextField("content", new FileReader(f)));
			doc.add(new TextField("fileName", f.getName(), Field.Store.YES));
			// doc.add(new Field("fileName", f.getName(), Field.Store.YES,
			// Field.Index.NOT_ANALYZED));
			doc.add(new TextField("filePath", f.getCanonicalPath(),
					Field.Store.YES));
			// doc.add(new Field("filePath", f.getCanonicalPath(),
			// Field.Store.YES, Field.Index.NOT_ANALYZED));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void createFileIndex(File f) throws Exception {
		System.out.println("make indexfile is " + f.getCanonicalPath());
		Document doc = getDocument(f);// 创建文件document
		iw.addDocument(doc);// 把当前文件document加入索引
	}

	public int createFileIndex(String dataDir, FileFilter filter)
			throws Exception {

		File[] files = new File(dataDir).listFiles();
		for (File file : files) {// 遍历文件目录下所有txt文件，把文件加入索引
			if (!file.isDirectory() && !file.isHidden() && file.exists()
					&& (filter == null || filter.accept(file))) {
				createFileIndex(file);
			}
		}
		return iw.numDocs();
	}

	public void openSearcher() throws IOException {
		Directory dir = FSDirectory.open(new File(PallasliLucene.indexDir));
		IndexReader reader = DirectoryReader.open(dir);
		is = new IndexSearcher(reader);
	}

	public void search(String queryByType, String queryStr, int maxSize)
			throws Exception {
		parser = new QueryParser(LUCENE_VERSION, queryByType,
				new StandardAnalyzer(LUCENE_VERSION));
		Query query = parser.parse(queryStr);
		long begin = System.currentTimeMillis();
		TopDocs hits = is.search(query, maxSize);
		long end = System.currentTimeMillis();
		System.out.println("search the word: " + queryStr
				+ ".  all search use time is:" + (end - begin)
				+ ". and get resultnum is : " + hits.totalHits);
		for (ScoreDoc doc : hits.scoreDocs) {
			Document document = is.doc(doc.doc);
			System.out.println(document.get("filePath"));
		}
		closeSearcher();
	}

	public void closeSearcher() throws IOException {
		reader.close();
	}

}
