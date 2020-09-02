package com.tengzhi.base.security.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tengzhi.base.security.common.service.LoginLogsService;
import com.tengzhi.base.security.common.service.impl.LoginLogsServiceImpl;

@Configuration
public class LoginLogsConfig {
	@Bean
	@ConditionalOnMissingBean(LoginLogsService.class)
	public LoginLogsService loginLogsService() {
		return new LoginLogsServiceImpl();
	}
}
