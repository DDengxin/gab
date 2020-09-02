package com.tengzhi.business.system.user.service.Impl;

import cn.hutool.core.util.EnumUtil;
import com.tengzhi.base.security.common.service.VerificationLoginSmsService;
import com.tengzhi.business.system.sms.constant.SmsType;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import com.tengzhi.business.system.sms.service.SysSmsService;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 实现手机号码验证登录
 * @author: gaodu
 * @date: 2020/4/29 14:07
 **/
@Service
public class LoginVerificationSmsServiceImpl  implements VerificationLoginSmsService {
    @Autowired
    LoginService loginService;
    @Autowired
    SysSmsService sysSmsService;


    @Override
    public Map.Entry<String,String> getCodeByPhone(String mobile) throws AuthenticationException {
        return getCodeByUserIdAndCorpId(mobile,null);
    }

    @Override
    public Map.Entry<String,String> getCodeByUserIdAndCorpId(String mobile, String corpId) throws AuthenticationException {
        SysUser sysUser = null;
        try {
            sysUser = loginService.getLoginUserByPhoneAndCorpId(mobile,corpId);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        List<SysSmsLog> sysSmsLogList = sysSmsService.getSmsLog(SmsType.LOGIN.getType(),sysUser.getOrgId(),sysUser.getUserId(),sysUser.getMobile(),false,true);
        if(null != sysSmsLogList && sysSmsLogList.size() > 0){
            sysSmsLogList.sort(Comparator.comparing(SysSmsLog::getSendTime,Comparator.nullsFirst(Date::compareTo)).reversed());
            return new AbstractMap.SimpleEntry<String,String>(sysSmsLogList.get(0).getCode(),sysUser.getJobNumber());

        }else{
            throw new BadCredentialsException("请先发送验证码");
        }
    }
}
