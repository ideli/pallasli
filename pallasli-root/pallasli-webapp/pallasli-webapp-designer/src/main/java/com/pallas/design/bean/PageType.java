package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PageType implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String pageTypeCode;

    /** persistent field */
    private String pageTypeCaption;

    /** persistent field */
    private int version;

    /** full constructor */
    public PageType(Long id, String pageTypeCode, String pageTypeCaption, int version) {
        this.id = id;
        this.pageTypeCode = pageTypeCode;
        this.pageTypeCaption = pageTypeCaption;
        this.version = version;
    }

    /** default constructor */
    public PageType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageTypeCode() {
        return this.pageTypeCode;
    }

    public void setPageTypeCode(String pageTypeCode) {
        this.pageTypeCode = pageTypeCode;
    }

    public String getPageTypeCaption() {
        return this.pageTypeCaption;
    }

    public void setPageTypeCaption(String pageTypeCaption) {
        this.pageTypeCaption = pageTypeCaption;
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
