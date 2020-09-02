package com.tengzhi.business.system.position.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 王翔
 * @create 2020-04-13
 */
@Entity
@Table(name = "sys_position")
public class SysPosition extends BaseModel {
    private String positionId;
    private String positionName;
    private Integer rank;
    private Boolean deleteLogo;
    private Boolean state;
    private String remark;
    private String creator;
    private String modifier;
    private Timestamp creationTime;
    private Timestamp modifyTime;
    private String deptId;
    private String deptName;
    private String creatorId;
    private String modifierId;
    private String tierId;
    private String orgName;
    private String orgId;

    @Id
    @NotBlank(message = "岗位ID不能为空")
    public String getPositionId() {
        return positionId;
    }

    public String getTierId() {
        return tierId;
    }

    public void setTierId(String tierId) {
        this.tierId = tierId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getDeleteLogo() {
        return deleteLogo;
    }

    public void setDeleteLogo(Boolean deleteLogo) {
        this.deleteLogo = deleteLogo;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysPosition that = (SysPosition) o;
        return Objects.equals(positionId, that.positionId) &&
                Objects.equals(positionName, that.positionName) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(deleteLogo, that.deleteLogo) &&
                Objects.equals(state, that.state) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(modifier, that.modifier) &&
                Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(modifyTime, that.modifyTime) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(creatorId, that.creatorId) &&
                Objects.equals(modifierId, that.modifierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, positionName, rank, deleteLogo, state, remark, creator, modifier, creationTime, modifyTime, deptName, creatorId, modifierId);
    }
}
