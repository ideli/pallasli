package com.pallasli.study.lucene5;

import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class PallasliLucene5 {
	Directory dir;
	IndexWriter iw;

	public void openIndexWriter() throws IOException {
		Analyzer a = new StandardAnalyzer();
		dir = FSDirectory.open(Paths.get("./index"));
		IndexWriterConfig iwc = new IndexWriterConfig(a);
		iw = new IndexWriter(dir, iwc);
	}

	public void closeIndexWriter() throws IOException {
		iw.close();
		dir.close();
	}

	public void createIndex(String dataDir, FileFilter filter)
			throws IOException {
		Document doc = new Document();
		doc.add(new TextField("info", "this is my first lucene test",
				Field.Store.YES));
		iw.addDocument(doc);

	}

	QueryParser parser;
	IndexSearcher is;
	IndexReader reader;

	public void openSearcher() throws IOException {
		Directory dir = FSDirectory.open(Paths.get("./index"));
		reader = DirectoryReader.open(dir);
		is = new IndexSearcher(reader);
	}

	public void search(String queryByType, String queryStr, int maxSize)
			throws Exception {
		Analyzer a = new StandardAnalyzer();
		parser = new QueryParser(queryByType, a);
		Query query = parser.parse(queryStr);
		TopDocs topDocs = is.search(query, maxSize);
		System.out.println("总共匹配多少个：" + topDocs.totalHits);
		ScoreDoc[] hits = topDocs.scoreDocs;
		// 应该与topDocs.totalHits相同
		System.out.println("多少条数据：" + hits.length);
		for (ScoreDoc scoreDoc : hits) {
			System.out.println("匹配得分：" + scoreDoc.score);
			System.out.println("文档索引ID：" + scoreDoc.doc);
			Document document = is.doc(scoreDoc.doc);
			System.out.println(document.get("info"));
		}
	}

	public void closeSearcher() throws IOException {
		reader.close();
		dir.close();
	}

}
