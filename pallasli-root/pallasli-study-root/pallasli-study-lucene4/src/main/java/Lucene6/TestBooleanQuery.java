package com.firstproject.testsearch;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestBooleanQuery {

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
		Term nterm=new Term("name","*g??");
		WildcardQuery wildcardQuery=new WildcardQuery(nterm);
		Term aterm=new Term("address","tianjing");
		TermQuery termQuery=new TermQuery(aterm);
		BooleanQuery query=new BooleanQuery();
		query.add(wildcardQuery, BooleanClause.Occur.SHOULD);
		query.add(termQuery, BooleanClause.Occur.MUST_NOT);
		TopDocs topDocs=searcher.search(query, 10);
		
		hits=topDocs.scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
//		    System.out.print(hits[i].score+" ");
			System.out.print(doc.get("id")+" ");
			System.out.print(doc.get("name")+" ");
			System.out.print(doc.get("address")+" ");
			System.out.println(doc.get("birthday")+" ");
		}
		searcher.close();
		dir.close();
		
	}


}
