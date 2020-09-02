package com.tengzhi.base.security.browser.extension.PasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.tengzhi.base.tools.MD5.MD5Util;

/**
 * @author lqy 重新实现PasswordEncoder实现MD5加密
 */
public class MyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return MD5Util.encode(String.valueOf(rawPassword));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return MD5Util.eq(rawPassword,encodedPassword);
	}

}
