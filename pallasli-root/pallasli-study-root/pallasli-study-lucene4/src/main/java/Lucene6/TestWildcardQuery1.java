package com.firstproject.testsearch;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestWildcardQuery1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Analyzer analyzer=new StandardAnalyzer();
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		Term term=new Term("contents","modeling");
//		WildcardQuery query=new WildcardQuery(term);
		TermQuery query=new TermQuery(term);
		TopDocs topDocs=searcher.search(query, 126);
		
		hits=topDocs.scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
		    System.out.print(hits[i].score+" ");
			System.out.println(doc.get("contents")+" ");
		}
		searcher.close();
		dir.close();
		
	}


}
