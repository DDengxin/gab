package com.tengzhi.base.thymeleaf.config.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AbstractLocaleContextResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.TimeZone;

/***
 * i18n配置文件
 * @author wangning
 *
 */
@Configuration
@ComponentScan
public class I18nConfig extends AbstractLocaleContextResolver {

    public static final String LOCALE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".LOCALE";
    public static final String TIME_ZONE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".TIME_ZONE";

    /**
     * 国际化文件路径
     */
    @Value("${spring.messages.basename}")
    public String[] basefilenames;


    /**
     * 默认解析器
     * 用于设置当前会话的默认的国际化语言
     *
     * @return
     */
    @Bean(value = "localeResolver")
    public LocaleResolver localeResolverBean() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    /**
     * 默认解析器
     * 指定切换国际化语言的参数名。例如?lang=zh_CN 表示读取国际化文件login_zh_CN.properties。
     * @return
     */
    @Bean(value="localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("language");
        return lci;
    }
    /**
     * 默认拦截器 其中lang表示切换语言的参数名
     * 在spring boot2.0 之后 通过继承WebMvcConfigurer类 就可以完成拦截
     * 在这里他直接返回了一个覆写了addInterceptors方法的WebMvcConfigurer匿名对象
     * 以十分简洁的方式达成了他的目的
     */
    @Bean(value="localeInterceptor")
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
                localeInterceptor.setParamName("lang");
                registry.addInterceptor(localeInterceptor);
            }
        };
    }

    /**
     * 用于解析消息的策略接口，支持这些消息的参数化和国际化。
     */
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        if (basefilenames != null) {
            for (int i = 0; i < basefilenames.length; i++) {
                String basename = basefilenames[i];
                Assert.hasText(basename, "Basename must not be empty");
                this.basefilenames[i] = basename.trim();
            }
            source.setBasenames(basefilenames);
        } else {
            this.basefilenames = new String[0];
            source.setBasename(basefilenames[0]);
        }
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(false);
        return source;
    }


    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        this.setLocaleContext(request, response, locale != null ? new SimpleLocaleContext(locale) : null);
    }

    @Override
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {
        return null;
    }

    @Override
    public void setLocaleContext(HttpServletRequest request, HttpServletResponse response, LocaleContext localeContext) {
        Locale locale = null;
        TimeZone timeZone = null;
        if (localeContext != null) {
            locale = localeContext.getLocale();
            if (localeContext instanceof TimeZoneAwareLocaleContext) {
                timeZone = ((TimeZoneAwareLocaleContext) localeContext).getTimeZone();
            }
        }
        WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        WebUtils.setSessionAttribute(request, TIME_ZONE_SESSION_ATTRIBUTE_NAME, timeZone);
    }
}