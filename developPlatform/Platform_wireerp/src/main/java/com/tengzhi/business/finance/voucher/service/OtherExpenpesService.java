package com.tengzhi.business.finance.voucher.service;

import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.OtherExpenpesDao;

public interface OtherExpenpesService extends BaseService{

	public BasePage<Map<String, Object>> findAll(BaseDto baseDto) ;
	public Result findById(String id);
		
}
