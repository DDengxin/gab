package com.tengzhi.base.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.tengzhi.base.security.browser.extension.session.BrowserFailureSession;
import com.tengzhi.base.security.browser.extension.session.BrowserKickSession;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;

/**
 * @author lqy session配置，失效和被踢的配置
 */
@Configuration
public class SessionConfig {
	@Autowired
	private SecurityProperty securityProperty;

	@Bean
	public InvalidSessionStrategy invalidSessionStrategy() {
		return new BrowserFailureSession(securityProperty.getBrowser().getSessionFailureUrl());
	}

	@Bean
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new BrowserKickSession(securityProperty.getBrowser().getSessionFailureUrl());
	}
}
