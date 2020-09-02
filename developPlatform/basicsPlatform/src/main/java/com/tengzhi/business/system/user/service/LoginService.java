package com.tengzhi.business.system.user.service;

import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.business.system.user.model.SysUser;

/**
 * @author: gaodu
 * @date: 2020/4/29 8:58
 **/
public interface LoginService {
    /**
     * 发送登录验证码
     * @param mobile
     * @param corpId
     * @return
     */
    boolean sendLoginSms(String mobile,String corpId) throws Exception;

    /**
     * 通过手机号码以及账套获取登录用户
     * @param mobild 手机号码
     * @param corpId 账套
     * @return
     * @throws Exception
     */
    SysUser getLoginUserByPhoneAndCorpId(String mobild,@Nullable String corpId) throws  Exception;
    
    /**
     * 获取系统配置
     * @param corp
     * @return
     */

	ModelAndView getSetting(String corp, ModelAndView mv);
}
