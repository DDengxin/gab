package com.tengzhi.IM.business.message.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-10
 */
@Entity
@Table(name = "tim_flock")
public class TimFlock {
    private String id;
    private String avatar;
    private String groupname;
    private String creator;
    private Timestamp gantime;
    private String corpId;

    @Id
    @Column(name = "id", nullable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 500)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "groupname", nullable = true, length = 255)
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 32)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "gantime", nullable = true)
    public Timestamp getGantime() {
        return gantime;
    }

    public void setGantime(Timestamp gantime) {
        this.gantime = gantime;
    }

    @Basic
    @Column(name = "corp_id", nullable = true, length = 32)
    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimFlock timFlock = (TimFlock) o;
        return Objects.equals(id, timFlock.id) &&
                Objects.equals(avatar, timFlock.avatar) &&
                Objects.equals(groupname, timFlock.groupname) &&
                Objects.equals(creator, timFlock.creator) &&
                Objects.equals(gantime, timFlock.gantime) &&
                Objects.equals(corpId, timFlock.corpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, groupname, creator, gantime, corpId);
    }
}
