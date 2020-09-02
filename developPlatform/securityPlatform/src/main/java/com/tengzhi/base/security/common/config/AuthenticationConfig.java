package com.tengzhi.base.security.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tengzhi.base.security.common.authentication.FailureHandler;
import com.tengzhi.base.security.common.authentication.SuccessHandler;

/**
 * @author lqy
 *定义的成功和失败的交给spring管理
 *如果已有失败和成功的处理默认成功失败配置不生效
 */
@Configuration
public class AuthenticationConfig {
	 /**
     * 成功处理器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "authenticationSuccessHandler")
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SuccessHandler();
    }

    /**
     * 失败处理器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "authenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FailureHandler();
    }
}
