package com.pallasli.study.lucene3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.pallasli.study.lucene.PallasliDoc;
import com.pallasli.study.lucene.PallasliDocProp;
import com.pallasli.study.lucene.PallasliLucene;

/**
 * 1. 首先根据构造方法创建实例对象时创建索引器indexWriter。这是创建索引时用到的一个主要类，在下面我们会详细降到。
 * 
 * 2. 创建完索引器之后，根据指定的文件目录遍历所有符合要求的文件，然后对每一个文件创建一个Document对象。
 * 
 * 3. 把创建的document对象加入索引器进行索引。
 * 上面只是大体的介绍了一下整个索引步骤，有些细节我没有提到，相信大家也很容易看懂。比如床架文件过滤fiter之类的操作。
 * 
 * 1. 首先打开索引文件，然后输入要搜索的关键字
 * 
 * 2. 创建索引搜索器IndexSearcher，创建查询条件QueryParser
 * 
 * 3. 利用查询字符串解析字符串在索引返回Query对象。
 * 
 * 4. 调用indexsearcher的search方法进行查询，返回TopDocs对象。
 * 
 * 5. 遍历查询得到的结果。
 * 
 * @author lyt
 * 
 */
public class PallasliLucene3 extends PallasliLucene {

	private static final Version LUCENE_VERSION = Version.LUCENE_35;

	public Directory dir;
	public IndexWriter iw;
	public QueryParser parser;
	public IndexSearcher is;
	public IndexReader reader;

	public void openIndexWriter() throws IOException {
		// 打开保存索引目录
		Directory dir = FSDirectory.open(new File(PallasliLucene.indexDir));
		IndexWriterConfig iwc = new IndexWriterConfig(LUCENE_VERSION,
				new StandardAnalyzer(LUCENE_VERSION));
		iwc.setOpenMode(OpenMode.CREATE);
		iw = new IndexWriter(dir, iwc);
	}

	public void closeIndexWriter() throws IOException {
		iw.close();
	}

	public void createIndex(String indexName, String docValue) throws Exception {
		Document doc = new Document();
		doc.add(new Field(indexName, docValue, Field.Store.YES,
				Field.Index.ANALYZED));
		doc.add(new Field("filePath", docValue, Field.Store.YES,
				Field.Index.ANALYZED));
		iw.addDocument(doc);
	}

	public void createIndex(File f) throws Exception {
		createFileIndex(f);
	}

	private Document getDocument(File f) {
		Document doc = new Document();
		try {
			doc.add(new Field("content", new FileReader(f)));
			doc.add(new Field("fileName", f.getName(), Field.Store.YES,
					Field.Index.NOT_ANALYZED));
			doc.add(new Field("filePath", f.getCanonicalPath(),
					Field.Store.YES, Field.Index.NOT_ANALYZED));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void createFileIndex(File f) throws Exception {
		System.out.println("make indexfile is " + f.getCanonicalPath());
		Document doc = getDocument(f);// 创建文件document
		iw.addDocument(doc);// 把当前文件document加入索引
	}

	public void openSearcher() throws IOException {
		Directory dir = FSDirectory.open(new File(PallasliLucene.indexDir));
		IndexReader reader = IndexReader.open(dir);
		is = new IndexSearcher(reader);
	}

	@Override
	public void search(String indexName, String queryStr, int maxSize)
			throws Exception {
		openSearcher();
		parser = new QueryParser(LUCENE_VERSION, indexName,
				new StandardAnalyzer(LUCENE_VERSION));
		Query query = parser.parse(queryStr);
		long begin = System.currentTimeMillis();
		TopDocs hits = is.search(query, maxSize);
		long end = System.currentTimeMillis();
		System.out.println("search the word: " + queryStr
				+ ".  all search use time is:" + (end - begin)
				+ ". and get resultnum is : " + hits.totalHits);
		for (ScoreDoc doc : hits.scoreDocs) {
			Document document = is.doc(doc.doc);
			System.out.println(document.get("filePath"));
		}
		closeSearcher();
	}

	public void closeSearcher() throws IOException {
		is.close();
	}

	@Override
	public void writerIndex(PallasliDoc pdoc) throws Exception {
		openIndexWriter();
		Document doc = new Document();
		for (PallasliDocProp p : pdoc.getProps()) {
			Store s = PallasliLucene3Utils.covertStoreType(p);
			Index i = PallasliLucene3Utils.convertIndexType(p);
			doc.add(new Field(p.getPropName(), p.getPropValue(), s, i));
		}
		iw.addDocument(doc);
		closeIndexWriter();
	}

}
