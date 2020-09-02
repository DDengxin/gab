package com.tengzhi.business.platform.common.model;

public class UsersPwdVo {
    private String userName;
    private String oldPwd;
    private String newPwd;
    private String surePwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getSurePwd() {
        return surePwd;
    }

    public void setSurePwd(String surePwd) {
        this.surePwd = surePwd;
    }
}
