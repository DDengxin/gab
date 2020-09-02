package com.tengzhi.base.security.common.service;

import org.springframework.security.core.AuthenticationException;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 * 登录手机验证码
 *
 * @author: gaodu
 * @date: 2020/4/29 13:30
 **/
public interface VerificationLoginSmsService {
    /**
     * 通过手机号码获取有效的登录验证码
     * @param mobile
     * @return Map.Entry<验证码,用户名>
     * @throws AuthenticationException 验证异常返回描述
     */
    Map.Entry<String,String>  getCodeByPhone(String mobile) throws AuthenticationException;

    /**
     * 通过手机号码获取有效的登录验证码
     * @param mobile
     * @param corpId 公司账套
     * @return Map.Entry<验证码,用户名>
     * @throws AuthenticationException 验证异常返回描述
     */
    Map.Entry<String,String> getCodeByUserIdAndCorpId(String mobile, String corpId) throws AuthenticationException;
}
