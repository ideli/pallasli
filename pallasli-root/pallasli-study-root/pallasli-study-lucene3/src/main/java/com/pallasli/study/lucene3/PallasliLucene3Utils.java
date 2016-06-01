package com.pallasli.study.lucene3;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.pallasli.study.lucene.IndexConstant;
import com.pallasli.study.lucene.PallasliDocProp;

public class PallasliLucene3Utils {

	public static Index convertIndexType(PallasliDocProp p) {
		Index i = null;
		switch (p.getAnalyzedType()) {
		case IndexConstant.FIELD_INDEX_ANALYZED:
			i = Field.Index.ANALYZED;
			break;
		case IndexConstant.FIELD_INDEX_NOT_ANALYZED:
			i = Field.Index.NOT_ANALYZED;
			break;
		default:
			break;
		}
		return i;
	}

	public static Store covertStoreType(PallasliDocProp p) {
		Store s = null;
		switch (p.getStoreType()) {
		case IndexConstant.FIELD_STORE_YES:
			s = Field.Store.YES;
			break;
		case IndexConstant.FIELD_STORE_NO:
			s = Field.Store.NO;
			break;
		default:
			break;
		}
		return s;
	}
}