package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DataType implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String dataTypeName;

    /** persistent field */
    private String dataTypeCaption;

    /** persistent field */
    private int version;

    /** full constructor */
    public DataType(Long id, String dataTypeName, String dataTypeCaption, int version) {
        this.id = id;
        this.dataTypeName = dataTypeName;
        this.dataTypeCaption = dataTypeCaption;
        this.version = version;
    }

    /** default constructor */
    public DataType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getDataTypeCaption() {
        return this.dataTypeCaption;
    }

    public void setDataTypeCaption(String dataTypeCaption) {
        this.dataTypeCaption = dataTypeCaption;
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
