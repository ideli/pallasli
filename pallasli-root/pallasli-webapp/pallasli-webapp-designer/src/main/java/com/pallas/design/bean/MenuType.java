package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MenuType implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String menuTypeCode;

    /** persistent field */
    private String menuTypeCaption;

    /** persistent field */
    private int version;

    /** full constructor */
    public MenuType(Long id, String menuTypeCode, String menuTypeCaption, int version) {
        this.id = id;
        this.menuTypeCode = menuTypeCode;
        this.menuTypeCaption = menuTypeCaption;
        this.version = version;
    }

    /** default constructor */
    public MenuType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuTypeCode() {
        return this.menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode;
    }

    public String getMenuTypeCaption() {
        return this.menuTypeCaption;
    }

    public void setMenuTypeCaption(String menuTypeCaption) {
        this.menuTypeCaption = menuTypeCaption;
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
