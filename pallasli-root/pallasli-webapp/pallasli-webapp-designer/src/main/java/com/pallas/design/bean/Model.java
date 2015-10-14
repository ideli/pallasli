package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Model implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String projectName;

    /** persistent field */
    private String modelName;

    /** nullable persistent field */
    private String modelProxyName;

    /** nullable persistent field */
    private String modelFields;

    /** nullable persistent field */
    private String modelApis;

    /** persistent field */
    private int version;

    /** full constructor */
    public Model(Long id, String projectName, String modelName, String modelProxyName, String modelFields, String modelApis, int version) {
        this.id = id;
        this.projectName = projectName;
        this.modelName = modelName;
        this.modelProxyName = modelProxyName;
        this.modelFields = modelFields;
        this.modelApis = modelApis;
        this.version = version;
    }

    /** default constructor */
    public Model() {
    }

    /** minimal constructor */
    public Model(Long id, String projectName, String modelName, int version) {
        this.id = id;
        this.projectName = projectName;
        this.modelName = modelName;
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

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelProxyName() {
        return this.modelProxyName;
    }

    public void setModelProxyName(String modelProxyName) {
        this.modelProxyName = modelProxyName;
    }

    public String getModelFields() {
        return this.modelFields;
    }

    public void setModelFields(String modelFields) {
        this.modelFields = modelFields;
    }

    public String getModelApis() {
        return this.modelApis;
    }

    public void setModelApis(String modelApis) {
        this.modelApis = modelApis;
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
