package com.tengzhi.IM.business.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-10
 */
@Entity
@Table(name = "tim_flock_add_mes")
public class TimFlockAddMes {
    private String id;
    private String groupId;
    private String userId;
    private String remark;
    private String allow;
    private String ganTime;
    private String content;
    private String groupManager;
    private String avatar;
    private String username;
    private String isread;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationTime;
    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "group_id", nullable = true, length = 32)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "allow", nullable = true, length = 5)
    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    @Basic
    @Column(name = "gan_time", nullable = true, length = 50)
    public String getGanTime() {
        return ganTime;
    }

    public void setGanTime(String ganTime) {
        this.ganTime = ganTime;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 50)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "group_manager", nullable = true, length = 32)
    public String getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(String groupManager) {
        this.groupManager = groupManager;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "isread", nullable = true, length = 2)
    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimFlockAddMes that = (TimFlockAddMes) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(allow, that.allow) &&
                Objects.equals(ganTime, that.ganTime) &&
                Objects.equals(content, that.content) &&
                Objects.equals(groupManager, that.groupManager) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(username, that.username) &&
                Objects.equals(isread, that.isread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupId, userId, remark, allow, ganTime, content, groupManager, avatar, username, isread);
    }
}
