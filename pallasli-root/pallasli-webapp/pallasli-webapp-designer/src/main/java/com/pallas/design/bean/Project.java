package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Project implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String projectName;

    /** persistent field */
    private String projectCaption;

    /** persistent field */
    private int version;

    /** full constructor */
    public Project(Long id, String projectName, String projectCaption, int version) {
        this.id = id;
        this.projectName = projectName;
        this.projectCaption = projectCaption;
        this.version = version;
    }

    /** default constructor */
    public Project() {
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

    public String getProjectCaption() {
        return this.projectCaption;
    }

    public void setProjectCaption(String projectCaption) {
        this.projectCaption = projectCaption;
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
