package com.tengzhi.base.security.common.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * @author lqy 优先级配置低，默认去拦截所有请求
 */
@Component
@Order(Integer.MAX_VALUE - 10)
public class AuthorizeStaticConfigImpl implements AuthorizeStaticConfig {
	@Override
	public void config(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().anyRequest().authenticated();
	}
}
