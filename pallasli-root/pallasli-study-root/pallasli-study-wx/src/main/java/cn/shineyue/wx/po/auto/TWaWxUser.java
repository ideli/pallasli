package cn.shineyue.wx.po.auto;

import java.util.Date;

public class TWaWxUser {
    private String grbh;

    private String wxOpenid;

    private Date regDate;

    private Short locked;

    private String lockOpenid;

    private Date lockDate;

    private Short wxState;

    private Date unregDate;

    private String wxComment;

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh == null ? null : grbh.trim();
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Short getLocked() {
        return locked;
    }

    public void setLocked(Short locked) {
        this.locked = locked;
    }

    public String getLockOpenid() {
        return lockOpenid;
    }

    public void setLockOpenid(String lockOpenid) {
        this.lockOpenid = lockOpenid == null ? null : lockOpenid.trim();
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Short getWxState() {
        return wxState;
    }

    public void setWxState(Short wxState) {
        this.wxState = wxState;
    }

    public Date getUnregDate() {
        return unregDate;
    }

    public void setUnregDate(Date unregDate) {
        this.unregDate = unregDate;
    }

    public String getWxComment() {
        return wxComment;
    }

    public void setWxComment(String wxComment) {
        this.wxComment = wxComment == null ? null : wxComment.trim();
    }
}