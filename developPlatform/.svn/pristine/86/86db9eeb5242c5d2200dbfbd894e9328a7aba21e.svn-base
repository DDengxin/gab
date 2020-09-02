package com.tengzhi.business.system.sms.model;

import com.tengzhi.base.jpa.model.BaseModel;
import com.tengzhi.business.system.sms.constant.SmsType;
import com.tengzhi.business.system.user.model.SysUser;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 短信日志
 *
 * @author lgl
 */
@Entity
@Table(name = "sys_sms_log")
public class SysSmsLog extends BaseModel {
    @Id

    private String uuid;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Date sendTime;
    @Column(nullable = false)
    private Boolean success;
    @Column(nullable = false)
    private Date operationTime;

    private String type;
    private String code;
    private Boolean expired;
    private String operationByUser;
    private Integer expiredTime;
    private String response;
    private String corpId;

    @Formula("(f_getname('GETUSERNAME',operation_by_user ,null, corp_id))")
    private String operationByUserName;



    public String getOperationByUserName() {
        return operationByUserName;
    }

    public void setOperationByUserName(String operationByUserName) {
        this.operationByUserName = operationByUserName;
    }

    /**
     *
     * @param userId      发送人
     * @param phone       发送人手机号码
     * @param content     内容
     * @param type        短信类型
     * @param code        验证码
     * @param expired     是否过期
     * @param expiredTime 过期毫秒数
     */
    public static SysSmsLog GenSms(String userId, String phone,String corpId, String content, String type, Integer expiredTime,String code, Boolean expired) {
        SysSmsLog sysSmsLog = new SysSmsLog();
        sysSmsLog.setUserId(userId);
        sysSmsLog.setPhone(phone);
        sysSmsLog.setCorpId(corpId);
        sysSmsLog.setContent(content);
        sysSmsLog.setType(type);
        sysSmsLog.setCode(code);
        sysSmsLog.setExpired(expired);
        sysSmsLog.setExpiredTime(expiredTime);
        return sysSmsLog;
    }

    public static SysSmsLog GenSms(SysUser sysUser, String content, String type, Integer expiredTime, String code,Boolean expired) {
        return GenSms(sysUser.getUserId(),sysUser.getMobile(),sysUser.getOrgId(),content,type,expiredTime,code,expired);
    }

    /**
     *
     * @param userId 发送人
     * @param phone 发送人手机号码
     * @param smsType 短信类型
     * @param code 验证码
     * @return
     */
    public static SysSmsLog GenSms(String userId, String phone,String corpId,SmsType smsType,String code) {
        return GenSms(userId,phone,corpId,smsType.getContent(),smsType.getType(),smsType.getExpiredTime(),code,smsType.getExpiredTime() <= 0);
    }

    public static SysSmsLog GenSms(SysUser sysUser,SmsType smsType,String code) {
        return GenSms(sysUser.getUserId(),sysUser.getMobile(),sysUser.getOrgId(),smsType,code);
    }

    /**
     * 生成普通短信的日志记录(无验证码，无过期时间)
     *
     * @param userId  收信人
     * @param phone   收信人手机号码
     * @param content 短信内容
     * @param type    短信类型
     */
    public static SysSmsLog GenNormalSms(String userId, String phone,String corpId, String content, String type) {
        return GenSms(userId, phone, corpId,content, type, null, null, null);
    }

    public static SysSmsLog GenNormalSms(SysUser sysUser, String content, String type) {
        return GenNormalSms(sysUser.getUserId(),sysUser.getMobile(),sysUser.getOrgId(),content, type);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getOperationByUser() {
        return operationByUser;
    }

    public void setOperationByUser(String operationByUser) {
        this.operationByUser = operationByUser;
    }

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}
