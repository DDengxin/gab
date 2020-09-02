package com.tengzhi.base.security.common.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

import com.tengzhi.base.security.common.extension.jurisdiction.PermissionsInterceptor;

/**
 * @author lqy 权限控制
 */

@Component
@Order(Integer.MAX_VALUE - 11)
public class Permissions implements AuthorizeStaticConfig {

	@Autowired
	private PermissionsInterceptor permissionsInterceptor;

	@Override
	public void config(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.addFilterBefore(permissionsInterceptor, FilterSecurityInterceptor.class);
	}

}