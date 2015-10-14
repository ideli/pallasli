package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CompType implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String compTypeCode;

    /** persistent field */
    private String compTypeCaption;

    /** persistent field */
    private int version;

    /** full constructor */
    public CompType(Long id, String compTypeCode, String compTypeCaption, int version) {
        this.id = id;
        this.compTypeCode = compTypeCode;
        this.compTypeCaption = compTypeCaption;
        this.version = version;
    }

    /** default constructor */
    public CompType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompTypeCode() {
        return this.compTypeCode;
    }

    public void setCompTypeCode(String compTypeCode) {
        this.compTypeCode = compTypeCode;
    }

    public String getCompTypeCaption() {
        return this.compTypeCaption;
    }

    public void setCompTypeCaption(String compTypeCaption) {
        this.compTypeCaption = compTypeCaption;
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
