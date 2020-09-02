package com.tengzhi.IM.business.message.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-10
 */
@Entity
@Table(name = "sys_im_read")
public class SysImRead {
    private String id;
    private String messageId;
    private String readUserId;
    private String isread;
    private String groupid;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message_id", nullable = true, length = 32)
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "read_user_id", nullable = true, length = 32)
    public String getReadUserId() {
        return readUserId;
    }

    public void setReadUserId(String readUserId) {
        this.readUserId = readUserId;
    }

    @Basic
    @Column(name = "isread", nullable = true, length = 2)
    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    @Basic
    @Column(name = "groupid", nullable = true, length = 32)
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysImRead sysImRead = (SysImRead) o;
        return Objects.equals(id, sysImRead.id) &&
                Objects.equals(messageId, sysImRead.messageId) &&
                Objects.equals(readUserId, sysImRead.readUserId) &&
                Objects.equals(isread, sysImRead.isread) &&
                Objects.equals(groupid, sysImRead.groupid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageId, readUserId, isread, groupid);
    }
}
