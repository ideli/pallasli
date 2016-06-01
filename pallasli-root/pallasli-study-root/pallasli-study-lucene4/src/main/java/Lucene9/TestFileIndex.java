package com.mylucene.testpaoding.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestFileIndex {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String dataDir="D:\\lucenedata";
		String indexDir="d:\\luceneindex";
		
		File [] files=new File(dataDir).listFiles();
		System.out.println(files.length);
		Analyzer analyzer=new PaodingAnalyzer();
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexWriter writer=new IndexWriter(dir,analyzer,true,IndexWriter.MaxFieldLength.UNLIMITED);
        for(int i=0;i<files.length;i++){
        	StringBuffer strBuffer=new StringBuffer();
        	String line="";
        	FileInputStream is=new FileInputStream(files[i].getCanonicalPath());
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        	line=reader.readLine();
        	while(line!=null){
        		strBuffer.append(line);
        		strBuffer.append("\n");
        		line=reader.readLine();
        	}
            
			Document document=new Document();
			document.add(new Field("fileName",files[i].getName(),Field.Store.YES,Field.Index.ANALYZED));
			document.add(new Field("contents",strBuffer.toString(),Field.Store.YES,Field.Index.ANALYZED));
			writer.addDocument(document);
			is.close();
			reader.close();
			
        }
        writer.close();
        dir.close();
	}

}
