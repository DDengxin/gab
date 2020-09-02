package com.tengzhi.base.security.common.authorize;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.base.security.common.property.detail.SecurityProperty;

/**
 * @author lqy 默认需要开放的地址 优先加载
 */
@Component
@Order(Integer.MIN_VALUE + 1)
public class OpenAuthorizeConfig implements AuthorizeWebconfig {
	private final ApplicationContext applicationContext;

	public OpenAuthorizeConfig(ApplicationContext applicationContext) {
		super();
		this.applicationContext = applicationContext;
	}
	
	@Autowired
	private SecurityProperty securityProperty;

	@Override
	public void config(WebSecurity httpSecurity) throws Exception {
		//实现对自定义注解方法的放行
		Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
		Set<String> anonymousUrls = new HashSet<>();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
			HandlerMethod handlerMethod = infoEntry.getValue();
			Anonymous anonymous = handlerMethod.getMethodAnnotation(Anonymous.class);
			if (null != anonymous) {
				anonymousUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
			}
		}
		httpSecurity.ignoring().antMatchers(anonymousUrls.toArray(new String[0]));
		//实现对yml中配置方法的放行
		httpSecurity.ignoring().antMatchers(getPermitUrls());
	}

	public String[] getPermitUrls() {
		//配置文件读取
		String permitUrls = securityProperty.getAuthorize().getPermitUrls();
		if (StringUtils.isNotEmpty(permitUrls) && StringUtils.isNotBlank(permitUrls)) {
			permitUrls = permitUrls.replace(" ", "");
			String[] urlArray = StringUtils.splitByWholeSeparator(permitUrls, ",");
			return urlArray;
		}
		return new String[] {};
	}
}
