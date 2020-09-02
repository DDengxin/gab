package com.tengzhi.business.finance.voucher.service;

import java.util.List;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.voucher.model.Summary;


public interface SummaryService extends BaseService{
	BasePage<Summary> findAll(BaseDto baseDto);

	Summary findById(Long Id);

	Summary save(Summary summary) throws Exception;
	
	List<Summary> findAll() ;

	void update(Summary summary);
	boolean judgeUniqueset(Summary summary);
	boolean judgeUniqueother(Summary summary);

	Result deleteById(Long Id);
}
