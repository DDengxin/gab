package com.tengzhi.business.sc.da.productionStaff.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/1 0001 14:09
 * @Description:
 */
@Entity(name = "sys_user")
public class ProductionStaff {

    @Id
    @NotBlank(message = "用户ID不能为空")
    private String userId;//用户id
    private String nickName;//昵称
    private String fact;//头像
    @NotBlank(message = "用户密码不能为空")
    private String password;//密码
    private String mobile;//手机号码
    private String email;//邮箱
    @JsonFormat(pattern = "yyyy-MM-dd")//创建时间
    private Timestamp genTime;//更新时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp modifyTime;//最后登录时间
    private String identityType;//登录类型
    private String indetifiter;//第三方登录标识
    private String identifierToken;//第三方登录密码凭证
    private Boolean gender;//性别
//    @Column(name = "is_forbidden")
    @JsonProperty(value="isForbidden")
    private Boolean isForbidden;//是否禁用
    private String jobNumber;//工号
    private String positionName;//所属岗位名称
    private String orgId;//机构Id
    private String deptId;//部门Id
    private String positionId;//岗位Id
    private String orgName;//所属机构名称
    private String deptName;//所属部门名称
    private Integer userOrd;//排序
    private Boolean deleteLogo;//逻辑删除
    private String tierId;//层级码
    private String realName;//真实姓名
    private String fileUuid;//
    private String sign;//IM签名字段
    private Boolean userLinkWorker;//是否关联员工
    private String orgIds;//账套组(用户可操作的账套组)
    private String defaultOrgId;//默认账套(用户登录的默认账套)
    private String businessPersonnelIds;//业务人员(逗号分隔,用途：用于管控业务查看权限)

    public ProductionStaff() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Timestamp getGenTime() {
        return genTime;
    }

    public void setGenTime(Timestamp genTime) {
        this.genTime = genTime;
    }

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

    public Boolean getForbidden() {
        return isForbidden;
    }

    public void setForbidden(Boolean forbidden) {
        isForbidden = forbidden;
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

    public Integer getUserOrd() {
        return userOrd;
    }

    public void setUserOrd(Integer userOrd) {
        this.userOrd = userOrd;
    }

    public Boolean getDeleteLogo() {
        return deleteLogo;
    }

    public void setDeleteLogo(Boolean deleteLogo) {
        this.deleteLogo = deleteLogo;
    }

    public String getTierId() {
        return tierId;
    }

    public void setTierId(String tierId) {
        this.tierId = tierId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getUserLinkWorker() {
        return userLinkWorker;
    }

    public void setUserLinkWorker(Boolean userLinkWorker) {
        this.userLinkWorker = userLinkWorker;
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

    @Override
    public String toString() {
        return "ProductionStaff{" +
                "userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", fact='" + fact + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", genTime=" + genTime +
                ", modifyTime=" + modifyTime +
                ", identityType='" + identityType + '\'' +
                ", indetifiter='" + indetifiter + '\'' +
                ", identifierToken='" + identifierToken + '\'' +
                ", gender=" + gender +
                ", isForbidden=" + isForbidden +
                ", jobNumber='" + jobNumber + '\'' +
                ", positionName='" + positionName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", positionId='" + positionId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", userOrd=" + userOrd +
                ", deleteLogo=" + deleteLogo +
                ", tierId='" + tierId + '\'' +
                ", realName='" + realName + '\'' +
                ", fileUuid='" + fileUuid + '\'' +
                ", sign='" + sign + '\'' +
                ", userLinkWorker=" + userLinkWorker +
                ", orgIds='" + orgIds + '\'' +
                ", defaultOrgId='" + defaultOrgId + '\'' +
                ", businessPersonnelIds='" + businessPersonnelIds + '\'' +
                '}';
    }



}
