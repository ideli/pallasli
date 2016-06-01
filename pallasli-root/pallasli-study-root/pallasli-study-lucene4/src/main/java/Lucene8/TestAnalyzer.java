package com.firstproject.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestAnalyzer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
//		Analyzer   analyzer=new StandardAnalyzer();
//		Analyzer   analyzer=new SimpleAnalyzer();
//		Analyzer   analyzer=new WhitespaceAnalyzer();
//		Analyzer   analyzer=new ChineseAnalyzer();
//		Analyzer   analyzer=new CJKAnalyzer();
		Analyzer   analyzer=new IKAnalyzer();
		TokenStream tokenStream=analyzer.tokenStream("", new StringReader("welcome to use lucene! ?"));
//		TokenStream tokenStream=analyzer.tokenStream("", new StringReader("明天会更美好！"));

		Token token =new Token();
		while(tokenStream.next(token)!=null)
		{
			System.out.println(token.term());
		}
	}

}
