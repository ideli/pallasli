package com.pallas.knowledge.bean;

public class TreeNode {

	private Long id;
	private int parentId;

	// 新增属性，这是实现动态异步加载的关键属性（09.10.31尝试后发现不用parentId也可以实现动态异步加载）
	// private String parentId;// The parent node id

	private String text;

	// private List<TreeNode> children = new ArrayList<TreeNode>(); //
	// 该属性不能实现异步加载

	private Boolean leaf = false;

	private Boolean expanded = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

}
