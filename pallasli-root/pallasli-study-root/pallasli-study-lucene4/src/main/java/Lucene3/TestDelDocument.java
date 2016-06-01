package com.firstproject.testindex;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestDelDocument {
	
	public static void main(String[] args) throws IOException {
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexReader reader=IndexReader.open(dir);
//		System.out.println(reader.maxDoc());
		reader.deleteDocument(0);
		System.out.println(reader.deleteDocuments(new Term("id","2")));
		System.out.println(reader.numDocs());
		reader.close();
		dir.close();
		
	}

}
