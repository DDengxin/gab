package com.tengzhi.business.system.email.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.email.model.EmailConfig;

public interface EmailConfigDao extends BasedaoRepository<EmailConfig, String> {
	
	EmailConfig findByUsername(String username);

}
