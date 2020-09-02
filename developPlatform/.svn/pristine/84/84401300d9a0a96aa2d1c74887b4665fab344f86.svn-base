package com.tengzhi.business.system.email.service;

import java.io.IOException;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.email.model.Inbox;

public interface InboxService {
	
	BasePage<Inbox> findAll(BaseDto baseDto) throws IOException;

	Inbox findById(String Id);

	Inbox save(Inbox inbox) throws Exception;

	void update(Inbox inbox);
	
	Result synchronous(String email);

	void deleteById(String Id);
}
