package com.tengzhi.base.security.common.property.detail;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tengzhi.security")
public class SecurityProperty {
	/** 授权模块配置 */
	private AuthorizeProperty authorize = new AuthorizeProperty(); 

	/** 认证模块配置 */
	private AuthenticationProperty authentication = new AuthenticationProperty();
	
	
	/** 浏览器配置 */
	private BrowserProperty browser;

	public AuthorizeProperty getAuthorize() {
		return authorize;
	}

	public void setAuthorize(AuthorizeProperty authorize) {
		this.authorize = authorize;
	}

	public AuthenticationProperty getAuthentication() {
		return authentication;
	}

	public void setAuthentication(AuthenticationProperty authentication) {
		this.authentication = authentication;
	}

	public BrowserProperty getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperty browser) {
		this.browser = browser;
	}

	public SecurityProperty() {
		super();
	}

}
