package com.tengzhi.base.security.core;

import com.tengzhi.base.security.browser.extension.PasswordEncoder.MyPasswordEncoder;
import com.tengzhi.base.security.common.authorize.AuthorizeConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security配置类
 * @EnableGlobalMethodSecurity用于开启@Securd注解
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled =true)
public class MySecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthorizeConfigImpl authorizeConfig;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		 auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setHideUserNotFoundExceptions(false);
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	// 用户密码加密实现
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new MyPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		authorizeConfig.config(http);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 解决静态资源被拦截的问题
		authorizeConfig.config(web);
	}

	/**
	 *
	 * 重写authenticationManagerBean，用于获取authenticationManager上下文 ps:实现AuthorizeStaticConfig接口时如需获取AuthenticationManager上下文应该使用该Bean,不应该新建配置类去新建Bean，
	 * 否则会导致AuthenticationManager不是同一个而出现问题
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


}
