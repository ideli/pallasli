package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Comp implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String compTypeCode;

    /** persistent field */
    private String compName;

    /** persistent field */
    private String compCaption;

    /** persistent field */
    private String compClass;

    /** persistent field */
    private int version;

    /** full constructor */
    public Comp(Long id, String compTypeCode, String compName, String compCaption, String compClass, int version) {
        this.id = id;
        this.compTypeCode = compTypeCode;
        this.compName = compName;
        this.compCaption = compCaption;
        this.compClass = compClass;
        this.version = version;
    }

    /** default constructor */
    public Comp() {
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

    public String getCompName() {
        return this.compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompCaption() {
        return this.compCaption;
    }

    public void setCompCaption(String compCaption) {
        this.compCaption = compCaption;
    }

    public String getCompClass() {
        return this.compClass;
    }

    public void setCompClass(String compClass) {
        this.compClass = compClass;
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
