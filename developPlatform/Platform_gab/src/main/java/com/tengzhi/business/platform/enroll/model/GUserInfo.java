package com.tengzhi.business.platform.enroll.model;

import com.tengzhi.base.jpa.model.BaseModel;
import com.tengzhi.business.system.user.model.SysUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户表
 * @author lgl
 */
@Entity
@Table(name = "g_userinfo")
public class GUserInfo extends BaseModel {
    @Id
	@NotNull(message = "用户ID不得为空")
    private String userId;
    private String userName;
    private String userTname;
	@NotNull(message = "密码不得为空")
    private String userPwd;
    private String userMtel;
    private String userEmail;
    private String userType;
    private String dataMan;
    private Date dataDate;
    private String dataCorp;
    private String erpUserid;
    private String erpCorp;
    private  String  flag;
    private  Boolean isPlatform;
    @Transient
    private String randCode;
    @Transient
    private String type;
    @Transient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTname() {
        return userTname;
    }

    public void setUserTname(String userTname) {
        this.userTname = userTname;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserMtel() {
        return userMtel;
    }

    public void setUserMtel(String userMtel) {
        this.userMtel = userMtel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDataMan() {
        return dataMan;
    }

    public void setDataMan(String dataMan) {
        this.dataMan = dataMan;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataCorp() {
        return dataCorp;
    }

    public void setDataCorp(String dataCorp) {
        this.dataCorp = dataCorp;
    }

    public String getErpUserid() {
        return erpUserid;
    }

    public void setErpUserid(String erpUserid) {
        this.erpUserid = erpUserid;
    }

    public String getErpCorp() {
        return erpCorp;
    }

    public void setErpCorp(String erpCorp) {
        this.erpCorp = erpCorp;
    }

    public String getRandCode() {
        return randCode;
    }

    public void setRandCode(String randCode) {
        this.randCode = randCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getPlatform() {
        return isPlatform;
    }

    public void setPlatform(Boolean platform) {
        isPlatform = platform;
    }

    public SysUser toSysUser(){
        SysUser sysUser = new SysUser();

        sysUser.setUserId(getErpUserid());
        sysUser.setJobNumber(sysUser.getUserId());
        sysUser.setOrgId(getErpCorp());

        sysUser.setRealName(getUserName());
        sysUser.setNickName(getUserTname());

        sysUser.setPassword(getUserPwd());
        sysUser.setEmail(getUserEmail());
        sysUser.setExpertType(getUserType());
        sysUser.setGenTime(null != getDataDate() ? new Timestamp(getDataDate().getTime()) :null );
        sysUser.setMobile(getUserMtel());
        return sysUser;


    }
}
