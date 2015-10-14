package com.pallas.design.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class User implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String userName;

    /** persistent field */
    private String userCaption;

    /** persistent field */
    private String userPassword;

    /** nullable persistent field */
    private String lastProjectName;

    /** nullable persistent field */
    private String lastLocalDataPath;

    /** persistent field */
    private int version;

    /** full constructor */
    public User(Long id, String userName, String userCaption, String userPassword, String lastProjectName, String lastLocalDataPath, int version) {
        this.id = id;
        this.userName = userName;
        this.userCaption = userCaption;
        this.userPassword = userPassword;
        this.lastProjectName = lastProjectName;
        this.lastLocalDataPath = lastLocalDataPath;
        this.version = version;
    }

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(Long id, String userName, String userCaption, String userPassword, int version) {
        this.id = id;
        this.userName = userName;
        this.userCaption = userCaption;
        this.userPassword = userPassword;
        this.version = version;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCaption() {
        return this.userCaption;
    }

    public void setUserCaption(String userCaption) {
        this.userCaption = userCaption;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLastProjectName() {
        return this.lastProjectName;
    }

    public void setLastProjectName(String lastProjectName) {
        this.lastProjectName = lastProjectName;
    }

    public String getLastLocalDataPath() {
        return this.lastLocalDataPath;
    }

    public void setLastLocalDataPath(String lastLocalDataPath) {
        this.lastLocalDataPath = lastLocalDataPath;
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
