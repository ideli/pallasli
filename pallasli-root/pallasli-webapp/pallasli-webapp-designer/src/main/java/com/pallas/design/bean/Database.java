package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Database implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String projectName;

    /** persistent field */
    private String databaseName;

    /** persistent field */
    private String databaseCaption;

    /** nullable persistent field */
    private String databaseType;

    /** nullable persistent field */
    private String databaseUser;

    /** nullable persistent field */
    private String databasePassword;

    /** nullable persistent field */
    private String databaseIp;

    /** nullable persistent field */
    private String databasePort;

    /** nullable persistent field */
    private String databaseSchema;

    /** persistent field */
    private int version;

    /** full constructor */
    public Database(Long id, String projectName, String databaseName, String databaseCaption, String databaseType, String databaseUser, String databasePassword, String databaseIp, String databasePort, String databaseSchema, int version) {
        this.id = id;
        this.projectName = projectName;
        this.databaseName = databaseName;
        this.databaseCaption = databaseCaption;
        this.databaseType = databaseType;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
        this.databaseIp = databaseIp;
        this.databasePort = databasePort;
        this.databaseSchema = databaseSchema;
        this.version = version;
    }

    /** default constructor */
    public Database() {
    }

    /** minimal constructor */
    public Database(Long id, String projectName, String databaseName, String databaseCaption, int version) {
        this.id = id;
        this.projectName = projectName;
        this.databaseName = databaseName;
        this.databaseCaption = databaseCaption;
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

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseCaption() {
        return this.databaseCaption;
    }

    public void setDatabaseCaption(String databaseCaption) {
        this.databaseCaption = databaseCaption;
    }

    public String getDatabaseType() {
        return this.databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseUser() {
        return this.databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return this.databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseIp() {
        return this.databaseIp;
    }

    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
    }

    public String getDatabasePort() {
        return this.databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseSchema() {
        return this.databaseSchema;
    }

    public void setDatabaseSchema(String databaseSchema) {
        this.databaseSchema = databaseSchema;
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
