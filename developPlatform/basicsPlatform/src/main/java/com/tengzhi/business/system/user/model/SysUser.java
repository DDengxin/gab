package com.tengzhi.business.system.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @author 王翔
 * @create 2020-04-18
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseModel {
    @NotBlank(message = "用户ID不能为空")
    private String userId;
    private String nickName;
    private String fact;
    @NotBlank(message = "用户密码不能为空")
    private String password;
    private String mobile;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp genTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp modifyTime;
    private String identityType;
    private String indetifiter;
    private String identifierToken;
    private Boolean gender;
    @Column(name = "is_forbidden")
    @JsonProperty(value="isForbidden")
    private Boolean isForbidden;
    private String jobNumber;
    private String positionName;
    private String orgId;
    private String deptId;
    private String positionId;
    private String orgName;
    private String deptName;
    private Integer userOrd;
    private Boolean deleteLogo;
    private String tierId;
    private String realName;
    private String fileUuid;
    private String sign;
    private Boolean userLinkWorker;

    private String orgIds;
    private String defaultOrgId;

    private String businessPersonnelIds;



    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getTierId() {
        return tierId;
    }

    public void setTierId(String tierId) {
        this.tierId = tierId;
    }

    /**
     * 获取用户档案账套
     * @return
     */
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Boolean getDeleteLogo() {
        return deleteLogo;
    }

    public void setDeleteLogo(Boolean deleteLogo) {
        this.deleteLogo = deleteLogo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserOrd() {
        return userOrd;
    }

    public void setUserOrd(Integer userOrd) {
        this.userOrd = userOrd;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Boolean getUserLinkWorker() {
        return userLinkWorker;
    }

    public void setUserLinkWorker(Boolean userLinkWorker) {
        this.userLinkWorker = userLinkWorker;
    }

    @Basic
    public Timestamp getGenTime() {
        return genTime;
    }

    public void setGenTime(Timestamp genTime) {
        this.genTime = genTime;
    }

    @Basic
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIndetifiter() {
        return indetifiter;
    }

    public void setIndetifiter(String indetifiter) {
        this.indetifiter = indetifiter;
    }

    public String getIdentifierToken() {
        return identifierToken;
    }

    public void setIdentifierToken(String identifierToken) {
        this.identifierToken = identifierToken;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getIsForbidden() {
        return isForbidden;
    }

    public Boolean IsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(Boolean isForbidden) {
        this.isForbidden = isForbidden;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public String getDefaultOrgId() {
        return defaultOrgId;
    }

    public void setDefaultOrgId(String defaultOrgId) {
        this.defaultOrgId = defaultOrgId;
    }

    public String getBusinessPersonnelIds() {
        return businessPersonnelIds;
    }

    public void setBusinessPersonnelIds(String businessPersonnelIds) {
        this.businessPersonnelIds = businessPersonnelIds;
    }
}
