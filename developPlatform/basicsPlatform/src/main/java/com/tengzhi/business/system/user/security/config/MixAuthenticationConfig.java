package com.tengzhi.business.system.user.security.config;

import com.tengzhi.base.security.common.authentication.FailureHandler;
import com.tengzhi.base.security.common.authentication.SuccessHandler;
import com.tengzhi.base.security.common.authorize.AuthorizeStaticConfig;
import com.tengzhi.base.security.common.extension.VerificationCode.VerificationCode;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import com.tengzhi.business.system.user.security.AuthenticationFilter.MixAuthenticationFilter;
import com.tengzhi.business.system.user.security.AuthenticationProvider.MixAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 配置认证信息，该配置类会由com.tengzhi.base.security.core.MySecurity通过List的方式依次调用
 * @author: gaodu
 * @date: 2020/4/30 16:25
 **/
@Component
@Configuration
@Order(Integer.MIN_VALUE + 3)
public class MixAuthenticationConfig implements AuthorizeStaticConfig {
    /**
     * 属性文件
     */
    @Autowired
    private SecurityProperty securityProperty;

    /**
     * 用户认证信息接口
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * authenticationManager上下文
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * 重写配置
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    public void config(HttpSecurity httpSecurity) throws Exception {
        //定义过滤器
        MixAuthenticationFilter smsCodeAuthenticationFilter = new MixAuthenticationFilter(securityProperty.getBrowser().getLoginProcessingUrl());
        //绑定AuthenticationManager上下文
        smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager);
        //设置成功/失败回调
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(new SuccessHandler());
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(new FailureHandler());

        //初始化鉴权处理器
        MixAuthenticationProvider smsCodeAuthenticationProvider = new MixAuthenticationProvider();
        //绑定用户认证信息接口
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        //添加鉴权处理器,将自定义过滤器添加到默认的图形验证码前面
        httpSecurity.authenticationProvider(smsCodeAuthenticationProvider).addFilterBefore(smsCodeAuthenticationFilter, VerificationCode.class);


    }
}
