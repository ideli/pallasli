package com.pallasli.webapp.edu.model;

import java.util.Date;

public class UserInfo {
    private String id;

    private String userId;

    private String userCaption;

    private String idcards;

    private Byte sex;

    private String birthPlace;

    private String qq;

    private String telphone;

    private String mobile;

    private String registeredPlace;

    private String presentPlace;

    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserCaption() {
        return userCaption;
    }

    public void setUserCaption(String userCaption) {
        this.userCaption = userCaption == null ? null : userCaption.trim();
    }

    public String getIdcards() {
        return idcards;
    }

    public void setIdcards(String idcards) {
        this.idcards = idcards == null ? null : idcards.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace == null ? null : birthPlace.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRegisteredPlace() {
        return registeredPlace;
    }

    public void setRegisteredPlace(String registeredPlace) {
        this.registeredPlace = registeredPlace == null ? null : registeredPlace.trim();
    }

    public String getPresentPlace() {
        return presentPlace;
    }

    public void setPresentPlace(String presentPlace) {
        this.presentPlace = presentPlace == null ? null : presentPlace.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}