package com.lyt.pallas.menu.treemenu.model;

public class TreeMenu {
	private long id;
	private String isLeaf;
	private String name;
	private String urlPath;
	private String urlCode;
	private long parentId;
	private long orderNum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getIsLeaf() {
		if (isLeaf.equals("1")) {
			return true;
		}
		return false;
	}

	public void setIsLeaf(boolean isLeaf) {
		if (isLeaf) {
			this.isLeaf = "1";
		}

		this.isLeaf = "0";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}
}
