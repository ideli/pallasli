package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.Date;

public class TWaWxUserDetail {
    private String wxOpenid;

    private Short wxSubscribe;

    private String wxNickname;

    private Short wxSex;

    private String wxLanguage;

    private String wxCity;

    private String wxProvince;

    private String wxCountry;

    private String wxHeadimgurl;

    private BigDecimal wxSubscribeTime;

    private String wxUnionid;

    private String wxRemark;

    private Short wxGroupid;

    private Short wxIsSuccess;

    private String wxFailMsg;

    private Date wxUpdateDate;

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public Short getWxSubscribe() {
        return wxSubscribe;
    }

    public void setWxSubscribe(Short wxSubscribe) {
        this.wxSubscribe = wxSubscribe;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname == null ? null : wxNickname.trim();
    }

    public Short getWxSex() {
        return wxSex;
    }

    public void setWxSex(Short wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxLanguage() {
        return wxLanguage;
    }

    public void setWxLanguage(String wxLanguage) {
        this.wxLanguage = wxLanguage == null ? null : wxLanguage.trim();
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince == null ? null : wxProvince.trim();
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry == null ? null : wxCountry.trim();
    }

    public String getWxHeadimgurl() {
        return wxHeadimgurl;
    }

    public void setWxHeadimgurl(String wxHeadimgurl) {
        this.wxHeadimgurl = wxHeadimgurl == null ? null : wxHeadimgurl.trim();
    }

    public BigDecimal getWxSubscribeTime() {
        return wxSubscribeTime;
    }

    public void setWxSubscribeTime(BigDecimal wxSubscribeTime) {
        this.wxSubscribeTime = wxSubscribeTime;
    }

    public String getWxUnionid() {
        return wxUnionid;
    }

    public void setWxUnionid(String wxUnionid) {
        this.wxUnionid = wxUnionid == null ? null : wxUnionid.trim();
    }

    public String getWxRemark() {
        return wxRemark;
    }

    public void setWxRemark(String wxRemark) {
        this.wxRemark = wxRemark == null ? null : wxRemark.trim();
    }

    public Short getWxGroupid() {
        return wxGroupid;
    }

    public void setWxGroupid(Short wxGroupid) {
        this.wxGroupid = wxGroupid;
    }

    public Short getWxIsSuccess() {
        return wxIsSuccess;
    }

    public void setWxIsSuccess(Short wxIsSuccess) {
        this.wxIsSuccess = wxIsSuccess;
    }

    public String getWxFailMsg() {
        return wxFailMsg;
    }

    public void setWxFailMsg(String wxFailMsg) {
        this.wxFailMsg = wxFailMsg == null ? null : wxFailMsg.trim();
    }

    public Date getWxUpdateDate() {
        return wxUpdateDate;
    }

    public void setWxUpdateDate(Date wxUpdateDate) {
        this.wxUpdateDate = wxUpdateDate;
    }
}