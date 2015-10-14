package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CompConfig implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String configName;

    /** persistent field */
    private String compName;

    /** nullable persistent field */
    private String defaultValue;

    /** persistent field */
    private int version;

    /** full constructor */
    public CompConfig(Long id, String configName, String compName, String defaultValue, int version) {
        this.id = id;
        this.configName = configName;
        this.compName = compName;
        this.defaultValue = defaultValue;
        this.version = version;
    }

    /** default constructor */
    public CompConfig() {
    }

    /** minimal constructor */
    public CompConfig(Long id, String configName, String compName, int version) {
        this.id = id;
        this.configName = configName;
        this.compName = compName;
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

    public String getCompName() {
        return this.compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
