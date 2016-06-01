package com.pallasli.study.lucene;

import com.pallasli.study.lucene.filter.LogFilesFilter;

public class PallasliLuceneTest {
	public PallasliLucene p = null;

	/**
	 * 测试模板
	 * 
	 * @throws Exception
	 */
	public void test() throws Exception {

		// p.openIndexWriter();
		PallasliDoc doc = new PallasliDoc();
		PallasliDocProp docProp = new PallasliDocProp("info", "var log test",
				IndexConstant.FIELD_STORE_YES,
				IndexConstant.FIELD_INDEX_ANALYZED);
		p.writerIndex(doc);

		p.writerIndex("G:\\tomcat7.0.47\\logs", new LogFilesFilter());
		// p.closeIndexWriter();
		// p.openSearcher();
		// Scanner scanner = new Scanner(System.in);
		// String queryStr = scanner.next();
		String queryStr = "log";
		p.search(IndexConstant.CONTENT, queryStr, 10);
		p.search(IndexConstant.INFO, queryStr, 10);
		p.search("filePath",
				"G:\\tomcat7.0.47\\logs\\localhost.2014-11-10.log", 10);
		p.search("filePath", "log", 10);
		// p.closeSearcher();

	}
}
