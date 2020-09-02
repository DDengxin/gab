package com.tengzhi.wire.build;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tengzhi.base.jpa.factory.BaseRepositoryFactoryBean;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.tengzhi")
@EntityScan("com.tengzhi")
@EnableCaching
@EnableJpaRepositories(basePackages = "com.tengzhi", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class ApplicationServer {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ApplicationServer.class);
		application.setBannerMode(Banner.Mode.CONSOLE);
		application.run(args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource[] {
				/** 请求url地址 */
				new ClassPathResource("config/application-custom.yml") });
		pspc.setProperties(yaml.getObject());
		return pspc;
	}

}

//禁止datasource自动装配
/*
 * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 * 
 * @ComponentScan("com.tzsoft") public class ApplicationServer extends SpringBootServletInitializer { // 必须继承指定的父类
 * 
 * @Override protected SpringApplicationBuilder configure( SpringApplicationBuilder builder) { logger.info("starting spring boot initializer ......"); return
 * builder.sources(ApplicationServer.class); }
 * 
 * public static void main(String[] args) { SpringApplication application = new SpringApplication(ApplicationServer.class); application.setBannerMode(Banner.Mode.CONSOLE);
 * application.run(args); }
 * 
 * }
 */