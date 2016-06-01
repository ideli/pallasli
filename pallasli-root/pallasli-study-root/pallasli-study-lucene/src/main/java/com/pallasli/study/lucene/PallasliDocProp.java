package com.pallasli.study.lucene;

public class PallasliDocProp {

	/**
	 * 文档属性名
	 */
	private String propName;
	/**
	 * 文档属性值
	 */
	private String propValue;
	/**
	 * 属性存储方式
	 */
	private int storeType;
	/**
	 * 属性索引方式
	 */
	private int analyzedType;

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public int getStoreType() {
		return storeType;
	}

	public void setStoreType(int storeType) {
		this.storeType = storeType;
	}

	public int getAnalyzedType() {
		return analyzedType;
	}

	public void setAnalyzedType(int analyzedType) {
		this.analyzedType = analyzedType;
	}

	public PallasliDocProp() {
		super();

	}

	public PallasliDocProp(String propName, String propValue, int storeType,
			int analyzedType) {
		super();
		this.propName = propName;
		this.propValue = propValue;
		this.storeType = storeType;
		this.analyzedType = analyzedType;
	}

}
