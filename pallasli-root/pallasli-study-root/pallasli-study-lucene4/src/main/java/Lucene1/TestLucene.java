package Lucene1;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class TestLucene {

	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new StandardAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("", new StringReader("welcome to use lucene!"));
		Token token = new Token();
		while (tokenStream.next(token) != null)
			System.out.println(token.term());
	}

}
