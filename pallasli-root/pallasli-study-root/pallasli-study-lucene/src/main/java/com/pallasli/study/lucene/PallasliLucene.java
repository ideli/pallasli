package com.pallasli.study.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pallasli Lucene公共接口，方便lucene版本切换
 * 
 * @author lyt
 * 
 */
public abstract class PallasliLucene {
	public static final String indexDir = "./index";

	public abstract void writerIndex(PallasliDoc pdoc) throws Exception;

	public void writerIndex(String dataDir, FileFilter fileFilter)
			throws Exception {
		List<PallasliDoc> pDocs = convertToPallasliDoc(dataDir, fileFilter);
		writerIndex(pDocs);
	}

	public void search(String indexName, String queryStr) throws Exception {
		search(indexName, queryStr, 50);
	}

	public abstract void search(String indexName, String queryStr, int maxSize)
			throws Exception;

	public void writerIndex(List<PallasliDoc> pdocs) throws Exception {
		for (PallasliDoc pdoc : pdocs) {
			writerIndex(pdoc);
		}
	}

	public List<PallasliDoc> convertToPallasliDoc(String dataDir,
			FileFilter filter) throws Exception {
		List<PallasliDoc> list = new ArrayList<PallasliDoc>();
		File[] files = new File(dataDir).listFiles();
		for (File file : files) {// 遍历文件目录下所有txt文件，把文件加入索引
			if (!file.isDirectory() && !file.isHidden() && file.exists()
					&& (filter == null || filter.accept(file))) {
				list.add(convertToPallasliDoc(file));
			}
		}
		return list;
	}

	public PallasliDoc convertToPallasliDoc(File f) throws IOException {
		PallasliDoc doc = new PallasliDoc();
		List<PallasliDocProp> docProps = new ArrayList<PallasliDocProp>();
		PallasliDocProp docProp = new PallasliDocProp("content",
				"var log test", IndexConstant.FIELD_STORE_YES,
				IndexConstant.FIELD_INDEX_ANALYZED);
		docProps.add(docProp);
		docProp = new PallasliDocProp("fileName", f.getName(),
				IndexConstant.FIELD_STORE_YES,
				IndexConstant.FIELD_INDEX_NOT_ANALYZED);
		docProps.add(docProp);
		docProp = new PallasliDocProp("filePath", f.getCanonicalPath(),
				IndexConstant.FIELD_STORE_YES,
				IndexConstant.FIELD_INDEX_NOT_ANALYZED);
		docProps.add(docProp);
		doc.setProps(docProps);
		return doc;
	}
}
