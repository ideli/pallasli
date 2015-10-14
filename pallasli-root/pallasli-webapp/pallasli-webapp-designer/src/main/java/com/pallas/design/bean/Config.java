package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Config implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String configName;

    /** persistent field */
    private String configCaption;

    /** nullable persistent field */
    private String parentConfigName;

    /** persistent field */
    private String dataTypeName;

    /** persistent field */
    private int version;

    /** full constructor */
    public Config(Long id, String configName, String configCaption, String parentConfigName, String dataTypeName, int version) {
        this.id = id;
        this.configName = configName;
        this.configCaption = configCaption;
        this.parentConfigName = parentConfigName;
        this.dataTypeName = dataTypeName;
        this.version = version;
    }

    /** default constructor */
    public Config() {
    }

    /** minimal constructor */
    public Config(Long id, String configName, String configCaption, String dataTypeName, int version) {
        this.id = id;
        this.configName = configName;
        this.configCaption = configCaption;
        this.dataTypeName = dataTypeName;
        this.version = version;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigCaption() {
        return this.configCaption;
    }

    public void setConfigCaption(String configCaption) {
        this.configCaption = configCaption;
    }

    public String getParentConfigName() {
        return this.parentConfigName;
    }

    public void setParentConfigName(String parentConfigName) {
        this.parentConfigName = parentConfigName;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
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
