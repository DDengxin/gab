package com.tengzhi.base.security.common.config;

import com.tengzhi.base.security.common.service.impl.DeafaultUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lqy
 * 定义的用户管理交给spring管理
 * 如果已有默认配置不生效
 */
@Configuration
public class UserServiceConfig {
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new DeafaultUserDetailsServiceImpl();
    }

}
