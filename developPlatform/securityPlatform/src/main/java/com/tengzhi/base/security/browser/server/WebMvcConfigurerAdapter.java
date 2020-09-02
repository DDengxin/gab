package com.tengzhi.base.security.browser.server;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * WebMvc配置，配置常见异常状态码错误跳转地址
 * @author: gaodu
 * @date: 2020/8/24 17:35
 **/
@Component
@Configuration
public class WebMvcConfigurerAdapter {
    @Autowired
    private SecurityProperty securityProperty;
    /**
     * WebServer自定义配置
     * 该Bean中主要配置了错误跳转地址
     * 如需在子项目中自定义，可以直接覆盖其路径，或者在yml配置中覆盖tengzhi.security.browser下的相关key即可
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN,
                    StrUtil.emptyToDefault(securityProperty.getBrowser().getForbidden_page(),"/static/html/error/403.html"));

            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,
                    StrUtil.emptyToDefault(securityProperty.getBrowser().getNot_found_page(),"/static/html/error/404.html"));

            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                    StrUtil.emptyToDefault(securityProperty.getBrowser().getInternal_server_error_page(),"/static/html/error/500.html"));
            factory.addErrorPages(error401Page, error404Page, error500Page);
        };
    }
}