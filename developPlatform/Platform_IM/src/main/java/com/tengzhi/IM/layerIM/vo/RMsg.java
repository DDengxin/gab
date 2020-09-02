package com.tengzhi.IM.layerIM.vo;

import java.util.Date;

public class RMsg {
    private String id;
    private String username;
    private String avatar;
    private String senduser;
    private String content;
    private Date timestam;
    private String type;

    public RMsg() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSenduser() {
        return senduser;
    }

    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestam() {
        return timestam;
    }

    public void setTimestam(Date timestam) {
        this.timestam = timestam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
