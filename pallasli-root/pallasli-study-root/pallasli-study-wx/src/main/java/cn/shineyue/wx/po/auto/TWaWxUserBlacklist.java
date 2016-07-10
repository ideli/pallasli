package cn.shineyue.wx.po.auto;

import java.util.Date;

public class TWaWxUserBlacklist {
    private String wxErrOpenid;

    private Date blackDate;

    private Short toWhite;

    private Date whiteDate;

    private String whiteComment;

    private String whiteUserid;

    public String getWxErrOpenid() {
        return wxErrOpenid;
    }

    public void setWxErrOpenid(String wxErrOpenid) {
        this.wxErrOpenid = wxErrOpenid == null ? null : wxErrOpenid.trim();
    }

    public Date getBlackDate() {
        return blackDate;
    }

    public void setBlackDate(Date blackDate) {
        this.blackDate = blackDate;
    }

    public Short getToWhite() {
        return toWhite;
    }

    public void setToWhite(Short toWhite) {
        this.toWhite = toWhite;
    }

    public Date getWhiteDate() {
        return whiteDate;
    }

    public void setWhiteDate(Date whiteDate) {
        this.whiteDate = whiteDate;
    }

    public String getWhiteComment() {
        return whiteComment;
    }

    public void setWhiteComment(String whiteComment) {
        this.whiteComment = whiteComment == null ? null : whiteComment.trim();
    }

    public String getWhiteUserid() {
        return whiteUserid;
    }

    public void setWhiteUserid(String whiteUserid) {
        this.whiteUserid = whiteUserid == null ? null : whiteUserid.trim();
    }
}