package com.tengzhi.business.system.main.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 王翔
 * @create 2020-04-30
 */
@Entity
@Table(name = "sys_user_menu")
public class SysUserMenu {
    private String id;
    private String userId;
    private String rightId;
    private Timestamp genTime;
    private Integer rightOrd;
    private String dataCorp;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 600)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "right_id", nullable = true, length = 32)
    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    @Basic
    @Column(name = "gen_time", nullable = true)
    public Timestamp getGenTime() {
        return genTime;
    }

    public void setGenTime(Timestamp genTime) {
        this.genTime = genTime;
    }

    @Basic
    @Column(name = "right_ord", nullable = true)
    public Integer getRightOrd() {
        return rightOrd;
    }

    public void setRightOrd(Integer rightOrd) {
        this.rightOrd = rightOrd;
    }

    @Basic
    @Column(name = "data_corp", nullable = true, length = 600)
    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUserMenu that = (SysUserMenu) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(rightId, that.rightId) &&
                Objects.equals(genTime, that.genTime) &&
                Objects.equals(rightOrd, that.rightOrd) &&
                Objects.equals(dataCorp, that.dataCorp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, rightId, genTime, rightOrd, dataCorp);
    }
}
