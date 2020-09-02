package com.tengzhi.base.property;

import com.tengzhi.base.property.minute.Sms;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.tengzhi.base.property.minute.Business;
import com.tengzhi.base.property.minute.Security;
import com.tengzhi.base.property.minute.SystemConfig;

/**
 * @author lqy 获取常用配置 包含业务配置，安全配置，以及系统配置
 */
@Component
@ConfigurationProperties(prefix = "collocate")
public class Property {
	/**
	 * 業務配置
	 */
	private Business business = new Business();
	/**
	 * 安全配置
	 */
	private Security security = new Security();
	/**
	 * 系統配置
	 */
	private SystemConfig system = new SystemConfig();

	private Sms sms = new Sms();


	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public SystemConfig getSystem() {
		return system;
	}

	public void setSystem(SystemConfig system) {
		this.system = system;
	}

	public Sms getSms() {
		return sms;
	}

	public void setSms(Sms sms) {
		this.sms = sms;
	}
}
