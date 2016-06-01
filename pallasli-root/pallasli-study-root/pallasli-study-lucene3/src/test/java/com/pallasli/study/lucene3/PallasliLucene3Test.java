package com.pallasli.study.lucene3;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pallasli.study.lucene.IndexConstant;
import com.pallasli.study.lucene.PallasliDoc;
import com.pallasli.study.lucene.PallasliDocProp;
import com.pallasli.study.lucene.PallasliLucene;
import com.pallasli.study.lucene.filter.LogFilesFilter;

public class PallasliLucene3Test {
	PallasliLucene p = null;

	@Before
	public void before() {
		p = new PallasliLucene3();
	}

	@Test
	public void test() throws Exception {

		// p.openIndexWriter();
		PallasliDoc doc = new PallasliDoc();
		PallasliDocProp docProp = new PallasliDocProp("info", "var log test",
				IndexConstant.FIELD_STORE_YES,
				IndexConstant.FIELD_INDEX_ANALYZED);
		List<PallasliDocProp> docProps = new ArrayList<PallasliDocProp>();
		docProps.add(docProp);
		doc.setProps(docProps);
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
