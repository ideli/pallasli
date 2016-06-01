package com.firstproject.testindex;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class UpdateDocument {
	public static void main(String[] args) throws IOException {
		Analyzer analyzer=new StandardAnalyzer();
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexReader reader=IndexReader.open(dir);
		System.out.println("before delete : "+reader.numDocs());
		reader.deleteDocuments(new Term("id","2"));
		System.out.println("after delete : "+reader.numDocs());
		reader.close();
		IndexWriter writer=new IndexWriter(dir,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);
		Document document=new Document();
		Field field1=new Field("id","2",Field.Store.YES,Field.Index.ANALYZED);
		field1.setBoost(1.5f);
		document.add(field1);
		document.add(new Field("name","Tom",Field.Store.YES,Field.Index.NO));
		document.add(new Field("address","tianjin",Field.Store.YES,Field.Index.ANALYZED));
//		document.setBoost(1.5f);//比较重要
		document.setBoost(0.5f);//不重要
		writer.addDocument(document);
		writer.close();
		reader=IndexReader.open(dir);
		System.out.println("after add : "+reader.numDocs());
		reader.close();
		dir.close();
	}
}
