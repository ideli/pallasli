package com.pallas.design.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@SuppressWarnings("serial")
public class Fieldset implements Serializable {

	/** identifier field */
	private Long id;

	/** persistent field */
	private String projectName;

	/** persistent field */
	private String fieldsetName;

	/** nullable persistent field */
	private String fieldsetCaption;

	/** nullable persistent field */
	private String fieldsetConfigs;

	/** persistent field */
	private int version;

	/** full constructor */
	public Fieldset(Long id, String projectName, String fieldsetName,
			String fieldsetCaption, String fieldsetConfigs, int version) {
		this.id = id;
		this.projectName = projectName;
		this.fieldsetName = fieldsetName;
		this.fieldsetCaption = fieldsetCaption;
		this.fieldsetConfigs = fieldsetConfigs;
		this.version = version;
	}

	/** default constructor */
	public Fieldset() {
	}

	/** minimal constructor */
	public Fieldset(Long id, String projectName, String fieldsetName,
			int version) {
		this.id = id;
		this.projectName = projectName;
		this.fieldsetName = fieldsetName;
		this.version = version;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFieldsetName() {
		return this.fieldsetName;
	}

	public void setFieldsetName(String fieldsetName) {
		this.fieldsetName = fieldsetName;
	}

	public String getFieldsetCaption() {
		return this.fieldsetCaption;
	}

	public void setFieldsetCaption(String fieldsetCaption) {
		this.fieldsetCaption = fieldsetCaption;
	}

	public String getFieldsetConfigs() {
		return this.fieldsetConfigs;
	}

	public void setFieldsetConfigs(String fieldsetConfigs) {
		this.fieldsetConfigs = fieldsetConfigs;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
