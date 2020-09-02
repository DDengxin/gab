package com.tengzhi.business.system.email.service;

import java.io.IOException;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.email.model.EmailConfig;

public interface EmailService extends BaseService{
	BasePage<EmailConfig> findAll(BaseDto baseDto) throws IOException;

	EmailConfig findById(String Id);

	EmailConfig save(EmailConfig emailConfig) throws Exception;

	void update(EmailConfig emailConfig);

	void deleteById(String Id);
}
