package com.firstproject.testindex;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class UnDeleteDocument {
	
	public static void main(String[] args) throws IOException {
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexReader reader=IndexReader.open(dir);
		
		System.out.println("before undelete:"+reader.numDocs());
		
//		reader.deleteDocuments(new Term("id","1"));
		
		
		reader.undeleteAll();
		
		System.out.println("after  undelete:"+reader.numDocs());
		
		reader.close();
		dir.close();
		
	}

}
