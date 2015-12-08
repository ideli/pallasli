package com.pallas.designer.bean.designobject;

public class ConfigBean {
	private int height;
	private int width;
	private int minHeight;
	private int maxHeight;
	private int minWidth;
	private int maxWidth;
	private int queryFormHeight;
	private int queryFormWidth;
	private String heightVar;
	private String widthVar;
	private boolean autoLoad;
	private String[] paramOder;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getQueryFormHeight() {
		return queryFormHeight;
	}

	public void setQueryFormHeight(int queryFormHeight) {
		this.queryFormHeight = queryFormHeight;
	}

	public int getQueryFormWidth() {
		return queryFormWidth;
	}

	public void setQueryFormWidth(int queryFormWidth) {
		this.queryFormWidth = queryFormWidth;
	}

	public String getHeightVar() {
		return heightVar;
	}

	public void setHeightVar(String heightVar) {
		this.heightVar = heightVar;
	}

	public String getWidthVar() {
		return widthVar;
	}

	public void setWidthVar(String widthVar) {
		this.widthVar = widthVar;
	}

	public boolean isAutoLoad() {
		return autoLoad;
	}

	public void setAutoLoad(boolean autoLoad) {
		this.autoLoad = autoLoad;
	}

	public String[] getParamOder() {
		return paramOder;
	}

	public void setParamOder(String[] paramOder) {
		this.paramOder = paramOder;
	}
}
