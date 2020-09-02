package com.tengzhi.base.security.browser.authorize;

import com.tengzhi.base.security.browser.extension.Logout.BrowserLogout;
import com.tengzhi.base.security.common.authentication.FailureHandler;
import com.tengzhi.base.security.common.authentication.SuccessHandler;
import com.tengzhi.base.security.common.authorize.AuthorizeStaticConfig;
import com.tengzhi.base.security.common.extension.VerificationCode.VerificationCode;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author lqy 浏览器默认配置优先级小于公共中的配置
 */
@Component
@Configuration
@Order(Integer.MIN_VALUE + 2)
public class BrowserAuthorizeConfig implements AuthorizeStaticConfig {

    @Autowired
    private SecurityProperty securityProperty;// 属性文件


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    public void config(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/system/upload/download").permitAll()
                .and()
         .formLogin()
                //登录页面
                .loginPage(securityProperty.getBrowser().getLoginPage())
                //登录请求地址
                .loginProcessingUrl(securityProperty.getBrowser().getLoginProcessingUrl())
                //成功回调
                .successHandler(new SuccessHandler())
                //错误回调
                .failureHandler(new FailureHandler())
                //禁用csrf
                .and().csrf().disable() ;
        httpSecurity
                // 退出登录
                .logout().
                //退出请求地址&删除cookie
                        logoutUrl(securityProperty.getBrowser().getLogoutUrl())
                //退出成功回调页面
                .logoutSuccessHandler(new BrowserLogout(securityProperty.getBrowser().getLogoutSuccessUrl())).and();
        // 记住我
        if (securityProperty.getBrowser().isRememberMe()) {
            httpSecurity.rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperty.getBrowser().getSeconds())
                    .userDetailsService(userDetailsService);
        }
        httpSecurity.sessionManagement()
                // session失效
                .invalidSessionStrategy(invalidSessionStrategy)
                // session最大并发数
                .maximumSessions(securityProperty.getBrowser().getMaxSessions())
                .sessionRegistry(sessionRegistry())
                // 被踢后的处理
                .expiredSessionStrategy(sessionInformationExpiredStrategy).and().and()
                .authorizeRequests().antMatchers(getPermitAllUrl()).permitAll();
        // 允许iframe在同域内跳转(默认是deny)
        if (securityProperty.getBrowser().isSameOrigin()) {
            httpSecurity.headers().frameOptions().sameOrigin();
        }
        // 启用/禁用缓存管理
        if (securityProperty.getBrowser().isCache()) {
            httpSecurity.headers().cacheControl();
        }else{
            httpSecurity.headers().cacheControl().disable();
        }
        // 将过滤器配置在UsernamePasswordAuthenticationFilter之前
        httpSecurity.addFilterBefore(new VerificationCode(), UsernamePasswordAuthenticationFilter.class);
    }

    private String[] getPermitAllUrl() {
        return new String[]{securityProperty.getBrowser().getLogoutUrl(), "/templates/**", "/static/**", "/error", "/"};
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();

    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() { // (5)
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
}
