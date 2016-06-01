package com.pallasli.study.lucene5;

import org.junit.Test;

import com.pallasli.study.lucene.IndexConstant;

/**
 * Lucene的核心jar包
 * 
 * lucene-core-5.2.1.jar
 * 
 * lucene-analyzers-common-5.2.1.jar
 * 
 * lucene-queryparser-5.2.1.jar
 * 
 * 主要开发包说明
 * 
 * org.apache.lucene.analysis：语言分析器，主要用于分词
 * 
 * org.apache.lucene.document：索引文档的管理
 * 
 * org.apache.lucene.index：索引管理，如增、删、改
 * 
 * org.apache.lucene.queryparser：查询分析
 * 
 * org.apache.lucene.search：检索管理
 * 
 * org.apache.lucene.store：数据存储管理
 * 
 * org.apache.lucene.util：工具包
 * 
 * @author lyt
 * 
 */
public class PallasliLucene5Test {
	@Test
	public void test() throws Exception {
		PallasliLucene5 p = new PallasliLucene5();
		p.openIndexWriter();
		p.createIndex("G:\\tomcat7.0.47\\logs", null);
		p.closeIndexWriter();
		p.openSearcher();
		// Scanner scanner = new Scanner(System.in);
		// String queryStr = scanner.next();
		String queryStr = "log";
		p.search(IndexConstant.INFO, queryStr, 1);
		p.closeSearcher();
	}
}
