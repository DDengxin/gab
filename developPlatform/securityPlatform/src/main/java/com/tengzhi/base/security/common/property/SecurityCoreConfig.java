package com.tengzhi.base.security.common.property;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.tengzhi.base.security.common.property.detail.SecurityProperty;
import com.tengzhi.base.security.common.tool.yml.YmlConfigFactory;

/**
 * @author lqy 用于扫配置，其他包指定配置，目录包含此yml默认使用其他包配置
 */
@Configuration
@PropertySource(value = {"classpath:/config/security.yml"},factory = YmlConfigFactory.class)
@EnableConfigurationProperties(SecurityProperty.class)
public class SecurityCoreConfig {

}
