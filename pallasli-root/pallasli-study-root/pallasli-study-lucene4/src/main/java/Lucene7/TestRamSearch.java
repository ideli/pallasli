package com.firstproject.testsearch;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class TestRamSearch {

    Directory directory=new RAMDirectory();
	
    public void creatRamIndex() throws IOException{
    	String [] ids={"1","2","3","4"};
		String [] names={"ZHangsan","lisi","wangwu","zhaoliu"};
		String [] addresses={"tianjing","nanjing","beijing","nanning"};
		String [] birthdays={"19820720","19840203","19770409","19830130"};
		Analyzer analyzer=new StandardAnalyzer();
			
		//true 表示创建或覆盖当前索引；false表示对当前索引进行追加
		//Default value is 128
		IndexWriter writer=new IndexWriter(directory,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);
		
		for(int i=0;i<ids.length;i++){
			Document document=new Document();
			document.add(new Field("id",ids[i],Field.Store.YES,Field.Index.ANALYZED));
			document.add(new Field("name",names[i],Field.Store.YES,Field.Index.ANALYZED));
			document.add(new Field("address",addresses[i],Field.Store.YES,Field.Index.ANALYZED));
			document.add(new Field("birthday",birthdays[i],Field.Store.YES,Field.Index.ANALYZED));
		    writer.addDocument(document);
		}
		
		writer.optimize();
	
		writer.close();
	    System.out.println("ok");
	}

	
    
    public void searchRam() throws CorruptIndexException, IOException{
    	IndexSearcher searcher=new IndexSearcher(directory);
		ScoreDoc [] hits=null;
		Term term=new Term("address","nanjing");
		TermQuery query=new TermQuery(term);
		TopDocs topDocs=searcher.search(query, 100);
		hits=topDocs.scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
		    System.out.print(hits[i].score+" ");
			System.out.print(doc.get("id")+" ");
			System.out.print(doc.get("name")+" ");
			System.out.print(doc.get("address")+" ");
			System.out.println(doc.get("birthday")+" ");
		}
		searcher.close();
		directory.close();
		
	}

    
	public static void main(String[] args) throws Exception {
		TestRamSearch trs=new TestRamSearch();
		trs.creatRamIndex();
		trs.searchRam();
	}

}
