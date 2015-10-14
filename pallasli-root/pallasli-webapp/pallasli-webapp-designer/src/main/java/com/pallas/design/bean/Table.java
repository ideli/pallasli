package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Table implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String projectName;

    /** persistent field */
    private String tableName;

    /** nullable persistent field */
    private String tableCaption;

    /** nullable persistent field */
    private String tableComments;

    /** persistent field */
    private int version;

    /** full constructor */
    public Table(Long id, String projectName, String tableName, String tableCaption, String tableComments, int version) {
        this.id = id;
        this.projectName = projectName;
        this.tableName = tableName;
        this.tableCaption = tableCaption;
        this.tableComments = tableComments;
        this.version = version;
    }

    /** default constructor */
    public Table() {
    }

    /** minimal constructor */
    public Table(Long id, String projectName, String tableName, int version) {
        this.id = id;
        this.projectName = projectName;
        this.tableName = tableName;
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

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCaption() {
        return this.tableCaption;
    }

    public void setTableCaption(String tableCaption) {
        this.tableCaption = tableCaption;
    }

    public String getTableComments() {
        return this.tableComments;
    }

    public void setTableComments(String tableComments) {
        this.tableComments = tableComments;
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
