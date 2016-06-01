package com.mylucene.testpaoding.search;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestFileSearch {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		Term term=new Term("contents","¹úÎñÔº");
		TermQuery query=new TermQuery(term);
//		PrefixQuery query=new PrefixQuery(term);
//		WildcardQuery query=new WildcardQuery(term);
		TopDocs topDocs=searcher.search(query, 100);
		hits=topDocs.scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
		    System.out.print(hits[i].score+" ");
			System.out.println(doc.get("fileName")+" ");
			System.out.println(doc.get("contents")+" ");
		}
		searcher.close();
		dir.close();
		
	}

}

