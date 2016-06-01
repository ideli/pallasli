package com.firstproject.testsearch;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestQueryParser {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		Analyzer analyzer=new StandardAnalyzer();
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		QueryParser parser=new QueryParser("name",analyzer);
		Query query=parser.parse("zhangsan~");
		TopDocCollector topdoc=new TopDocCollector(100);
		
		searcher.search(query, topdoc);
		hits=topdoc.topDocs().scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
		    System.out.print(hits[i].score+" ");
			System.out.print(doc.get("id")+" ");
			System.out.print(doc.get("name")+" ");
			System.out.print(doc.get("address")+" ");
			System.out.println(doc.get("birthday")+" ");
		}
		searcher.close();
		dir.close();

	}

}
