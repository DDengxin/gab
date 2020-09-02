package com.tengzhi.base.security.common.extension.jurisdiction;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lqy 扩展GrantedAuthority,实现对请求方式判断
 */
public class MyGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String url;
	private String method;

	public String getPermissionUrl() {
		return url;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.url = permissionUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public MyGrantedAuthority(String url, String method) {
		this.url = url;
		this.method = method;
	}

	@Override
	public String getAuthority() {
		return this.url + ";" + this.method;
	}

}
