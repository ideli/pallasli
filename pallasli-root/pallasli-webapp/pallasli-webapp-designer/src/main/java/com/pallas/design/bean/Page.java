package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Page implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String projectName;

    /** persistent field */
    private String pageName;

    /** nullable persistent field */
    private String pageCaption;

    /** persistent field */
    private String pageTypeCode;

    /** nullable persistent field */
    private String pageLayout;

    /** nullable persistent field */
    private String pageScripts;

    /** nullable persistent field */
    private String pageConfigs;

    /** persistent field */
    private int version;

    /** full constructor */
    public Page(Long id, String projectName, String pageName, String pageCaption, String pageTypeCode, String pageLayout, String pageScripts, String pageConfigs, int version) {
        this.id = id;
        this.projectName = projectName;
        this.pageName = pageName;
        this.pageCaption = pageCaption;
        this.pageTypeCode = pageTypeCode;
        this.pageLayout = pageLayout;
        this.pageScripts = pageScripts;
        this.pageConfigs = pageConfigs;
        this.version = version;
    }

    /** default constructor */
    public Page() {
    }

    /** minimal constructor */
    public Page(Long id, String projectName, String pageName, String pageTypeCode, int version) {
        this.id = id;
        this.projectName = projectName;
        this.pageName = pageName;
        this.pageTypeCode = pageTypeCode;
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

    public String getPageCaption() {
        return this.pageCaption;
    }

    public void setPageCaption(String pageCaption) {
        this.pageCaption = pageCaption;
    }

    public String getPageTypeCode() {
        return this.pageTypeCode;
    }

    public void setPageTypeCode(String pageTypeCode) {
        this.pageTypeCode = pageTypeCode;
    }

    public String getPageLayout() {
        return this.pageLayout;
    }

    public void setPageLayout(String pageLayout) {
        this.pageLayout = pageLayout;
    }

    public String getPageScripts() {
        return this.pageScripts;
    }

    public void setPageScripts(String pageScripts) {
        this.pageScripts = pageScripts;
    }

    public String getPageConfigs() {
        return this.pageConfigs;
    }

    public void setPageConfigs(String pageConfigs) {
        this.pageConfigs = pageConfigs;
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
