package com.mypdfbox.test;

import java.io.File;
import java.io.IOException;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.pdfbox.searchengine.lucene.LucenePDFDocument;

public class TestLucenePDFDocument {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String indexDir="d:/luceneindex";
		Analyzer analyzer=new StandardAnalyzer();
		IndexWriter writer=new IndexWriter(indexDir,analyzer,true);
		Document doc=LucenePDFDocument.getDocument(new File("d:/Exploiting.pdf"));
		writer.addDocument(doc);
		writer.close();
	}

}
