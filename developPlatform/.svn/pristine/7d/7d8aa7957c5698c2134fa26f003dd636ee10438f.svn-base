package com.tengzhi.base.security.browser.config;

import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: gaodu
 * @date: 2020/5/11 11:30
 **/
@Configuration
@Component
public class DefaultViewConfig implements WebMvcConfigurer {
    @Autowired
    private SecurityProperty securityProperty;// 属性文件

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //默认路径默认跳转到登录页面
        if(null != securityProperty.getBrowser().getHomePage()){
            registry.addViewController("/").setViewName("forward:" + securityProperty.getBrowser().getHomePage());
        }
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}