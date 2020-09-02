package com.tengzhi.base.security.common.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 权限自定义配置管理
 * 
 * @author lqy 收集处理
 */
@Service
public class AuthorizeConfigImpl implements AuthorizeConfig {

	@Autowired
	private List<AuthorizeStaticConfig> AuthorizeStaticConfig;
	
	@Autowired
	private List<AuthorizeWebconfig> AuthorizeWebconfig;

	@Override
	public void config(HttpSecurity httpSecurity) throws Exception {
		for (AuthorizeStaticConfig Authori : AuthorizeStaticConfig) {
			Authori.config(httpSecurity);
		}
	}

	@Override
	public void config(WebSecurity httpSecurity) throws Exception {
		for (AuthorizeWebconfig Authori : AuthorizeWebconfig) {
			Authori.config(httpSecurity);
		}
	}

}
