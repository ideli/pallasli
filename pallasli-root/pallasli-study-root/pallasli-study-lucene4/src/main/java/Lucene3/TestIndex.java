package com.firstproject.testindex;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestIndex {
	
	/**
	 * user(id,name,address)
	 * insert into user(id,name,address) values(1,'zhangsan','shanghaichangningqv');
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String [] ids={"1","2","3"};
		String [] names={"zhangsan","lisi","wangwu"};
		String [] addresses={"shanghai","beijing","guangzhou"};
		Analyzer analyzer=new StandardAnalyzer();
		String indexDir="d:/luceneindex";
		Directory dir=FSDirectory.getDirectory(indexDir);
		
		//true 表示创建或覆盖当前索引；false表示对当前索引进行追加
		//Default value is 128
		IndexWriter writer=new IndexWriter(dir,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);
		for(int i=0;i<ids.length;i++){
			Document document=new Document();
			document.add(new Field("id",ids[i],Field.Store.YES,Field.Index.ANALYZED));
			document.add(new Field("name",names[i],Field.Store.YES,Field.Index.NO));
			document.add(new Field("address",addresses[i],Field.Store.YES,Field.Index.ANALYZED));
		    writer.addDocument(document);
		}
		writer.optimize();
	
		writer.close();
	    
	}

}
