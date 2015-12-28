package com.pallas.knowledge.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@SuppressWarnings("serial")
public class KnowledgeType implements Serializable {

	/** identifier field */
	private Long id;

	/** persistent field */
	private long parentId;

	/** persistent field */
	private String text;

	/** persistent field */
	private boolean leaf;

	/** persistent field */
	private boolean expanded;

	/** full constructor */
	public KnowledgeType(long parentId, String text, boolean leaf,
			boolean expanded) {
		this.parentId = parentId;
		this.text = text;
		this.leaf = leaf;
		this.expanded = expanded;
	}

	/** default constructor */
	public KnowledgeType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getParentId() {
		return this.parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return this.leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return this.expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
