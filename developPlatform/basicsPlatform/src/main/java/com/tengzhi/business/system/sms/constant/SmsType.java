package com.tengzhi.business.system.sms.constant;

/**
 * 短信类别
 **/
public enum SmsType {
    //登录
    LOGIN("LOGIN", "您的登录密码是【{sms.code}】，请不要把密码泄漏给其他人，如非本人请勿操作【{sms.signature}】", 60 * 5),
    REGISTER("REGISTER", "您的短信验证码为【{sms.code}】，请不要把验证码泄露个其他人，如非本人请勿操作【{sms.signature}】", 60 * 5),
    GABREGISTER("GABREGISTER", "尊敬的用户，您好，您在哥爱帮平台申请注册的账号验证码为 {sms.code}，请不要把验证码泄露个其他人，如非本人请勿操作。【{sms.signature}】", 60 * 5),
    GREANT("GREANT", "您在哥爱帮申请注册的账号【{sms.code}】。【{sms.signature}】", 60 * 5);
    private String value = null;
    private String text = null;
    private int expiredtime = -1;

    SmsType(String value, String text, int expiredtime) {
        this.value = value;
        this.text = text;
        this.expiredtime = expiredtime;
    }

    public String getContent() {
        return this.text;
    }

    public String getType() {
        return this.value;
    }

    public int getExpiredTime() {
        return this.expiredtime;
    }

}