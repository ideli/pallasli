package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.Date;

public class TWaWxUserErrDetail {
    private BigDecimal id;

    private String grbh;

    private String wxErrOpenid;

    private Date regErrDate;

    private Short toBlacklist;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh == null ? null : grbh.trim();
    }

    public String getWxErrOpenid() {
        return wxErrOpenid;
    }

    public void setWxErrOpenid(String wxErrOpenid) {
        this.wxErrOpenid = wxErrOpenid == null ? null : wxErrOpenid.trim();
    }

    public Date getRegErrDate() {
        return regErrDate;
    }

    public void setRegErrDate(Date regErrDate) {
        this.regErrDate = regErrDate;
    }

    public Short getToBlacklist() {
        return toBlacklist;
    }

    public void setToBlacklist(Short toBlacklist) {
        this.toBlacklist = toBlacklist;
    }
}