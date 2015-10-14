package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PageFieldset implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String projectName;

    /** persistent field */
    private String pageName;

    /** persistent field */
    private String fieldsetName;

    /** nullable persistent field */
    private String fieldsetCaption;

    /** nullable persistent field */
    private String fieldsetType;

    /** nullable persistent field */
    private Integer fieldsetLength;

    /** nullable persistent field */
    private Integer fieldsetPrecision;

    /** nullable persistent field */
    private Integer fieldsetAllowblank;

    /** nullable persistent field */
    private Integer fieldsetOrder;

    /** nullable persistent field */
    private String fieldsetConfigs;

    /** persistent field */
    private int version;

    /** full constructor */
    public PageFieldset(Long id, String projectName, String pageName, String fieldsetName, String fieldsetCaption, String fieldsetType, Integer fieldsetLength, Integer fieldsetPrecision, Integer fieldsetAllowblank, Integer fieldsetOrder, String fieldsetConfigs, int version) {
        this.id = id;
        this.projectName = projectName;
        this.pageName = pageName;
        this.fieldsetName = fieldsetName;
        this.fieldsetCaption = fieldsetCaption;
        this.fieldsetType = fieldsetType;
        this.fieldsetLength = fieldsetLength;
        this.fieldsetPrecision = fieldsetPrecision;
        this.fieldsetAllowblank = fieldsetAllowblank;
        this.fieldsetOrder = fieldsetOrder;
        this.fieldsetConfigs = fieldsetConfigs;
        this.version = version;
    }

    /** default constructor */
    public PageFieldset() {
    }

    /** minimal constructor */
    public PageFieldset(Long id, String projectName, String pageName, String fieldsetName, int version) {
        this.id = id;
        this.projectName = projectName;
        this.pageName = pageName;
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

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
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

    public String getFieldsetType() {
        return this.fieldsetType;
    }

    public void setFieldsetType(String fieldsetType) {
        this.fieldsetType = fieldsetType;
    }

    public Integer getFieldsetLength() {
        return this.fieldsetLength;
    }

    public void setFieldsetLength(Integer fieldsetLength) {
        this.fieldsetLength = fieldsetLength;
    }

    public Integer getFieldsetPrecision() {
        return this.fieldsetPrecision;
    }

    public void setFieldsetPrecision(Integer fieldsetPrecision) {
        this.fieldsetPrecision = fieldsetPrecision;
    }

    public Integer getFieldsetAllowblank() {
        return this.fieldsetAllowblank;
    }

    public void setFieldsetAllowblank(Integer fieldsetAllowblank) {
        this.fieldsetAllowblank = fieldsetAllowblank;
    }

    public Integer getFieldsetOrder() {
        return this.fieldsetOrder;
    }

    public void setFieldsetOrder(Integer fieldsetOrder) {
        this.fieldsetOrder = fieldsetOrder;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
