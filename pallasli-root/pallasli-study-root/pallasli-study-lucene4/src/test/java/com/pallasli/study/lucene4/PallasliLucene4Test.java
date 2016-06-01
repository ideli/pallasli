package com.pallasli.study.lucene4;

import org.junit.Test;

import com.pallasli.study.lucene.IndexConstant;

public class PallasliLucene4Test {

	@Test
	public void test() throws Exception {
		PallasliLucene4 p = new PallasliLucene4();
		p.openIndexWriter();
		p.createIndex("G:\\tomcat7.0.47\\logs", null);
		p.closeIndexWriter();
		p.openSearcher();
		// Scanner scanner = new Scanner(System.in);
		// String queryStr = scanner.next();
		String queryStr = "log";
		p.search(IndexConstant.CONTENT, queryStr, 1);
		p.closeSearcher();
	}

}
