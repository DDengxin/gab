package com.tengzhi.business.platform.enroll.service;

import com.tengzhi.business.system.user.model.SysUser;

public interface RegisterService {
	 /**
     * 发送登录验证码
     * @param user
     * @param corpId
     * @return
     */
	Boolean sendRegisterSms(SysUser user,String corpId) throws Exception;
	
	
	/**
	 * 
	 * @param user
	 * @param text
	 * @return
	 * @throws Exception
	 */
	String sendNoticeSms(SysUser user,String text) throws Exception;

    /**
     * 通过手机号码以及账套获取登录用户
     * @param mobild 手机号码
     * @param corpId 账套
     * @return
     * @throws Exception
     */
//    SysUser getLoginUserByPhoneAndCorpId(String mobild,@Nullable String corpId) throws  Exception;
    
    /**
     * 获取系统配置
     * @param corp
     * @return
     */

	//ModelAndView getSetting(String corp, ModelAndView mv);

}