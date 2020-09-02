package com.tengzhi.business.system.email.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.system.email.dao.EmailConfigDao;
import com.tengzhi.business.system.email.model.EmailConfig;
import com.tengzhi.business.system.email.service.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
	@Autowired
	private EmailConfigDao emailConfigDao;

	@Override
	public BasePage<EmailConfig> findAll(BaseDto baseDto) throws IOException {
		SecurityUser securityUser= SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		return emailConfigDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.startingWith("username", map.get("username"));
			c.eq("userId", securityUser.getUserId());
		}));
	}

	@Override
	public EmailConfig findById(String Id) {
		return emailConfigDao.findById(Id).orElse(null);
	}

	@Override
	public EmailConfig save(EmailConfig emailConfig) throws Exception {
		SecurityUser securityUser= SessionUser.SessionUser();
		emailConfig.setCreationTime(new Date());
		emailConfig.setUserId(securityUser.getUserId());
		emailConfig.setCreatorId(securityUser.getUserId());
		emailConfig.setId(UUIDUtil.uuid());
		return emailConfigDao.save(emailConfig);
	}

	@Override
	public void update(EmailConfig emailConfig) {
		emailConfig.setModifyTime(new Date());
		emailConfigDao.dynamicSave(emailConfig, emailConfigDao.findById(emailConfig.getId()).orElse(null));
	}

	@Override
	public void deleteById(String Id) {
		emailConfigDao.deleteById(Id);
	}
}
